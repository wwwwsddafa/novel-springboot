package com.moyue.novel.core.schedule;

import com.moyue.novel.core.bean.DiskInfo;
import com.moyue.novel.core.config.DiskMonitorProperties;
import com.moyue.novel.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author 王聪
 * @date 2025/10/24
 */
@ConditionalOnProperty(prefix = "disk-monitor", name = "enabled", havingValue = "true")
@Service
@RequiredArgsConstructor
@Slf4j
public class DiskMonitorSchedule {

    private final DiskMonitorProperties properties;

    private final EmailService emailService;

    private final AtomicBoolean criticalAlertSent = new AtomicBoolean(false);

    @Scheduled(fixedDelayString = "#{1000 * 60 * ${disk-monitor.interval-minutes}}")
    public void checkDiskUsage() {
        log.info("🔍 开始检查磁盘使用情况...");

        File[] roots = File.listRoots();
        List<DiskInfo> diskInfos = new ArrayList<>();
        boolean criticalDetected = false;

        for (File root : roots) {
            String path = root.getAbsolutePath().trim();
            if (path.isEmpty()) continue;
            long total = root.getTotalSpace();
            if (total == 0) continue;
            long free = root.getFreeSpace();
            double usage = (double)(total - free) / total * 100;
            diskInfos.add(new DiskInfo(path, total, free, usage));
            if (usage >= properties.getCriticalThreshold()) {
                criticalDetected = true;
            }
        }

        if (criticalDetected) {
            if (criticalAlertSent.compareAndSet(false, true)) {
                sendAlertEmail("CRITICAL", diskInfos);
                log.error("🚨 磁盘使用率 ≥ 95%，10秒后终止进程...");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.exit(1);
            }
        } else {
            criticalAlertSent.set(false);
            double maxUsage = diskInfos.stream().mapToDouble(DiskInfo::getUsage).max().orElse(0);
            if (maxUsage >= properties.getSevereThreshold() && maxUsage < properties.getCriticalThreshold()) {
                sendAlertEmail("WARNING", diskInfos);
            } else if (maxUsage >= properties.getWarningThreshold() && maxUsage < properties.getSevereThreshold()) {
                sendAlertEmail("INFO", diskInfos);
            }
        }
    }

    private void sendAlertEmail(String level, List<DiskInfo> diskInfos) {
        Map<String, Object> model = new HashMap<>();
        model.put("diskInfos", diskInfos);
        model.put("alertLevel", getAlertLevelText(level));
        model.put("icon", getIcon(level));
        model.put("actionText", getActionText(level));
        model.put("levelColor", getLevelColor(level));

        String subject = getSubject(level);
        emailService.sendHtmlEmail(subject, "disk_alert", model, properties.getRecipients());
    }

    private String getAlertLevelText(String level) {
        return switch (level) {
            case "CRITICAL" -> "严重";
            case "WARNING" -> "警告";
            case "INFO" -> "提示";
            default -> "通知";
        };
    }

    private String getIcon(String level) {
        return switch (level) {
            case "CRITICAL" -> "🚨";
            case "WARNING" -> "⚠️";
            case "INFO" -> "💡";
            default -> "📢";
        };
    }

    private String getActionText(String level) {
        return switch (level) {
            case "CRITICAL" ->
                "⚠️ 检测到任一磁盘使用率 ≥ 95%，为防止系统崩溃，系统已自动<strong style='color:#d32f2f'>关闭爬虫程序</strong>。";
            case "WARNING" ->
                "📌 建议：<strong style='color:#f57c00'>请立即暂停爬虫程序</strong>，防止磁盘写满导致服务中断。";
            case "INFO" ->
                "📌 提示：磁盘使用率已较高，请关注爬虫数据写入情况。";
            default -> "";
        };
    }

    private String getLevelColor(String level) {
        return switch (level) {
            case "CRITICAL" -> "#d32f2f";
            case "WARNING" -> "#f57c00";
            case "INFO" -> "#388e3c";
            default -> "#1976d2";
        };
    }

    private String getSubject(String level) {
        return switch (level) {
            case "CRITICAL" -> "🚨 严重告警：磁盘使用率超 95%，爬虫已关闭";
            case "WARNING" -> "⚠️ 警告：磁盘使用率超 90%，请暂停爬虫";
            case "INFO" -> "💡 提示：磁盘使用率超 85%";
            default -> "磁盘使用率告警";
        };
    }

}
