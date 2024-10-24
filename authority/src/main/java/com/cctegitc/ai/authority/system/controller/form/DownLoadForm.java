package com.cctegitc.ai.authority.system.controller.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: zhen
 * @Date: 2022-06-27
 * @Time: 10:41
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownLoadForm {

    private String fileName;

    private Boolean delete;
}
