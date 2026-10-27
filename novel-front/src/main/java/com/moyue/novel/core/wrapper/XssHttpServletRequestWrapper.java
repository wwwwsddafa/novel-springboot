package com.moyue.novel.core.wrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

/**
 * XSS过滤处理
 * 严格模式：全量转义 < > 为 HTML 实体
 * 白名单模式：通过 Jsoup Safelist 清洗，只保留安全标签
 *
 * @author Administrator
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    /**
     * 评论富文本白名单（Jsoup Safelist，经过社区安全审计）
     * 允许：加粗/斜体/下划线/强调/引用/段落/换行
     * Jsoup 自动处理：属性剥离、javascript: 伪协议、CSS 表达式、嵌套畸形标签、Unicode 绕过
     */
    private static final Safelist COMMENT_SAFELIST = Safelist.none()
        .addTags("b", "i", "u", "em", "strong", "blockquote", "p", "br");

    /**
     * 是否启用白名单模式（评论富文本场景）
     */
    private final boolean useWhitelist;

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.useWhitelist = false;
    }

    public XssHttpServletRequestWrapper(HttpServletRequest request, boolean useWhitelist) {
        super(request);
        this.useWhitelist = useWhitelist;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values != null) {
            int length = values.length;
            String[] escapeValues = new String[length];
            for (int i = 0; i < length; i++) {
                if (useWhitelist) {
                    escapeValues[i] = Jsoup.clean(values[i], COMMENT_SAFELIST);
                } else {
                    escapeValues[i] = values[i].replaceAll("<", "&lt;").replaceAll(">", "&gt;");
                }
            }
            return escapeValues;
        }
        return null;
    }
}