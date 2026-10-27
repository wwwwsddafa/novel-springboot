package com.moyue.novel.service;

import java.util.List;
import java.util.Map;

/**
 * @author 王聪
 * @date 2025/10/24
 */
public interface EmailService {

    void sendHtmlEmail(String subject, String templateName, Map<String, Object> templateModel, List<String> to);

}
