package com.cctegitc.ai.authority.system.service;

import com.cctegitc.ai.authority.common.core.domain.entity.Prompt;

public interface PromptService {
    void save(Prompt prompt);

    Prompt findByPrompt(String path);

    void savePrompt(Prompt prompt);

    void updatePrompt(Prompt prompt);

    void update(Prompt prompt);
}
