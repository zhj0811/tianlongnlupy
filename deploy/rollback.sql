/*
 Navicat Premium Data Transfer

 Source Server         : 10.25.10.16
 Source Server Type    : MySQL
 Source Server Version : 50744
 Source Host           : 10.25.10.16:3306
 Source Schema         : nvsa

 Target Server Type    : MySQL
 Target Server Version : 50744
 File Encoding         : 65001

 Date: 28/12/2023 11:07:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for associated_event_management
-- ----------------------------
CREATE TABLE IF NOT EXISTS `associated_event_management`  (
                                                `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID号',
                                                `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则名称',
                                                `description` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则描述',
                                                `level` int(11) NULL DEFAULT NULL COMMENT '风险等级，1-4，从高到低',
                                                `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联表名',
                                                `wazuh_rule_ids` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '安全事件库rule_id集合',
                                                `rule_content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则详情',
                                                `time_range` int(11) NULL DEFAULT NULL COMMENT '时间范围：5-60',
                                                `trigger_num` int(11) NULL DEFAULT NULL COMMENT '触发次数：1-10',
                                                `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态：0禁用1启用',
                                                `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                                                `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
                                                `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                                `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '关联事件管理' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for event_category
-- ----------------------------
DROP TABLE IF EXISTS `event_category`;
CREATE TABLE `event_category`  (
                                   `id` int(11) NOT NULL AUTO_INCREMENT,
                                   `primary_category` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   `sub_category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   `primary_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   `sub_code` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   `sort` int(11) DEFAULT '0',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of event_category
-- ----------------------------
INSERT INTO `event_category` VALUES (1, '恶意程序事件', '计算机病毒事件', 'MP', 'MPEVIR', '制造、传播或利用恶意程序，影响计算机使用，破坏计算机功能，毁坏或窃取数据',0);
INSERT INTO `event_category` VALUES (2, '恶意程序事件', '网络蠕虫事件', 'MP', 'MPWORM', '利用网络缺陷，蓄意制造或通过网络自动复制并传播网络蠕虫',0);
INSERT INTO `event_category` VALUES (3, '恶意程序事件', '特洛伊木马事件', 'MP', 'MPTROJ', '制造、传播或利用具有远程控制功能的恶意程序，实现非法窃取或截获数据',0);
INSERT INTO `event_category` VALUES (4, '恶意程序事件', '僵尸网络事件', 'MP', 'MPZOMB', '利用僵尸工具程序形成僵尸网络',0);
INSERT INTO `event_category` VALUES (5, '恶意程序事件', '恶意代码内嵌网页事件', 'MP', 'MPWEB', '在访问被嵌入恶意代码而受到污损的网页时，该恶意代码在访问该网页的计算机系统中安装恶意软件',0);
INSERT INTO `event_category` VALUES (6, '恶意程序事件', '恶意代码宿主站点事件', 'MP', 'MPHOST', '诱使目标用户到存储恶意代码的宿主站点下载恶意代码',0);
INSERT INTO `event_category` VALUES (7, '恶意程序事件', '勒索软件事件', 'MP', 'MPRANS', '采取加密或屏蔽用户操作等方式劫持用户对系统或数据的访问权，并籍此向用户索取赎金',0);
INSERT INTO `event_category` VALUES (8, '恶意程序事件', '挖矿病毒事件', 'MP', 'MPMINER', '以获得数字加密货币为目的，控制他人的计算机并植入挖矿病毒程序完成大量运算',0);
INSERT INTO `event_category` VALUES (9, '恶意程序事件', '混合攻击程序事件', 'MP', 'MPHYBR', '利用多种方法传播和利用多种恶意程序',0);
INSERT INTO `event_category` VALUES (10, '恶意程序事件', '其他恶意程序事件', 'MP', 'MPOTH', '不在以上子类之中的恶意程序事件',0);
INSERT INTO `event_category` VALUES (11, '网络攻击事件', '网络扫描探测事件', 'NA', 'NASCAN', '利用网络扫描软件获取有关网络配置、端口、服务和现有脆弱性等信息',0);
INSERT INTO `event_category` VALUES (12, '网络攻击事件', '网络钓鱼事件', 'NA', 'NAPHISH', '利用欺诈性网络技术诱使用户泄露重要数据或个人信息',0);
INSERT INTO `event_category` VALUES (13, '网络攻击事件', '漏洞利用事件', 'NA', 'NAEXPLOIT', '通过挖掘并利用网络配置缺陷、通信协议缺陷或应用程序缺陷等漏洞对网络实施攻击',0);
INSERT INTO `event_category` VALUES (14, '网络攻击事件', '后门利用事件', 'NA', 'NABACKDOOR', '恶意利用软件或硬件系统设计过程中未经严格验证所留下的接口、功能模块、程序等，非法获取网络管理权限',0);
INSERT INTO `event_category` VALUES (15, '网络攻击事件', '后门植入事件', 'NA', 'NABACKIMPL', '非法在网络中创建能够持续获取其管理权限的后门',0);
INSERT INTO `event_category` VALUES (16, '网络攻击事件', '凭据攻击事件', 'NA', 'NACRED', '破解口令，解析登录口令或凭据等',0);
INSERT INTO `event_category` VALUES (17, '网络攻击事件', '信号干扰事件', 'NA', 'NASIGINT', '通过技术手段阻碍有线或无线信号在网络中正常传播',0);
INSERT INTO `event_category` VALUES (18, '网络攻击事件', '拒绝服务事件', 'NA', 'NADOS', '通过非正常使用网络资源(诸如CPU、内存、磁盘空间或网络带宽)影响或破坏网络可用性，例如：DDOS等',0);
INSERT INTO `event_category` VALUES (19, '网络攻击事件', '网页篡改事件', 'NA', 'NAWEBMOD', '通过恶意破坏或更改网页内容影响网站声誉或破坏网页及网站可用性',0);
INSERT INTO `event_category` VALUES (20, '网络攻击事件', '暗链植入事件', 'NA', 'NALINK', '通过隐形篡改技术在网页内非法植入违法网站链接',0);
INSERT INTO `event_category` VALUES (21, '网络攻击事件', '域名劫持事件', 'NA', 'NADNSHIJ', '通过攻击或伪造DNS的方式蓄意或恶意诱导用户访问非预期的指定IP地址',0);
INSERT INTO `event_category` VALUES (22, '网络攻击事件', '域名转嫁事件', 'NA', 'NATRANSFER', '把自己的域名指向一个不属于自己的IP地址，导致针对该域名的攻击都将被引向所指向的IP地址',0);
INSERT INTO `event_category` VALUES (23, '网络攻击事件', 'DNS污染事件', 'NA', 'NADNSPOLL', '利用刻意制造或无意制造的DNS数据包，把域名指向不正确的IP地址',0);
INSERT INTO `event_category` VALUES (24, '网络攻击事件', 'WLAN劫持事件', 'NA', 'NAWLANH', '通过口令破解、固件替换等方法非法获取无线局域网的控制权限',0);
INSERT INTO `event_category` VALUES (25, '网络攻击事件', '流量劫持事件', 'NA', 'NATRAFHIJ', '通过恶意诱导或非法强制用户访问特定网络资源造成用户流量损失',0);
INSERT INTO `event_category` VALUES (26, '网络攻击事件', 'BGP劫持攻击事件', 'NA', 'NABGPATT', '通过 BGP 恶意操纵网络路由路径',0);
INSERT INTO `event_category` VALUES (27, '网络攻击事件', '广播欺诈事件', 'NA', 'NABRDCAST', '通过广播欺骗的方式干扰网络数据包正常传输或窃取网络用户敏感信息',0);
INSERT INTO `event_category` VALUES (28, '网络攻击事件', '失陷主机事件', 'NA', 'NAHACKED', '攻击者获得某主机的控制权后，能以该主机为跳板继续攻击组织内网其他主机',0);
INSERT INTO `event_category` VALUES (29, '网络攻击事件', '供应链攻击事件', 'NA', 'NASUPPLY', '通过利用供应链管理中存在的脆弱性，感染合法应用来分发恶意程序',0);
INSERT INTO `event_category` VALUES (30, '网络攻击事件', 'APT 事件', 'NA', 'NAAPT', '通过对特定对象展开持续有效的攻击活动，这种攻击活动具有极强的隐蔽性和针对性，通常会运用受感染的各种介质、供应链和社会工程学等多种手段实施先进的、持久的且有效的威胁和攻击',0);
INSERT INTO `event_category` VALUES (31, '网络攻击事件', '其他网络攻击事件', 'NA', 'NAOTH', '不在以上子类之中的网络攻击事件',0);
INSERT INTO `event_category` VALUES (32, '数据安全事件', '数据篡改事件', 'DS', 'DSDATAALTER', '未经授权接触或修改数据',0);
INSERT INTO `event_category` VALUES (33, '数据安全事件', '数据假冒事件', 'DS', 'DSDATAFAKE', '非法或未经许可使用、伪造数据',0);
INSERT INTO `event_category` VALUES (34, '数据安全事件', '数据泄露事件', 'DS', 'DSDATALEAK', '无意或恶意通过技术手段使数据或敏感个人信息对外公开泄露',0);
INSERT INTO `event_category` VALUES (35, '数据安全事件', '社会工程事件', 'DS', 'DSSOCENG', '通过非技术手段(如心理学、话术等)诱导他人泄露数据或执行行动',0);
INSERT INTO `event_category` VALUES (36, '数据安全事件', '数据窃取事件', 'DS', 'DSDATASTEAL', '未经授权利用技术手段(例如窃听、间谍等)偷窃数据',0);
INSERT INTO `event_category` VALUES (37, '数据安全事件', '数据拦截事件', 'DS', 'DSDATAINTER', '在数据到达目标接收者之前非法捕获数据',0);
INSERT INTO `event_category` VALUES (38, '数据安全事件', '位置检测事件', 'DS', 'DSLOCDETECT', '非法检测系统、个人的地理位置信息或敏感数据的存储位置',0);
INSERT INTO `event_category` VALUES (39, '数据安全事件', '数据投毒事件', 'DS', 'DSDATAPOLL', '干预深度学习训练数据集，在训练数据中加入精心构造的异常数据，破坏原有训练数据的概率分布，导致模型在某些特定条件下产生分类或聚类错误',0);
INSERT INTO `event_category` VALUES (40, '数据安全事件', '数据滥用事件', 'DS', 'DSDATAABUSE', '无意或恶意滥用数据',0);
INSERT INTO `event_category` VALUES (41, '数据安全事件', '隐私侵犯事件', 'DS', 'DSPRIVACY', '无意或恶意侵犯网络中存在的敏感个人信息',0);
INSERT INTO `event_category` VALUES (42, '数据安全事件', '数据损失事件', 'DS', 'DSDATALOSS', '因误操作、人为蓄意或软硬件缺陷等因素导致数据损失',0);
INSERT INTO `event_category` VALUES (43, '数据安全事件', '其他数据安全事件', 'DS', 'DSOTH', '不在以上子类之中的数据安全事件',0);
INSERT INTO `event_category` VALUES (44, '信息内容安全事件', '反动宣传事件', 'IC', 'ICRADICAL', '利用网络传播煽动颠覆国家政权、推翻社会主义制度，煽动分裂国家、破坏国家统一等危害国家安全、荣誉和利益的非法信息',0);
INSERT INTO `event_category` VALUES (45, '信息内容安全事件', '暴恐宣扬事件', 'IC', 'ICTERROR', '利用网络宣扬恐怖主义、极端主义，煽动民族仇恨、民族歧视的信息，引起社会恐慌和动乱',0);
INSERT INTO `event_category` VALUES (46, '信息内容安全事件', '色情传播事件', 'IC', 'ICPORN', '利用网络传播违背社会伦理道德的淫秽色情信息',0);
INSERT INTO `event_category` VALUES (47, '信息内容安全事件', '虚假信息传播事件', 'IC', 'ICFAKENEWS', '利用网络编造并传播虚假信息来扰乱经济秩序和社会秩序，造成负面影响',0);
INSERT INTO `event_category` VALUES (48, '信息内容安全事件', '权益侵害事件', 'IC', 'ICRIGHTS', '利用网络传播的信息侵害了社会组织或公民的合法权益',0);
INSERT INTO `event_category` VALUES (49, '信息内容安全事件', '信息滥发事件', 'IC', 'ICSPAM', '利用网络传播未经接收者准许的信息，例如：垃圾邮件等',0);
INSERT INTO `event_category` VALUES (50, '信息内容安全事件', '网络欺诈事件', 'IC', 'ICFRAUD', '恶意利用技术或非技术手段对特定或不特定目标通过网络进行欺诈以非法获取信息或钱财',0);
INSERT INTO `event_category` VALUES (51, '信息内容安全事件', '其他信息内容安全事件', 'IC', 'ICOTH', '不在以上子类之中的信息内容安全事件',0);
INSERT INTO `event_category` VALUES (52, '设备设施故障事件', '技术故障事件', 'DE', 'DETECH', '网络中软硬件的自然缺陷、设计缺陷或运行环境发生变化而引起系统故障，例如：硬件故障、软件故障、过载等',0);
INSERT INTO `event_category` VALUES (53, '设备设施故障事件', '配套设施故障事件', 'DE', 'DEACCFAC', '支撑网络运行的配套设施发生故障，例如：电力供应故障、照明系统故障、温湿度控制系统故障等',0);
INSERT INTO `event_category` VALUES (54, '设备设施故障事件', '物理损害事件', 'DE', 'DEPHYS', '故意或意外的物理行动造成网络环境或网络设备损坏，例如：失火、漏水、静电、设备毁坏或丢失等',0);
INSERT INTO `event_category` VALUES (55, '设备设施故障事件', '辐射干扰事件', 'DE', 'DERADINT', '因辐射产生干扰影响网络正常运行，例如：电磁辐射、电磁脉冲、电子干扰、电压波动、热辐射等',0);
INSERT INTO `event_category` VALUES (56, '设备设施故障事件', '其他设备设施故障事件', 'DE', 'DEOTH', '不在以上子类之中的设备设施故障事件',0);
INSERT INTO `event_category` VALUES (57, '违规操作事件', '权限滥用事件', 'RO', 'ROPABUSE', '由于网络服务端功能开放过多或权限限制不严格，导致攻击者通过直接或间接调用权限的方式进行攻击',0);
INSERT INTO `event_category` VALUES (58, '违规操作事件', '权限伪造事件', 'RO', 'ROPFAKE', '为了欺骗制造虚假权限',0);
INSERT INTO `event_category` VALUES (59, '违规操作事件', '行为抵赖事件', 'RO', 'ROPDENY', '用户否认其有害行为',0);
INSERT INTO `event_category` VALUES (60, '违规操作事件', '故意违规操作事件', 'RO', 'ROPINTENT', '故意执行非法操作',0);
INSERT INTO `event_category` VALUES (61, '违规操作事件', '误操作事件', 'RO', 'ROPMISTAKE', '无意地执行错误操作',0);
INSERT INTO `event_category` VALUES (62, '违规操作事件', '人员可用性破坏事件', 'RO', 'ROPUAVAIL', '人力资源受损，导致人员缺失或缺席',0);
INSERT INTO `event_category` VALUES (63, '违规操作事件', '资源未授权使用事件', 'RO', 'ROPNOUNAUTH', '版权违反事件',0);
INSERT INTO `event_category` VALUES (64, '违规操作事件', '其他违规操作事', 'RO', 'ROPO', '不在以上子类之中的违规操作事件',0);
INSERT INTO `event_category` VALUES (65, '安全隐患事件', '网络漏洞事件', 'SHE', 'SHEVULN', '因操作系统、应用程序或安全协议开发及设计过程中，对安全性考虑不充分而出现安全隐患',0);
INSERT INTO `event_category` VALUES (66, '安全隐患事件', '网络配置合规缺陷事件', 'SHE', 'SHECONFIG', '由于软硬件安全配置不合理或缺省配置，不符合网络安全要求而产生安全缺陷或隐患',0);
INSERT INTO `event_category` VALUES (67, '安全隐患事件', '其他安全隐患事件', 'SHE', 'SHEOTH', '不在以上子类之中的安全隐患事件',0);
INSERT INTO `event_category` VALUES (68, '异常行为事件', '访问异常事件', 'AB', 'ABACCESS', '因网络软硬件运行环境发生变化导致不能提供服务',0);
INSERT INTO `event_category` VALUES (69, '异常行为事件', '流量异常事件', 'AB', 'ABTRAFFIC', '网络流量行为模式偏离正常基线',0);
INSERT INTO `event_category` VALUES (70, '异常行为事件', '其他异常行为事件', 'AB', 'ABOTH', '不在以上子类之中的异常行为事件',0);
INSERT INTO `event_category` VALUES (71, '不可抗力事件', '自然灾害事件', 'FOC', 'FOCDISASTER', '大自然的极端现象导致信息和信息系统受损，例如：地震、火山、洪水、暴风、闪电、海啸、崩塌等',0);
INSERT INTO `event_category` VALUES (72, '不可抗力事件', '事故灾难事件', 'FOC', 'FOCACCIDENT', '具有灾难性后果的事故导致信息和信息系统受损，例如：公共设施和设备事故、环境污染事故等',0);
INSERT INTO `event_category` VALUES (73, '不可抗力事件', '公共卫生事件', 'FOC', 'FOCHEALTH', '传染病疫情等导致信息和信息系统受损',0);
INSERT INTO `event_category` VALUES (74, '不可抗力事件', '社会安全事件', 'FOC', 'FOCSOCIALSEC', '危害国家和社会的突发性群体性事件导致信息和信息系统受损，例如：恐怖袭击事件等',0);
INSERT INTO `event_category` VALUES (75, '不可抗力事件', '其他不可抗力事件', 'FOC', 'FOCOTH', '不在以上子类之中的不可抗力事件',0);
INSERT INTO `event_category` VALUES (76, '其他事件', '', 'OTH', '', '',99);
INSERT INTO `event_category` VALUES (77, '系统自身事件', '系统操作日志', 'ST', 'STOPLOG', NULL,0);
INSERT INTO `event_category` VALUES (78, '系统自身事件', '系统告警日志', 'ST', 'STWNLOG', NULL,0);
INSERT INTO `event_category` VALUES (79, '系统自身事件', '系统通知日志', 'ST', 'STIFLOG', NULL,0);
INSERT INTO `event_category` VALUES (80, '系统自身事件', '系统严重事件', 'ST', 'STSEEVENT', NULL,0);
INSERT INTO `event_category` VALUES (81, '系统自身事件', '系统告警事件', 'ST', 'STWNEVENT', NULL,0);
INSERT INTO `event_category` VALUES (82, '系统自身事件', '程序运行告警事件', 'ST', 'STPRWNEVENT', NULL,0);
INSERT INTO `event_category` VALUES (83, '系统自身事件', '程序运行日志事件', 'ST', 'STPRLOGEVENT', NULL,0);
INSERT INTO `event_category` VALUES (84, '系统自身事件', '系统通知事件', 'ST', 'STIFEVENT', NULL,0);
INSERT INTO `event_category` VALUES (85, '异常行为事件', '工控协议异常', 'AB', 'ABICPRO', '因工业协议异常导致不能提供服务',0);
INSERT INTO `event_category` VALUES (86, '异常行为事件', '基线异常事件', 'AB', 'ABBLEVENT', '因基线异常导致不能提供服务',0);
INSERT INTO `event_category` VALUES (87, '违规操作事件', '越权操作事件', 'RO', 'ROPBY', NULL,0);
INSERT INTO `event_category` VALUES (88, '安全隐患事件', '异常通信事件', 'SHE', 'SHEABCM', NULL,0);
INSERT INTO `event_category` VALUES (89, '系统自身事件', '系统严重日志', 'ST', 'STSELOG', NULL,0);
INSERT INTO `event_category` VALUES (90, '环境异常事件', '网络环境异常事件', 'ENAB', 'ENABNET', NULL,0);
INSERT INTO `event_category` VALUES (91, '环境异常事件', '基线日志', 'ENAB', 'ENABBLLOG', NULL,0);
INSERT INTO `event_category` VALUES (92, '其他事件', '协议审计日志', 'OTH', 'OTHPRAULOG', NULL,99);
INSERT INTO `event_category` VALUES (93, '其他事件', '连接管理事件', 'OTH', 'OTHCNMEVENT', NULL,99);
INSERT INTO `event_category` VALUES (94, '其他事件', '数据检测事件', 'OTH', 'OTHDDTEVENT', NULL,99);
INSERT INTO `event_category` VALUES (95, '其他事件', '文件检测事件', 'OTH', 'OTHFDTEVENT', NULL,99);
INSERT INTO `event_category` VALUES (96, '其他事件', '一体化防病毒事件', 'OTH', 'OTHAIOATEVENT', NULL,99);
INSERT INTO `event_category` VALUES (97, '其他事件', '入侵检测事件', 'OTH', 'OTHINDTEVENT', NULL,99);
INSERT INTO `event_category` VALUES (98, '其他事件', '行为监测日志', 'OTH', 'OTHCONMLOG', NULL,99);
INSERT INTO `event_category` VALUES (99, '网络攻击事件', '威胁日志', 'NA', 'NATDLOG', NULL,0);

-- ----------------------------
-- Table structure for log_source_mapping
-- ----------------------------
DROP TABLE IF EXISTS `log_source_mapping`;
CREATE TABLE `log_source_mapping`  (
                                       `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID号',
                                       `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签',
                                       `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '值',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日志来源映射表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log_source_mapping
-- ----------------------------
INSERT INTO `log_source_mapping` VALUES (1, '主机行为分析引擎', 'WAZUH');
INSERT INTO `log_source_mapping` VALUES (2, 'USB安全隔离装置', 'EVENT_USB');
INSERT INTO `log_source_mapping` VALUES (3, '工业安全审计', 'EVENT_AUDIT');
INSERT INTO `log_source_mapping` VALUES (4, '工业入侵检测', 'EVENT_IDS');
INSERT INTO `log_source_mapping` VALUES (5, '工业防火墙', 'EVENT_FIREWALL');
INSERT INTO `log_source_mapping` VALUES (6, '工业主机白名单', 'EVENT_HOST');

-- ----------------------------
-- Table structure for log_type_mapping
-- ----------------------------
DROP TABLE IF EXISTS `log_type_mapping`;
CREATE TABLE `log_type_mapping`  (
                                     `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID号',
                                     `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签',
                                     `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '值',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日志类型映射表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log_type_mapping
-- ----------------------------
INSERT INTO `log_type_mapping` VALUES (1, '告警日志', 'ALARMLOG');
INSERT INTO `log_type_mapping` VALUES (2, '操作日志', 'OPERATIONLOG');
INSERT INTO `log_type_mapping` VALUES (3, '威胁日志', 'THREATSYSLOG');
INSERT INTO `log_type_mapping` VALUES (4, '白名单日志', 'WHITELISTSYSLOG');
INSERT INTO `log_type_mapping` VALUES (5, '行为监测日志', 'BEHAVIORSYSLOG');
INSERT INTO `log_type_mapping` VALUES (6, '系统日志', 'SYSTEMSYSLOG');
INSERT INTO `log_type_mapping` VALUES (7, '基线日志', 'BASELINESYSLOG');
INSERT INTO `log_type_mapping` VALUES (8, '协议审计日志', 'AUDITSYSLOG');
INSERT INTO `log_type_mapping` VALUES (9, '数据检测日志', 'DATADETECTIONSYSLOG');
INSERT INTO `log_type_mapping` VALUES (10, '文件检测日志', 'FILEDETECTIONSYSLOG');
INSERT INTO `log_type_mapping` VALUES (11, '一体化防病毒日志', 'AIONEANTIVIRUSSYSLOG');
INSERT INTO `log_type_mapping` VALUES (12, '入侵检测日志', 'INTRUSIONDETECTIONSYSLOG');
INSERT INTO `log_type_mapping` VALUES (13, '基础策略日志', 'BASEPOLICYSYSLOG');
INSERT INTO `log_type_mapping` VALUES (14, '工业协议策略日志', 'DPISYSLOG');
INSERT INTO `log_type_mapping` VALUES (15, '连接管理日志', 'CONNECTIONMANAGEMENTSYSLOG');
INSERT INTO `log_type_mapping` VALUES (16, '流量日志', 'TRAFFICSYSLOG');
INSERT INTO `log_type_mapping` VALUES (17, '程序运行告警日志', 'SYSLOGALARMPROGRAM');
INSERT INTO `log_type_mapping` VALUES (18, '程序运行日志审计', 'SYSLOGAUDITPROGRAM');
INSERT INTO `log_type_mapping` VALUES (19, '外设管理告警日志', 'SYSLOGALARMUDISK');
INSERT INTO `log_type_mapping` VALUES (20, '外设管理日志审计', 'SYSLOGAUDITUDISK');
INSERT INTO `log_type_mapping` VALUES (21, '防疫卫士告警日志', 'SYSLOGALARMAPPGUARD');
INSERT INTO `log_type_mapping` VALUES (22, '防疫卫士日志审计', 'SYSLOGAUDITAPPGUARD');
INSERT INTO `log_type_mapping` VALUES (23, '访问控制告警日志', 'SYSLOGALARMACCESSCONTROLLER');
INSERT INTO `log_type_mapping` VALUES (24, '访问控制日志审计', 'SYSLOGAUDITACCESSCONTROLLER');
INSERT INTO `log_type_mapping` VALUES (25, '文件保护告警日志', 'SYSLOGALARMFILEGUARD');
INSERT INTO `log_type_mapping` VALUES (26, '文件保护日志审计', 'SYSLOGAUDITFILEGUARD');
INSERT INTO `log_type_mapping` VALUES (27, '注册表保护告警日志', 'SYSLOGALARMREGGUARD');
INSERT INTO `log_type_mapping` VALUES (28, '注册表保护日志审计', 'SYSLOGAUDITREGGUARD');
INSERT INTO `log_type_mapping` VALUES (29, '系统操作日志审计', 'SYSLOGAUDITUSER');

-- ----------------------------
-- Table structure for security_event_library
-- ----------------------------
DROP TABLE IF EXISTS `security_event_library`;
CREATE TABLE `security_event_library`  (
                                           `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID号',
                                           `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则名称',
                                           `wazuh_rule_id` int(11) NULL DEFAULT NULL COMMENT 'wazuh规则id',
                                           `level` int(11) NULL DEFAULT NULL COMMENT '等级，1-4，从高到低',
                                           `description` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
                                           `event_category_id` int(11) NULL DEFAULT NULL COMMENT '关联表：事件分类id',
                                           `status` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 1 COMMENT '状态：0禁用1启用',
                                           PRIMARY KEY (`id`) USING BTREE,
                                           INDEX `wazuh_rule_id`(`wazuh_rule_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1577 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '安全事件库' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of security_event_library
-- ----------------------------
INSERT INTO `security_event_library` VALUES (1, 'win_event_channel', 92652, 4, 'Windows远程用户登录&&win.eventdata.authenticationPackageName匹配到“NTLM”---远程登录检测到NTLM身份验证，可能是pass-the-hash攻击', 65, 1);
INSERT INTO `security_event_library` VALUES (2, 'win_event_channel', 92655, 1, 'Windows错误事件规则组&&win.system.eventID匹配到“808”---打印机驱动程序无法加载，可能使用 PrinterNightmare 漏洞远程执行代码：CVE-2021-34527', 65, 1);
INSERT INTO `security_event_library` VALUES (3, 'win_event_channel', 92656, 1, 'Windows错误事件规则组&&win.eventdata.logonType匹配到“10”&&win.eventdata.ipAddress匹配到“::1”或者“127.0.0.1”---用户使用远程桌面连接 (RDP) 从环回地址登录，可能利用被盗凭据进行反向隧道攻击', 65, 1);
INSERT INTO `security_event_library` VALUES (4, 'win_event_channel', 92657, 3, 'Windows远程用户登录&&win.eventdata.workstationName匹配到字段---检测到远程用户登录，NTLM 身份验证，可能是pass-the-hash攻击 - 可能的 RDP 连接。 验证是否允许执行 RDP 连接', 65, 1);
INSERT INTO `security_event_library` VALUES (5, 'syslog,attacks', 40102, 1, '匹配到^rpc.statd[\\d+]: gethostbyname error for \\W+，表示rpc.statd缓冲区溢出攻击', 65, 1);
INSERT INTO `security_event_library` VALUES (6, 'win_wdefender', 62103, 2, 'Windows Defender系统通道，检测到240s内有8次错误事件', 67, 1);
INSERT INTO `security_event_library` VALUES (7, 'win_wdefender', 62105, 1, 'Windows Defender系统通道，检测到60s内有8次错误事件', 67, 1);
INSERT INTO `security_event_library` VALUES (8, 'win_wdefender', 62104, 2, 'Windows Defender系统通道，检测到120s内有8次警告事件', 67, 1);
INSERT INTO `security_event_library` VALUES (9, 'win_wdefender', 62106, 1, 'Windows Defender系统通道，检测到30s内有8次警告事件', 67, 1);
INSERT INTO `security_event_library` VALUES (10, 'win_wdefender', 62164, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508020\"---Windows Defender 错误：配置错误', 66, 1);
INSERT INTO `security_event_library` VALUES (11, 'syslog,sshd', 5707, 1, '匹配到fatal: buffer_get_string: bad string，表示sshd：OpenSSH challenge-response漏洞攻击', 65, 1);
INSERT INTO `security_event_library` VALUES (12, 'syslog,sshd', 5714, 1, 'sshd损坏，匹配到Local: crc32 compensation attack，120s内3次SSH CRC-32补偿攻击', 65, 1);
INSERT INTO `security_event_library` VALUES (13, 'mse', 63606, 3, '微软反恶意软件信息事件、警告事件、错误事件中，字段 “win.system.eventID”为^1015$，选项没有完整日志，表示Microsoft安全要素-发现可疑活动', 67, 1);
INSERT INTO `security_event_library` VALUES (14, 'mse', 63601, 4, '字段\"win.system.severityValue\"中有^WARNING$，选项没有完整日志，属于微软反恶意软件警告事件。', 67, 1);
INSERT INTO `security_event_library` VALUES (15, 'mse', 63602, 3, '字段\"win.system.severityValue\"中有^ERROR$，选项没有完整日志，属于微软反恶意软件错误事件。', 67, 1);
INSERT INTO `security_event_library` VALUES (16, 'mse', 63617, 2, '240s内$MS_FREQ次微软反恶意软件错误事件', 67, 1);
INSERT INTO `security_event_library` VALUES (17, 'mse', 63618, 2, '240s内$MS_FREQ次微软反恶意软件警告事件', 67, 1);
INSERT INTO `security_event_library` VALUES (18, 'office365', 91589, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^61$，选项没有完整日志，表示Office 365：与妥协用户警报相关的事件', 67, 1);
INSERT INTO `security_event_library` VALUES (19, 'office365', 91605, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^78$，选项没有完整日志，表示Office 365：与Windows Defender为Endpoint生成的警报相关的事件', 67, 1);
INSERT INTO `security_event_library` VALUES (20, 'office365', 91615, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^90$，选项没有完整日志，表示Office 365：Microsoft Defender for Office 365中的威胁情报事件', 67, 1);
INSERT INTO `security_event_library` VALUES (21, 'office365', 91622, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^98$，选项没有完整日志，表示Office 365：Microsoft Cloud App Security触发的警报事件', 67, 1);
INSERT INTO `security_event_library` VALUES (22, 'office365', 91720, 1, '字段\"office365.Operation\"，类型是\"osregex\"中有^QuarantineExport$，选项没有完整日志，表示Office 365：导出隔离邮件', 67, 1);
INSERT INTO `security_event_library` VALUES (23, 'office365', 91721, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^QuarantinePreview$，选项没有完整日志，表示Office 365：预览隔离邮件', 67, 1);
INSERT INTO `security_event_library` VALUES (24, 'office365', 91722, 1, '字段\"office365.Operation\"，类型是\"osregex\"中有^QuarantineRelease$，选项没有完整日志，表示Office 365：发布隔离邮件', 67, 1);
INSERT INTO `security_event_library` VALUES (25, 'office365', 91723, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^QuarantineViewHeader$，选项没有完整日志，表示Office 365：查看隔离邮件的标题', 67, 1);
INSERT INTO `security_event_library` VALUES (26, 'office365', 91724, 2, '用户60s内100次可疑下载活动', 67, 1);
INSERT INTO `security_event_library` VALUES (27, 'office365', 91725, 2, '字段\"office365.Parameters\"，类型是\"pcre2\"中有\\\"Value\\\":\\s*\\\"FullAccess\\\"，选项没有完整日志，表示Office 365：用户$(office_365.UserId)在交换中获得了FullAccess权限', 65, 1);
INSERT INTO `security_event_library` VALUES (28, 'syslog,attacks', 40103, 1, '匹配到ftpd[\\d+]: \\S+ FTP LOGIN FROM \\.+ 0bin0sh，表示在2.6之前的WU-FTPD版本上存在缓冲区溢出', 65, 1);
INSERT INTO `security_event_library` VALUES (29, 'syslog,attacks', 40104, 1, '匹配到?????????????????????，表示缓冲区溢出尝试的可能', 65, 1);
INSERT INTO `security_event_library` VALUES (30, 'syslog,attacks', 40106, 1, '匹配到@@@@@@@@@@@@@@@@@@@@@@@@@，表示缓冲区溢出尝试(可能在yppasswd上)', 65, 1);
INSERT INTO `security_event_library` VALUES (31, 'syslog,attacks', 40107, 1, '匹配到cachefsd: Segmentation Fault - core dumped，表示Solaris cachefsd服务器上堆溢出', 65, 1);
INSERT INTO `security_event_library` VALUES (32, 'syslog,attacks', 40109, 1, '匹配到attempt to execute code on stack by，表示堆溢出尝试或 SEGV (Solaris)程序退出', 65, 1);
INSERT INTO `security_event_library` VALUES (33, 'ms_ipsec', 18668, 2, 'Windows审核失败事件&&ID匹配到“4712”---IPsec 服务遇到潜在的严重故障', 67, 1);
INSERT INTO `security_event_library` VALUES (34, 'win-security', 60196, 2, 'Windows DC登录失败事件&&win.eventdata.failureCode匹配到\"0x22\"---Windows DC - 可能的重放攻击', 65, 1);
INSERT INTO `security_event_library` VALUES (35, 'win-application', 60690, 3, 'VSS事件&&win.system.eventID匹配到\"5002\"---系统资源可能不足', 66, 1);
INSERT INTO `security_event_library` VALUES (36, 'win-application', 60709, 3, 'VSS事件&&win.system.eventID匹配到\"12296\"|\"12297\"|\"12298\"---系统资源可能不足', 66, 1);
INSERT INTO `security_event_library` VALUES (37, 'syslog,proftpd', 11219, 1, '匹配到Reallocating sreaddir buffer，ProFTPD: FTP服务器缓冲区溢出尝试', 65, 1);
INSERT INTO `security_event_library` VALUES (38, 'win-application', 60629, 3, '微软安装程序事件组&&win.system.eventID匹配到”1026“或\"1027\"--配置数据注册表项、子项或配置数据缓存文件夹的安全性不正确', 66, 1);
INSERT INTO `security_event_library` VALUES (39, 'win-application', 61040, 3, '磁盘事件&&win.system.eventID匹配到\"12\"---配置信息中列出的条带集或卷集成员丢失', 66, 1);
INSERT INTO `security_event_library` VALUES (40, 'win-application', 61041, 3, '磁盘事件&&win.system.eventID匹配到\"13\"---容错驱动程序配置信息已损坏', 66, 1);
INSERT INTO `security_event_library` VALUES (41, 'win-application', 60641, 3, 'SPP事件组&&win.system.eventID匹配到\"903\"--软件保护服务已停止', 67, 1);
INSERT INTO `security_event_library` VALUES (42, 'win-application', 60682, 3, 'VSS事件&&win.system.eventID匹配到\"19\"---EventSystem 服务已禁用或在安全模式下尝试启动', 67, 1);
INSERT INTO `security_event_library` VALUES (43, 'win-application', 60684, 4, 'VSS事件&&win.system.eventID匹配到\"26\"---COM+ 事件系统服务或 COM+ 系统应用程序服务已禁用', 67, 1);
INSERT INTO `security_event_library` VALUES (44, 'win-application', 60685, 4, 'VSS事件&&win.system.eventID匹配到\"27\"---卷影复制服务已禁用', 67, 1);
INSERT INTO `security_event_library` VALUES (45, 'win-application', 60686, 4, 'VSS事件&&win.system.eventID匹配到\"28\"---Microsoft 软件卷影复制提供商服务已禁用', 67, 1);
INSERT INTO `security_event_library` VALUES (46, 'win-application', 60723, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Application Error\"---应用程序错误事件组', 67, 1);
INSERT INTO `security_event_library` VALUES (47, 'win-application', 60732, 3, 'WMI事件&&win.system.eventID匹配到\"5612\"---配额达到警告值，WMI 停止 WMIPRVSE.EXE', 66, 1);
INSERT INTO `security_event_library` VALUES (48, 'win-application', 60745, 3, 'WMI事件&&win.system.eventID匹配到\"5612\"---由于配额达到警告值，WMI 停止了 WMIPRVSE.EXE', 66, 1);
INSERT INTO `security_event_library` VALUES (49, 'win-application', 61048, 3, '磁盘事件&&win.system.eventID匹配到\"27\"---容错驱动程序检测到系统被脏关闭', 67, 1);
INSERT INTO `security_event_library` VALUES (50, 'win-application', 61051, 3, '磁盘事件&&win.system.eventID匹配到\"34\"---驱动程序禁用了设备上的写入缓存', 76, 1);
INSERT INTO `security_event_library` VALUES (51, 'syslog,sshd', 5702, 3, '匹配到^reverse mapping和failed - POSSIBLE BREAK，表示sshd：反向查找错误(不良的ISP或攻击)', 67, 1);
INSERT INTO `security_event_library` VALUES (52, 'syslog,sshd', 5703, 2, '360s内6次可能的入侵企图(大量的反向查找错误)', 67, 1);
INSERT INTO `security_event_library` VALUES (53, 'win-ms_logs', 63101, 4, 'Windows系统通道字段匹配到\"Eventlog\"或者\"Microsoft-Windows-Eventlog\"&&win.system.severityValue字段匹配到\"WARNING\"---Windows 事件日志警告事件', 67, 1);
INSERT INTO `security_event_library` VALUES (54, 'win-ms_logs', 63102, 3, 'Windows系统通道字段匹配到\"Eventlog\"或者\"Microsoft-Windows-Eventlog\"&&win.system.severityValue字段匹配到\"ERROR\"---Windows 事件日志错误事件', 67, 1);
INSERT INTO `security_event_library` VALUES (55, 'win-ms_logs', 63106, 2, 'Windows系统日志错误事件---240s内检测到8次错误事件', 67, 1);
INSERT INTO `security_event_library` VALUES (56, 'win-ms_logs', 63109, 4, 'Windows系统通道字段匹配到\"Microsoft-Windows-Eventlog\"&&win.system.severityValue字段匹配到\"WARNING\"---Windows 事件日志警告事件', 67, 1);
INSERT INTO `security_event_library` VALUES (57, 'win-ms_logs', 63110, 3, 'Windows系统通道字段匹配到\"Microsoft-Windows-Eventlog\"&&win.system.severityValue字段匹配到\"ERROR\"---Windows 事件日志错误事件', 67, 1);
INSERT INTO `security_event_library` VALUES (58, 'win_wdefender', 62101, 4, 'Windows系统通道字段匹配到\"Microsoft-Windows-Windows Defender/Operational:\"&&win.system.severityValue字段匹配到\"WARNING\"---Windows Defender警告事件', 67, 1);
INSERT INTO `security_event_library` VALUES (59, 'win_wdefender', 62102, 3, 'Windows系统通道字段匹配到\"Microsoft-Windows-Windows Defender/Operational:\"&&win.system.severityValue字段匹配到\"ERROR\"---Windows Defender错误事件', 67, 1);
INSERT INTO `security_event_library` VALUES (60, 'win_wdefender', 62113, 1, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"1006\"---Windows Defender反恶意软件引擎发现恶意软件或可能有害软件', 67, 1);
INSERT INTO `security_event_library` VALUES (61, 'win_wdefender', 62122, 1, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"1015\"---Windows Defender反恶意软件检测到可疑的行为', 67, 1);
INSERT INTO `security_event_library` VALUES (62, 'win_wdefender', 62123, 1, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"1116\"---Windows Defender反恶意软件检测到可能的有害软件', 67, 1);
INSERT INTO `security_event_library` VALUES (63, 'win_wdefender', 62167, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508023\"---Windows Defender 错误：未找到威胁', 67, 1);
INSERT INTO `security_event_library` VALUES (64, 'win_wdefender', 62168, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508024\"---Windows Defender 错误：需要全面扫描', 67, 1);
INSERT INTO `security_event_library` VALUES (65, 'win_wdefender', 62169, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508025\"---Windows Defender 错误：需要手动执行步骤', 67, 1);
INSERT INTO `security_event_library` VALUES (66, 'win_wdefender', 62171, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508027\"---Windows Defender 错误：低度和中度威胁的删除已禁用', 67, 1);
INSERT INTO `security_event_library` VALUES (67, 'win_wdefender', 62172, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508029\"---Windows Defender 错误：需要重新扫描', 67, 1);
INSERT INTO `security_event_library` VALUES (68, 'win_wdefender', 62173, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508030\"---Windows Defender 错误：需要离线扫描', 67, 1);
INSERT INTO `security_event_library` VALUES (69, 'win_wdefender', 62178, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80501003\"---Windows Defender 错误：主动威胁', 67, 1);
INSERT INTO `security_event_library` VALUES (70, 'win_wdefender', 62188, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80501108\"---Windows Defender 错误：SIG 备份已禁用', 67, 1);
INSERT INTO `security_event_library` VALUES (71, 'win_wdefender', 63107, 2, 'Windows系统日志警告事件---120s内检测到8次警告事件', 67, 1);
INSERT INTO `security_event_library` VALUES (72, 'ms_ipsec', 18657, 2, 'Windows审核成功事件&&ID匹配到\"4963\"---IPsec 丢弃了本应受到保护的入站明文数据包', 67, 1);
INSERT INTO `security_event_library` VALUES (73, 'ms_ipsec', 18658, 3, 'Windows审核成功事件&&ID匹配到\"4965\"---IPsec 从远程计算机接收到安全参数索引 (SPI) 不正确的数据包', 67, 1);
INSERT INTO `security_event_library` VALUES (74, 'ms_ipsec', 18667, 2, 'Windows审核成功事件&&ID匹配到\"4710\"---IPsec 服务已禁用', 67, 1);
INSERT INTO `security_event_library` VALUES (75, 'ms_ipsec', 60010, 4, 'Windows规则组&&win.system.severityValue匹配到\"WARNING\"---Windows警告事件', 67, 1);
INSERT INTO `security_event_library` VALUES (76, 'ms_ipsec', 60011, 3, 'Windows规则组&&win.system.severityValue匹配到\"ERROR\"---Windows错误事件', 67, 1);
INSERT INTO `security_event_library` VALUES (77, 'ms_ipsec', 60012, 2, 'Windows规则组&&win.system.severityValue匹配到\"CRITICAL\"---Windows严重事件', 67, 1);
INSERT INTO `security_event_library` VALUES (78, 'win-base', 60013, 2, 'Windows警告事件规则&&120s内有8次警告事件', 67, 1);
INSERT INTO `security_event_library` VALUES (79, 'win-base', 60014, 2, 'Windows错误事件规则&&240s内有8次错误事件', 67, 1);
INSERT INTO `security_event_library` VALUES (80, 'win-base', 60015, 1, 'Windows严重事件规则&&240s内有4次严重事件', 67, 1);
INSERT INTO `security_event_library` VALUES (81, 'win-security', 60101, 4, 'Windows安全警告事件', 67, 1);
INSERT INTO `security_event_library` VALUES (82, 'win-security', 60102, 3, 'Windows安全错误事件', 67, 1);
INSERT INTO `security_event_library` VALUES (83, 'win-security', 60104, 3, 'Windows审计失败事件', 67, 1);
INSERT INTO `security_event_library` VALUES (84, 'win-security', 60205, 2, 'Window审计失败事件---240s内有8次审计失败事件', 67, 1);
INSERT INTO `security_event_library` VALUES (85, 'win-security', 60206, 2, 'Window审计错误事件---240s内有8次Windows 错误安全事件', 67, 1);
INSERT INTO `security_event_library` VALUES (86, 'win-security', 60207, 2, 'Windows审计警告事件---120s内有8次Windows 警告安全事件', 67, 1);
INSERT INTO `security_event_library` VALUES (87, 'win-security', 60214, 2, 'Window审计成功事件&&win.system.eventID匹配到\"4963\"---IPsec 丢弃应该被保护的入站明文数据包', 67, 1);
INSERT INTO `security_event_library` VALUES (88, 'win-security', 60215, 3, 'Window审计成功事件&&win.system.eventID匹配到\"4965\"---IPsec 从远程计算机收到一个安全参数索引 (SPI) 不正确的数据包', 67, 1);
INSERT INTO `security_event_library` VALUES (89, 'win-application', 60601, 4, 'Windows规则组&&win.system.channel匹配到\"Application\"&&win.system.severityValue匹配到\"WARNING\"&&选项没有完整日志--Windows应用程序警告事件', 67, 1);
INSERT INTO `security_event_library` VALUES (90, 'win-application', 60602, 2, 'Windows规则组&&win.system.channel匹配到\"Application\"&&win.system.severityValue匹配到\"ERROR\"&&选项没有完整日志--Windows应用程序错误事件', 67, 1);
INSERT INTO `security_event_library` VALUES (91, 'win-application', 61061, 2, 'Windows应用程序错误事件---240s内有8次Windows错误应用程序事件', 67, 1);
INSERT INTO `security_event_library` VALUES (92, 'win-application', 61062, 2, 'Windows应用程序警告事件---120s内有8次Windows警告应用程序事件', 67, 1);
INSERT INTO `security_event_library` VALUES (93, 'win-application', 61069, 4, 'Windows应用程序错误事件&&win.system.severityValue匹配到\"AUDIT_FAILURE\"---Windows应用程序审核失败事件', 67, 1);
INSERT INTO `security_event_library` VALUES (94, 'windows_system', 61110, 2, '240s内$MS_FREQ个系统错误事件', 67, 1);
INSERT INTO `security_event_library` VALUES (95, 'windows_system', 61111, 2, '120s内$MS_FREQ个系统警告事件', 67, 1);
INSERT INTO `security_event_library` VALUES (96, 'windows_system', 61112, 1, '120s内$MS_FREQ个系统关键事件', 67, 1);
INSERT INTO `security_event_library` VALUES (97, 'mongodb', 85751, 2, '字段\"mongodb.severity\"中为F，表示致命消息', 67, 1);
INSERT INTO `security_event_library` VALUES (98, 'mongodb', 85752, 3, '字段\"mongodb.severity\"中为E，表示错误消息', 67, 1);
INSERT INTO `security_event_library` VALUES (99, 'mongodb', 85753, 3, '字段\"mongodb.severity\"中为W，表示警告消息', 67, 1);
INSERT INTO `security_event_library` VALUES (100, 'mariadb', 88101, 3, '字段\"mariadb.type\"中有[ERROR]，表示MariaDB错误消息', 67, 1);
INSERT INTO `security_event_library` VALUES (101, 'mariadb', 88102, 4, '字段\"mariadb.type\"中有[Warning]，表示MariaDB警告消息', 67, 1);
INSERT INTO `security_event_library` VALUES (102, 'oracledb', 89101, 3, '解码为oracledb-alerts，表示OracleDB告警', 67, 1);
INSERT INTO `security_event_library` VALUES (103, 'mysql_log', 50125, 3, '匹配到^MySQL log: \\d+ \\S+ \\d+ [ERROR]，表示mysql错误', 67, 1);
INSERT INTO `security_event_library` VALUES (104, 'mysql_log', 50126, 1, '匹配到Fatal error:，表示mysql致命错误', 67, 1);
INSERT INTO `security_event_library` VALUES (105, 'mysql_log', 50180, 2, '表示120s内8次mysql错误', 67, 1);
INSERT INTO `security_event_library` VALUES (106, 'mysql_log', 50181, 3, '匹配到^MySQL log: \\d+-\\d+-\\d+T\\d+:\\d+:\\d+.\\d+\\.+ \\d+ [ERROR]，表示mysql错误', 67, 1);
INSERT INTO `security_event_library` VALUES (107, 'mysql_log', 50182, 1, '匹配到Fatal error:，表示mysql致命错误', 67, 1);
INSERT INTO `security_event_library` VALUES (108, 'mysql_log', 50183, 2, '表示120s内8次mysql错误', 67, 1);
INSERT INTO `security_event_library` VALUES (109, 'postgresql_log', 50503, 3, '状态为^ERROR，表示PostgreSQL错误消息', 67, 1);
INSERT INTO `security_event_library` VALUES (110, 'postgresql_log', 50504, 3, '状态为^FATAL，表示PostgreSQL致命错误消息', 67, 1);
INSERT INTO `security_event_library` VALUES (111, 'postgresql_log', 50520, 1, '符合致命错误消息，匹配到terminating connection due，表示PostgreSQL数据库关闭消息', 67, 1);
INSERT INTO `security_event_library` VALUES (112, 'postgresql_log', 50521, 1, '符合致命错误消息，匹配到aborting any active transactions|shutting down，表示PostgreSQL数据库关闭消息', 67, 1);
INSERT INTO `security_event_library` VALUES (113, 'postgresql_log', 50580, 2, '120s内8次PostgreSQL致命错误', 67, 1);
INSERT INTO `security_event_library` VALUES (114, 'postgresql_log', 50581, 2, '120s内8次PostgreSQL错误', 67, 1);
INSERT INTO `security_event_library` VALUES (115, 'redis', 81303, 4, '匹配到WARNING|can\'t|not permitted|error|Error|ERROR|Failed，表示Redis警告/错误', 67, 1);
INSERT INTO `security_event_library` VALUES (116, 'windows_firewall', 67002, 4, '字段\"win.system.severityValue\"中有^WARNING$，表示Windows防火墙高级安全警告事件', 67, 1);
INSERT INTO `security_event_library` VALUES (117, 'windows_firewall', 67003, 3, '字段\"win.system.severityValue\"中有^ERROR$，表示Windows保护程序错误事件', 67, 1);
INSERT INTO `security_event_library` VALUES (118, 'windows_firewall', 67009, 2, '120s内$MS_FREQ 个有高级安全警告事件的Windows防火墙', 67, 1);
INSERT INTO `security_event_library` VALUES (119, 'windows_firewall', 67010, 2, '240s内$MS_FREQ 个具有高级安全错误事件的Windows防火墙', 67, 1);
INSERT INTO `security_event_library` VALUES (120, 'windows_system', 61101, 4, '字段\"win.system.severityValue\"中有^WARNING$，表示Windows系统警告事件', 67, 1);
INSERT INTO `security_event_library` VALUES (121, 'windows_system', 61102, 3, '字段\"win.system.severityValue\"中有^ERROR$，表示Windows系统错误事件', 67, 1);
INSERT INTO `security_event_library` VALUES (122, 'windows_system', 61103, 2, '字段\"win.system.severityValue\"中有^CRITICAL$，表示Windows系统关键事件', 67, 1);
INSERT INTO `security_event_library` VALUES (123, 'windows_system', 61137, 2, '字段\"win.system.eventID\"中有^10051$，表示有些错误大约每三秒发生一次', 67, 1);
INSERT INTO `security_event_library` VALUES (124, 'mse', 63603, 1, '微软反恶意软件信息事件、警告事件、错误事件中，字段 “win.system.eventID”为^1118$|^1119$，选项没有完整日志，表示Microsoft安全要素-检测到病毒，但无法删除', 1, 1);
INSERT INTO `security_event_library` VALUES (125, 'mse', 63604, 3, '微软反恶意软件信息事件、警告事件、错误事件中，字段 “win.system.eventID”为^1107$，选项没有完整日志，表示Microsoft安全要素-检测到病毒并已正确删除', 1, 1);
INSERT INTO `security_event_library` VALUES (126, 'mse', 63605, 3, '微软反恶意软件信息事件中，字段 “win.system.eventID”为^1119$|^1118$|^1117$|^1116$，选项没有完整日志，表示Microsoft安全要素-检测到病毒', 1, 1);
INSERT INTO `security_event_library` VALUES (127, 'office365', 91700, 1, '在字段\"office365.Operation\"，类型是\"osregex\"中有^FileMalwareDetected$，选项没有完整日志，表示在$(office_365.SourceFileName)文件中检测到恶意软件。', 1, 1);
INSERT INTO `security_event_library` VALUES (128, 'office365', 91556, 1, '字段\"office365.RecordType\"，类型是\"osregex\"中有^28$，选项没有完整日志，表示Office 365：来自Exchange Online Protection和Microsoft Defender给Office 365的钓鱼和恶意软件事件', 1, 1);
INSERT INTO `security_event_library` VALUES (129, 'office365', 91575, 1, '字段\"office365.RecordType\"，类型是\"osregex\"中有^47$，选项没有完整日志，表示Office 365：针对SharePoint、OneDrive商务版和Microsoft Defender Office 365的微软团队的文件的网络钓鱼和恶意软件事件', 1, 1);
INSERT INTO `security_event_library` VALUES (130, 'syslog,attacks', 40113, 1, '360s内8次检测到病毒，可能爆发', 1, 1);
INSERT INTO `security_event_library` VALUES (131, 'ms_wdefender', 83001, 1, 'Windows系统日志匹配到\"Microsoft-Windows-Windows Defender/Operational: WARNING(1116):\"---Windows Defender检测到系统中存在可能有害软件', 1, 1);
INSERT INTO `security_event_library` VALUES (132, 'syslog,attacks', 40501, 1, '300s内4次攻击之后添加一个用户', 33, 1);
INSERT INTO `security_event_library` VALUES (133, 'win_wdefender', 62163, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x8050800C\"---Windows Defender 错误：输入数据错误', 33, 1);
INSERT INTO `security_event_library` VALUES (134, 'win_wdefender', 62192, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x8050800C\"---Windows Defender 错误：输入数据错误', 76, 1);
INSERT INTO `security_event_library` VALUES (135, 'syslog,arpwatch', 7209, 3, '匹配到ethernet mismatch，表示Arpwatch:可能的ARP攻击', 13, 1);
INSERT INTO `security_event_library` VALUES (136, 'syslog,sshd', 5701, 2, '匹配到Bad protocol version identification，表示sshd:可能针对ssh服务器的攻击(或版本收集)', 16, 1);
INSERT INTO `security_event_library` VALUES (137, 'audit_detections', 92602, 4, '匹配type\"pcre2\"中有(psexec.py|smbexec.py).+a\\d=\"-hashes\".+a\\d=\".+:.+，表示可疑的python脚本与Impacket签名匹配，可能使用了窃取的凭据或pass hash攻击', 16, 1);
INSERT INTO `security_event_library` VALUES (138, 'syslog,named', 12101, 4, '匹配dropping source port zero packet from，表示无效的DNS报文，有攻击的可能性', 23, 1);
INSERT INTO `security_event_library` VALUES (139, 'syslog,proftpd', 11209, 4, '匹配到Refused PORT，表示ProFTPD: 试图绕过无法充分保持FTP流量的状态的防火墙', 13, 1);
INSERT INTO `security_event_library` VALUES (140, 'syslog,attacks', 40601, 3, '90s内12次来自同一源ip的网络扫描', 11, 1);
INSERT INTO `security_event_library` VALUES (141, 'syslog,sshd', 5705, 3, '360s内6次可能的扫描或闯入尝试（登录超时次数高）', 11, 1);
INSERT INTO `security_event_library` VALUES (142, 'syslog,telnetd', 5631, 3, '120s内6次来自同一个源的多个连接尝试(可能是扫描)', 11, 1);
INSERT INTO `security_event_library` VALUES (143, 'syslog', 2551, 3, '匹配到^Connection from \\S+ on illegal port$，表示从非特权端口连接到rshd，可能是网络扫描', 11, 1);
INSERT INTO `security_event_library` VALUES (144, 'win_wdefender', 62135, 2, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"2005\"---Windows Defender尝试加载反恶意软件引擎时出错，因为反恶意软件平台未更新', 63, 1);
INSERT INTO `security_event_library` VALUES (145, 'win_wdefender', 62174, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508031\"---Windows Defender 错误：平台未更新', 63, 1);
INSERT INTO `security_event_library` VALUES (146, 'win_wdefender', 62199, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508013\"---Windows Defender 错误：用户数据库版本错误', 76, 1);
INSERT INTO `security_event_library` VALUES (147, 'win-security', 60115, 2, 'Windows审计成功事件&&win.system.eventID匹配到”644“|”4740“---用户帐户被锁定（多次登录错误）', 64, 1);
INSERT INTO `security_event_library` VALUES (148, 'win-security', 60120, 2, 'Windows审计失败事件&&win.system.eventID匹配到”680“---Windows重复登录尝试（被忽略）', 64, 1);
INSERT INTO `security_event_library` VALUES (149, 'win-application', 60619, 3, '微软安装程序事件组&&win.system.eventID匹配到”1012“--此版本不支持64位程序包部署', 63, 1);
INSERT INTO `security_event_library` VALUES (150, 'win-application', 60938, 3, 'MSDTC事件&&win.system.eventID匹配到\"4402\"---事务管理器的版本与此功能不兼容', 63, 1);
INSERT INTO `security_event_library` VALUES (151, 'win-application', 60971, 3, 'MSDTC事件&&win.system.eventID匹配到\"4442\"---MS DTC 无法启动，因为此版本的 dtc 低于允许的最低有效版本', 63, 1);
INSERT INTO `security_event_library` VALUES (152, 'win-application', 60972, 3, 'MSDTC事件&&win.system.eventID匹配到\"4443\"---MS DTC 无法启动，因为此版本的 dtc 与群集另一个节点上安装的 MS DTC 版本不兼容', 63, 1);
INSERT INTO `security_event_library` VALUES (153, 'win-application', 61020, 3, '.Net Runtime事件&&win.system.eventID匹配到\"1022\"---.NET Runtime - CLR 2.0 不支持为 CLR 1.x 编写的探查器', 63, 1);
INSERT INTO `security_event_library` VALUES (154, 'win-application', 60654, 3, 'Windows搜索事件组&&win.system.eventID匹配到\"3034\"--注册表版本与预期版本不匹配', 63, 1);
INSERT INTO `security_event_library` VALUES (155, 'win-application', 60700, 3, 'VSS事件&&win.system.eventID匹配到\"8213\"---卷影复制服务：写入者没有足够的权限来创建卷影复制', 76, 1);
INSERT INTO `security_event_library` VALUES (156, 'win-application', 60701, 3, 'VSS事件&&win.system.eventID匹配到\"8213\"---集群服务未在具有足够访问权限的用户下运行', 76, 1);
INSERT INTO `security_event_library` VALUES (157, 'win_event_channel', 92650, 1, 'Windows系统通道错误事件&&win.eventdata.imagePath匹配到\"%systemroot%\\\\\\\\\\w+\\.exe\"---多次创建新的Windows服务，并从根路径启动，属于刻意事件，因为二进制文件可能会使用Windows管理员共享删除', 57, 1);
INSERT INTO `security_event_library` VALUES (158, 'win_wdefender', 62162, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508007\"---Windows Defender 错误：没有内存', 52, 1);
INSERT INTO `security_event_library` VALUES (159, 'win_wdefender', 62191, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508004\"---Windows Defender 错误：UFS 损坏', 52, 1);
INSERT INTO `security_event_library` VALUES (160, 'win_wdefender', 62190, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508002\"---Windows Defender 错误：数据库错误', 52, 1);
INSERT INTO `security_event_library` VALUES (161, 'win_wdefender', 62205, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x8050A001\"---Windows Defender 错误：数据库损坏：打开', 76, 1);
INSERT INTO `security_event_library` VALUES (162, 'win_wdefender', 62206, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x8050A002\"---Windows Defender 错误：数据库损坏：标头', 76, 1);
INSERT INTO `security_event_library` VALUES (163, 'win_wdefender', 62207, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x8050A003\"---Windows Defender 错误：数据库损坏：旧引擎', 76, 1);
INSERT INTO `security_event_library` VALUES (164, 'win_wdefender', 62208, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x8050A004\"---Windows Defender 错误：数据库损坏：内容', 76, 1);
INSERT INTO `security_event_library` VALUES (165, 'win_wdefender', 62209, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x8050A005\"---Windows Defender 错误：数据库损坏：未签名', 76, 1);
INSERT INTO `security_event_library` VALUES (166, 'win_wdefender', 62210, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x8050A002\"---Windows Defender 错误：数据库损坏：标头', 76, 1);
INSERT INTO `security_event_library` VALUES (167, 'syslog', 5108, 1, '匹配到Out of Memory: ，表示系统内存不足，系统的可用性存在风险', 52, 1);
INSERT INTO `security_event_library` VALUES (168, 'win-application', 61037, 3, '磁盘事件&&win.system.eventID匹配到\"7\"---设备有坏块', 52, 1);
INSERT INTO `security_event_library` VALUES (169, 'win-application', 61038, 3, '磁盘事件&&win.system.eventID匹配到\"9\"---设备在超时时间内没有响应', 52, 1);
INSERT INTO `security_event_library` VALUES (170, 'win-application', 60652, 2, 'Windows搜索事件组&&win.system.eventID匹配到\"3025\"--严重错误：系统资源不足', 52, 1);
INSERT INTO `security_event_library` VALUES (171, 'win-application', 60664, 3, 'Windows搜索事件组&&win.system.eventID匹配到\"7013\"--磁盘已满，更新已暂停', 52, 1);
INSERT INTO `security_event_library` VALUES (172, 'win-application', 60712, 3, 'VSS事件&&win.system.eventID匹配到\"12305\"---卷/磁盘未连接或未找到', 52, 1);
INSERT INTO `security_event_library` VALUES (173, 'win-application', 61052, 3, '磁盘事件&&win.system.eventID匹配到\"41\"---磁盘上的文件系统结构已损坏且无法使用', 52, 1);
INSERT INTO `security_event_library` VALUES (174, 'syslog,named', 12144, 2, '匹配到reloading configuration failed: out of memory，表示服务器没有足够的内存来重新加载配置', 52, 1);
INSERT INTO `security_event_library` VALUES (175, 'windows,dhcp', 6303, 2, 'id为^02，表示MS-DHCP:由于磁盘空间不足，日志暂时暂停', 52, 1);
INSERT INTO `security_event_library` VALUES (176, 'windows,dhcp', 6308, 1, 'id为^14，表示MS-DHCP:作用域地址池耗尽，无法满足租约请求', 52, 1);
INSERT INTO `security_event_library` VALUES (177, 'windows,dhcp', 6314, 2, 'id为^22，表示MS-DHCP:BOOTP范围地址池耗尽，无法满足BOOTP请求', 52, 1);
INSERT INTO `security_event_library` VALUES (178, 'syslog,sshd', 5713, 3, '匹配到Corrupted check bytes on，表示sshd损坏', 52, 1);
INSERT INTO `security_event_library` VALUES (179, 'syslog,postfix', 3331, 2, 'id为^452，表示后缀磁盘空间不足错误', 52, 1);
INSERT INTO `security_event_library` VALUES (180, 'syslog', 1007, 3, '匹配到file system full|No space left on device，表示文件系统已满', 52, 1);
INSERT INTO `security_event_library` VALUES (181, 'syslog', 5130, 3, '匹配到ADSL line is down，表示监视器ADSL线路故障', 52, 1);
INSERT INTO `security_event_library` VALUES (182, 'syslog', 5135, 3, '匹配到Disk failure，表示RAID盘故障', 52, 1);
INSERT INTO `security_event_library` VALUES (183, 'syslog', 5138, 3, '匹配到ata\\S+: failed command，表示一般SATA硬盘故障', 52, 1);
INSERT INTO `security_event_library` VALUES (184, 'syslog', 5139, 3, '匹配到device not ready，表示一般设备故障：设备未准备好', 52, 1);
INSERT INTO `security_event_library` VALUES (185, 'syslog', 7101, 2, '匹配到Integrity Check failed: File could not，表示绊线检查有问题', 52, 1);
INSERT INTO `security_event_library` VALUES (186, 'syslog', 2937, 1, '状态为FAILED，可能是磁盘故障，SCSI控制器错误', 52, 1);
INSERT INTO `security_event_library` VALUES (187, 'syslog', 2938, 1, 'action为failed，指SCSI RAID阵列错误，驱动器故障', 52, 1);
INSERT INTO `security_event_library` VALUES (188, 'windows', 64103, 2, '字段\"win.system.eventID\"中有^13570$，表示Windows文件系统满了', 52, 1);
INSERT INTO `security_event_library` VALUES (189, 'windows_system', 61105, 2, '字段\"win.system.eventID\"中有^41$，字段\"win.system.providerName\"中有^Microsoft-Windows-Kernel-Power$，表示系统异常停止响应、崩溃或断电', 52, 1);
INSERT INTO `security_event_library` VALUES (190, 'windows_system', 61106, 3, '字段\"win.system.eventID\"中有^219$，字段\"win.system.providerName\"中有^Microsoft-Windows-Kernel-PnP$，表示驱动程序$(win.eventdata.failureName)加载设备失败$(win.eventdata.driverName)', 52, 1);
INSERT INTO `security_event_library` VALUES (191, 'syslog,smbd', 13112, 4, '解码为kernel，匹配到gvfsd-smb和segfault at \\S+ ip \\S+ sp \\S+ error \\d+ in，表示Samba的gvfs-smb存储器段错误', 52, 1);
INSERT INTO `security_event_library` VALUES (192, 'mse', 63607, 4, '微软反恶意软件信息事件、警告事件、错误事件中，字段 “win.system.eventID”为^5007$，选项没有完整日志，表示Microsoft安全要素-配置更改', 70, 1);
INSERT INTO `security_event_library` VALUES (193, 'mse', 63608, 2, '微软反恶意软件信息事件、警告事件、错误事件中，字段 “win.system.eventID”为^5008$，选项没有完整日志，表示Microsoft安全要素-服务失败', 70, 1);
INSERT INTO `security_event_library` VALUES (194, 'mse', 63609, 2, '微软反恶意软件信息事件、警告事件、错误事件中，字段 “win.system.eventID”为^3002$，选项没有完整日志，表示Microsoft安全要素-实时保护失败', 70, 1);
INSERT INTO `security_event_library` VALUES (195, 'mse', 63610, 2, '微软反恶意软件信息事件、警告事件、错误事件中，字段 “win.system.eventID”为^2012$，选项没有完整日志，表示Microsoft安全要素-不能使用动态签名服务', 70, 1);
INSERT INTO `security_event_library` VALUES (196, 'mse', 63611, 2, '微软反恶意软件信息事件、警告事件、错误事件中，字段 “win.system.eventID”为^2004$，选项没有完整日志，表示Microsoft安全要素-加载定义失败。使用最后一个好的集合', 70, 1);
INSERT INTO `security_event_library` VALUES (197, 'mse', 63612, 2, '微软反恶意软件信息事件、警告事件、错误事件中，字段 “win.system.eventID”为^2003$，选项没有完整日志，表示Microsoft安全要素-引擎更新失败', 70, 1);
INSERT INTO `security_event_library` VALUES (198, 'mse', 63613, 2, '微软反恶意软件信息事件、警告事件、错误事件中，字段 “win.system.eventID”为^2001$，选项没有完整日志，表示Microsoft安全要素-定义更新失败', 70, 1);
INSERT INTO `security_event_library` VALUES (199, 'mse', 63614, 3, '微软反恶意软件信息事件、警告事件、错误事件中，字段 “win.system.eventID”为^1005$，选项没有完整日志，表示Microsoft安全要素-扫描错误，扫描停止', 70, 1);
INSERT INTO `security_event_library` VALUES (200, 'mse', 63615, 3, '微软反恶意软件信息事件、警告事件、错误事件中，字段 “win.system.eventID”为^1002$，选项没有完整日志，表示Microsoft安全要素-扫描在完成前停止', 70, 1);
INSERT INTO `security_event_library` VALUES (201, 'win_wdefender', 62179, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80501004\"---Windows Defender 错误：网络未连接', 69, 1);
INSERT INTO `security_event_library` VALUES (202, 'win_wdefender', 62112, 3, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"1005\"---Windows Defender扫描反恶意软件扫描失败', 68, 1);
INSERT INTO `security_event_library` VALUES (203, 'win_wdefender', 62115, 4, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"1008\"---Windows Defender反恶意软件采取措施保护系统免受恶意软件或可能有害软件的攻击失败', 76, 1);
INSERT INTO `security_event_library` VALUES (204, 'win_wdefender', 62117, 3, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"1010\"---Windows Defender反恶意软件无法从软件隔离区恢复项目', 68, 1);
INSERT INTO `security_event_library` VALUES (205, 'win_wdefender', 62119, 3, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"1012\"---Windows Defender反恶意软件无法从软件隔离区删除项目', 68, 1);
INSERT INTO `security_event_library` VALUES (206, 'win_wdefender', 62121, 3, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"1014\"---Windows Defender反恶意软件无法删除恶意软件或可能有害软件的历史记录', 68, 1);
INSERT INTO `security_event_library` VALUES (207, 'win_wdefender', 62125, 2, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"1118\"---Windows Defender反恶意软件采取操作保护您免受可能有害的软件的攻击失败', 76, 1);
INSERT INTO `security_event_library` VALUES (208, 'win_wdefender', 62126, 1, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"1119\"---Windows Defender反恶意软件在尝试对可能有害的软件执行操作时遇到严重错误', 68, 1);
INSERT INTO `security_event_library` VALUES (209, 'win_wdefender', 62131, 3, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"2001\"---Windows Defender反恶意软件定义更新失败', 68, 1);
INSERT INTO `security_event_library` VALUES (210, 'win_wdefender', 62133, 3, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"2003\"---Windows Defender反恶意软件引擎更新失败', 68, 1);
INSERT INTO `security_event_library` VALUES (211, 'win_wdefender', 62134, 2, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"2004\"---Windows Defender尝试加载反恶意软件定义时出错', 68, 1);
INSERT INTO `security_event_library` VALUES (212, 'win_wdefender', 62136, 2, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"2006\"---Windows Defender尝试更新反恶意软件平台失败', 68, 1);
INSERT INTO `security_event_library` VALUES (213, 'win_wdefender', 62140, 2, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"2012\"---Windows Defender尝试使用动态签名服务时出错', 68, 1);
INSERT INTO `security_event_library` VALUES (214, 'win_wdefender', 62143, 3, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"2021\"---Windows Defender反恶意软件引擎下载干净的文件失败', 68, 1);
INSERT INTO `security_event_library` VALUES (215, 'win_wdefender', 62145, 3, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"2031\"---Windows Defender反恶意软件引擎无法下载和配置离线扫描', 68, 1);
INSERT INTO `security_event_library` VALUES (216, 'win_wdefender', 62149, 2, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"3002\"---Windows Defender实时保护遇到错误并失败', 68, 1);
INSERT INTO `security_event_library` VALUES (217, 'win_wdefender', 62150, 3, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"3007\"---Windows Defender实时保护遇到错误并失败', 68, 1);
INSERT INTO `security_event_library` VALUES (218, 'win_wdefender', 62155, 1, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"5008\"---Windows Defender反恶意软件引擎遇到错误并失败', 68, 1);
INSERT INTO `security_event_library` VALUES (219, 'win_wdefender', 62161, 2, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"5101\"---Windows Defender由于错误 $(win.eventdata.errorCode)，反恶意软件平台已过期', 70, 1);
INSERT INTO `security_event_library` VALUES (220, 'win_wdefender', 62165, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x805080211\"---Windows Defender 错误：隔离失败', 70, 1);
INSERT INTO `security_event_library` VALUES (221, 'win_wdefender', 62166, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508022\"---Windows Defender 错误：需要重新启动', 70, 1);
INSERT INTO `security_event_library` VALUES (222, 'win_wdefender', 62170, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508026\"---Windows Defender 错误：不支持删除', 70, 1);
INSERT INTO `security_event_library` VALUES (223, 'win_wdefender', 62175, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80501000\"---Windows Defender 错误：UI整合基础（内部错误）', 70, 1);
INSERT INTO `security_event_library` VALUES (224, 'win_wdefender', 62176, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80501001\"---Windows Defender 错误：操作失败', 70, 1);
INSERT INTO `security_event_library` VALUES (225, 'win_wdefender', 62177, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80501002\"---Windows Defender 错误：无引擎', 70, 1);
INSERT INTO `security_event_library` VALUES (226, 'win_wdefender', 62180, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80501101\"---Windows Defender 错误：LUA取消（内部错误）', 68, 1);
INSERT INTO `security_event_library` VALUES (227, 'win_wdefender', 62181, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x805011011\"---Windows Defender 错误：LUA代码已取消（内部错误）', 68, 1);
INSERT INTO `security_event_library` VALUES (228, 'win_wdefender', 62182, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80501102\"---Windows Defender 错误：代码已关闭（内部错误）', 68, 1);
INSERT INTO `security_event_library` VALUES (229, 'win_wdefender', 62183, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80501103\"---Windows Defender 错误：代码异步调用待处理', 68, 1);
INSERT INTO `security_event_library` VALUES (230, 'win_wdefender', 62184, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80501104\"---Windows Defender 错误：代码已取消', 68, 1);
INSERT INTO `security_event_library` VALUES (231, 'win_wdefender', 62185, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80501105\"---Windows Defender 错误：代码无目标', 68, 1);
INSERT INTO `security_event_library` VALUES (232, 'win_wdefender', 62186, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80501106\"---Windows Defender 错误：代码错误 REGEXP', 68, 1);
INSERT INTO `security_event_library` VALUES (233, 'win_wdefender', 62187, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80501107\"---Windows Defender 错误：测试引发的错误', 70, 1);
INSERT INTO `security_event_library` VALUES (234, 'win_wdefender', 62189, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508001\"---Windows Defender 错误：错误的初始化模块', 70, 1);
INSERT INTO `security_event_library` VALUES (235, 'win_wdefender', 62193, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x8050800D\"---Windows Defender 错误：全局存储错误', 70, 1);
INSERT INTO `security_event_library` VALUES (236, 'win_wdefender', 62194, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x8050800E\"---Windows Defender 错误：已过时', 70, 1);
INSERT INTO `security_event_library` VALUES (237, 'win_wdefender', 62195, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x8050800F\"---Windows Defender 错误：不支持', 70, 1);
INSERT INTO `security_event_library` VALUES (238, 'win_wdefender', 62196, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508010\"---Windows Defender 错误：没有更多项目', 70, 1);
INSERT INTO `security_event_library` VALUES (239, 'win_wdefender', 62197, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508011\"---Windows Defender 错误：扫描 ID 重复', 70, 1);
INSERT INTO `security_event_library` VALUES (240, 'win_wdefender', 62198, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508012\"---Windows Defender 错误：扫描 ID 错误', 70, 1);
INSERT INTO `security_event_library` VALUES (241, 'win_wdefender', 62212, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508018\"---Windows Defender 错误：扫描中止', 70, 1);
INSERT INTO `security_event_library` VALUES (242, 'win_wdefender', 62200, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508014\"---Windows Defender 错误：恢复失败', 70, 1);
INSERT INTO `security_event_library` VALUES (243, 'win_wdefender', 62201, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508016\"---Windows Defender 错误：错误操作', 70, 1);
INSERT INTO `security_event_library` VALUES (244, 'win_wdefender', 62202, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80508019\"---Windows Defender 错误：未找到', 70, 1);
INSERT INTO `security_event_library` VALUES (245, 'win_wdefender', 62203, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80509001\"---Windows Defender 错误：EHANDLE 错误', 68, 1);
INSERT INTO `security_event_library` VALUES (246, 'win_wdefender', 62204, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x80509003\"---Windows Defender 错误：RELO 内核未加载', 68, 1);
INSERT INTO `security_event_library` VALUES (247, 'win_wdefender', 62211, 1, 'Windows系统通道错误事件&&win.eventdata.errorCode字段匹配到\"0x8050801\"---Windows Defender 错误：删除失败', 70, 1);
INSERT INTO `security_event_library` VALUES (248, 'office365', 91531, 4, '解码为json，字段“integration”为office365，选项没有完整日志，表示Office 365: 工作负载操作', 70, 1);
INSERT INTO `security_event_library` VALUES (249, 'office365', 91532, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有\\d+，选项没有完整日志，表示Office 365: 工作负载操作', 70, 1);
INSERT INTO `security_event_library` VALUES (250, 'office365', 91648, 4, '字段\"office365.actor\"中有wazuh，表示Office 365模块内部事件，3个请求失败', 68, 1);
INSERT INTO `security_event_library` VALUES (251, 'office365', 91701, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^DocumentSensitivityMismatchDetected$，选项没有完整日志，表示Office 365：检测文档敏感度不匹配', 70, 1);
INSERT INTO `security_event_library` VALUES (252, 'sqlserver', 85005, 3, '匹配到Login failed for user，表示SQL Server登录失败', 68, 1);
INSERT INTO `security_event_library` VALUES (253, 'sqlserver', 85006, 2, '表示8次SQL Server登录失败', 68, 1);
INSERT INTO `security_event_library` VALUES (254, 'sqlserver', 85008, 3, '匹配到The SQL Server Network Interface library could not register the Service Principal Name，表示SQL Server网络接口库未注册', 70, 1);
INSERT INTO `security_event_library` VALUES (255, 'sqlserver', 85009, 4, '匹配到Error: ，表示SQL Server异常', 70, 1);
INSERT INTO `security_event_library` VALUES (256, 'mongodb', 85759, 3, '符合信息消息，字段\"mongodb.component\"中有ACCESS，匹配到authentication failed for，表示MongoDB身份验证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (257, 'mongodb', 85760, 2, '表示8次MongoDB身份验证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (258, 'mongodb', 85761, 3, '符合信息消息，字段\"mongodb.component\"中有ACCESS，匹配到Unauthorized not authorized on \\S+ to execute command，表示在没有必要权限的情况下执行命令', 70, 1);
INSERT INTO `security_event_library` VALUES (259, 'mysql_audit', 88003, 2, '字段\"audit_record.name\"中有^Connect$，字段\"audit_record.status\"中有^1，表示Percona audit身份验证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (260, 'mysql_audit', 88006, 4, '字段\"audit_record.name\"中有^Query$，字段\"audit_record.status\"中有^1，表示Percona审计失败:$(audit_record.command_class)声明', 70, 1);
INSERT INTO `security_event_library` VALUES (261, 'mysql_audit', 88007, 3, '字段\"audit_record.name\"中有^Query$，字段\"audit_record.command_class\"中有^drop|^alter|^insert|^update|^grant|^delete，表示Percona审计失败:$(audit_record.command_class)声明', 70, 1);
INSERT INTO `security_event_library` VALUES (262, 'mysql_audit', 89053, 2, '字段\"cmd\"中有^Failed Login$，表示McAfee MySQL审计:身份验证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (263, 'mysql_audit', 89056, 4, '状态为^1，表示McAfee MySQL审计失败:$(cmd)语句', 70, 1);
INSERT INTO `security_event_library` VALUES (264, 'mysql_audit', 89057, 3, '字段\"cmd\"为^drop|^alter|^insert|^update|^grant|^delete，表示McAfee MySQL审计失败:$(cmd)语句', 70, 1);
INSERT INTO `security_event_library` VALUES (265, 'mysql_log', 50106, 2, '匹配到Access denied for user，表示MySQL：身份验证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (266, 'postgresql_log', 50512, 2, '符合致命错误消息，匹配到authentication failed，表示PostgreSQL数据库身份验证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (267, 'syslog,attacks', 40111, 2, '160s内12次身份验证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (268, 'syslog,attacks', 40112, 1, '240s内多次身份验证失败后出现一次成功', 68, 1);
INSERT INTO `security_event_library` VALUES (269, 'ms_ipsec', 18652, 2, 'Windows审核失败事件&&ID匹配到“4652”或者“4653”---IPsec 主模式协商失败', 68, 1);
INSERT INTO `security_event_library` VALUES (270, 'ms_ipsec', 18653, 2, 'Windows审核失败事件&&ID匹配到“4654”---IPsec 快速模式协商失败', 68, 1);
INSERT INTO `security_event_library` VALUES (271, 'ms_ipsec', 18654, 2, 'Windows审核成功事件&&ID匹配到“4983”或者“4984”---IPsec 扩展模式协商失败', 68, 1);
INSERT INTO `security_event_library` VALUES (272, 'ms_ipsec', 18663, 2, 'Windows审核失败事件&&ID匹配到“5480”---IPsec 服务无法获取计算机上网络接口的完整列表', 68, 1);
INSERT INTO `security_event_library` VALUES (273, 'ms_ipsec', 18664, 2, 'Windows审核失败事件&&ID匹配到“5483”---IPsec 服务无法初始化 RPC 服务器。 IPsec 服务无法启动', 68, 1);
INSERT INTO `security_event_library` VALUES (274, 'ms_ipsec', 18665, 2, 'Windows审核失败事件&&ID匹配到“5484”---IPsec 服务遇到严重故障并已关闭', 68, 1);
INSERT INTO `security_event_library` VALUES (275, 'ms_ipsec', 18666, 2, 'Windows审核失败事件&&ID匹配到“5485”---IPsec 服务无法处理网络接口即插即用事件中的某些 IPsec 筛选器', 68, 1);
INSERT INTO `security_event_library` VALUES (276, 'win-security', 60105, 3, 'Windows审计失败事件&&win.system.eventID匹配到“529”|“530”|“531”|“532”|“533”|“534”|“535”|“536”|“537”|“539“|”4625“---Windows登录失败', 68, 1);
INSERT INTO `security_event_library` VALUES (277, 'win-security', 60107, 3, 'Windows审计失败事件&&win.system.eventID匹配到”577“|”4673“---尝试执行特权操作失败', 70, 1);
INSERT INTO `security_event_library` VALUES (278, 'win-security', 60122, 3, 'Windows登录失败&&win.system.eventID匹配到“529“|”4625”---登录失败，用户名不存在或密码错误', 68, 1);
INSERT INTO `security_event_library` VALUES (279, 'win-security', 60123, 3, 'Windows登录失败&&win.system.eventID匹配到“530”---登录失败 - 违反帐户登录时间限制', 68, 1);
INSERT INTO `security_event_library` VALUES (280, 'win-security', 60124, 3, 'Windows登录失败&&win.system.eventID匹配到“531”---登录失败 - 帐户当前已禁用', 68, 1);
INSERT INTO `security_event_library` VALUES (281, 'win-security', 60125, 3, 'Windows登录失败&&win.system.eventID匹配到“532”---登录失败 - 指定帐户已过期', 68, 1);
INSERT INTO `security_event_library` VALUES (282, 'win-security', 60126, 3, 'Windows登录失败&&win.system.eventID匹配到“533”---登录失败 - 用户不允许在此计算机上登录', 68, 1);
INSERT INTO `security_event_library` VALUES (283, 'win-security', 60127, 3, 'Windows登录失败&&win.system.eventID匹配到“534”---登录失败 - 未授予用户登录类型', 68, 1);
INSERT INTO `security_event_library` VALUES (284, 'win-security', 60128, 3, 'Windows登录失败&&win.system.eventID匹配到“535”---登录失败 - 账户密码已过期', 68, 1);
INSERT INTO `security_event_library` VALUES (285, 'win-security', 60129, 3, 'Windows登录失败&&win.system.eventID匹配到“536”|“537”---登录失败 - 内部错误', 68, 1);
INSERT INTO `security_event_library` VALUES (286, 'win-security', 60130, 3, 'Windows登录失败&&win.system.eventID匹配到“539”---登录失败 - 帐户被锁定', 68, 1);
INSERT INTO `security_event_library` VALUES (287, 'win-security', 60131, 3, 'Windows审计失败事件&&win.system.eventID匹配到”673\"|\"675\"|\"681\"|\"4769“---Windows DC 登录失败', 68, 1);
INSERT INTO `security_event_library` VALUES (288, 'win-security', 60195, 2, 'Windows DC登录失败事件&&win.eventdata.failureCode匹配到\"0x1F\"---Windows DC 对解密字段的完整性检查失败', 68, 1);
INSERT INTO `security_event_library` VALUES (289, 'win-security', 60197, 3, 'Windows DC登录失败事件&&win.eventdata.failureCode匹配到\"0x25\"---Windows DC - 时钟偏差太大', 68, 1);
INSERT INTO `security_event_library` VALUES (290, 'win-security', 60203, 2, 'Windows审计失败事件---240s内有8次同一用户尝试执行特权操作失败', 68, 1);
INSERT INTO `security_event_library` VALUES (291, 'win-security', 60204, 2, 'Window授权失败---240s内有8次登录失败', 68, 1);
INSERT INTO `security_event_library` VALUES (292, 'win-security', 60209, 2, 'Window审计失败事件&&win.system.eventID匹配到\"4652\"|\"4653\"---IPsec主模式协商失败', 68, 1);
INSERT INTO `security_event_library` VALUES (293, 'win-security', 60210, 2, 'Window审计失败事件&&win.system.eventID匹配到\"4654\"---IPsec快速模式协商失败', 68, 1);
INSERT INTO `security_event_library` VALUES (294, 'win-security', 60211, 2, 'Window审计成功事件&&win.system.eventID匹配到\"4983\"|\"4984\"---IPsec扩展模式协商失败', 68, 1);
INSERT INTO `security_event_library` VALUES (295, 'win-security', 60219, 2, 'Window审计成功事件&&win.system.eventID匹配到\"5453\"---由于 IKE 和 AuthIP IPsec 密钥模块 (IKEEXT) 服务未启动，与远程计算机的 IPsec 协商失败', 68, 1);
INSERT INTO `security_event_library` VALUES (296, 'win-security', 60220, 2, 'Window审计失败事件&&win.system.eventID匹配到\"5480\"---IPsec 服务无法获取计算机上网络接口的完整列表', 68, 1);
INSERT INTO `security_event_library` VALUES (297, 'win-security', 60221, 2, 'Window审计失败事件&&win.system.eventID匹配到\"5483\"---IPsec 服务无法初始化 RPC 服务器。 IPsec 服务无法启动', 68, 1);
INSERT INTO `security_event_library` VALUES (298, 'win-security', 60222, 2, 'Window审计失败事件&&win.system.eventID匹配到\"5484\"---IPsec 服务遇到严重故障并已关闭', 68, 1);
INSERT INTO `security_event_library` VALUES (299, 'win-security', 60223, 2, 'Window审计失败事件&&win.system.eventID匹配到\"5485\"---IPsec 服务无法处理网络接口即插即用事件上的某些 IPsec 筛选器', 68, 1);
INSERT INTO `security_event_library` VALUES (300, 'win-security', 60225, 2, 'Window审计失败事件&&win.system.eventID匹配到\"4712\"---IPsec 服务遇到潜在的严重故障', 68, 1);
INSERT INTO `security_event_library` VALUES (301, 'local,systemd', 40702, 4, '匹配到Failed to get unit file state for，表示获取Systemd服务单位状态失败，这意味着缺少.service文件', 68, 1);
INSERT INTO `security_event_library` VALUES (302, 'local,systemd', 40703, 3, '匹配到entered failed state，表示Systemd服务进入失败状态，并且可能还没有启动', 68, 1);
INSERT INTO `security_event_library` VALUES (303, 'local,systemd', 40704, 3, '匹配到status=1/FAILURE，表示Systemd服务退出失败', 70, 1);
INSERT INTO `security_event_library` VALUES (304, 'local,systemd', 40705, 3, '匹配到Time has been changed$，表示Systemd系统时间被修改', 70, 1);
INSERT INTO `security_event_library` VALUES (305, 'syslog,smbd', 13101, 4, '匹配到getpeername failed. Error was Transport endpoint，表示Samba网络问题', 69, 1);
INSERT INTO `security_event_library` VALUES (306, 'syslog,smbd', 13102, 3, '匹配到Denied connection from|Connection denied from，表示Samba连接被拒绝', 68, 1);
INSERT INTO `security_event_library` VALUES (307, 'syslog,smbd', 13103, 4, '匹配到Connection reset by peer，表示Samba网络问题', 69, 1);
INSERT INTO `security_event_library` VALUES (308, 'syslog,smbd', 13104, 3, '匹配到Permission denied--，表示Samba用户操作被配置拒绝', 68, 1);
INSERT INTO `security_event_library` VALUES (309, 'syslog,smbd', 13105, 4, '匹配到Unable to connect to CUPS server，表示Samba网络问题(无法连接)', 69, 1);
INSERT INTO `security_event_library` VALUES (310, 'syslog,smbd', 13108, 4, '匹配到smbd is already running，表示已经尝试启动smbd，但进程已经在运行', 70, 1);
INSERT INTO `security_event_library` VALUES (311, 'syslog,smbd', 13109, 4, '匹配到nmbd is already running，表示已经尝试启动nmbd，但进程已经运行', 70, 1);
INSERT INTO `security_event_library` VALUES (312, 'syslog,smbd', 13110, 4, '匹配到Connection denied from，表示Samba连接被拒绝', 68, 1);
INSERT INTO `security_event_library` VALUES (313, 'syslog,smbd', 13111, 4, '匹配到Socket is not connected，表示Samba Socket未连接，写入失败', 68, 1);
INSERT INTO `security_event_library` VALUES (314, 'win-application', 60677, 3, 'VSS事件&&win.system.eventID匹配到\"11\"|\"13\"---COM 服务器无法启动', 68, 1);
INSERT INTO `security_event_library` VALUES (315, 'win-application', 60678, 3, 'VSS事件&&win.system.eventID匹配到\"12\"---DeleteVolumeMountPoint 返回意外错误', 68, 1);
INSERT INTO `security_event_library` VALUES (316, 'win-application', 60679, 3, 'VSS事件&&win.system.eventID匹配到\"14\"---注册表用户名定义生成错误', 68, 1);
INSERT INTO `security_event_library` VALUES (317, 'win-application', 60680, 3, 'VSS事件&&win.system.eventID匹配到\"15\"|\"16\"|\"17\"---无法解释注册表中指定的用户名', 68, 1);
INSERT INTO `security_event_library` VALUES (318, 'win-application', 60683, 3, 'VSS事件&&win.system.eventID匹配到\"20\"|\"21\"---卷影复制服务：COM+ 数据库损坏，写入器将不会收到事件', 68, 1);
INSERT INTO `security_event_library` VALUES (319, 'win-application', 60687, 3, 'VSS事件&&win.system.eventID匹配到\"2004\"---卷影复制 6 超时', 68, 1);
INSERT INTO `security_event_library` VALUES (320, 'win-application', 60688, 3, 'VSS事件&&win.system.eventID匹配到\"4001\"---找不到用于创建卷影副本的 Diff 区域', 68, 1);
INSERT INTO `security_event_library` VALUES (321, 'win-application', 60689, 3, 'VSS事件&&win.system.eventID匹配到\"4609\"---在 EventSystem 服务的内部处理期间检测到错误的返回代码', 68, 1);
INSERT INTO `security_event_library` VALUES (322, 'win-application', 60691, 3, 'VSS事件&&win.system.eventID匹配到\"5013\"---EventLogs 调用的例程 BackupEventLogW 失败', 68, 1);
INSERT INTO `security_event_library` VALUES (323, 'win-application', 60692, 3, 'VSS事件&&win.system.eventID匹配到\"6004\"---Sqllib 错误：数据库不简单', 68, 1);
INSERT INTO `security_event_library` VALUES (324, 'win-application', 60693, 3, 'VSS事件&&win.system.eventID匹配到\"6005\"---SQL 卷影复制配置错误', 68, 1);
INSERT INTO `security_event_library` VALUES (325, 'win-application', 60694, 3, 'VSS事件&&win.system.eventID匹配到\"6008\"---Sqllib 错误：无法创建 VDS 对象', 68, 1);
INSERT INTO `security_event_library` VALUES (326, 'win-application', 60695, 3, 'VSS事件&&win.system.eventID匹配到\"6013\"---Sqllib 错误：调用 IDBInitialize 时遇到 OLEDB 错误', 68, 1);
INSERT INTO `security_event_library` VALUES (327, 'win-application', 60696, 3, 'VSS事件&&win.system.eventID匹配到\"7001\"---VssAdmin：无法创建卷影副本', 68, 1);
INSERT INTO `security_event_library` VALUES (328, 'win-application', 60697, 3, 'VSS事件&&win.system.eventID匹配到\"8193\"---调用例程时出现意外错误', 68, 1);
INSERT INTO `security_event_library` VALUES (329, 'win-application', 60698, 3, 'VSS事件&&win.system.eventID匹配到\"8194\"---查询 IVssWriterCallback 接口时出现意外错误', 68, 1);
INSERT INTO `security_event_library` VALUES (330, 'win-application', 60705, 3, 'VSS错误事件&&win.system.eventID匹配到\"12290\"---卷影复制服务警告：ESENT 错误', 68, 1);
INSERT INTO `security_event_library` VALUES (331, 'win-application', 60706, 3, 'VSS错误事件&&win.system.eventID匹配到\"12291\"---创建或使用 COM+ Writers 发布者接口时出错', 68, 1);
INSERT INTO `security_event_library` VALUES (332, 'win-application', 60707, 3, 'VSS错误事件&&win.system.eventID匹配到\"12292\"---使用 CLSID 创建卷影副本提供程序 COM 类时出错', 68, 1);
INSERT INTO `security_event_library` VALUES (333, 'win-application', 60708, 3, 'VSS错误事件&&win.system.eventID匹配到\"12293\"---调用卷影复制提供程序上的例程时出错', 68, 1);
INSERT INTO `security_event_library` VALUES (334, 'win-application', 60710, 3, 'VSS事件&&win.system.eventID匹配到\"12301\"---Writer 未响应 GatherWriterStatus 调用', 68, 1);
INSERT INTO `security_event_library` VALUES (335, 'win-application', 60711, 3, 'VSS事件&&win.system.eventID匹配到\"12302\"---尝试联系卷影复制服务写入程序时检测到内部不一致', 68, 1);
INSERT INTO `security_event_library` VALUES (336, 'win-application', 60713, 3, 'VSS事件&&win.system.eventID匹配到\"12310\"---无法提交卷影副本', 68, 1);
INSERT INTO `security_event_library` VALUES (337, 'win-application', 60714, 3, 'VSS事件&&win.system.eventID匹配到\"12348\"---VSS 被拒绝访问根目录', 68, 1);
INSERT INTO `security_event_library` VALUES (338, 'win-application', 60717, 3, 'Windows系统恢复事件&&win.system.eventID匹配到\"8193\"---创建还原点失败', 68, 1);
INSERT INTO `security_event_library` VALUES (339, 'win-application', 60724, 3, 'Windows应用程序错误事件&&win.system.eventID匹配到\"100\"|\"1000\"|\"1001\"|\"1004\"---应用程序出现故障', 68, 1);
INSERT INTO `security_event_library` VALUES (340, 'win-application', 60725, 3, 'Windows应用程序错误事件&&win.system.eventID匹配到\"1010\"---故障桶报告', 68, 1);
INSERT INTO `security_event_library` VALUES (341, 'win-application', 60727, 3, 'WMI事件&&win.system.eventID匹配到\"10\"---无法激活事件过滤器', 68, 1);
INSERT INTO `security_event_library` VALUES (342, 'win-application', 60728, 3, 'WMI错误事件&&win.system.eventID匹配到\"29\"---WMI 服务无法初始化', 68, 1);
INSERT INTO `security_event_library` VALUES (343, 'win-application', 60729, 3, 'WMI事件&&win.system.eventID匹配到\"43\"---WMI ADAP 无法连接到命名空间', 68, 1);
INSERT INTO `security_event_library` VALUES (344, 'win-application', 60730, 3, 'WMI事件&&win.system.eventID匹配到\"5611\"---检测到不一致的系统关闭', 68, 1);
INSERT INTO `security_event_library` VALUES (345, 'win-application', 60731, 3, 'WMI事件&&win.system.eventID匹配到\"5601\"---WMI 服务无法加载存储库文件', 68, 1);
INSERT INTO `security_event_library` VALUES (346, 'win-application', 60733, 3, 'WMI事件&&win.system.eventID匹配到\"4\"---尝试加载 MOF 时遇到错误', 68, 1);
INSERT INTO `security_event_library` VALUES (347, 'win-application', 60734, 3, 'WMI事件&&win.system.eventID匹配到\"21\"---事件提供程序尝试注册语法无效的查询', 68, 1);
INSERT INTO `security_event_library` VALUES (348, 'win-application', 60735, 3, 'WMI事件&&win.system.eventID匹配到\"22\"---事件提供者尝试注册内部事件查询', 68, 1);
INSERT INTO `security_event_library` VALUES (349, 'win-application', 60736, 3, 'WMI事件&&win.system.eventID匹配到\"23\"|\"24\"|\"25\"---事件提供者尝试注册查询', 68, 1);
INSERT INTO `security_event_library` VALUES (350, 'win-application', 60737, 3, 'WMI事件&&win.system.eventID匹配到\"43\"---WMI ADAP 无法连接到命名空间', 68, 1);
INSERT INTO `security_event_library` VALUES (351, 'win-application', 60738, 3, 'WMI事件&&win.system.eventID匹配到\"48\"---WMI ADAP 无法保存对象', 68, 1);
INSERT INTO `security_event_library` VALUES (352, 'win-application', 60739, 3, 'WMI事件&&win.system.eventID匹配到\"58\"|\"59\"---WMI ADAP 无法创建 Win32_Perf 基类', 68, 1);
INSERT INTO `security_event_library` VALUES (353, 'win-application', 60741, 3, 'WMI事件&&win.system.eventID匹配到\"5600\"---WMI 服务检测到与 WMI 存储库不一致', 68, 1);
INSERT INTO `security_event_library` VALUES (354, 'win-application', 60742, 3, 'WMI事件&&win.system.eventID匹配到\"5601\"---WMI 服务无法加载存储库文件', 68, 1);
INSERT INTO `security_event_library` VALUES (355, 'win-application', 60743, 3, 'WMI事件&&win.system.eventID匹配到\"5605\"---访问命名空间被拒绝', 68, 1);
INSERT INTO `security_event_library` VALUES (356, 'win-application', 60744, 3, 'WMI事件&&win.system.eventID匹配到\"5606\"---WMI 服务无法为命名空间异步传送结果', 68, 1);
INSERT INTO `security_event_library` VALUES (357, 'win-application', 60746, 3, 'WMI事件&&win.system.eventID匹配到\"5614\"---WMI 服务在服务启动期间无法找到存储库文件', 68, 1);
INSERT INTO `security_event_library` VALUES (358, 'win-application', 60751, 3, 'EventSystem事件&&win.system.eventID匹配到\"4354\"|\"4356\"---EventSystem为订阅者创建实例失败', 68, 1);
INSERT INTO `security_event_library` VALUES (359, 'win-application', 60752, 3, 'EventSystem事件&&win.system.eventID匹配到\"4357\"---无法触发 EventObjectChange 事件，查询条件包含错误', 68, 1);
INSERT INTO `security_event_library` VALUES (360, 'win-application', 60753, 3, 'EventSystem事件&&win.system.eventID匹配到\"4358\"---COM+ 事件系统无法触发事件对象更改事件', 68, 1);
INSERT INTO `security_event_library` VALUES (361, 'win-application', 60754, 3, 'EventSystem事件&&win.system.eventID匹配到\"4359\"---无法加载 EventClass 中指定的类型库', 68, 1);
INSERT INTO `security_event_library` VALUES (362, 'win-application', 60755, 3, 'EventSystem事件&&win.system.eventID匹配到\"4361\"---COM+ 事件系统检测到损坏的 IEventClass 对象', 68, 1);
INSERT INTO `security_event_library` VALUES (363, 'win-application', 60756, 3, 'EventSystem事件&&win.system.eventID匹配到\"4362\"---COM+ 事件系统检测到损坏的 IEventSubscription对象', 68, 1);
INSERT INTO `security_event_library` VALUES (364, 'win-application', 60757, 3, 'EventSystem事件&&win.system.eventID匹配到\"4609\"|\"4610\"---COM+ 事件系统在其内部处理期间检测到错误的返回代码', 68, 1);
INSERT INTO `security_event_library` VALUES (365, 'win-application', 60758, 3, 'EventSystem事件&&win.system.eventID匹配到\"4611\"---COM+ 事件系统在其内部处理期间检测到意外的空指针', 68, 1);
INSERT INTO `security_event_library` VALUES (366, 'win-application', 60759, 2, 'EventSystem事件&&win.system.eventID匹配到\"4612\"---COM+ 事件系统在其内部处理期间内存不足', 68, 1);
INSERT INTO `security_event_library` VALUES (367, 'win-application', 60760, 3, 'EventSystem事件&&win.system.eventID匹配到\"4613\"---COM+ 事件系统检测到 Win32 API 调用出现意外错误', 68, 1);
INSERT INTO `security_event_library` VALUES (368, 'win-application', 60761, 3, 'EventSystem事件&&win.system.eventID匹配到\"4614\"---COM+ 事件系统检测到其内部状态不一致', 68, 1);
INSERT INTO `security_event_library` VALUES (369, 'win-application', 60762, 3, 'EventSystem事件&&win.system.eventID匹配到\"4615\"---COM+ 事件系统捕获异常', 68, 1);
INSERT INTO `security_event_library` VALUES (370, 'win-application', 60763, 3, 'EventSystem事件&&win.system.eventID匹配到\"4615\"---COM+ 事件系统捕获访问冲突', 68, 1);
INSERT INTO `security_event_library` VALUES (371, 'win-application', 60764, 3, 'EventSystem事件&&win.system.eventID匹配到\"4619\"---COM+ 事件系统无法存储每用户订阅', 68, 1);
INSERT INTO `security_event_library` VALUES (372, 'win-application', 60765, 3, 'EventSystem事件&&win.system.eventID匹配到\"4620\"---COM+ 事件系统在尝试查询对象时检测到错误，因为条件字符串包含错误', 68, 1);
INSERT INTO `security_event_library` VALUES (373, 'win-application', 60766, 3, 'EventSystem事件&&win.system.eventID匹配到\"4621\"---COM+ 事件系统无法删除对象', 68, 1);
INSERT INTO `security_event_library` VALUES (374, 'win-application', 60767, 3, 'EventSystem事件&&win.system.eventID匹配到\"4622\"---COM+ 事件系统无法编组订阅者进行订阅', 68, 1);
INSERT INTO `security_event_library` VALUES (375, 'win-application', 60768, 3, 'EventSystem事件&&win.system.eventID匹配到\"4623\"---COM+ 事件系统无法创建 MultiInterfacePublisherFilter 的实例', 68, 1);
INSERT INTO `security_event_library` VALUES (376, 'win-application', 60769, 3, 'EventSystem事件&&win.system.eventID匹配到\"4624\"---COM+ 事件系统无法将筛选条件应用于订阅，因为条件字符串包含错误', 68, 1);
INSERT INTO `security_event_library` VALUES (377, 'win-application', 60771, 3, 'Windows桌面窗口管理事件&&win.system.eventID匹配到\"9002\"---无法启动桌面窗口管理器', 68, 1);
INSERT INTO `security_event_library` VALUES (378, 'win-application', 60772, 3, 'Windows桌面窗口管理事件&&win.system.eventID匹配到\"9003\"---由于未使用复合主题，无法启动桌面窗口管理器', 68, 1);
INSERT INTO `security_event_library` VALUES (379, 'win-application', 60773, 3, 'Windows桌面窗口管理事件&&win.system.eventID匹配到\"9009\"---桌面窗口管理器退出并显示代码xxx', 68, 1);
INSERT INTO `security_event_library` VALUES (380, 'win-application', 60775, 3, 'Winlogon事件&&win.system.eventID匹配到\"6000\"---无法处理通知事件', 68, 1);
INSERT INTO `security_event_library` VALUES (381, 'win-application', 60776, 3, 'Winlogon事件&&win.system.eventID匹配到\"6003\"---无法处理关键通知事件', 68, 1);
INSERT INTO `security_event_library` VALUES (382, 'win-application', 60778, 3, 'Winlogon事件&&win.system.eventID匹配到\"1002\"---shell意外停止', 68, 1);
INSERT INTO `security_event_library` VALUES (383, 'win-application', 60780, 3, 'Winlogon事件&&win.system.eventID匹配到\"4003\"---桌面切换失败', 68, 1);
INSERT INTO `security_event_library` VALUES (384, 'win-application', 60781, 3, 'Winlogon事件&&win.system.eventID匹配到\"4005\"|\"4006\"---Windows 登录进程意外终止', 68, 1);
INSERT INTO `security_event_library` VALUES (385, 'win-application', 60782, 3, 'Winlogon事件&&win.system.eventID匹配到\"4007\"---Windows 登录过程无法断开用户会话', 68, 1);
INSERT INTO `security_event_library` VALUES (386, 'win-application', 60784, 3, 'Winlogon事件&&win.system.eventID匹配到\"4102\"---Windows 许可证无效', 70, 1);
INSERT INTO `security_event_library` VALUES (387, 'win-application', 60785, 3, 'Winlogon事件&&win.system.eventID匹配到\"4103\"---许可证激活失败', 68, 1);
INSERT INTO `security_event_library` VALUES (388, 'win-application', 60786, 3, 'Winlogon事件&&win.system.eventID匹配到\"6001\"---winlogon 通知订阅者通知事件失败', 68, 1);
INSERT INTO `security_event_library` VALUES (389, 'win-application', 60787, 3, 'Winlogon事件&&win.system.eventID匹配到\"6002\"---无法加载 Winlogon 通知订户注册', 68, 1);
INSERT INTO `security_event_library` VALUES (390, 'win-application', 60788, 2, 'Winlogon事件&&win.system.eventID匹配到\"6004\"---winlogon 通知订阅者未能完成关键通知事件', 68, 1);
INSERT INTO `security_event_library` VALUES (391, 'win-application', 60789, 3, 'Winlogon事件&&win.system.eventID匹配到\"4004\"---Windows 登录进程无法终止当前登录的用户进程', 68, 1);
INSERT INTO `security_event_library` VALUES (392, 'win-application', 60792, 3, '用户配置事件&&win.system.eventID匹配到\"1508\"---无法加载注册表', 68, 1);
INSERT INTO `security_event_library` VALUES (393, 'win-application', 60799, 3, 'ESENT事件&&win.system.eventID匹配到\"0\"---意外的 Win32 错误', 68, 1);
INSERT INTO `security_event_library` VALUES (394, 'win-application', 60800, 3, 'ESENT事件&&win.system.eventID匹配到\"215\"---备份被客户端中断或连接失败', 68, 1);
INSERT INTO `security_event_library` VALUES (395, 'win-application', 60801, 3, 'ESENT事件&&win.system.eventID匹配到\"404\"|\"405\"---同步读取页校验和错误', 68, 1);
INSERT INTO `security_event_library` VALUES (396, 'win-application', 60802, 3, 'ESENT事件&&win.system.eventID匹配到\"412\"---无法读取日志头', 68, 1);
INSERT INTO `security_event_library` VALUES (397, 'win-application', 60810, 3, 'ESENT事件&&win.system.eventID匹配到\"411\"---日志版本标记与数据库引擎版本标记不匹配', 68, 1);
INSERT INTO `security_event_library` VALUES (398, 'win-application', 60811, 3, 'ESENT事件&&win.system.eventID匹配到\"427\"---数据库引擎无法访问文件', 68, 1);
INSERT INTO `security_event_library` VALUES (399, 'win-application', 60812, 3, 'ESENT事件&&win.system.eventID匹配到\"439\"---无法写入隐藏头文件', 68, 1);
INSERT INTO `security_event_library` VALUES (400, 'win-application', 60813, 3, 'ESENT事件&&win.system.eventID匹配到\"454\"---无法读取日志头', 68, 1);
INSERT INTO `security_event_library` VALUES (401, 'win-application', 60814, 3, 'ESENT事件&&win.system.eventID匹配到\"474\"---由于页面校验和不匹配，数据库页面读取验证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (402, 'win-application', 60815, 3, 'ESENT事件&&win.system.eventID匹配到\"476\"---数据库页面读取验证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (403, 'win-application', 60816, 3, 'ESENT事件&&win.system.eventID匹配到\"489\"---尝试打开文件进行只读访问失败，出现系统错误', 68, 1);
INSERT INTO `security_event_library` VALUES (404, 'win-application', 60818, 3, 'ESENT事件&&win.system.eventID匹配到\"614\"---表 ConfigTable 的二级索引 WReplicaSetNameIndex 可能已损坏', 68, 1);
INSERT INTO `security_event_library` VALUES (405, 'win-application', 60822, 3, 'ESENT事件&&win.system.eventID匹配到\"12290\"---卷影复制服务警告：无法执行备份', 68, 1);
INSERT INTO `security_event_library` VALUES (406, 'win-application', 60825, 4, 'CAPI2事件&&win.system.eventID匹配到\"3\"|\"5\"|\"8\"---第三方根目录自动更新检索失败', 68, 1);
INSERT INTO `security_event_library` VALUES (407, 'win-application', 60826, 3, 'CAPI2事件&&win.system.eventID匹配到\"11\"|\"4107\"---从自动更新 cab 中提取第三方根列表失败', 68, 1);
INSERT INTO `security_event_library` VALUES (408, 'win-application', 60827, 3, 'CAPI2事件&&win.system.eventID匹配到\"513\"---处理 Onidentify 调用时加密服务失败', 68, 1);
INSERT INTO `security_event_library` VALUES (409, 'win-application', 60831, 3, 'CAPI2事件&&win.system.eventID匹配到\"6\"---已达到 crypt32 事件阈值', 68, 1);
INSERT INTO `security_event_library` VALUES (410, 'win-application', 60835, 3, 'CAPI2事件&&win.system.eventID匹配到\"256\"---加密服务无法初始化目录数据库', 68, 1);
INSERT INTO `security_event_library` VALUES (411, 'win-application', 60836, 3, 'CAPI2事件&&win.system.eventID匹配到\"257\"---加密服务无法初始化目录数据库，ESENT 错误', 68, 1);
INSERT INTO `security_event_library` VALUES (412, 'win-application', 60837, 3, 'CAPI2事件&&win.system.eventID匹配到\"512\"---加密服务无法初始化 VSS 备份“System Writer”对象', 68, 1);
INSERT INTO `security_event_library` VALUES (413, 'win-application', 60839, 3, 'MSDTC事件&&win.system.eventID匹配到\"4098\"---无法将 MSDTC 错误代码转换为相应的 MSDTC 错误消息', 68, 1);
INSERT INTO `security_event_library` VALUES (414, 'win-application', 60840, 3, 'MSDTC事件&&win.system.eventID匹配到\"4099\"---管理此系统上的 MS DTC 的权限被拒绝', 68, 1);
INSERT INTO `security_event_library` VALUES (415, 'win-application', 60841, 3, 'MSDTC事件&&win.system.eventID匹配到\"4100\"---处理来自服务控制管理器的控制请求时发生异常', 68, 1);
INSERT INTO `security_event_library` VALUES (416, 'win-application', 60843, 3, 'MSDTC事件&&win.system.eventID匹配到\"4102\"---MS DTC 事务管理器无法初始化', 68, 1);
INSERT INTO `security_event_library` VALUES (417, 'win-application', 60844, 2, 'MSDTC事件&&win.system.eventID匹配到\"4103\"---MS DTC 内存不足', 68, 1);
INSERT INTO `security_event_library` VALUES (418, 'win-application', 60845, 3, 'MSDTC事件&&win.system.eventID匹配到\"4102\"---MS DTC 事务管理器无法初始化', 68, 1);
INSERT INTO `security_event_library` VALUES (419, 'win-application', 60847, 3, 'MSDTC事件&&win.system.eventID匹配到\"4106\"---无法安装 MS DTC 服务', 68, 1);
INSERT INTO `security_event_library` VALUES (420, 'win-application', 60848, 3, 'MSDTC事件&&win.system.eventID匹配到\"4107\"---无法删除 MS DTC 服务', 68, 1);
INSERT INTO `security_event_library` VALUES (421, 'win-application', 60849, 3, 'MSDTC事件&&win.system.eventID匹配到\"4113\"---未找到 MS DTC 日志路径', 68, 1);
INSERT INTO `security_event_library` VALUES (422, 'win-application', 60850, 3, 'MSDTC事件&&win.system.eventID匹配到\"4114\"---无法加载 MS DTC 事务管理器', 68, 1);
INSERT INTO `security_event_library` VALUES (423, 'win-application', 60851, 3, 'MSDTC事件&&win.system.eventID匹配到\"4116\"---无法创建 MS DTC 名称对象', 68, 1);
INSERT INTO `security_event_library` VALUES (424, 'win-application', 60852, 3, 'MSDTC事件&&win.system.eventID匹配到\"4117\"---MS DTC 用户界面服务器对象无法初始化', 68, 1);
INSERT INTO `security_event_library` VALUES (425, 'win-application', 60853, 3, 'MSDTC事件&&win.system.eventID匹配到\"4118\"---MS DTC 用户界面服务器对象无法加载', 68, 1);
INSERT INTO `security_event_library` VALUES (426, 'win-application', 60854, 3, 'MSDTC事件&&win.system.eventID匹配到\"4119\"---MS DTC 用户界面服务器对象不支持请求的接口', 68, 1);
INSERT INTO `security_event_library` VALUES (427, 'win-application', 60855, 3, 'MSDTC事件&&win.system.eventID匹配到\"4120\"---无法加载 MS DTC 用户界面名称对象', 68, 1);
INSERT INTO `security_event_library` VALUES (428, 'win-application', 60856, 3, 'MSDTC事件&&win.system.eventID匹配到\"4121\"---无法加载 MS DTC 日志管理器 DLL', 68, 1);
INSERT INTO `security_event_library` VALUES (429, 'win-application', 60857, 3, 'MSDTC事件&&win.system.eventID匹配到\"4124\"---MS DTC 日志文件无法解压缩', 68, 1);
INSERT INTO `security_event_library` VALUES (430, 'win-application', 60858, 3, 'MSDTC事件&&win.system.eventID匹配到\"4125\"---MS DTC 日志文件已满，无法接受新的日志记录', 68, 1);
INSERT INTO `security_event_library` VALUES (431, 'win-application', 60859, 3, 'MSDTC事件&&win.system.eventID匹配到\"4126\"---MS DTC 日志文件属性无效', 68, 1);
INSERT INTO `security_event_library` VALUES (432, 'win-application', 60860, 3, 'MSDTC事件&&win.system.eventID匹配到\"4127\"---MS DTC 日志文件已换行', 68, 1);
INSERT INTO `security_event_library` VALUES (433, 'win-application', 60862, 3, 'MSDTC事件&&win.system.eventID匹配到\"4137\"---无法强制事务结果', 68, 1);
INSERT INTO `security_event_library` VALUES (434, 'win-application', 60863, 3, 'MSDTC事件&&win.system.eventID匹配到\"4138\"---指定的事务桥 CLSID 不是有效的 GUID', 68, 1);
INSERT INTO `security_event_library` VALUES (435, 'win-application', 60864, 3, 'MSDTC事件&&win.system.eventID匹配到\"4137\"---无法强制事务结果', 68, 1);
INSERT INTO `security_event_library` VALUES (436, 'win-application', 60865, 3, 'MSDTC事件&&win.system.eventID匹配到\"4139\"---创建事务桥时发生错误', 68, 1);
INSERT INTO `security_event_library` VALUES (437, 'win-application', 60866, 3, 'MSDTC事件&&win.system.eventID匹配到\"4140\"---初始化事务桥时发生错误', 68, 1);
INSERT INTO `security_event_library` VALUES (438, 'win-application', 60867, 3, 'MSDTC事件&&win.system.eventID匹配到\"4141\"---启动事务桥时发生错误', 68, 1);
INSERT INTO `security_event_library` VALUES (439, 'win-application', 60870, 3, 'MSDTC事件&&win.system.eventID匹配到\"4149\"---事务未找到', 68, 1);
INSERT INTO `security_event_library` VALUES (440, 'win-application', 60871, 3, 'MSDTC事件&&win.system.eventID匹配到\"4150\"---该事务不是子事务', 68, 1);
INSERT INTO `security_event_library` VALUES (441, 'win-application', 60872, 4, 'MSDTC事件&&win.system.eventID匹配到\"4151\"---MSDTC TM 无法确定传入的连接请求是否来自远程计算机', 68, 1);
INSERT INTO `security_event_library` VALUES (442, 'win-application', 60873, 3, 'MSDTC事件&&win.system.eventID匹配到\"4152\"---事务未提交，或者资源管理器或从属 MS DTC 事务管理器仍处于连接状态', 68, 1);
INSERT INTO `security_event_library` VALUES (443, 'win-application', 60874, 3, 'MSDTC事件&&win.system.eventID匹配到\"4153\"---由于未知错误而无法解决事务', 68, 1);
INSERT INTO `security_event_library` VALUES (444, 'win-application', 60875, 3, 'MSDTC事件&&win.system.eventID匹配到\"4154\"---MS DTC 日志文件不可读', 68, 1);
INSERT INTO `security_event_library` VALUES (445, 'win-application', 60876, 3, 'MSDTC事件&&win.system.eventID匹配到\"4155\"---MS DTC 遇到内部错误并正在终止', 68, 1);
INSERT INTO `security_event_library` VALUES (446, 'win-application', 60877, 3, 'MSDTC事件&&win.system.eventID匹配到\"4157\"---无法停止 MS DTC 服务或其依赖服务之一', 68, 1);
INSERT INTO `security_event_library` VALUES (447, 'win-application', 60878, 3, 'MSDTC事件&&win.system.eventID匹配到\"4160\"---无效的命令行参数', 68, 1);
INSERT INTO `security_event_library` VALUES (448, 'win-application', 60879, 3, 'MSDTC事件&&win.system.eventID匹配到\"4162\"---MS DTC 日志文件是不兼容的版本', 68, 1);
INSERT INTO `security_event_library` VALUES (449, 'win-application', 60880, 3, 'MSDTC事件&&win.system.eventID匹配到\"4165\"---未选择事务', 68, 1);
INSERT INTO `security_event_library` VALUES (450, 'win-application', 60881, 3, 'MSDTC事件&&win.system.eventID匹配到\"4166\"---超过 MS DTC 日志文件可容纳的最大活动事务数', 68, 1);
INSERT INTO `security_event_library` VALUES (451, 'win-application', 60882, 3, 'MSDTC事件&&win.system.eventID匹配到\"4169\"|\"4170\"---MS DTC 事务管理器处于不一致状态，无法继续', 68, 1);
INSERT INTO `security_event_library` VALUES (452, 'win-application', 60883, 3, 'MSDTC事件&&win.system.eventID匹配到\"4171\"---MS DTC 事务管理器日志写入失败', 68, 1);
INSERT INTO `security_event_library` VALUES (453, 'win-application', 60884, 3, 'MSDTC事件&&win.system.eventID匹配到\"4172\"|\"4173\"|\"4174\"|\"4175\"|\"4176\"|\"4177\"|\"4178\"|\"4179\"|\"4180\"|\"4181\"|\"4182\"|\"4181\"|\"4182\"|\"4183\"|\"4184\"|\"4185\"|\"4186\"|\"4187\"|\"4188\"|\"4189\"|\"4190\"|\"4191\"|\"4192\"---MS DTC 事务管理器启动失败', 68, 1);
INSERT INTO `security_event_library` VALUES (454, 'win-application', 60886, 3, 'MSDTC事件&&win.system.eventID匹配到\"4194\"---无法创建 MS DTC XA 事务管理器对象', 68, 1);
INSERT INTO `security_event_library` VALUES (455, 'win-application', 60887, 3, 'MSDTC事件&&win.system.eventID匹配到\"4196\"---无法启动MS DTC XA 事务管理器', 68, 1);
INSERT INTO `security_event_library` VALUES (456, 'win-application', 60888, 3, 'MSDTC事件&&win.system.eventID匹配到\"4198\"---无法找到 MS DTC TIP 网关联系对象', 68, 1);
INSERT INTO `security_event_library` VALUES (457, 'win-application', 60889, 3, 'MSDTC事件&&win.system.eventID匹配到\"4199\"---无法创建 MS DTC TIP 网关初始化对象', 68, 1);
INSERT INTO `security_event_library` VALUES (458, 'win-application', 60890, 3, 'MSDTC事件&&win.system.eventID匹配到\"4200\"---MS DTC TIP 网关初始化失败', 68, 1);
INSERT INTO `security_event_library` VALUES (459, 'win-application', 60891, 3, 'MSDTC事件&&win.system.eventID匹配到\"4201\"---MS DTC TIP 网关启动失败', 68, 1);
INSERT INTO `security_event_library` VALUES (460, 'win-application', 60892, 3, 'MSDTC事件&&win.system.eventID匹配到\"4208\"---尝试启动 MS DTC 时无法初始化 COM', 68, 1);
INSERT INTO `security_event_library` VALUES (461, 'win-application', 60893, 2, 'MSDTC事件&&win.system.eventID匹配到\"4209\"---MS DTC 组件中发生严重错误，进程终止', 68, 1);
INSERT INTO `security_event_library` VALUES (462, 'win-application', 60894, 3, 'MSDTC事件&&win.system.eventID匹配到\"4211\"---无法将调用者提供的 TIP 事务 URL 转换为有效的 MS DTC 事务 ID', 68, 1);
INSERT INTO `security_event_library` VALUES (463, 'win-application', 60895, 3, 'MSDTC事件&&win.system.eventID匹配到\"4212\"|\"4213\"---无法获取 OLE 事务代理的文件名', 68, 1);
INSERT INTO `security_event_library` VALUES (464, 'win-application', 60896, 3, 'MSDTC事件&&win.system.eventID匹配到\"4214\"---OLE 事务代理 DLL 中缺少 DllGetTransactionManagerCore 函数', 68, 1);
INSERT INTO `security_event_library` VALUES (465, 'win-application', 60897, 3, 'MSDTC事件&&win.system.eventID匹配到\"4215\"---无法连接到远程 MS DTC 事务管理器系统上的注册表', 68, 1);
INSERT INTO `security_event_library` VALUES (466, 'win-application', 60898, 3, 'MSDTC事件&&win.system.eventID匹配到\"4216\"---无法打开包含事务管理器列表的注册表项', 68, 1);
INSERT INTO `security_event_library` VALUES (467, 'win-application', 60899, 3, 'MSDTC事件&&win.system.eventID匹配到\"4217\"---无法从注册表读取默认事务管理器的名称', 68, 1);
INSERT INTO `security_event_library` VALUES (468, 'win-application', 60900, 3, 'MSDTC事件&&win.system.eventID匹配到\"4224\"---无法打开包含 OLE 事务代理 DLL 名称的注册表项', 68, 1);
INSERT INTO `security_event_library` VALUES (469, 'win-application', 60901, 3, 'MSDTC事件&&win.system.eventID匹配到\"4225\"---无法从注册表读取 OLE 事务代理 DLL 的名称', 68, 1);
INSERT INTO `security_event_library` VALUES (470, 'win-application', 60902, 3, 'MSDTC事件&&win.system.eventID匹配到\"4226\"---无法通过 MS DTC 代理 DLL 启动 MS DTC', 68, 1);
INSERT INTO `security_event_library` VALUES (471, 'win-application', 60903, 3, 'MSDTC事件&&win.system.eventID匹配到\"4227\"---由于群集支持初始化失败，无法启动 MS DTC', 68, 1);
INSERT INTO `security_event_library` VALUES (472, 'win-application', 60904, 2, 'MSDTC事件&&win.system.eventID匹配到\"4229\"---MS DTC TIP 网关非法转换', 68, 1);
INSERT INTO `security_event_library` VALUES (473, 'win-application', 60905, 3, 'MSDTC事件&&win.system.eventID匹配到\"4230\"---MS DTC TIP 网关值无效', 68, 1);
INSERT INTO `security_event_library` VALUES (474, 'win-application', 60906, 3, 'MSDTC事件&&win.system.eventID匹配到\"4231\"|\"4232\"---MS DTC TIP 网关调用 CoCreateInstance 时遇到错误', 68, 1);
INSERT INTO `security_event_library` VALUES (475, 'win-application', 60907, 2, 'MSDTC事件&&win.system.eventID匹配到\"4233\"---MS DTC 组件无法为关键操作分配内存', 68, 1);
INSERT INTO `security_event_library` VALUES (476, 'win-application', 60916, 3, 'MSDTC事件&&win.system.eventID匹配到\"4356\"---无法初始化 MS DTC 通信管理器', 68, 1);
INSERT INTO `security_event_library` VALUES (477, 'win-application', 60917, 3, 'MSDTC事件&&win.system.eventID匹配到\"4357\"---MS DTC 无法与远程系统上的 MS DTC 通信', 68, 1);
INSERT INTO `security_event_library` VALUES (478, 'win-application', 60918, 3, 'MSDTC事件&&win.system.eventID匹配到\"4358\"---MS DTC 连接管理器无法向 RPC 注册以使用 TCP/IP 或 UDP/IP', 68, 1);
INSERT INTO `security_event_library` VALUES (479, 'win-application', 60919, 3, 'MSDTC事件&&win.system.eventID匹配到\"4359\"---MS DTC 无法与远程系统上的 MS DTC 通信', 68, 1);
INSERT INTO `security_event_library` VALUES (480, 'win-application', 60920, 3, 'MSDTC事件&&win.system.eventID匹配到\"4360\"---MS DTC 无法分配控制台', 68, 1);
INSERT INTO `security_event_library` VALUES (481, 'win-application', 60921, 3, 'MSDTC事件&&win.system.eventID匹配到\"4361\"---检测到未完全初始化的 MS DTC 日志文件', 68, 1);
INSERT INTO `security_event_library` VALUES (482, 'win-application', 60922, 2, 'MSDTC事件&&win.system.eventID匹配到\"4362\"---非法转换导致致命错误', 68, 1);
INSERT INTO `security_event_library` VALUES (483, 'win-application', 60923, 2, 'MSDTC事件&&win.system.eventID匹配到\"4363\"---内存不足导致致命错误', 68, 1);
INSERT INTO `security_event_library` VALUES (484, 'win-application', 60924, 3, 'MSDTC事件&&win.system.eventID匹配到\"4367\"---同步对象发生意外事件', 68, 1);
INSERT INTO `security_event_library` VALUES (485, 'win-application', 60925, 3, 'MSDTC事件&&win.system.eventID匹配到\"4368\"---MS DTC XA 事务管理器中发生意外错误', 68, 1);
INSERT INTO `security_event_library` VALUES (486, 'win-application', 60926, 3, 'MSDTC事件&&win.system.eventID匹配到\"4370\"---MS DTC 无法绑定到 TIP socket', 68, 1);
INSERT INTO `security_event_library` VALUES (487, 'win-application', 60930, 3, 'MSDTC事件&&win.system.eventID匹配到\"4378\"---MS DTC 的现有群集资源配置不正确', 68, 1);
INSERT INTO `security_event_library` VALUES (488, 'win-application', 60933, 3, 'MSDTC事件&&win.system.eventID匹配到\"4381\"---MS DTC 无法确定本地注册表中与群集支持相关的数据版本', 68, 1);
INSERT INTO `security_event_library` VALUES (489, 'win-application', 60934, 3, 'MSDTC事件&&win.system.eventID匹配到\"4386\"---MS DTC 无法启动，因为它无法在群集安装后首次使用时初始化日志文件', 68, 1);
INSERT INTO `security_event_library` VALUES (490, 'win-application', 60935, 3, 'MSDTC事件&&win.system.eventID匹配到\"4399\"---无法建立连接以跟踪事务', 68, 1);
INSERT INTO `security_event_library` VALUES (491, 'win-application', 60936, 3, 'MSDTC事件&&win.system.eventID匹配到\"4400\"---未选择任何事务', 68, 1);
INSERT INTO `security_event_library` VALUES (492, 'win-application', 60937, 4, 'MSDTC事件&&win.system.eventID匹配到\"4401\"---未选择事务', 68, 1);
INSERT INTO `security_event_library` VALUES (493, 'win-application', 60939, 3, 'MSDTC事件&&win.system.eventID匹配到\"4403\"---尝试将事务转储到跟踪文件失败', 68, 1);
INSERT INTO `security_event_library` VALUES (494, 'win-application', 60940, 3, 'MSDTC事件&&win.system.eventID匹配到\"4404\"---跟踪基础设施初始化失败', 68, 1);
INSERT INTO `security_event_library` VALUES (495, 'win-application', 60941, 3, 'MSDTC事件&&win.system.eventID匹配到\"4405\"---尝试创建新的跟踪会话失败', 68, 1);
INSERT INTO `security_event_library` VALUES (496, 'win-application', 60942, 3, 'MSDTC事件&&win.system.eventID匹配到\"4406\"---尝试停止现有跟踪会话失败', 68, 1);
INSERT INTO `security_event_library` VALUES (497, 'win-application', 60943, 3, 'MSDTC事件&&win.system.eventID匹配到\"4407\"---尝试刷新现有跟踪数据失败', 68, 1);
INSERT INTO `security_event_library` VALUES (498, 'win-application', 60944, 3, 'MSDTC事件&&win.system.eventID匹配到\"4408\"---尝试更新现有跟踪会话的最大缓冲区计数失败', 68, 1);
INSERT INTO `security_event_library` VALUES (499, 'win-application', 60945, 4, 'MSDTC事件&&win.system.eventID匹配到\"4409\"---基础设施报告到目前为止丢失的跟踪事件数量等于xxx', 68, 1);
INSERT INTO `security_event_library` VALUES (500, 'win-application', 60946, 3, 'MSDTC事件&&win.system.eventID匹配到\"4410\"---启动更新跟踪设置的线程失败', 68, 1);
INSERT INTO `security_event_library` VALUES (501, 'win-application', 60947, 3, 'MSDTC事件&&win.system.eventID匹配到\"4411\"---尝试设置跟踪特定注册表设置失败', 68, 1);
INSERT INTO `security_event_library` VALUES (502, 'win-application', 60948, 3, 'MSDTC事件&&win.system.eventID匹配到\"4412\"---无法创建新的跟踪会话', 68, 1);
INSERT INTO `security_event_library` VALUES (503, 'win-application', 60949, 3, 'MSDTC事件&&win.system.eventID匹配到\"4413\"---无法停止现有的跟踪会话', 68, 1);
INSERT INTO `security_event_library` VALUES (504, 'win-application', 60950, 3, 'MSDTC事件&&win.system.eventID匹配到\"4414\"---无法刷新现有跟踪数据', 68, 1);
INSERT INTO `security_event_library` VALUES (505, 'win-application', 60951, 3, 'MSDTC事件&&win.system.eventID匹配到\"4415\"---无法更新现有跟踪会话的最大缓冲区计数', 68, 1);
INSERT INTO `security_event_library` VALUES (506, 'win-application', 60952, 3, 'MSDTC事件&&win.system.eventID匹配到\"4416\"---无法更新注册表中的所有跟踪设置', 68, 1);
INSERT INTO `security_event_library` VALUES (507, 'win-application', 60953, 3, 'MSDTC事件&&win.system.eventID匹配到\"4417\"---无法从注册表加载所有跟踪设置', 68, 1);
INSERT INTO `security_event_library` VALUES (508, 'win-application', 60954, 3, 'MSDTC事件&&win.system.eventID匹配到\"4418\"---MS DTC 事务管理器启动失败', 68, 1);
INSERT INTO `security_event_library` VALUES (509, 'win-application', 60955, 3, 'MSDTC事件&&win.system.eventID匹配到\"4419\"---MS DTC 无法加载系统还原信息', 68, 1);
INSERT INTO `security_event_library` VALUES (510, 'win-application', 60956, 3, 'MSDTC事件&&win.system.eventID匹配到\"4420\"---MS DTC 无法处理系统还原事件', 68, 1);
INSERT INTO `security_event_library` VALUES (511, 'win-application', 60957, 3, 'MSDTC事件&&win.system.eventID匹配到\"4421\"---MS DTC 无法保存系统还原信息', 68, 1);
INSERT INTO `security_event_library` VALUES (512, 'win-application', 60958, 4, 'MSDTC事件&&win.system.eventID匹配到\"4422\"---MS DTC 已处理系统还原事件', 68, 1);
INSERT INTO `security_event_library` VALUES (513, 'win-application', 60959, 3, 'MSDTC事件&&win.system.eventID匹配到\"4425\"---基础设施报告称，由于缺乏足够的缓冲区空间，尝试跟踪事件失败', 70, 1);
INSERT INTO `security_event_library` VALUES (514, 'win-application', 60960, 3, 'MSDTC事件&&win.system.eventID匹配到\"4426\"---无法从注册表读取所需的名称对象', 68, 1);
INSERT INTO `security_event_library` VALUES (515, 'win-application', 60961, 3, 'MSDTC事件&&win.system.eventID匹配到\"4427\"---无法初始化所需的名称对象', 68, 1);
INSERT INTO `security_event_library` VALUES (516, 'win-application', 60962, 3, 'MSDTC事件&&win.system.eventID匹配到\"4428\"---无法停止 MSDTC 服务或其依赖服务之一', 68, 1);
INSERT INTO `security_event_library` VALUES (517, 'win-application', 60963, 3, 'MSDTC事件&&win.system.eventID匹配到\"4429\"---无法设置 MS DTC 服务的安全属性', 68, 1);
INSERT INTO `security_event_library` VALUES (518, 'win-application', 60964, 3, 'MSDTC事件&&win.system.eventID匹配到\"4431\"---停止 MS DTC 服务失败', 68, 1);
INSERT INTO `security_event_library` VALUES (519, 'win-application', 60965, 3, 'MSDTC事件&&win.system.eventID匹配到\"4432\"---重新启动 MS DTC 服务失败', 68, 1);
INSERT INTO `security_event_library` VALUES (520, 'win-application', 60966, 3, 'MSDTC事件&&win.system.eventID匹配到\"4433\"|\"4434\"|\"4435\"|\"4436\"---MS DTC 事务管理器处于不一致状态，无法继续', 68, 1);
INSERT INTO `security_event_library` VALUES (521, 'win-application', 60967, 3, 'MSDTC事件&&win.system.eventID匹配到\"4437\"---运行 MS DTC 的帐户无效', 68, 1);
INSERT INTO `security_event_library` VALUES (522, 'win-application', 60968, 3, 'MSDTC事件&&win.system.eventID匹配到\"4438\"---无法在注册表中设置 MS DTC 服务帐户信息', 68, 1);
INSERT INTO `security_event_library` VALUES (523, 'win-application', 60969, 3, 'MSDTC事件&&win.system.eventID匹配到\"4440\"---MS DTC 服务正在终止，因为它没有及时启动', 68, 1);
INSERT INTO `security_event_library` VALUES (524, 'win-application', 60970, 3, 'MSDTC事件&&win.system.eventID匹配到\"4441\"---MS DTC 组件遇到内部错误', 68, 1);
INSERT INTO `security_event_library` VALUES (525, 'win-application', 60973, 3, 'MSDTC事件&&win.system.eventID匹配到\"4444\"---XA事务管理器处于不一致状态，无法继续', 68, 1);
INSERT INTO `security_event_library` VALUES (526, 'win-application', 60976, 3, 'MSDTC事件&&win.system.eventID匹配到\"4448\"---KTMRM 服务无法加载系统恢复信息', 68, 1);
INSERT INTO `security_event_library` VALUES (527, 'win-application', 60977, 3, 'MSDTC事件&&win.system.eventID匹配到\"4452\"---尝试冻结事务管理器失败', 68, 1);
INSERT INTO `security_event_library` VALUES (528, 'win-application', 60978, 3, 'MSDTC事件&&win.system.eventID匹配到\"4453\"---尝试解冻事务管理器失败', 68, 1);
INSERT INTO `security_event_library` VALUES (529, 'win-application', 60979, 3, 'MSDTC事件&&win.system.eventID匹配到\"4454\"---无法准备 MSDTC 进行成像', 68, 1);
INSERT INTO `security_event_library` VALUES (530, 'win-application', 60980, 3, 'MSDTC事件&&win.system.eventID匹配到\"4455\"---连接到内核事务管理器时出现意外错误', 68, 1);
INSERT INTO `security_event_library` VALUES (531, 'win-application', 60981, 3, 'MSDTC事件&&win.system.eventID匹配到\"4456\"---无法创建到内核事务管理器的通信通道', 68, 1);
INSERT INTO `security_event_library` VALUES (532, 'win-application', 60983, 3, 'MSDTC事件&&win.system.eventID匹配到\"4458\"---MSDTC 在迁移联系人标识符期间遇到错误', 68, 1);
INSERT INTO `security_event_library` VALUES (533, 'win-application', 60984, 3, 'MSDTC事件&&win.system.eventID匹配到\"4459\"---MSDTC 在 Windows 升级期间遇到错误', 68, 1);
INSERT INTO `security_event_library` VALUES (534, 'win-application', 60985, 3, 'MSDTC事件&&win.system.eventID匹配到\"53252\"---已超出异步检查点的最大数量', 70, 1);
INSERT INTO `security_event_library` VALUES (535, 'win-application', 60986, 3, 'MSDTC事件&&win.system.eventID匹配到\"53253\"---已达到正在使用的日志缓冲区的最大数量', 70, 1);
INSERT INTO `security_event_library` VALUES (536, 'win-application', 60994, 3, 'MSDTC事件&&win.system.eventID匹配到\"53287\"---意外的MS DTC 事务结果', 70, 1);
INSERT INTO `security_event_library` VALUES (537, 'win-application', 60995, 3, 'MSDTC事件&&win.system.eventID匹配到\"53288\"---MS DTC XA 事务管理器无法执行恢复', 68, 1);
INSERT INTO `security_event_library` VALUES (538, 'win-application', 60996, 3, 'MSDTC事件&&win.system.eventID匹配到\"53294\"|\"53295\"|\"53296\"---启发式损坏信息', 68, 1);
INSERT INTO `security_event_library` VALUES (539, 'win-application', 61005, 3, 'MSDTC事件&&win.system.eventID匹配到\"53305\"---MS DTC XA 事务管理器无法代表 XA 资源管理器向 MS DTC 事务管理器注册', 68, 1);
INSERT INTO `security_event_library` VALUES (540, 'win-application', 61010, 4, 'MSDTC事件&&win.system.eventID匹配到\"53319\"---XaTmMinWarmRecoveryInterval 的值大于 XaTmMaxWarmRecoveryInterval 的值', 70, 1);
INSERT INTO `security_event_library` VALUES (541, 'win-application', 61011, 3, 'MSDTC事件&&win.system.eventID匹配到\"53320\"---在 MS DTC 安装期间，尝试保留旧日志文件失败', 68, 1);
INSERT INTO `security_event_library` VALUES (542, 'win-application', 61012, 3, 'MSDTC事件&&win.system.eventID匹配到\"53321\"---MS DTC 收到无效消息', 70, 1);
INSERT INTO `security_event_library` VALUES (543, 'win-application', 61013, 3, 'MSDTC事件&&win.system.eventID匹配到\"53322\"---MS DTC 组件中的连接管理器收到无效消息', 70, 1);
INSERT INTO `security_event_library` VALUES (544, 'win-application', 61014, 3, 'MSDTC事件&&win.system.eventID匹配到\"53323\"---MS DTC XA 事务管理器尝试解密日志记录中的信息失败', 68, 1);
INSERT INTO `security_event_library` VALUES (545, 'win-application', 61015, 3, 'MSDTC事件&&win.system.eventID匹配到\"53325\"---KtmRm 收到无效消息', 70, 1);
INSERT INTO `security_event_library` VALUES (546, 'win-application', 61017, 2, '.Net Runtime事件&&win.system.eventID匹配到\"1026\"---由于未处理的异常，进程被终止', 68, 1);
INSERT INTO `security_event_library` VALUES (547, 'win-application', 61018, 3, '.Net Runtime事件&&win.system.eventID匹配到\"0\"---无法找到源 .NET Runtime中事件 ID 0 的描述', 68, 1);
INSERT INTO `security_event_library` VALUES (548, 'win-application', 61019, 3, '.Net Runtime事件&&win.system.eventID匹配到\"1000\"---.NET Runtime加载失败', 68, 1);
INSERT INTO `security_event_library` VALUES (549, 'win-application', 61021, 2, '.Net Runtime事件&&win.system.eventID匹配到\"1023\"---.NET Runtime - 致命执行引擎错误', 68, 1);
INSERT INTO `security_event_library` VALUES (550, 'win-application', 61022, 3, 'Windows应用程序错误事件&&win.system.providerName匹配到\"Microsoft-Windows-LocationProvider\"&&win.system.eventID匹配到\"2006\"---Windows 位置提供程序数据库出现错误', 68, 1);
INSERT INTO `security_event_library` VALUES (551, 'win-application', 61027, 4, '应用程序管理事件&&win.system.eventID匹配到\"101\"---应用程序分配失败', 68, 1);
INSERT INTO `security_event_library` VALUES (552, 'win-application', 61028, 3, '应用程序管理事件&&win.system.eventID匹配到\"103\"---从策略中删除应用程序分配失败', 68, 1);
INSERT INTO `security_event_library` VALUES (553, 'win-application', 61030, 3, '应用程序管理事件&&win.system.eventID匹配到\"107\"---执行应用程序的安装程序失败', 68, 1);
INSERT INTO `security_event_library` VALUES (554, 'win-application', 61031, 3, '应用程序管理事件&&win.system.eventID匹配到\"108\"---应用对软件安装设置的更改失败', 68, 1);
INSERT INTO `security_event_library` VALUES (555, 'win-application', 61039, 3, '磁盘事件&&win.system.eventID匹配到\"10\"|\"11\"---驱动程序检测到控制器错误', 68, 1);
INSERT INTO `security_event_library` VALUES (556, 'win-application', 61042, 3, '磁盘事件&&win.system.eventID匹配到\"14\"---无法使用包含 \\Device\\MissingMirror1Member0 的 FT 集', 70, 1);
INSERT INTO `security_event_library` VALUES (557, 'win-application', 61044, 3, '磁盘事件&&win.system.eventID匹配到\"17\"---VDS 无法启动提供程序', 68, 1);
INSERT INTO `security_event_library` VALUES (558, 'win-application', 61045, 3, '磁盘事件&&win.system.eventID匹配到\"23\"---镜像初始化失败', 68, 1);
INSERT INTO `security_event_library` VALUES (559, 'win-application', 61046, 3, '磁盘事件&&win.system.eventID匹配到\"25\"---DCOM错误', 68, 1);
INSERT INTO `security_event_library` VALUES (560, 'win-application', 61055, 3, '磁盘事件&&win.system.eventID匹配到\"1\"|\"2\"|\"4\"---由于通信错误，无法在目标计算机上激活订阅', 69, 1);
INSERT INTO `security_event_library` VALUES (561, 'win-application', 61056, 3, '磁盘事件&&win.system.eventID匹配到\"3\"---订阅已过期，将不再提供服务', 68, 1);
INSERT INTO `security_event_library` VALUES (562, 'win-application', 61057, 3, '磁盘事件&&win.system.eventID匹配到\"6\"---订阅无法将事件从目标计算机发布到该订阅的日志文件', 68, 1);
INSERT INTO `security_event_library` VALUES (563, 'win-application', 61058, 3, '磁盘事件&&win.system.eventID匹配到\"501\"---来自目标机器的事件丢失且无法转发', 68, 1);
INSERT INTO `security_event_library` VALUES (564, 'win-application', 61059, 3, '磁盘事件&&win.system.eventID匹配到\"502\"---订阅检测到丢弃的事件', 68, 1);
INSERT INTO `security_event_library` VALUES (565, 'win-application', 61071, 3, 'Windows应用程序审核失败事件&&win.system.eventID匹配到\"18456\"---MS SQL 服务器登录失败', 68, 1);
INSERT INTO `security_event_library` VALUES (566, 'win-application', 60620, 3, '微软安装程序事件组&&win.system.eventID匹配到”1014“--未正确注册Windows安装程序代理信息', 70, 1);
INSERT INTO `security_event_library` VALUES (567, 'win-application', 60621, 3, '微软安装程序事件组&&win.system.eventID匹配到”1015“--window安装程序:无法连接到服务器', 68, 1);
INSERT INTO `security_event_library` VALUES (568, 'win-application', 60622, 3, '微软安装程序事件组&&win.system.eventID匹配到”1016“--产品功能组件检测失败', 68, 1);
INSERT INTO `security_event_library` VALUES (569, 'win-application', 60623, 3, '微软安装程序事件组&&win.system.eventID匹配到”1018“或\"1023\"--无法安装应用程序', 68, 1);
INSERT INTO `security_event_library` VALUES (570, 'win-application', 60625, 3, '微软安装程序事件组&&win.system.eventID匹配到”1020“或\"1021\"--无法删除更新', 70, 1);
INSERT INTO `security_event_library` VALUES (571, 'win-application', 60627, 3, '微软安装程序事件组&&win.system.eventID匹配到”1024“--无法安装更新', 68, 1);
INSERT INTO `security_event_library` VALUES (572, 'win-application', 60633, 3, '微软安装程序事件组&&win.system.eventID匹配到”1032“--安装期间更新的环境变量生成错误', 68, 1);
INSERT INTO `security_event_library` VALUES (573, 'win-application', 60645, 4, 'SPP事件组&&win.system.eventID匹配到\"8225\"--现有计划程序数据与预期数据不匹配', 70, 1);
INSERT INTO `security_event_library` VALUES (574, 'win-application', 60646, 3, 'Windows应用程序错误事件&&win.system.providerName匹配到\"Microsoft-Windows-Security-SPP\"&&win.system.eventID匹配到\"8198\"--许可证激活(slui.exe)失败', 68, 1);
INSERT INTO `security_event_library` VALUES (575, 'win-application', 60649, 3, 'Windows搜索事件组&&win.system.eventID匹配到\"3007\"--无法初始化性能监视', 70, 1);
INSERT INTO `security_event_library` VALUES (576, 'win-application', 60650, 3, 'Windows搜索事件组&&win.system.eventID匹配到\"3014\"--系统中加载的组件产生的内部错误', 68, 1);
INSERT INTO `security_event_library` VALUES (577, 'win-application', 60651, 3, 'Windows搜索事件组&&win.system.eventID匹配到\"3024\"--无法启动更新，因为无法访问内容源', 68, 1);
INSERT INTO `security_event_library` VALUES (578, 'win-application', 60656, 3, 'Windows搜索事件组&&win.system.eventID匹配到\"3085\"--应用程序网络访问帐户无效', 68, 1);
INSERT INTO `security_event_library` VALUES (579, 'win-application', 60657, 3, 'Windows搜索事件组&&win.system.eventID匹配到\"3085\"--无法刷新gatherer文件', 68, 1);
INSERT INTO `security_event_library` VALUES (580, 'win-application', 60658, 3, 'Windows搜索事件组&&win.system.eventID匹配到\"3088\"--无法更新检查点记录', 68, 1);
INSERT INTO `security_event_library` VALUES (581, 'win-application', 60659, 3, 'Windows搜索事件组&&win.system.eventID匹配到\"3089\"--无法保存gatherer文件', 68, 1);
INSERT INTO `security_event_library` VALUES (582, 'win-application', 60660, 3, 'Windows搜索事件组&&win.system.eventID匹配到\"3090\"--无法从上一个检查点还原gatherer文件', 68, 1);
INSERT INTO `security_event_library` VALUES (583, 'win-application', 60661, 3, 'Windows搜索事件组&&win.system.eventID匹配到\"3091\"--无法读取检查点记录', 68, 1);
INSERT INTO `security_event_library` VALUES (584, 'win-application', 60662, 3, 'Windows搜索事件组&&win.system.eventID匹配到\"3099\"--无法正常终止通知', 68, 1);
INSERT INTO `security_event_library` VALUES (585, 'win-application', 60663, 3, 'Windows搜索事件组&&win.system.eventID匹配到\"7011\"--目录位置无效', 70, 1);
INSERT INTO `security_event_library` VALUES (586, 'win-application', 60665, 3, 'Windows搜索事件组&&win.system.eventID匹配到\"7040\"--无法读取索引数据', 68, 1);
INSERT INTO `security_event_library` VALUES (587, 'win-application', 60666, 3, 'Windows搜索事件组&&win.system.eventID匹配到\"7066\"--缺少目录配置', 70, 1);
INSERT INTO `security_event_library` VALUES (588, 'win-application', 60667, 3, 'Windows搜索事件组&&win.system.eventID匹配到\"7068\"--无法读取注册表，缺少注册表项', 70, 1);
INSERT INTO `security_event_library` VALUES (589, 'win-application', 60670, 4, 'Windows搜索事件组&&win.system.eventID匹配到\"7064\"--无法初始化性能监视，因为未加载计数器或无法打开共享内存对象', 68, 1);
INSERT INTO `security_event_library` VALUES (590, 'win-application', 60704, 3, 'VSS事件&&win.system.eventID匹配到\"12289\"---OFO：目录无法删除', 70, 1);
INSERT INTO `security_event_library` VALUES (591, 'win-application', 61035, 3, 'Windows应用程序信息事件&&win.system.providerName匹配到\"DNSCache\"&&win.system.eventID匹配到\"11050\"---DNS 客户端服务多次尝试都无法联系任何 DNS 服务器', 68, 1);
INSERT INTO `security_event_library` VALUES (592, 'win-application', 61060, 3, 'Windows应用程序事件&&win.system.providerName匹配到\"Microsoft-Windows-Perflib\"&&win.system.eventID匹配到\"1008\"---服务远程访问开启流程失败', 68, 1);
INSERT INTO `security_event_library` VALUES (593, 'syslog,named', 12102, 2, '匹配到denied AXFR from，表示尝试执行一个区域传输失败', 70, 1);
INSERT INTO `security_event_library` VALUES (594, 'syslog,named', 12103, 3, '匹配到denied update from|unapproved update from，表示DNS更新被拒绝，通常是错误配置', 68, 1);
INSERT INTO `security_event_library` VALUES (595, 'syslog,named', 12104, 3, '匹配到unable to rename log file，表示named的日志权限错误配置', 68, 1);
INSERT INTO `security_event_library` VALUES (596, 'syslog,named', 12105, 3, '匹配到unexpected RCODE，表示解析域时出现意外错误', 70, 1);
INSERT INTO `security_event_library` VALUES (597, 'syslog,named', 12106, 3, '匹配到refused notify from non-master，表示DNS配置错误', 68, 1);
INSERT INTO `security_event_library` VALUES (598, 'syslog,named', 12108, 3, '匹配到query (cache) denied|: query (cache)，表示查询缓存被拒绝(可能是配置错误)', 68, 1);
INSERT INTO `security_event_library` VALUES (599, 'syslog,named', 12109, 1, '匹配到exiting (due to fatal error)，表示Named致命错误，DNS服务关闭', 68, 1);
INSERT INTO `security_event_library` VALUES (600, 'syslog,named', 12110, 2, '匹配到^zone \\S+ serial number \\S+ received from master和\\S+ \\S ours (\\S+)，表示从master得到的序列号低于存储的序列号', 70, 1);
INSERT INTO `security_event_library` VALUES (601, 'syslog,named', 12111, 2, '匹配到^transfer of \\S+ from \\S+ failed while receiving \\S+ REFUSED，表示无法执行区域传输', 70, 1);
INSERT INTO `security_event_library` VALUES (602, 'syslog,named', 12112, 3, '匹配到^zone \\S+: expired，表示区域转移错误', 70, 1);
INSERT INTO `security_event_library` VALUES (603, 'syslog,named', 12113, 4, '匹配到zone transfer deferred due to quota，表示区域转移延迟', 70, 1);
INSERT INTO `security_event_library` VALUES (604, 'syslog,named', 12114, 4, '匹配到bad owner name (check-names)，表示Hostname包含check-names不匹配的字符', 70, 1);
INSERT INTO `security_event_library` VALUES (605, 'syslog,named', 12116, 4, '匹配到syntax error near|reloading configuration failed: unexpected token，表示命名配置文件中有语法错误', 70, 1);
INSERT INTO `security_event_library` VALUES (606, 'syslog,named', 12117, 4, '匹配到refresh: retry limit for master \\S+ exceeded，表示超过区域转移限制', 70, 1);
INSERT INTO `security_event_library` VALUES (607, 'syslog,named', 12120, 4, '匹配到has no address records，表示缺少一条或一条AAAA记录', 70, 1);
INSERT INTO `security_event_library` VALUES (608, 'syslog,named', 12122, 4, '匹配到loading from master file \\S+ failed: not at top of zone$，表示域的来源和SOA的所有者名称不匹配', 68, 1);
INSERT INTO `security_event_library` VALUES (609, 'syslog,named', 12125, 4, '匹配到reloading configuration failed: unexpected end of input，表示绑定配置错误', 68, 1);
INSERT INTO `security_event_library` VALUES (610, 'syslog,named', 12127, 4, '匹配到loading from master file \\S+ failed: not at top of zone$，表示域的来源和SOA的所有者名称不匹配', 68, 1);
INSERT INTO `security_event_library` VALUES (611, 'syslog,named', 12129, 3, '匹配到failed to connect: connection refused，表示区域传输失败，无法连接到主服务器', 70, 1);
INSERT INTO `security_event_library` VALUES (612, 'syslog,named', 12130, 4, '匹配到IPv6 interfaces failed，表示无法在IPv6接口上监听', 70, 1);
INSERT INTO `security_event_library` VALUES (613, 'syslog,named', 12131, 4, '匹配到failed; interface ignored，表示无法绑定到接口', 70, 1);
INSERT INTO `security_event_library` VALUES (614, 'syslog,named', 12132, 4, '匹配到failed while receiving responses: not authoritative，表示Master对zone没有权威', 70, 1);
INSERT INTO `security_event_library` VALUES (615, 'syslog,named', 12133, 3, '匹配到open: \\S+: permission denied$，表示无法打开配置文件，权限被拒绝', 68, 1);
INSERT INTO `security_event_library` VALUES (616, 'syslog,named', 12134, 3, '匹配到loading configuration: permission denied，表示无法打开配置文件，权限被拒绝', 68, 1);
INSERT INTO `security_event_library` VALUES (617, 'syslog,named', 12136, 3, '匹配到failed to connect: host unreachable，表示主服务器似乎已经宕机', 68, 1);
INSERT INTO `security_event_library` VALUES (618, 'syslog,named', 12140, 4, '匹配到refresh: failure trying master，表示无法从主服务器刷新域', 70, 1);
INSERT INTO `security_event_library` VALUES (619, 'syslog,named', 12141, 4, '匹配到SOA record not at top of zone，表示域的来源和SOA的所有者名称不匹配', 70, 1);
INSERT INTO `security_event_library` VALUES (620, 'syslog,named', 12145, 4, '匹配到zone transfer \\S+ denied，表示区域转移被拒绝', 70, 1);
INSERT INTO `security_event_library` VALUES (621, 'syslog,named', 12146, 4, '匹配到error sending response: host unreachable$，表示无法发送DNS响应', 68, 1);
INSERT INTO `security_event_library` VALUES (622, 'syslog,named', 12147, 4, '匹配到update forwarding \\.+ denied$，表示无法更新转发域', 70, 1);
INSERT INTO `security_event_library` VALUES (623, 'syslog,named', 12148, 4, '匹配到: parsing failed$，表示日志含义解析配置文件失败', 70, 1);
INSERT INTO `security_event_library` VALUES (624, 'syslog,named', 12149, 2, '120s内8次查询(缓存)失败', 68, 1);
INSERT INTO `security_event_library` VALUES (625, 'syslog,msftp', 11502, 3, 'action为PASS，id为530，表示MS-FTP: FTP身份验证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (626, 'syslog,msftp', 11504, 3, 'id为^5，表示MS-FTP: FTP客户端请求失败', 68, 1);
INSERT INTO `security_event_library` VALUES (627, 'syslog,msftp', 11510, 2, '120s内8次FTP暴力破解(多次登录失败)', 68, 1);
INSERT INTO `security_event_library` VALUES (628, 'syslog,msftp', 11511, 2, '30s内10次来自同一个源的连接尝试', 68, 1);
INSERT INTO `security_event_library` VALUES (629, 'syslog,msftp', 11512, 2, '120s内8次来自同一个来源的FTP错误', 68, 1);
INSERT INTO `security_event_library` VALUES (630, 'syslog,vsftpd', 11403, 3, '匹配到FAIL LOGIN: ，表示vsftpd访问FTP服务器登录失败', 68, 1);
INSERT INTO `security_event_library` VALUES (631, 'syslog,vsftpd', 11451, 2, '120s内8次FTP暴力破解(多次登录失败)', 68, 1);
INSERT INTO `security_event_library` VALUES (632, 'syslog,vsftpd', 11452, 2, '60s内12次自同一个源IP的多个FTP连接', 68, 1);
INSERT INTO `security_event_library` VALUES (633, 'syslog,pure-ftpd', 11302, 3, '匹配到[WARNING] Authentication failed for user，表示pure-ftpd: FTP身份验证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (634, 'syslog,pure-ftpd', 11306, 2, '120s内8次FTP暴力破解(多次登录失败)。', 68, 1);
INSERT INTO `security_event_library` VALUES (635, 'syslog,pure-ftpd', 11307, 2, '60s内8次来自同一个源的连接尝试', 68, 1);
INSERT INTO `security_event_library` VALUES (636, 'syslog,proftpd', 11203, 3, '匹配到no such user，表示ProFTPD:尝试使用不存在的用户登录', 70, 1);
INSERT INTO `security_event_library` VALUES (637, 'syslog,proftpd', 11204, 3, '匹配到Incorrect password.$|Login failed，表示ProFTPD:访问FTP服务器登录失败', 68, 1);
INSERT INTO `security_event_library` VALUES (638, 'syslog,proftpd', 11206, 3, '匹配到Connection from \\S+ [\\S+] denied，表示ProFTPD配置拒绝连接', 68, 1);
INSERT INTO `security_event_library` VALUES (639, 'syslog,proftpd', 11207, 3, '匹配到refused connect from，表示连接被TCP封装器拒绝', 68, 1);
INSERT INTO `security_event_library` VALUES (640, 'syslog,proftpd', 11208, 3, '匹配到unable to find open port in PassivePorts range，表示ProFTPD:配置文件中的Passive Ports范围小，服务器错误配置', 68, 1);
INSERT INTO `security_event_library` VALUES (641, 'syslog,proftpd', 11210, 2, '匹配到Maximum login attempts，表示ProFTPD: 多次登录失败', 68, 1);
INSERT INTO `security_event_library` VALUES (642, 'syslog,proftpd', 11211, 3, '匹配到host name/name mismatch|host name/address mismatch，表示服务器主机名不匹配', 70, 1);
INSERT INTO `security_event_library` VALUES (643, 'syslog,proftpd', 11212, 3, '匹配到warning: can\'t verify hostname: ，表示ProFTPD: 反向查找错误(糟糕的ISP配置)', 70, 1);
INSERT INTO `security_event_library` VALUES (644, 'syslog,proftpd', 11214, 4, '匹配到FTP no transfer timeout, disconnected，表示ProFTPD: 远程主机由于不活动而断开', 70, 1);
INSERT INTO `security_event_library` VALUES (645, 'syslog,proftpd', 11215, 4, '匹配到FTP login timed out, disconnected，表示ProFTPD: 远程主机由于登录超时断开连接', 70, 1);
INSERT INTO `security_event_library` VALUES (646, 'syslog,proftpd', 11216, 4, '匹配到FTP session idle timeout, disconnected，表示ProFTPD:远程主机超时断开', 70, 1);
INSERT INTO `security_event_library` VALUES (647, 'syslog,proftpd', 11217, 4, '匹配到Data transfer stall timeout:，表示ProFTPD: 数据传输停滞', 70, 1);
INSERT INTO `security_event_library` VALUES (648, 'syslog,proftpd', 11218, 1, '匹配到ProFTPD terminating (signal 11)，表示ProFTPD: FTP进程崩溃', 68, 1);
INSERT INTO `security_event_library` VALUES (649, 'syslog,proftpd', 11220, 3, '匹配到listen() failed in，表示ProFTPD:无法绑定到地址', 70, 1);
INSERT INTO `security_event_library` VALUES (650, 'syslog,proftpd', 11221, 4, '匹配到error setting IPV6_V6ONLY: Protocol not available|- mod_delay/|PAM(setcred): System error|PAM(close_session): System error|cap_set_proc failed|reverting to normal operation|error retrieving information about user，表示ProFTPD: IPv6错误和mod-delay信息(忽略)', 70, 1);
INSERT INTO `security_event_library` VALUES (651, 'syslog,proftpd', 11222, 3, '匹配到unable to open incoming connection，表示ProFTPD:无法打开传入的连接。检查日志信息，了解原因。', 70, 1);
INSERT INTO `security_event_library` VALUES (652, 'syslog,proftpd', 11251, 2, '120s内8次FTP暴力破解(多次登录失败)', 68, 1);
INSERT INTO `security_event_library` VALUES (653, 'syslog,proftpd', 11252, 2, '60s内12次来自同一个源的连接尝试', 68, 1);
INSERT INTO `security_event_library` VALUES (654, 'syslog,proftpd', 11253, 2, '120s内12次来自同一源的超时登录', 68, 1);
INSERT INTO `security_event_library` VALUES (655, 'syslog,ftpd', 11101, 3, '匹配到FTP LOGIN REFUSED，表示FTPD: 拒绝连接', 68, 1);
INSERT INTO `security_event_library` VALUES (656, 'syslog,ftpd', 11107, 3, '匹配到refused connect from，表示FTPD: Tcp封装阻塞连接', 68, 1);
INSERT INTO `security_event_library` VALUES (657, 'syslog,ftpd', 11108, 3, '匹配到warning: can\'t verify hostname: |gethostbyaddr: ，表示FTPD:反向查找错误(糟糕的ISP配置)', 70, 1);
INSERT INTO `security_event_library` VALUES (658, 'syslog,ftpd', 11109, 2, '匹配到repeated login failures，表示FTPD:多次FTP登录失败', 68, 1);
INSERT INTO `security_event_library` VALUES (659, 'syslog,ftpd', 11110, 4, '匹配到timed out after，表示FTPD:用户超时断开连接', 70, 1);
INSERT INTO `security_event_library` VALUES (660, 'syslog,ftpd', 11111, 2, '匹配到PAM_ERROR_MSG: Account is disabled，表示FTPD:试图用被禁用的帐号登录', 68, 1);
INSERT INTO `security_event_library` VALUES (661, 'syslog,ftpd', 11112, 3, '匹配到^Failed authentication from，表示FTPD: 身份验证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (662, 'syslog,ftpd', 11113, 3, '匹配到^login \\S+ from \\S+ failed，表示FTPD: 身份验证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (663, 'syslog,arpwatch', 7202, 2, '匹配到flip flop ，表示Arpwatch:“flip flop”消息。IP /MAC关系变化太频繁', 70, 1);
INSERT INTO `security_event_library` VALUES (664, 'syslog,arpwatch', 7206, 4, '匹配到sent bad addr len，表示Arpwatch:检测到错误地址len(忽略)', 70, 1);
INSERT INTO `security_event_library` VALUES (665, 'syslog,arpwatch', 7207, 4, '匹配到/dev/bpf0: Permission denied，表示Arpwatch可能有运行权限错误', 70, 1);
INSERT INTO `security_event_library` VALUES (666, 'windows,dhcp', 6319, 3, 'id为^31，表示MS-DHCP: DNS更新失败', 70, 1);
INSERT INTO `security_event_library` VALUES (667, 'windows,dhcp', 6365, 3, 'id为^11014，表示MS-DHCP：错误地址', 70, 1);
INSERT INTO `security_event_library` VALUES (668, 'windows,dhcp', 6366, 3, 'id为^11015，表示MS-DHCP：地址已经在使用中', 70, 1);
INSERT INTO `security_event_library` VALUES (669, 'windows,dhcp', 6373, 1, 'id为^11023，表示MS-DHCP：AD未授权服务', 70, 1);
INSERT INTO `security_event_library` VALUES (670, 'windows,dhcp', 6376, 1, 'id为^11025，表示MS-DHCP：服务在AD中尚未确定是否授权', 70, 1);
INSERT INTO `security_event_library` VALUES (671, 'syslog,sshd', 5704, 3, '匹配到fatal: Timeout before authentication for，表示sshd：登录超时', 68, 1);
INSERT INTO `security_event_library` VALUES (672, 'syslog,sshd', 5706, 3, '匹配到Did not receive identification string from，表示sshd：不安全连接尝试(scan)', 68, 1);
INSERT INTO `security_event_library` VALUES (673, 'syslog,sshd', 5709, 4, '匹配到error: Could not get shadow information for NOUSER|fatal: Read from socket failed: |error: ssh_msg_send: write|^syslogin_perform_logout: |^pam_succeed_if(sshd:auth): error retrieving information about user|can\'t verify hostname: getaddrinfo，表示sshd：没有用户/ip和上下文的无用sshd消息', 70, 1);
INSERT INTO `security_event_library` VALUES (674, 'syslog,sshd', 5710, 3, '匹配到illegal user|invalid user，表示sshd：尝试使用不存在的用户登录', 68, 1);
INSERT INTO `security_event_library` VALUES (675, 'syslog,sshd', 5711, 4, '匹配到authentication failure; logname= uid=0 euid=0 tty=ssh|input_userauth_request: invalid user|PAM: User not known to the underlying authentication module for illegal user|error retrieving information about user，表示sshd：没有用户/ip的无用/重复的SSHD消息', 70, 1);
INSERT INTO `security_event_library` VALUES (676, 'syslog,sshd', 5712, 2, '120s内8次试图使用不存在的用户暴力访问系统', 68, 1);
INSERT INTO `security_event_library` VALUES (677, 'syslog,sshd', 5716, 3, '匹配到^Failed|^error: PAM: Authentication，表示sshd：身份验证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (678, 'syslog,sshd', 5717, 3, '匹配到error: Bad prime description in line，表示sshd：配置错误(moduli)', 70, 1);
INSERT INTO `security_event_library` VALUES (679, 'syslog,sshd', 5718, 3, '匹配到not allowed because，表示sshd：尝试使用被拒绝的用户登录', 68, 1);
INSERT INTO `security_event_library` VALUES (680, 'syslog,sshd', 5719, 2, '120s内8次尝试使用被拒绝的用户登录', 68, 1);
INSERT INTO `security_event_library` VALUES (681, 'syslog,sshd', 5720, 2, '8次身份验证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (682, 'syslog,sshd', 5721, 4, '匹配到Received disconnect from，表示sshd：系统与sshd断开', 68, 1);
INSERT INTO `security_event_library` VALUES (683, 'syslog,sshd', 5723, 4, '匹配到error: buffer_get_bignum2_ret: negative numbers not supported，表示sshd：键错误', 68, 1);
INSERT INTO `security_event_library` VALUES (684, 'syslog,sshd', 5724, 4, '匹配到fatal: buffer_get_bignum2: buffer error，表示sshd：键错误', 68, 1);
INSERT INTO `security_event_library` VALUES (685, 'syslog,sshd', 5725, 4, '匹配到fatal: Write failed: Host is down，表示sshd：主机不正常断开', 68, 1);
INSERT INTO `security_event_library` VALUES (686, 'syslog,sshd', 5726, 3, '匹配到error: PAM: Module is unknown for，表示sshd：未知PAM模块，PAM配置错误', 70, 1);
INSERT INTO `security_event_library` VALUES (687, 'syslog,sshd', 5727, 4, '匹配到failed: Address already in use.，表示sshd：当某些东西已经绑定到端口时，尝试启动sshd', 70, 1);
INSERT INTO `security_event_library` VALUES (688, 'syslog,sshd', 5728, 3, '匹配到Authentication service cannot retrieve user credentials，表示sshd：身份验证服务无法检索用户凭据', 68, 1);
INSERT INTO `security_event_library` VALUES (689, 'syslog,sshd', 5730, 3, '匹配到error: connect to \\S+ port \\d+ failed: Connection refused，表示sshd不接受连接', 68, 1);
INSERT INTO `security_event_library` VALUES (690, 'syslog,sshd', 5732, 4, '匹配到error: connect_to，表示sshd：可能的端口转发失败', 70, 1);
INSERT INTO `security_event_library` VALUES (691, 'syslog,sshd', 5733, 4, '匹配到Invalid credentials，表示sshd：用户输入错误密码', 70, 1);
INSERT INTO `security_event_library` VALUES (692, 'syslog,sshd', 5734, 4, '匹配到Could not load host key，表示sshd无法加载一个或多个主机密钥', 70, 1);
INSERT INTO `security_event_library` VALUES (693, 'syslog,sshd', 5735, 4, '匹配到Write failed: Broken pipe，表示sshd：主机消失，写入失败', 70, 1);
INSERT INTO `security_event_library` VALUES (694, 'syslog,sshd', 5736, 4, '匹配到^error: setsockopt SO_KEEPALIVE: Connection reset by peer$|^error: accept: Software caused connection abort$，表示sshd：连接重置或终止', 70, 1);
INSERT INTO `security_event_library` VALUES (695, 'syslog,sshd', 5737, 3, '匹配到^fatal: Cannot bind any address.$，表示sshd：不能绑定到配置的地址', 70, 1);
INSERT INTO `security_event_library` VALUES (696, 'syslog,sshd', 5738, 3, '匹配到set_loginuid failed opening loginuid$，表示sshd：pam_loginuid无法打开loginuid', 70, 1);
INSERT INTO `security_event_library` VALUES (697, 'syslog,sshd', 5739, 3, '匹配到^error: Could not stat AuthorizedKeysCommand，表示sshd：配置错误(AuthorizedKeysCommand)', 70, 1);
INSERT INTO `security_event_library` VALUES (698, 'syslog,sshd', 5741, 3, '匹配到Connection refused$，表示sshd：拒绝连接', 68, 1);
INSERT INTO `security_event_library` VALUES (699, 'syslog,sshd', 5742, 3, '匹配到Connection timed out$，表示sshd：连接超时', 68, 1);
INSERT INTO `security_event_library` VALUES (700, 'syslog,sshd', 5743, 3, '匹配到No route to host$，表示sshd：没有连接到主机的路由', 68, 1);
INSERT INTO `security_event_library` VALUES (701, 'syslog,sshd', 5744, 3, '匹配到failure direct-tcpip$，表示sshd：端口转发问题', 70, 1);
INSERT INTO `security_event_library` VALUES (702, 'syslog,sshd', 5745, 3, '匹配到Transport endpoint is not connected$，表示sshd：传输端点未连接', 70, 1);
INSERT INTO `security_event_library` VALUES (703, 'syslog,sshd', 5746, 3, '匹配到get_remote_port failed$，表示sshd：获取远程端口失败', 70, 1);
INSERT INTO `security_event_library` VALUES (704, 'syslog,sshd', 5747, 3, '匹配到bad client public DH value，表示sshd：坏的客户端公开DH值', 70, 1);
INSERT INTO `security_event_library` VALUES (705, 'syslog,sshd', 5748, 3, '匹配到Corrupted MAC on input.，表示sshd：输入MAC损坏', 70, 1);
INSERT INTO `security_event_library` VALUES (706, 'syslog,sshd', 5749, 3, '匹配到^Bad packet length，表示sshd：坏包长度', 70, 1);
INSERT INTO `security_event_library` VALUES (707, 'syslog,sshd', 5750, 4, '解码为sshd，匹配到Unable to negotiate with |Unable to negotiate a key|fatal: no matching，表示sshd：无法与客户协商', 70, 1);
INSERT INTO `security_event_library` VALUES (708, 'syslog,sshd', 5751, 4, '解码为sshd，匹配到no hostkey alg [preauth]，表示sshd：没有hostkey alg', 70, 1);
INSERT INTO `security_event_library` VALUES (709, 'syslog,sshd', 5752, 4, '匹配到no matching key exchange method found.|Unable to negotiate a key exchange method，表示sshd：客户端没有提供可接受的密钥交换方法', 70, 1);
INSERT INTO `security_event_library` VALUES (710, 'syslog,sshd', 5753, 4, '匹配到no matching cipher found，表示sshd：无法与客户端协商，没有匹配的密码', 70, 1);
INSERT INTO `security_event_library` VALUES (711, 'syslog,sshd', 5754, 4, '匹配到Failed to create session:，表示sshd：创建会话失败', 70, 1);
INSERT INTO `security_event_library` VALUES (712, 'syslog,sshd', 5755, 4, '匹配到bad ownership or modes for file，表示sshd：由于授权密钥的所有者/权限而拒绝认证', 68, 1);
INSERT INTO `security_event_library` VALUES (713, 'syslog,sshd', 5756, 4, '匹配到failed, subsystem not found$，表示sshd：子系统请求失败', 68, 1);
INSERT INTO `security_event_library` VALUES (714, 'syslog,sshd', 5757, 4, '匹配到but this does not map back to the address - POSSIBLE BREAK-IN ATTEMPT!$，表示DNS映射错误', 70, 1);
INSERT INTO `security_event_library` VALUES (715, 'syslog,sshd', 5758, 2, '匹配到^error: maximum authentication attempts exceeded，表示超过最大认证次数', 68, 1);
INSERT INTO `security_event_library` VALUES (716, 'syslog,sshd', 5759, 4, '匹配到no matching mac found，表示sshd：无法与客户端协商，没有匹配的MAC', 70, 1);
INSERT INTO `security_event_library` VALUES (717, 'syslog,sshd', 5760, 3, '匹配到Failed password|Failed keyboard|authentication error，表示sshd：身份验证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (718, 'syslog,sshd', 5761, 4, '匹配到Disconnected from user，表示sshd：SSH连接关闭', 68, 1);
INSERT INTO `security_event_library` VALUES (719, 'syslog,sshd', 5762, 3, '匹配到Connection reset，表示sshd：连接重置', 68, 1);
INSERT INTO `security_event_library` VALUES (720, 'syslog,sshd', 5763, 2, '120s内8次试图使用暴力访问系统。身份验证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (721, 'syslog,telnetd', 5601, 3, '匹配到refused connect from，表示telnetd: TCP封装拒绝连接', 68, 1);
INSERT INTO `security_event_library` VALUES (722, 'syslog,telnetd', 5603, 3, '匹配到ttloop:  peer died:|ttloop:  read:，远程主机建立telnet无效连接', 68, 1);
INSERT INTO `security_event_library` VALUES (723, 'syslog,telnetd', 5604, 3, '匹配到warning: can\'t verify hostname:，表示telentd：反向查找错误(主机名配置错误)', 70, 1);
INSERT INTO `security_event_library` VALUES (724, 'pam,syslog', 5503, 3, '匹配到authentication failure; logname=，表示PAM：用户登录失败', 68, 1);
INSERT INTO `security_event_library` VALUES (725, 'pam,syslog', 5504, 3, '匹配到check pass; user unknown，表示PAM：尝试使用无效用户登录', 68, 1);
INSERT INTO `security_event_library` VALUES (726, 'pam,syslog', 5551, 2, '180s内8次登录失败', 68, 1);
INSERT INTO `security_event_library` VALUES (727, 'pam,syslog', 5552, 4, '匹配到gdm:auth): conversation failed，表示PAM和gdm不太好', 70, 1);
INSERT INTO `security_event_library` VALUES (728, 'pam,syslog', 5553, 3, '程序名是login，匹配到cannot open shared object file: No such file or directory，表示PAM错误配置', 70, 1);
INSERT INTO `security_event_library` VALUES (729, 'pam,syslog', 5554, 3, '程序名是login，匹配到illegal module type:，表示PAM错误配置', 70, 1);
INSERT INTO `security_event_library` VALUES (730, 'pam,syslog', 5557, 3, '匹配到password check failed，表示unix_chkpwd：密码检查失败', 70, 1);
INSERT INTO `security_event_library` VALUES (731, 'syslog,sendmail', 3102, 3, '匹配到reject=451 4.1.8，表示sendmail:发送方域没有任何有效的MX记录(请求的操作中止)', 70, 1);
INSERT INTO `security_event_library` VALUES (732, 'syslog,sendmail', 3103, 3, '匹配到reject=550 5.0.0 |reject=553 5.3.0，表示sendmail:被访问列表拒绝(55x:请求的操作没有被采取)', 68, 1);
INSERT INTO `security_event_library` VALUES (733, 'syslog,sendmail', 3104, 3, '匹配到reject=550 5.7.1 ，表示sendmail:尝试使用邮件服务器作为中继(550:请求的操作未被执行)', 70, 1);
INSERT INTO `security_event_library` VALUES (734, 'syslog,sendmail', 3105, 3, '匹配到reject=553 5.1.8 ，表示sendmail:发件人域不存在(553:请求的操作未被执行)', 70, 1);
INSERT INTO `security_event_library` VALUES (735, 'syslog,sendmail', 3106, 3, '匹配到reject=553 5.5.4，表示sendmail:发件人地址没有域(553:请求的操作未被执行)', 70, 1);
INSERT INTO `security_event_library` VALUES (736, 'syslog,sendmail', 3108, 3, '匹配到rejecting commands from，表示sendmail:由于pre-greeting原因，sendmail被拒绝', 70, 1);
INSERT INTO `security_event_library` VALUES (737, 'syslog,sendmail', 3109, 2, '匹配到savemail panic，表示sendmail: sendmail保存邮件困难', 70, 1);
INSERT INTO `security_event_library` VALUES (738, 'syslog,sendmail', 3191, 3, '匹配到^sender check failed|^sender check tempfailed，表示SMF-SAV sendmail milter无法验证地址(被拒绝)', 70, 1);
INSERT INTO `security_event_library` VALUES (739, 'syslog,postfix', 3301, 3, 'id为^554$，表示Postfix:试图使用邮件服务器作为中继(客户端主机被拒绝)', 70, 1);
INSERT INTO `security_event_library` VALUES (740, 'syslog,postfix', 3302, 3, 'id为^550$，表示Postfix:被访问列表拒绝(请求的操作未被执行)', 70, 1);
INSERT INTO `security_event_library` VALUES (741, 'syslog,postfix', 3303, 3, 'id为^450$，表示Postfix:未找到发件人域(450:未采取所请求的邮件操作)', 70, 1);
INSERT INTO `security_event_library` VALUES (742, 'syslog,postfix', 3304, 3, 'id为^503$，表示Postfix:不正确使用SMTP命令管道(503:错误的命令序列)', 70, 1);
INSERT INTO `security_event_library` VALUES (743, 'syslog,postfix', 3305, 3, 'id为^504$，表示Postfix:接收地址必须包含FQDN(504:不实现命令参数)', 70, 1);
INSERT INTO `security_event_library` VALUES (744, 'syslog,postfix', 3306, 3, '匹配到blocked using，试图使用邮件服务器作为中继，被访问列表拒绝，表示Postfix:被反垃圾邮件列入黑名单的IP地址', 70, 1);
INSERT INTO `security_event_library` VALUES (745, 'syslog,postfix', 3330, 2, '匹配到defer service failure|Resource temporarily unavailable|^fatal: the Postfix mail system is not running，表示Postfix流程错误', 70, 1);
INSERT INTO `security_event_library` VALUES (746, 'syslog,postfix', 3332, 3, '匹配到authentication failed，表示Postfix SASL身份认证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (747, 'syslog,postfix', 3335, 3, '匹配到^too many，表示Postfix: 太多未知的RCPT错误', 70, 1);
INSERT INTO `security_event_library` VALUES (748, 'syslog,postfix', 3357, 2, '120s内8次SASL认证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (749, 'syslog,postfix', 3396, 3, '匹配到verification，表示Postfix:主机名验证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (750, 'syslog,postfix', 3397, 3, '匹配到RBL，表示postfix警告：RBL查找错误:主机或域名未找到', 70, 1);
INSERT INTO `security_event_library` VALUES (751, 'syslog,postfix', 3398, 3, '匹配到MAIL|does not resolve to address，表示postfix警告：来自未知发件人的非法地址', 70, 1);
INSERT INTO `security_event_library` VALUES (752, 'syslog,imapd', 3601, 3, '匹配到Login failed user=|AUTHENTICATE LOGIN failure，表示Imapd用户登录失败', 68, 1);
INSERT INTO `security_event_library` VALUES (753, 'syslog,imapd', 3651, 2, '120s内$IMAPD_FREQ次Imapd同一源ip登录失败', 68, 1);
INSERT INTO `security_event_library` VALUES (754, 'syslog', 1001, 4, '匹配到^Couldn\'t open /etc/securetty，表示文件丢失，Root访问不受限制', 68, 1);
INSERT INTO `security_event_library` VALUES (755, 'syslog', 1002, 4, '匹配到$BAD_WORDS，表示系统中有未知问题', 70, 1);
INSERT INTO `security_event_library` VALUES (756, 'syslog', 1003, 1, '非标准syslog消息(太大)，最大1025', 70, 1);
INSERT INTO `security_event_library` VALUES (757, 'syslog', 1010, 3, '匹配到segfault at ，表示段错误的过程', 70, 1);
INSERT INTO `security_event_library` VALUES (758, 'syslog', 2101, 3, '匹配到nfs: mount failure，表示无法挂载NFS共享', 70, 1);
INSERT INTO `security_event_library` VALUES (759, 'syslog', 2102, 3, '匹配到reason given by server: Permission denied，表示无法挂载NFS目录', 70, 1);
INSERT INTO `security_event_library` VALUES (760, 'syslog', 2103, 3, '匹配到^rpc.mountd: refused mount request from，表示无法挂载NFS目录', 70, 1);
INSERT INTO `security_event_library` VALUES (761, 'syslog', 2301, 2, '匹配到^Deactivating service，表示xinetd:服务连接数过多', 68, 1);
INSERT INTO `security_event_library` VALUES (762, 'syslog', 2501, 3, '匹配到FAILED LOGIN |authentication failure|Authentication failed for|invalid password for|LOGIN FAILURE|auth failure: |authentication error|authinternal failed|Failed to authorize|Wrong password given for|login failed|Auth: Login incorrect|Failed to authenticate user，表示syslog:用户认证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (763, 'syslog', 2502, 2, '匹配到more authentication failures;|REPEATED login failures，表示syslog:用户多次输入错误密码', 68, 1);
INSERT INTO `security_event_library` VALUES (764, 'syslog', 2503, 3, '匹配到^refused connect from|^libwrap refused connection|Connection from \\S+ denied，表示syslog: Tcp Wrappers阻断连接', 70, 1);
INSERT INTO `security_event_library` VALUES (765, 'syslog', 2504, 2, '匹配到ILLEGAL ROOT LOGIN|ROOT LOGIN REFUSED，表示syslog:非法root登录', 68, 1);
INSERT INTO `security_event_library` VALUES (766, 'syslog', 2505, 4, '匹配到^ROOT LOGIN  on，表示syslog:物理root登录', 68, 1);
INSERT INTO `security_event_library` VALUES (767, 'syslog', 2509, 3, '匹配到RESULT tag=97 err=49，OpenLDAP连接打开后，10秒内触发2次匹配的内容表示OpenLDAP认证失败', 68, 1);
INSERT INTO `security_event_library` VALUES (768, 'syslog', 2801, 4, '匹配到No configuration file /etc/smartd.conf found，表示Smartd已启动但未配置', 70, 1);
INSERT INTO `security_event_library` VALUES (769, 'syslog', 2802, 4, '匹配到Unable to register ATA device，表示无法注册ATA设备警报Smartd配置问题', 70, 1);
INSERT INTO `security_event_library` VALUES (770, 'syslog', 2803, 4, '匹配到No such device or address，表示设备已配置但Smartd无法使用', 70, 1);
INSERT INTO `security_event_library` VALUES (771, 'syslog', 5102, 4, '匹配到modprobe: Can\'t locate module sound，表示来自内核的信息消息：modprobe:无法定位模块声音', 70, 1);
INSERT INTO `security_event_library` VALUES (772, 'syslog', 5103, 2, '匹配到Oversized packet received from，表示收到错误消息：超大包报警', 70, 1);
INSERT INTO `security_event_library` VALUES (773, 'syslog', 5105, 4, '匹配到end_request: I/O error, dev fd0, sector 0|Buffer I/O error on device fd0, logical block 0，表示对/dev/ f0的无效请求(内核上的错误)', 70, 1);
INSERT INTO `security_event_library` VALUES (774, 'syslog', 5109, 3, '匹配到I/O error: dev |end_request: I/O error, dev，表示内核输入/输出错误', 70, 1);
INSERT INTO `security_event_library` VALUES (775, 'syslog', 5110, 3, '匹配到Forged DCC command from，表示IRC错误配置', 70, 1);
INSERT INTO `security_event_library` VALUES (776, 'syslog', 5111, 4, '匹配到ipw2200: Firmware error detected.| ACPI Error，表示内核设备错误', 70, 1);
INSERT INTO `security_event_library` VALUES (777, 'syslog', 5112, 4, '匹配到usbhid: probe of，表示内核usbhid探测错误(忽略)', 70, 1);
INSERT INTO `security_event_library` VALUES (778, 'syslog', 5132, 2, '匹配到module verification failed，表示模块验证失败', 70, 1);
INSERT INTO `security_event_library` VALUES (779, 'syslog', 5133, 2, '匹配到PKCS#7 signature not signed with a trusted key，表示已加载签名但不受信任的内核模块', 70, 1);
INSERT INTO `security_event_library` VALUES (780, 'syslog', 5134, 3, '程序名为^rngd，匹配到failure，表示RNGD失败', 70, 1);
INSERT INTO `security_event_library` VALUES (781, 'syslog', 5137, 3, 'shuyuRAID普通管理事件，匹配到Fail，表示RAID普通管理事件失败', 70, 1);
INSERT INTO `security_event_library` VALUES (782, 'syslog', 5141, 3, '匹配到cannot import，表示一般ZFS池事件：由于错误，无法导入ZFS池', 70, 1);
INSERT INTO `security_event_library` VALUES (783, 'syslog', 2831, 4, '匹配到^unable to exec，表示crond配置错误', 70, 1);
INSERT INTO `security_event_library` VALUES (784, 'syslog', 5905, 4, '程序名为useradd，匹配到failed adding user ，表示添加用户失败', 70, 1);
INSERT INTO `security_event_library` VALUES (785, 'syslog', 5401, 3, '匹配到incorrect password attempt，表示输入错误的密码导致运行sudo失败', 70, 1);
INSERT INTO `security_event_library` VALUES (786, 'syslog', 5404, 2, '匹配到3 incorrect password attempts，表示输入三次错误密码导致运行sudo失败', 70, 1);
INSERT INTO `security_event_library` VALUES (787, 'syslog', 5405, 3, '匹配到user NOT in sudoers，表示未经授权的用户试图使用sudo', 70, 1);
INSERT INTO `security_event_library` VALUES (788, 'syslog', 5406, 3, '匹配到command not allowed，表示命令不被允许', 70, 1);
INSERT INTO `security_event_library` VALUES (789, 'syslog', 9101, 4, '匹配到^GRE: \\S+ from \\S+ failed: status = -1，表示PPTPD消息失败(通信错误)', 70, 1);
INSERT INTO `security_event_library` VALUES (790, 'syslog', 9102, 4, '匹配到^tcflush failed: Bad file descriptor，表示PPTPD通信错误', 70, 1);
INSERT INTO `security_event_library` VALUES (791, 'syslog', 2939, 1, 'action为degraded，指SCSI RAID处于降级状态', 70, 1);
INSERT INTO `security_event_library` VALUES (792, 'syslog', 2941, 4, '匹配到No chain/target/match by that name.$，表示不正确的链/目标/匹配', 70, 1);
INSERT INTO `security_event_library` VALUES (793, 'syslog', 2945, 3, '匹配到^imuxsock begins to drop messages，表示Rsyslog可能由于速率限制而丢消息', 70, 1);
INSERT INTO `security_event_library` VALUES (794, 'syslog', 2964, 2, '摧毁自定义应用组中，建立新的连接时，30秒内同一源连接4次时告警', 70, 1);
INSERT INTO `security_event_library` VALUES (795, 'win-application', 60613, 3, '微软安装程序事件组&&win.system.eventID匹配到”1001“--请求产品组件失败', 68, 1);
INSERT INTO `security_event_library` VALUES (796, 'win-application', 60614, 3, '微软安装程序事件组&&win.system.eventID匹配到”1002“--键中出现意外值或缺少值', 68, 1);
INSERT INTO `security_event_library` VALUES (797, 'win-application', 60615, 3, '微软安装程序事件组&&win.system.eventID匹配到”1003“--子项中出现意外值或缺少值', 68, 1);
INSERT INTO `security_event_library` VALUES (798, 'windows', 64101, 3, '字段\"win.system.eventID\"中有20187$|^20014$|^20078$|^20050$|^20049$|^20189$，表示远程访问登录失败', 68, 1);
INSERT INTO `security_event_library` VALUES (799, 'windows', 64107, 3, '字段\"win.system.eventID\"中有^201$|^203$|^204$|^301$|^304$|^305$|^306$|^1001$，表示TS网关登录失败', 68, 1);
INSERT INTO `security_event_library` VALUES (800, 'windows', 64108, 4, '字段\"win.system.eventID\"中有^202$|^303$，表示TS网关用户断开', 68, 1);
INSERT INTO `security_event_library` VALUES (801, 'windows', 64109, 2, '240s内$MS_FREQ次远程接入登录失败', 68, 1);
INSERT INTO `security_event_library` VALUES (802, 'windows', 64110, 2, '241s内$MS_FREQ次TS网关登录失败', 68, 1);
INSERT INTO `security_event_library` VALUES (803, 'windows_system', 61107, 3, '字段\"win.system.eventID\"中有^7031$，字段\"win.eventdata.param1\"中有\\.+，字段\"win.system.providerName\"中有^Service Control Manager$，表示$(win.eventdata.param1)意外终止', 70, 1);
INSERT INTO `security_event_library` VALUES (804, 'windows_system', 61109, 3, '字段\"win.system.eventID\"中有^1014$，字段\"win.system.providerName\"中有^Microsoft-Windows-DNS-Client$，表示$(win.eventdata.queryName)的名称解析超时', 70, 1);
INSERT INTO `security_event_library` VALUES (805, 'windows_system', 61114, 3, '字段\"win.system.eventID\"中有^2504$|^2505$，表示无法浏览', 70, 1);
INSERT INTO `security_event_library` VALUES (806, 'windows_system', 61119, 2, '字段\"win.system.eventID\"中有^8006$，表示浏览器收到了来自远程计算机的非法数据报', 70, 1);
INSERT INTO `security_event_library` VALUES (807, 'windows_system', 61120, 3, '字段\"win.system.eventID\"中有^8007$，表示浏览器无法更新服务状态位', 70, 1);
INSERT INTO `security_event_library` VALUES (808, 'windows_system', 61121, 3, '字段\"win.system.eventID\"中有^8008$，表示浏览器无法更新其角色', 70, 1);
INSERT INTO `security_event_library` VALUES (809, 'windows_system', 61122, 3, '字段\"win.system.eventID\"中有^8009$，表示浏览器无法将自己推广为精通浏览器', 70, 1);
INSERT INTO `security_event_library` VALUES (810, 'windows_system', 61123, 3, '字段\"win.system.eventID\"中有^8010$，表示浏览器驱动程序无法将字符串转换为unicode字符串', 70, 1);
INSERT INTO `security_event_library` VALUES (811, 'windows_system', 61124, 3, '字段\"win.system.eventID\"中有^8011$，表示浏览器无法添加配置参数', 70, 1);
INSERT INTO `security_event_library` VALUES (812, 'windows_system', 61126, 2, '字段\"win.system.eventID\"中有^8016$，表示浏览器驱动程序从远程计算机接收了太多非法数据报', 70, 1);
INSERT INTO `security_event_library` VALUES (813, 'windows_system', 61127, 3, '字段\"win.system.eventID\"中有^8019$|^8020$，表示浏览器无法将自己推广为精通浏览器', 70, 1);
INSERT INTO `security_event_library` VALUES (814, 'windows_system', 61128, 3, '字段\"win.system.eventID\"中有^8021$，表示浏览器无法从网络上的浏览器主服务器检索服务器列表', 70, 1);
INSERT INTO `security_event_library` VALUES (815, 'windows_system', 61129, 3, '字段\"win.system.eventID\"中有^8022$，表示浏览器无法从网络上的浏览器主服务器检索域列表', 70, 1);
INSERT INTO `security_event_library` VALUES (816, 'windows_system', 61130, 2, '字段\"win.system.eventID\"中有^8023$，表示浏览器服务的参数值不合法', 70, 1);
INSERT INTO `security_event_library` VALUES (817, 'windows_system', 61131, 3, '字段\"win.system.eventID\"中有^8029$，表示浏览器驱动程序无法从注册表初始化变量', 70, 1);
INSERT INTO `security_event_library` VALUES (818, 'windows_system', 61132, 2, '字段\"win.system.eventID\"中有^8030$，表示浏览器驱动程序丢弃了太多的邮件槽消息', 70, 1);
INSERT INTO `security_event_library` VALUES (819, 'windows_system', 61133, 3, '字段\"win.system.eventID\"中有^8031$，表示浏览器驱动程序丢弃了太多的GetBrowserServerList请求', 70, 1);
INSERT INTO `security_event_library` VALUES (820, 'windows_system', 61134, 3, '字段\"win.system.eventID\"中有^8032$，表示浏览器服务在传输过程中多次未能检索备份列表', 70, 1);
INSERT INTO `security_event_library` VALUES (821, 'syslog,sendmail', 3151, 2, '120s内8次发送方域有伪造的MX记录。它不应该发送电子邮件', 49, 1);
INSERT INTO `security_event_library` VALUES (822, 'syslog,sendmail', 3152, 3, '120s内8次尝试从先前被拒绝的发件人发送电子邮件(access)', 49, 1);
INSERT INTO `security_event_library` VALUES (823, 'syslog,sendmail', 3153, 3, '120s内8次转发垃圾邮件', 49, 1);
INSERT INTO `security_event_library` VALUES (824, 'syslog,sendmail', 3154, 2, '120s内8次尝试从无效/未知的发送者域发送电子邮件', 49, 1);
INSERT INTO `security_event_library` VALUES (825, 'syslog,sendmail', 3155, 2, '120s内8次尝试从无效/未知的发件人发送电子邮件', 49, 1);
INSERT INTO `security_event_library` VALUES (826, 'syslog,sendmail', 3156, 2, '120s内12次来自同一个源ip的被拒绝的邮件', 49, 1);
INSERT INTO `security_event_library` VALUES (827, 'syslog,sendmail', 3158, 2, '120s内8次问候前拒绝', 49, 1);
INSERT INTO `security_event_library` VALUES (828, 'syslog,postfix', 3351, 3, '90秒内$POSTFIX_FREQ次转发垃圾邮件', 49, 1);
INSERT INTO `security_event_library` VALUES (829, 'syslog,postfix', 3352, 3, '120秒内$POSTFIX_FREQ次尝试从被拒绝的发送方IP发送电子邮件(访问)', 49, 1);
INSERT INTO `security_event_library` VALUES (830, 'syslog,postfix', 3353, 2, '120秒内$POSTFIX_FREQ次尝试从无效/未知的发件人域名发送电子邮件', 49, 1);
INSERT INTO `security_event_library` VALUES (831, 'syslog,postfix', 3354, 1, '120秒内$POSTFIX_FREQ次误用SMTP服务(错误的命令序列)', 49, 1);
INSERT INTO `security_event_library` VALUES (832, 'syslog,postfix', 3355, 2, '120秒内$POSTFIX_FREQ次尝试将电子邮件发送给无效的收件人或从未知的发件人域', 49, 1);
INSERT INTO `security_event_library` VALUES (833, 'syslog,postfix', 3356, 2, '120秒内$POSTFIX_FREQ次尝试从黑名单IP地址发送电子邮件(被阻止)', 49, 1);
INSERT INTO `security_event_library` VALUES (834, 'syslog,mailscanner', 3702, 3, 'action为spam，表示mailscanner：检测到垃圾邮件', 49, 1);
INSERT INTO `security_event_library` VALUES (835, 'syslog,mailscanner', 3751, 3, '180s内8次尝试发送垃圾邮件', 49, 1);
INSERT INTO `security_event_library` VALUES (836, 'mse', 63616, 3, '微软反恶意软件信息事件、警告事件、错误事件中，检测到病毒（并已正确删除），字段 “win.system.message”为\\.*DOS/EICAR_Test_File，选项没有完整日志，表示Microsoft安全要素-检测到EICAR测试文件', 76, 1);
INSERT INTO `security_event_library` VALUES (837, 'mse', 63600, 4, '属于ossec类别，解码为Windows事件通道的，字段名为\"win.system.providerName\"，选项没有完整的日志，属于windows规则分组（60000）；且字段名为“win.system.channel”中有^System$，选项没有完整的日志，属于windows规则中的系统通道规则（60002）；且字段“win.system.providerName”中有^Microsoft Antimalware$，选项没有完整日志，属于Microsoft安全要素规则（60008）。\n字段“win.system.severityValue”中有^INFORMATION$，选项没有完整日志，属于微软反恶意软件信息事件。', 76, 1);
INSERT INTO `security_event_library` VALUES (838, 'win-application', 60783, 4, 'Winlogon事件&&win.system.eventID匹配到\"4101\"---Windows 许可证已验证', 76, 1);
INSERT INTO `security_event_library` VALUES (839, 'win-application', 60974, 4, 'MSDTC事件&&win.system.eventID匹配到\"4446\"---输入 MSDTC 日志存储警告限制', 76, 1);
INSERT INTO `security_event_library` VALUES (840, 'win-application', 60975, 4, 'MSDTC事件&&win.system.eventID匹配到\"4447\"---保存 MSDTC 日志存储警告限制', 76, 1);
INSERT INTO `security_event_library` VALUES (841, 'win-application', 60982, 3, 'MSDTC事件&&win.system.eventID匹配到\"4457\"---MSDTC 无法在 Windows 升级期间迁移其日志', 76, 1);
INSERT INTO `security_event_library` VALUES (842, 'ms_wdefender', 83000, 4, 'Windows系统日志匹配到\"Microsoft-Windows-Windows Defender/Operational:\"---Windows Defender（系统内置的杀毒及防火墙）事件消息组', 76, 1);
INSERT INTO `security_event_library` VALUES (843, 'ms_wdefender', 83002, 3, 'Windows系统日志匹配到\"Microsoft-Windows-Windows Defender/Operational: INFORMATION(1117):\"---Windows Defender采取措施保护系统免受有害软件的攻击', 76, 1);
INSERT INTO `security_event_library` VALUES (844, 'office365', 91533, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^1$，选项没有完整日志，表示Office 365: Exchange管理员审计日志事件', 76, 1);
INSERT INTO `security_event_library` VALUES (845, 'office365', 91534, 4, 'Office 365：来自Exchange邮箱审计日志的事件，用于对单个项目执行的操作，例如创建或接收电子邮件。', 76, 1);
INSERT INTO `security_event_library` VALUES (846, 'office365', 91535, 4, 'Office 365：对于可以在多个项目上执行的操作，例如移动或删除一个或多个电子邮件，Exchange邮箱审计日志中的事件。', 76, 1);
INSERT INTO `security_event_library` VALUES (847, 'office365', 91536, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^4$，选项没有完整日志，表示Office 365: SharePoint的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (848, 'office365', 91537, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^6$，选项没有完整日志，表示Office 365: SharePoint文件操作事件', 76, 1);
INSERT INTO `security_event_library` VALUES (849, 'office365', 91538, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^7$，选项没有完整日志，表示Office 365：OneDrive用于Business事件', 76, 1);
INSERT INTO `security_event_library` VALUES (850, 'office365', 91539, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^8$，选项没有完整日志，表示Office 365：Azure活动目录事件', 76, 1);
INSERT INTO `security_event_library` VALUES (851, 'office365', 91540, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^9$，选项没有完整日志，表示Office 365：Azure Active Directory OrgId登录事件(已弃用)', 76, 1);
INSERT INTO `security_event_library` VALUES (852, 'office365', 91541, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^10$，选项没有完整日志，表示Office 365：数据中心安全cmdlet事件', 76, 1);
INSERT INTO `security_event_library` VALUES (853, 'office365', 91542, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^11$，选项没有完整日志，表示Office 365：数据丢失保护(DLP)事件在SharePoint和OneDrive的业务', 76, 1);
INSERT INTO `security_event_library` VALUES (854, 'office365', 91543, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^13$，选项没有完整日志，表示Office 365：数据丢失保护(DLP)事件交换，当通过统一的DLP策略配置时。不支持基于交换传输规则的DLP事件。', 76, 1);
INSERT INTO `security_event_library` VALUES (855, 'office365', 91544, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^14$，选项没有完整日志，表示Office 365：SharePoint共享事件', 76, 1);
INSERT INTO `security_event_library` VALUES (856, 'office365', 91545, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^15$，选项没有完整日志，表示Office 365：Azure Active Directory中的安全令牌服务(STS)登录事件', 76, 1);
INSERT INTO `security_event_library` VALUES (857, 'office365', 91546, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^16$，选项没有完整日志，表示Office 365：来自Skype为了Business的公用交换式电话网(PSTN)事件', 76, 1);
INSERT INTO `security_event_library` VALUES (858, 'office365', 91547, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^17$，选项没有完整日志，表示Office 365：屏蔽了Skype商务版的用户事件', 76, 1);
INSERT INTO `security_event_library` VALUES (859, 'office365', 91548, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^18$，选项没有完整日志，表示Office 365：来自安全和合规中心的管理操作', 76, 1);
INSERT INTO `security_event_library` VALUES (860, 'office365', 91549, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^19$，选项没有完整日志，表示Office 365：聚合的交换邮箱审计事件', 76, 1);
INSERT INTO `security_event_library` VALUES (861, 'office365', 91550, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^20$，选项没有完整日志，表示Office 365：Power BI事件', 76, 1);
INSERT INTO `security_event_library` VALUES (862, 'office365', 91551, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^21$，选项没有完整日志，表示Office 365：动力学365事件', 76, 1);
INSERT INTO `security_event_library` VALUES (863, 'office365', 91552, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^22$，选项没有完整日志，表示Office 365：Yammer事件', 76, 1);
INSERT INTO `security_event_library` VALUES (864, 'office365', 91553, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^23$，选项没有完整日志，表示Office 365：Skype Business事件', 76, 1);
INSERT INTO `security_event_library` VALUES (865, 'office365', 91554, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^24$，选项没有完整日志，表示Office 365：通过在安全和合规中心运行内容搜索和管理电子发现案例执行的电子发现活动的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (866, 'office365', 91555, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^25$，选项没有完整日志，表示Office 365：来自微软团队的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (867, 'office365', 91557, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^29$，选项没有完整日志，表示Office 365：Exchange Online Protection和Microsoft Defender给Office 365的提交事件', 76, 1);
INSERT INTO `security_event_library` VALUES (868, 'office365', 91558, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^30$，选项没有完整日志，表示Office 365：Microsoft Power自动化(以前称为Microsoft Flow)事件', 76, 1);
INSERT INTO `security_event_library` VALUES (869, 'office365', 91559, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^31$，选项没有完整日志，表示Office 365：高级电子发现事件', 76, 1);
INSERT INTO `security_event_library` VALUES (870, 'office365', 91560, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^32$，选项没有完整日志，表示Office 365：Microsoft流事件', 76, 1);
INSERT INTO `security_event_library` VALUES (871, 'office365', 91561, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^33$，选项没有完整日志，表示Office 365：SharePoint中与DLP分类相关的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (872, 'office365', 91562, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^34$，选项没有完整日志，表示Office 365：Microsoft Defender为Office 365的相关活动事件', 76, 1);
INSERT INTO `security_event_library` VALUES (873, 'office365', 91563, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^35$，选项没有完整日志，表示Office 365：微软项目活动事件', 76, 1);
INSERT INTO `security_event_library` VALUES (874, 'office365', 91564, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^36$，选项没有完整日志，表示Office 365：SharePoint列表事件', 76, 1);
INSERT INTO `security_event_library` VALUES (875, 'office365', 91565, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^37$，选项没有完整日志，表示Office 365：SharePoint注释事件', 76, 1);
INSERT INTO `security_event_library` VALUES (876, 'office365', 91566, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^38$，选项没有完整日志，表示Office 365：与安全合规中心保留策略和保留标签相关的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (877, 'office365', 91567, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^39$，选项没有完整日志，表示Office 365：Kaizala事件', 76, 1);
INSERT INTO `security_event_library` VALUES (878, 'office365', 91568, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^40$，选项没有完整日志，表示Office 365：安全和合规性警报信号', 76, 1);
INSERT INTO `security_event_library` VALUES (879, 'office365', 91569, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^41$，选项没有完整日志，表示Office 365：来自Microsoft Defender的为Office 365的安全链接的时间封锁和封锁覆盖事件', 76, 1);
INSERT INTO `security_event_library` VALUES (880, 'office365', 91570, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^42$，选项没有完整日志，表示Office 365：与Office 365安全合规中心的洞察和报告相关的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (881, 'office365', 91571, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^43$，选项没有完整日志，表示Office 365：与在传输管道中检测已(手动或自动)标记为敏感度标签的电子邮件消息相关的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (882, 'office365', 91572, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^44$，选项没有完整日志，表示Office 365：工作场所分析事件', 76, 1);
INSERT INTO `security_event_library` VALUES (883, 'office365', 91573, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^45$，选项没有完整日志，表示Office 365：Power Apps事件', 76, 1);
INSERT INTO `security_event_library` VALUES (884, 'office365', 91574, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^46$，选项没有完整日志，表示Office 365：Power Apps的订阅计划事件', 76, 1);
INSERT INTO `security_event_library` VALUES (885, 'office365', 91576, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^48$，选项没有完整日志，表示Office 365：与数据分类内容浏览器相关的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (886, 'office365', 91577, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^49$，选项没有完整日志，表示Office 365：与Microsoft医疗保健团队中的患者应用程序相关的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (887, 'office365', 91578, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^50$，选项没有完整日志，表示Office 365：与MailItemsAccessed邮箱审计操作相关的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (888, 'office365', 91579, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^51$，选项没有完整日志，表示Office 365：与发送垃圾邮件保护相关的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (889, 'office365', 91580, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^52$，选项没有完整日志，表示Office 365：数据洞察REST API事件', 76, 1);
INSERT INTO `security_event_library` VALUES (890, 'office365', 91581, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^53$，选项没有完整日志，表示Office 365：与信息屏障策略应用相关的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (891, 'office365', 91582, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^54$，选项没有完整日志，表示Office 365：SharePoint list item事件', 76, 1);
INSERT INTO `security_event_library` VALUES (892, 'office365', 91583, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^55$，选项没有完整日志，表示Office 365：SharePoint list内容类型事件', 76, 1);
INSERT INTO `security_event_library` VALUES (893, 'office365', 91584, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^56$，选项没有完整日志，表示Office 365：SharePoint列表字段事件', 76, 1);
INSERT INTO `security_event_library` VALUES (894, 'office365', 91585, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^57$，选项没有完整日志，表示Office 365：团队管理事件', 76, 1);
INSERT INTO `security_event_library` VALUES (895, 'office365', 91586, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^58$，选项没有完整日志，表示Office 365：支持内部风险管理解决方案的信号的与人力资源数据相关的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (896, 'office365', 91587, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^59$，选项没有完整日志，表示Office 365：团队设备事件', 76, 1);
INSERT INTO `security_event_library` VALUES (897, 'office365', 91588, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^60$，选项没有完整日志，表示Office 365：团队分析事件', 76, 1);
INSERT INTO `security_event_library` VALUES (898, 'office365', 91590, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^62$，选项没有完整日志，表示Office 365：Microsoft Defender为Office 365的电子邮件活动事件', 76, 1);
INSERT INTO `security_event_library` VALUES (899, 'office365', 91591, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^63$，选项没有完整日志，表示Office 365：端点DLP事件', 76, 1);
INSERT INTO `security_event_library` VALUES (900, 'office365', 91592, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^64$，选项没有完整日志，表示Office 365：自动事件响应(航空)事件', 76, 1);
INSERT INTO `security_event_library` VALUES (901, 'office365', 91593, 2, '字段\"office365.RecordType\"，类型是\"osregex\"中有^65$，选项没有完整日志，表示Office 365：检疫事件', 76, 1);
INSERT INTO `security_event_library` VALUES (902, 'office365', 91594, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^66$，选项没有完整日志，表示Office 365：微软组织活动事件', 76, 1);
INSERT INTO `security_event_library` VALUES (903, 'office365', 91595, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^67$，选项没有完整日志，表示Office 365：应用审计事件', 76, 1);
INSERT INTO `security_event_library` VALUES (904, 'office365', 91596, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^68$，选项没有完整日志，表示Office 365：基于传播合规性攻击性语言模型的事件跟踪', 76, 1);
INSERT INTO `security_event_library` VALUES (905, 'office365', 91597, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^69$，选项没有完整日志，表示Office 365：客户密钥加密业务相关事件', 76, 1);
INSERT INTO `security_event_library` VALUES (906, 'office365', 91598, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^70$，选项没有完整日志，表示Office 365：Office文档敏感标签相关事件', 76, 1);
INSERT INTO `security_event_library` VALUES (907, 'office365', 91599, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^71$，选项没有完整日志，表示Office 365：SharePoint中的自动标记事件', 76, 1);
INSERT INTO `security_event_library` VALUES (908, 'office365', 91600, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^72$，选项没有完整日志，表示Office 365：SharePoint中的自动标记策略事件', 76, 1);
INSERT INTO `security_event_library` VALUES (909, 'office365', 91601, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^73$，选项没有完整日志，表示Office 365：团队改变事件', 76, 1);
INSERT INTO `security_event_library` VALUES (910, 'office365', 91602, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^75$，选项没有完整日志，表示Office 365：交换的自动标记事件', 76, 1);
INSERT INTO `security_event_library` VALUES (911, 'office365', 91603, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^76$，选项没有完整日志，表示Office 365：简介电子邮件活动事件', 76, 1);
INSERT INTO `security_event_library` VALUES (912, 'office365', 91604, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^77$，选项没有完整日志，表示Office 365：与在SharePoint和Exchange中执行搜索查询相关的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (913, 'office365', 91606, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^81$，选项没有完整日志，表示Office 365：微软防御端点事件', 76, 1);
INSERT INTO `security_event_library` VALUES (914, 'office365', 91607, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^82$，选项没有完整日志，表示Office 365：打开或重命名标记为敏感标签的文件时生成的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (915, 'office365', 91608, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^83$，选项没有完整日志，表示Office 365：当从文件中应用、更新或删除敏感标签时生成的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (916, 'office365', 91609, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^84$，选项没有完整日志，表示Office 365：打开或重命名标记为敏感标签的文件时生成的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (917, 'office365', 91610, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^85$，选项没有完整日志，表示Office 365：攻击模拟器事件', 76, 1);
INSERT INTO `security_event_library` VALUES (918, 'office365', 91611, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^86$，选项没有完整日志，表示Office 365：自动调查与响应(AIR)中与人工调查相关的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (919, 'office365', 91612, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^87$，选项没有完整日志，表示Office 365：安全和合规性RBAC事件', 76, 1);
INSERT INTO `security_event_library` VALUES (920, 'office365', 91613, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^88$，选项没有完整日志，表示Office 365：Microsoft Defender for Office 365中的攻击模拟器训练事件', 76, 1);
INSERT INTO `security_event_library` VALUES (921, 'office365', 91614, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^89$，选项没有完整日志，表示Office 365：自动调查和响应(AIR)中与管理行动有关的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (922, 'office365', 91616, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^91$，选项没有完整日志，表示Office 365：与支持内部风险管理解决方案的物理标记信号相关的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (923, 'office365', 91617, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^93$，选项没有完整日志，表示Office 365：Azure信息保护(AIP)扫描器事件', 76, 1);
INSERT INTO `security_event_library` VALUES (924, 'office365', 91618, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^94$，选项没有完整日志，表示Office 365：AIP敏感性标签事件', 76, 1);
INSERT INTO `security_event_library` VALUES (925, 'office365', 91619, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^95$，选项没有完整日志，表示Office 365：AIP保护事件', 76, 1);
INSERT INTO `security_event_library` VALUES (926, 'office365', 91620, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^96$，选项没有完整日志，表示Office 365：AIP文件删除事件', 76, 1);
INSERT INTO `security_event_library` VALUES (927, 'office365', 91621, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^97$，选项没有完整日志，表示Office 365：AIP心跳事件', 76, 1);
INSERT INTO `security_event_library` VALUES (928, 'office365', 91623, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^99$，选项没有完整日志，表示Office 365：与扫描文件共享中的敏感数据相关的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (929, 'office365', 91624, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^100$，选项没有完整日志，表示Office 365：与扫描SharePoint中的敏感数据相关的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (930, 'office365', 91625, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^101$，选项没有完整日志，表示Office 365：与使用Outlook on the web (OWA)搜索邮箱项目相关的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (931, 'office365', 91626, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^102$，选项没有完整日志，表示Office 365：与搜索组织的SharePoint首页相关的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (932, 'office365', 91627, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^103$，选项没有完整日志，表示Office 365：隐私洞察事件', 76, 1);
INSERT INTO `security_event_library` VALUES (933, 'office365', 91628, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^105$，选项没有完整日志，表示Office 365：MyAnalytics事件', 76, 1);
INSERT INTO `security_event_library` VALUES (934, 'office365', 91629, 3, '字段\"office365.RecordType\"，类型是\"osregex\"中有^106$，选项没有完整日志，表示Office 365：修改、删除用户的相关事件', 76, 1);
INSERT INTO `security_event_library` VALUES (935, 'office365', 91630, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^107$，选项没有完整日志，表示Office 365：交换DLP分类事件', 76, 1);
INSERT INTO `security_event_library` VALUES (936, 'office365', 91631, 4, '字段\"office365.RecordType\"，类型是\"osregex\"中有^109$，选项没有完整日志，表示Office 365：精确数据匹配(EDM)分类事件', 76, 1);
INSERT INTO `security_event_library` VALUES (937, 'office365', 91702, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^FileDownloaded$，选项没有完整日志，表示Office 365：用户下载文件', 76, 1);
INSERT INTO `security_event_library` VALUES (938, 'office365', 91703, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^PermissionLevelAdded$，选项没有完整日志，表示Office 365：已将权限级别添加到站点集合', 76, 1);
INSERT INTO `security_event_library` VALUES (939, 'office365', 91704, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^DocumentSensitivityMismatchDetected$，选项没有完整日志，表示Office 365：屏蔽分享邀请', 76, 1);
INSERT INTO `security_event_library` VALUES (940, 'office365', 91705, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^Add-MailboxPermission$，选项没有完整日志，表示Office 365：添加委托邮箱权限', 76, 1);
INSERT INTO `security_event_library` VALUES (941, 'office365', 91706, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^AddFolderPermissions$，选项没有完整日志，表示Office 365：为文件夹添加权限', 76, 1);
INSERT INTO `security_event_library` VALUES (942, 'office365', 91707, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^Send$，选项没有完整日志，表示Office 365：发送消息', 76, 1);
INSERT INTO `security_event_library` VALUES (943, 'office365', 91708, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^SendAs|SendOnBehalf$，选项没有完整日志，表示Office 365：使用不同权限发送的消息', 76, 1);
INSERT INTO `security_event_library` VALUES (944, 'office365', 91709, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^Add user.$，选项没有完整日志，表示Office 365：添加用户', 76, 1);
INSERT INTO `security_event_library` VALUES (945, 'office365', 91710, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^Update user.$，选项没有完整日志，表示Office 365：更新用户', 76, 1);
INSERT INTO `security_event_library` VALUES (946, 'office365', 91711, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^Add member to role.$，选项没有完整日志，表示Office 365：添加成员到角色', 76, 1);
INSERT INTO `security_event_library` VALUES (947, 'office365', 91712, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^Add group.$，选项没有完整日志，表示Office 365：添加组', 76, 1);
INSERT INTO `security_event_library` VALUES (948, 'office365', 91713, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^Add member to group.$，选项没有完整日志，表示Office 365：新增群组成员', 76, 1);
INSERT INTO `security_event_library` VALUES (949, 'office365', 91714, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^Add service principal credentials.$，选项没有完整日志，表示Office 365：向服务主体添加凭据', 76, 1);
INSERT INTO `security_event_library` VALUES (950, 'office365', 91715, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^CaseAdminUpdated$，选项没有完整日志，表示Office 365：更改了eDiscovery管理员成员资格', 76, 1);
INSERT INTO `security_event_library` VALUES (951, 'office365', 91716, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^CaseAdminAdded$，选项没有完整日志，表示Office 365：已创建eDiscovery管理员', 76, 1);
INSERT INTO `security_event_library` VALUES (952, 'office365', 91717, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^CaseAdded$，选项没有完整日志，表示Office 365：创建eDiscovery案例', 76, 1);
INSERT INTO `security_event_library` VALUES (953, 'office365', 91718, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^SearchCreated$，选项没有完整日志，表示Office 365：创建内容搜索', 76, 1);
INSERT INTO `security_event_library` VALUES (954, 'office365', 91719, 3, '字段\"office365.Operation\"，类型是\"osregex\"中有^QuarantineDelete$，选项没有完整日志，表示Office 365：删除隔离邮件', 76, 1);
INSERT INTO `security_event_library` VALUES (955, 'sqlserver', 85000, 4, '解码为sqlserver，表示SQL Server消息', 76, 1);
INSERT INTO `security_event_library` VALUES (956, 'sqlserver', 85001, 4, '匹配到Starting up database，表示启动数据库', 76, 1);
INSERT INTO `security_event_library` VALUES (957, 'sqlserver', 85002, 4, '匹配到Attempting to load library，表示尝试加载库', 76, 1);
INSERT INTO `security_event_library` VALUES (958, 'sqlserver', 85003, 4, '匹配到Server process ID is，表示SQL Server进程ID', 76, 1);
INSERT INTO `security_event_library` VALUES (959, 'sqlserver', 85004, 3, '匹配到Login succeeded for user，表示SQL Server登录成功', 76, 1);
INSERT INTO `security_event_library` VALUES (960, 'sqlserver', 85007, 4, '匹配到Using，表示SQL Server库的使用', 76, 1);
INSERT INTO `security_event_library` VALUES (961, 'sqlserver', 85010, 4, '匹配到FILESTREAM:，表示SQL Server文件流信息', 76, 1);
INSERT INTO `security_event_library` VALUES (962, 'mongodb', 85750, 4, '解码为mongodb，表示mongodb消息', 76, 1);
INSERT INTO `security_event_library` VALUES (963, 'mongodb', 85754, 4, '字段\"mongodb.severity\"中为D，表示调试消息', 76, 1);
INSERT INTO `security_event_library` VALUES (964, 'mongodb', 85755, 4, '字段\"mongodb.severity\"中为I，表示信息消息', 76, 1);
INSERT INTO `security_event_library` VALUES (965, 'mongodb', 85756, 4, '符合信息消息，字段\"mongodb.component\"中有NETWORK，匹配到connection accepted，表示MongoDB接受连接', 76, 1);
INSERT INTO `security_event_library` VALUES (966, 'mongodb', 85757, 4, '符合信息消息，字段\"mongodb.component\"中有NETWORK，匹配到end connection，表示MongoDB连接结束', 76, 1);
INSERT INTO `security_event_library` VALUES (967, 'mongodb', 85758, 4, '符合信息消息，字段\"mongodb.component\"中有ACCESS，匹配到Successfully authenticated，表示MongoDB身份验证成功', 76, 1);
INSERT INTO `security_event_library` VALUES (968, 'mysql_audit', 88000, 4, '解码为json，字段\"mysql_audit_log\"中有percona，选项没有完整日志，表示Percona服务器审计事件分组', 76, 1);
INSERT INTO `security_event_library` VALUES (969, 'mysql_audit', 88001, 4, '字段\"audit_record.name\"中有^Connect$，字段\"audit_record.status\"中有^0$，表示Percona audit身份验证成功', 76, 1);
INSERT INTO `security_event_library` VALUES (970, 'mysql_audit', 88002, 4, '字段\"audit_record.name\"中有^Quit$，表示Percona audit用户注销', 76, 1);
INSERT INTO `security_event_library` VALUES (971, 'mysql_audit', 88004, 4, '字段\"audit_record.name\"中有^Query$，字段\"audit_record.status\"中有^0$，表示Percona审计成功:$(audit_record.command_class) 声明', 76, 1);
INSERT INTO `security_event_library` VALUES (972, 'mysql_audit', 88005, 3, '字段\"audit_record.name\"中有^Query$，字段\"audit_record.command_class\"中有^drop|^alter|^insert|^update|^grant|^delete，表示Percona审计成功:$(audit_record.command_class) 声明', 76, 1);
INSERT INTO `security_event_library` VALUES (973, 'mysql_audit', 89050, 4, '解码为json，字段\"mysql_audit_log\"中有mcafee，表示MySQL事件分组的McAfee审计插件', 76, 1);
INSERT INTO `security_event_library` VALUES (974, 'mysql_audit', 89051, 4, '字段\"cmd\"为^Connect$，表示McAfee MySQL审计:身份验证尝试', 76, 1);
INSERT INTO `security_event_library` VALUES (975, 'mysql_audit', 89052, 4, '字段\"cmd\"为^Quit$，表示McAfee MySQL审计:用户注销', 76, 1);
INSERT INTO `security_event_library` VALUES (976, 'mysql_audit', 89054, 4, '状态为^0$，表示McAfee MySQL audit成功: $(cmd)语句', 76, 1);
INSERT INTO `security_event_library` VALUES (977, 'mysql_audit', 89055, 3, '字段\"cmd\"为^drop|^alter|^insert|^update|^grant|^delete，表示McAfee MySQL audit成功: $(cmd)语句', 76, 1);
INSERT INTO `security_event_library` VALUES (978, 'mariadb', 88100, 4, '解码为mariadb-syslog，表示MariaDB消息', 76, 1);
INSERT INTO `security_event_library` VALUES (979, 'mariadb', 88103, 4, '字段\"mariadb.type\"中有[NOTE]，表示MariaDB日志消息', 76, 1);
INSERT INTO `security_event_library` VALUES (980, 'oracledb', 89100, 4, '解码为oracledb-log，表示OracleDB事务', 76, 1);
INSERT INTO `security_event_library` VALUES (981, 'mysql_log', 50100, 4, '解码为mysql_log，表示mysql消息', 76, 1);
INSERT INTO `security_event_library` VALUES (982, 'mysql_log', 50105, 4, '匹配到^MySQL log: \\d+ \\S+ \\d+ Connect，表示MySQL：身份验证成功', 76, 1);
INSERT INTO `security_event_library` VALUES (983, 'mysql_log', 50107, 4, '匹配到^MySQL log: \\d+ \\S+ \\d+ Query，表示mysql查询', 76, 1);
INSERT INTO `security_event_library` VALUES (984, 'mysql_log', 50108, 4, '匹配到^MySQL log: \\d+ \\S+ \\d+ Quit，表示用户与数据库断开连接', 76, 1);
INSERT INTO `security_event_library` VALUES (985, 'mysql_log', 50120, 1, '匹配到mysqld ended|Shutdown complete，表示mysql关闭消息', 76, 1);
INSERT INTO `security_event_library` VALUES (986, 'mysql_log', 50121, 4, '匹配到mysqld started|mysqld restarted，表示mysql开启消息', 76, 1);
INSERT INTO `security_event_library` VALUES (987, 'postgresql_log', 50500, 4, '解码为postgresql_log，表示PostgreSQL消息', 76, 1);
INSERT INTO `security_event_library` VALUES (988, 'postgresql_log', 50501, 4, '状态为^LOG，表示PostgreSQL日志消息', 76, 1);
INSERT INTO `security_event_library` VALUES (989, 'postgresql_log', 50502, 4, '状态为^NOTICE|INFO，表示PostgreSQL信息消息', 76, 1);
INSERT INTO `security_event_library` VALUES (990, 'postgresql_log', 50505, 4, '状态为^DEBUG，表示PostgreSQL调试消息', 76, 1);
INSERT INTO `security_event_library` VALUES (991, 'postgresql_log', 50510, 4, '符合日志消息，匹配到duration: | statement: ，表示PostgreSQL数据库查询', 76, 1);
INSERT INTO `security_event_library` VALUES (992, 'postgresql_log', 50511, 4, '符合日志消息，匹配到connection authorized，表示PostgreSQL数据库身份验证成功', 76, 1);
INSERT INTO `security_event_library` VALUES (993, 'redis', 81300, 4, '解码为redis，表示Redis消息', 76, 1);
INSERT INTO `security_event_library` VALUES (994, 'redis', 81301, 4, '匹配到Server started，表示Redis开启', 76, 1);
INSERT INTO `security_event_library` VALUES (995, 'redis', 81302, 4, '匹配到Redis is now ready to exit, bye bye，表示Redis关闭', 76, 1);
INSERT INTO `security_event_library` VALUES (996, 'redis', 81304, 4, '匹配到Accepted和\\S+:\\d+，表示Redis客户端连接', 76, 1);
INSERT INTO `security_event_library` VALUES (997, 'redis', 81305, 4, '匹配到Client closed connection，表示Redis客户端连接关闭', 76, 1);
INSERT INTO `security_event_library` VALUES (998, 'usb', 81100, 4, '解码为kernel，id为usb，表示USB消息', 76, 1);
INSERT INTO `security_event_library` VALUES (999, 'usb', 81101, 4, '匹配到New USB device found，表示USB存储', 76, 1);
INSERT INTO `security_event_library` VALUES (1000, 'usb', 81102, 4, '匹配到USB disconnect，表示USB设备断开', 76, 1);
INSERT INTO `security_event_library` VALUES (1001, 'syslog,attacks', 40101, 1, '用户$SYS_USERS身份验证成功，表示系统用户成功登录系统', 76, 1);
INSERT INTO `security_event_library` VALUES (1002, 'syslog,attacks', 40105, 1, '匹配到changed by \\(\\(null\\)，表示\"Null\"用户更改了一些信息', 76, 1);
INSERT INTO `security_event_library` VALUES (1003, 'local,systemd', 40700, 4, '程序名为^systemd$|^systemctl$，表示为Systemd规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1004, 'local,systemd', 40701, 4, '匹配到Stale file handle$，表示Systemd过期文件处理', 76, 1);
INSERT INTO `security_event_library` VALUES (1005, 'syslog,smbd', 13100, 4, '解码为smbd，表示为smbd规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1006, 'syslog,smbd', 13106, 4, '解码为nmbd，表示nmbd规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1007, 'syslog,named', 12100, 4, '解码为named，表示named规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1008, 'syslog,named', 12107, 4, '匹配到update \\S+ denied，表示利用RFC2136动态协议进行DNS更新', 76, 1);
INSERT INTO `security_event_library` VALUES (1009, 'syslog,named', 12115, 4, '匹配到loaded serial|transferred serial，表示区域转移', 76, 1);
INSERT INTO `security_event_library` VALUES (1010, 'syslog,named', 12118, 4, '匹配到already exists previous definition，表示区域已复制', 76, 1);
INSERT INTO `security_event_library` VALUES (1011, 'syslog,named', 12119, 4, '匹配到starting BIND，表示启动BIND ', 76, 1);
INSERT INTO `security_event_library` VALUES (1012, 'syslog,named', 12121, 4, '匹配到zone \\S+: \\(master\\) removed，表示从主服务器移除Zone', 76, 1);
INSERT INTO `security_event_library` VALUES (1013, 'syslog,named', 12123, 4, '匹配到already exists previous definition，表示复制了Zone', 76, 1);
INSERT INTO `security_event_library` VALUES (1014, 'syslog,named', 12126, 4, '匹配都zone \\S+: \\(master\\) removed，表示从主服务器移除Zone', 76, 1);
INSERT INTO `security_event_library` VALUES (1015, 'syslog,named', 12128, 4, '匹配到^transfer of|AXFR started$，表示区域转移', 76, 1);
INSERT INTO `security_event_library` VALUES (1016, 'syslog,named', 12135, 4, '匹配到IN SOA -E，表示SOA -E中的域', 76, 1);
INSERT INTO `security_event_library` VALUES (1017, 'syslog,named', 12137, 4, '匹配到IN AXFR -，表示查询转移的zone的\"域\"', 76, 1);
INSERT INTO `security_event_library` VALUES (1018, 'syslog,named', 12138, 4, '匹配到IN A +，表示找到域名A记录', 76, 1);
INSERT INTO `security_event_library` VALUES (1019, 'syslog,named', 12139, 4, '匹配到client \\S+: bad zone transfer request: \\S+: non-authoritative zone \\(NOTAUTH\\)，表示坏区转移请求', 76, 1);
INSERT INTO `security_event_library` VALUES (1020, 'syslog,named', 12142, 4, '匹配到command channel listening on，表示命名命令通道正在监听', 76, 1);
INSERT INTO `security_event_library` VALUES (1021, 'syslog,named', 12143, 4, '匹配到automatic empty zone，表示Named已经自动创建了一个空区域', 76, 1);
INSERT INTO `security_event_library` VALUES (1022, 'syslog,msftp', 11500, 4, '解码为msftp，表示Microsoft ftp规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1023, 'syslog,msftp', 11501, 4, 'action为USER，表示MS-FTP:新建FTP连接', 76, 1);
INSERT INTO `security_event_library` VALUES (1024, 'syslog,msftp', 11503, 4, 'action为PASS，id为230，表示MS-FTP: FTP身份验证成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1025, 'syslog,vsftpd', 11400, 4, '解码为vsftpd，表示vsftpd规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1026, 'syslog,vsftpd', 11401, 4, '匹配到CONNECT: Client，表示vsftpd打开FTP会话', 76, 1);
INSERT INTO `security_event_library` VALUES (1027, 'syslog,vsftpd', 11402, 4, '匹配到OK LOGIN:，表示vsftpd FTP身份验证成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1028, 'syslog,vsftpd', 11404, 4, '匹配到OK UPLOAD: ，表示vsftpd:FTP服务器上传文件', 76, 1);
INSERT INTO `security_event_library` VALUES (1029, 'syslog,pure-ftpd', 11300, 4, '解码为pure-ftpd，表示pure-ftpd规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1030, 'syslog,pure-ftpd', 11301, 4, '匹配到[INFO] New connection from，表示pure-ftpd:新建FTP连接', 76, 1);
INSERT INTO `security_event_library` VALUES (1031, 'syslog,pure-ftpd', 11303, 4, '匹配到[INFO] Logout| [INFO] Timeout，表示pure-ftpd: FTP用户退出/超时退出', 76, 1);
INSERT INTO `security_event_library` VALUES (1032, 'syslog,pure-ftpd', 11304, 4, '匹配到[NOTICE] ，表示pure-ftpd: FTP通知消息', 76, 1);
INSERT INTO `security_event_library` VALUES (1033, 'syslog,pure-ftpd', 11305, 3, '匹配到[INFO] Can\'t change directory to，表示pure-ftpd:试图访问无效目录', 76, 1);
INSERT INTO `security_event_library` VALUES (1034, 'syslog,pure-ftpd', 11309, 4, '匹配到[INFO] \\S+ is now logged in| is now logged in，表示pure-ftpd:FTP身份验证成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1035, 'syslog,pure-ftpd', 11310, 4, '解码为pure-transfer，表示纯ftpd传输的规则分组', 76, 1);
INSERT INTO `security_event_library` VALUES (1036, 'syslog,pure-ftpd', 11311, 4, '纯ftpd传输的规则中，action为PUT，表示pure-ftpd:添加到ftpd的文件', 76, 1);
INSERT INTO `security_event_library` VALUES (1037, 'syslog,pure-ftpd', 11312, 4, '纯ftpd传输的规则中，action为GET，表示pure-ftpd:从ftpd检索的文件', 76, 1);
INSERT INTO `security_event_library` VALUES (1038, 'syslog,proftpd', 11200, 4, '解码为proftpd，表示proftpd规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1039, 'syslog,proftpd', 11201, 4, '匹配到FTP session opened.$，表示ProFTPD:打开FTP会话', 76, 1);
INSERT INTO `security_event_library` VALUES (1040, 'syslog,proftpd', 11202, 4, '匹配到FTP session closed.$，表示ProFTPD:关闭FTP会话', 76, 1);
INSERT INTO `security_event_library` VALUES (1041, 'syslog,proftpd', 11205, 4, '匹配到Login successful，表示ProFTPD: FTP身份验证成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1042, 'syslog,proftpd', 11213, 4, '匹配到connect from ，表示ProFTPD: 远程主机连接到FTP服务器', 76, 1);
INSERT INTO `security_event_library` VALUES (1043, 'syslog,ftpd', 11100, 4, '解码为ftpd，表示ftpd规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1044, 'syslog,ftpd', 11102, 4, '匹配到 created ，表示FTPD：通过FTP创建的文件', 76, 1);
INSERT INTO `security_event_library` VALUES (1045, 'syslog,ftpd', 11103, 4, '匹配到 deleted ，表示FTPD：通过FTP删除文件', 76, 1);
INSERT INTO `security_event_library` VALUES (1046, 'syslog,ftpd', 11104, 4, '匹配到FTPD: IMPORT file，表示FTPD:用户上传文件到服务器', 76, 1);
INSERT INTO `security_event_library` VALUES (1047, 'syslog,ftpd', 11105, 4, '匹配到FTPD: EXPORT file，表示FTPD:用户下载文件到服务器', 76, 1);
INSERT INTO `security_event_library` VALUES (1048, 'syslog,ftpd', 11106, 4, '匹配到FTP LOGIN FROM|connection from|connect from，表示FTPD:远程连接FTP服务器的主机', 76, 1);
INSERT INTO `security_event_library` VALUES (1049, 'windows,dhcp', 6300, 4, '解码为ms-dhcp-ipv4，表示MS-DHCP ipv4规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1050, 'windows,dhcp', 6301, 4, 'id为^00，表示MS-DHCP:日志开启', 76, 1);
INSERT INTO `security_event_library` VALUES (1051, 'windows,dhcp', 6302, 4, 'id为^01，表示MS-DHCP:日志停止', 76, 1);
INSERT INTO `security_event_library` VALUES (1052, 'windows,dhcp', 6304, 4, 'id为^10，表示MS-DHCP:给客户端新的IP地址', 76, 1);
INSERT INTO `security_event_library` VALUES (1053, 'windows,dhcp', 6305, 4, 'id为^11，表示MS-DHCP:客户端续签租约', 76, 1);
INSERT INTO `security_event_library` VALUES (1054, 'windows,dhcp', 6306, 4, 'id为^12，表示MS-DHCP:客户端续签租约', 76, 1);
INSERT INTO `security_event_library` VALUES (1055, 'windows,dhcp', 6307, 4, 'id为^13，表示MS-DHCP:在网络上发现IP地址正在被使用', 76, 1);
INSERT INTO `security_event_library` VALUES (1056, 'windows,dhcp', 6309, 3, 'id为^15，表示MS-DHCP:租约被拒绝', 76, 1);
INSERT INTO `security_event_library` VALUES (1057, 'windows,dhcp', 6310, 4, 'id为^16，表示MS-DHCP:删除租约', 76, 1);
INSERT INTO `security_event_library` VALUES (1058, 'windows,dhcp', 6311, 4, 'id为^17，表示MS-DHCP:租约已过期，且过期的DNS记录未被删除', 76, 1);
INSERT INTO `security_event_library` VALUES (1059, 'windows,dhcp', 6322, 4, 'id为^18，表示MS-DHCP:租约到期，删除DNS记录', 76, 1);
INSERT INTO `security_event_library` VALUES (1060, 'windows,dhcp', 6312, 4, 'id为^20，表示MS-DHCP:租用BOOTP地址给客户端', 76, 1);
INSERT INTO `security_event_library` VALUES (1061, 'windows,dhcp', 6313, 4, 'id为^21，表示MS-DHCP:租用给客户端的动态BOOTP地址', 76, 1);
INSERT INTO `security_event_library` VALUES (1062, 'windows,dhcp', 6315, 4, 'id为^23，表示MS-DHCP:确认BOOTP IP地址不再使用后，删除该地址', 76, 1);
INSERT INTO `security_event_library` VALUES (1063, 'windows,dhcp', 6316, 4, 'id为^24，表示MS-DHCP:IP地址清理操作已经开始', 76, 1);
INSERT INTO `security_event_library` VALUES (1064, 'windows,dhcp', 6317, 4, 'id为^25，表示MS-DHCP:IP地址清理统计', 76, 1);
INSERT INTO `security_event_library` VALUES (1065, 'windows,dhcp', 6318, 4, 'id为^30，表示MS-DHCP:向命名DNS服务器发起DNS更新请求', 76, 1);
INSERT INTO `security_event_library` VALUES (1066, 'windows,dhcp', 6320, 4, 'id为^32，表示MS-DHCP:DNS更新成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1067, 'windows,dhcp', 6323, 1, 'id为^33，表示MS-DHCP:由于NAP策略丢弃的报文', 76, 1);
INSERT INTO `security_event_library` VALUES (1068, 'windows,dhcp', 6321, 1, 'id为^5，表示MS-DHCP:50以上的代码用于检测非法服务器信息', 76, 1);
INSERT INTO `security_event_library` VALUES (1069, 'windows,dhcp', 6350, 4, '解码为ms-dhcp-ipv6，表示MS-DHCP ipv6规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1070, 'windows,dhcp', 6351, 4, 'id为^11000，表示MS-DHCP:征求', 76, 1);
INSERT INTO `security_event_library` VALUES (1071, 'windows,dhcp', 6352, 4, 'id为^11001|^11002，表示MS-DHCP：广告 ', 76, 1);
INSERT INTO `security_event_library` VALUES (1072, 'windows,dhcp', 6354, 4, 'id为^11003，表示MS-DHCP：确认', 76, 1);
INSERT INTO `security_event_library` VALUES (1073, 'windows,dhcp', 6355, 4, 'id为^11004，表示MS-DHCP：更新', 76, 1);
INSERT INTO `security_event_library` VALUES (1074, 'windows,dhcp', 6356, 4, 'id为^11005，表示MS-DHCP：重新绑定', 76, 1);
INSERT INTO `security_event_library` VALUES (1075, 'windows,dhcp', 6357, 3, 'id为^11006，表示MS-DHCP： DHCP衰减', 76, 1);
INSERT INTO `security_event_library` VALUES (1076, 'windows,dhcp', 6358, 4, 'id为^11007，表示MS-DHCP： 释放', 76, 1);
INSERT INTO `security_event_library` VALUES (1077, 'windows,dhcp', 6359, 4, 'id为^11008，表示MS-DHCP：信息请求', 76, 1);
INSERT INTO `security_event_library` VALUES (1078, 'windows,dhcp', 6360, 1, 'id为^11009，表示MS-DHCP：完整的范围', 76, 1);
INSERT INTO `security_event_library` VALUES (1079, 'windows,dhcp', 6361, 4, 'id为^11010，表示MS-DHCP：开始', 76, 1);
INSERT INTO `security_event_library` VALUES (1080, 'windows,dhcp', 6362, 3, 'id为^11011，表示MS-DHCP：停止', 76, 1);
INSERT INTO `security_event_library` VALUES (1081, 'windows,dhcp', 6363, 2, 'id为^11012，表示MS-DHCP：审计日志暂停', 76, 1);
INSERT INTO `security_event_library` VALUES (1082, 'windows,dhcp', 6364, 3, 'id为^11013，表示MS-DHCP：DHCP日志文件', 76, 1);
INSERT INTO `security_event_library` VALUES (1083, 'windows,dhcp', 6367, 4, 'id为^11016，表示MS-DHCP：删除客户端', 76, 1);
INSERT INTO `security_event_library` VALUES (1084, 'windows,dhcp', 6368, 4, 'id为^11017，表示MS-DHCP：未删除DNS记录', 76, 1);
INSERT INTO `security_event_library` VALUES (1085, 'windows,dhcp', 6369, 4, 'id为^11018，表示MS-DHCP：过期', 76, 1);
INSERT INTO `security_event_library` VALUES (1086, 'windows,dhcp', 6370, 4, 'id为^11019，表示MS-DHCP：过期和删除计数', 76, 1);
INSERT INTO `security_event_library` VALUES (1087, 'windows,dhcp', 6371, 4, 'id为^11020，表示MS-DHCP：开始数据库清理', 76, 1);
INSERT INTO `security_event_library` VALUES (1088, 'windows,dhcp', 6372, 4, 'id为^11021，表示MS-DHCP：数据库清理结束', 76, 1);
INSERT INTO `security_event_library` VALUES (1089, 'windows,dhcp', 6374, 4, 'id为^11024，表示MS-DHCP：AD授权服务', 76, 1);
INSERT INTO `security_event_library` VALUES (1090, 'syslog,sshd', 5700, 4, '解码为sshd，表示SSHD消息', 76, 1);
INSERT INTO `security_event_library` VALUES (1091, 'syslog,sshd', 5715, 4, '匹配到^Accepted|authenticated.$，表示sshd：身份验证成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1092, 'syslog,sshd', 5722, 4, '匹配到Connection closed，表示sshd：SSH连接关闭', 76, 1);
INSERT INTO `security_event_library` VALUES (1093, 'syslog,sshd', 5729, 4, '匹配到debug1: attempt，表示sshd：调试消息', 76, 1);
INSERT INTO `security_event_library` VALUES (1094, 'syslog,sshd', 5731, 3, '匹配到AKASSH_Version_Mapper1.，表示sshd：SSH扫描', 76, 1);
INSERT INTO `security_event_library` VALUES (1095, 'syslog,sshd', 5740, 3, '匹配到Connection reset by peer$，表示sshd：peer复位连接', 76, 1);
INSERT INTO `security_event_library` VALUES (1096, 'syslog,telnetd', 5600, 4, '解码为telnetd，表示telnetd规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1097, 'syslog,telnetd', 5602, 4, '匹配到connect from，表示telnetd: 远程主机建立telnet连接', 76, 1);
INSERT INTO `security_event_library` VALUES (1098, 'pam,syslog', 5500, 4, '解码为pam，表示pam_unix规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1099, 'pam,syslog', 5501, 4, '匹配到session opened for user，表示PAM：登录会话打开', 76, 1);
INSERT INTO `security_event_library` VALUES (1100, 'pam,syslog', 5502, 4, '匹配到session closed for user，表示PAM：登录会话关闭', 76, 1);
INSERT INTO `security_event_library` VALUES (1101, 'pam,syslog', 5521, 4, '登录会话打开，程序名为^CRON$，匹配到^pam_unix(cron:session): session opened for user，表示PAM：忽略恼人的Ubuntu/debian cron登录事件', 76, 1);
INSERT INTO `security_event_library` VALUES (1102, 'pam,syslog', 5522, 4, '登录会话关闭，程序名为^CRON$，匹配到^pam_unix(cron:session): session closed for user，表示PAM：忽略恼人的Ubuntu/debian cron登录事件', 76, 1);
INSERT INTO `security_event_library` VALUES (1103, 'pam,syslog', 5523, 4, '尝试使用无效用户登录，匹配到^pam_unix\\S+: check pass; user unknown$，表示PAM：忽略与用户或密码相关的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (1104, 'pam,syslog', 5555, 4, '匹配到: password changed for，表示PAM：用户修改密码', 76, 1);
INSERT INTO `security_event_library` VALUES (1105, 'pam,syslog', 5556, 4, '解码为unix_chkpwd，表示unix_chkpwd分组', 76, 1);
INSERT INTO `security_event_library` VALUES (1106, 'syslog,sendmail', 3100, 4, '解码为sendmail-reject，表示sendmail规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1107, 'syslog,sendmail', 3101, 4, '匹配到reject=，表示sendmail reject规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1108, 'syslog,sendmail', 3107, 3, '表示Sendmail被拒绝的消息', 76, 1);
INSERT INTO `security_event_library` VALUES (1109, 'syslog,sendmail', 3190, 4, '解码为smf-sav-reject，表示smf-sav sendmail milter规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1110, 'syslog,postfix', 3300, 4, '解码为postfix-reject，表示postfix reject规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1111, 'syslog,postfix', 3320, 4, '解码为postfix，表示postfix规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1112, 'syslog,postfix', 3334, 4, '匹配到^daemon started ，表示Postfix启动', 76, 1);
INSERT INTO `security_event_library` VALUES (1113, 'syslog,postfix', 3333, 3, '匹配到^terminating on signal，表示Postfix停止', 76, 1);
INSERT INTO `security_event_library` VALUES (1114, 'syslog,postfix', 3390, 4, '匹配到^clamsmtpd:，表示clamsmtpd规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1115, 'syslog,postfix', 3395, 4, '解码为postfix-warning，表示postfix警告规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1116, 'syslog,postfix', 3399, 4, '匹配到Read-only file system，表示Postfix:忽略权限警告', 76, 1);
INSERT INTO `security_event_library` VALUES (1117, 'syslog,spamd', 3500, 4, '匹配到^spamd，表示spamd规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1118, 'syslog,spamd', 3501, 4, '匹配到: result:，表示SPAMD结果消息(这里不太有用)', 76, 1);
INSERT INTO `security_event_library` VALUES (1119, 'syslog,spamd', 3502, 4, '匹配到checking message | processing message ，表示Spamd调试事件(读取消息)', 76, 1);
INSERT INTO `security_event_library` VALUES (1120, 'syslog,imapd', 3600, 4, '解码为imapd，表示imapd规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1121, 'syslog,imapd', 3602, 4, '匹配到Authenticated user=，表示Imapd用户登录', 76, 1);
INSERT INTO `security_event_library` VALUES (1122, 'syslog,imapd', 3603, 4, '匹配到Logout user=，表示Imapd用户登出', 76, 1);
INSERT INTO `security_event_library` VALUES (1123, 'syslog,mailscanner', 3700, 4, '解码为mailscanner，表示mailscanner规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1124, 'syslog,mailscanner', 3701, 4, 'action为not，表示mailscanner：非垃圾邮件。忽略。', 76, 1);
INSERT INTO `security_event_library` VALUES (1125, 'syslog,mailscanner', 3752, 4, '程序名为update.bad.phishing.sites，匹配到^Phishing bad sites list updated，表示忽略', 76, 1);
INSERT INTO `security_event_library` VALUES (1126, 'syslog', 1004, 3, '匹配到^exiting on signal，表示Syslogd退出(日志已停止)', 76, 1);
INSERT INTO `security_event_library` VALUES (1127, 'syslog', 1005, 3, '程序名是syslogd，匹配到^restart，表示Syslogd重启', 76, 1);
INSERT INTO `security_event_library` VALUES (1128, 'syslog', 1006, 3, '匹配到^syslogd \\S+ restart，表示Syslogd重启', 76, 1);
INSERT INTO `security_event_library` VALUES (1129, 'syslog', 1008, 3, '匹配到killed by SIGTERM，表示进程退出(已杀死)', 76, 1);
INSERT INTO `security_event_library` VALUES (1130, 'syslog', 1009, 4, '匹配到terminated without error|can\'t verify hostname: getaddrinfo|PPM exceeds tolerance，表示系统中存在未知问题时，忽略1002规则上已知的误报', 76, 1);
INSERT INTO `security_event_library` VALUES (1131, 'syslog', 2100, 4, '程序名是^automount|^mount，表示NFS规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1132, 'syslog', 2104, 4, '匹配到lookup for \\S+ failed，表示自动挂载信息', 76, 1);
INSERT INTO `security_event_library` VALUES (1133, 'syslog', 2506, 4, '匹配到^Authentication passed，表示syslog: Pop3认证通过', 76, 1);
INSERT INTO `security_event_library` VALUES (1134, 'syslog', 2507, 4, '解码为openldap，表示OpenLDAP组', 76, 1);
INSERT INTO `security_event_library` VALUES (1135, 'syslog', 2508, 4, '匹配到ACCEPT from，表示OpenLDAP连接打开', 76, 1);
INSERT INTO `security_event_library` VALUES (1136, 'syslog', 2550, 4, '解码为rshd，表示rshd消息', 76, 1);
INSERT INTO `security_event_library` VALUES (1137, 'syslog', 2701, 4, '程序名为^procmail，表示忽略procmail消息', 76, 1);
INSERT INTO `security_event_library` VALUES (1138, 'syslog', 2800, 4, '程序名是^smart，表示smartd的预匹配规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1139, 'syslog', 5100, 4, '程序名为^kernel，表示内核消息的预匹配规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1140, 'syslog', 5101, 4, '匹配到PCI: if you experience problems, try using option，表示来自内核的信息消息：PCI:如果您遇到问题，请尝试使用选项', 76, 1);
INSERT INTO `security_event_library` VALUES (1141, 'syslog', 5104, 2, '匹配到Promiscuous mode enabled|device \\S+ entered promiscuous mode，表示接口以promiscuous(sniffing) mode接入', 76, 1);
INSERT INTO `security_event_library` VALUES (1142, 'syslog', 5106, 4, '匹配到svc: unknown program 100227 (me 100003)，表示Linux和Solaris之间不支持的NFS协议', 76, 1);
INSERT INTO `security_event_library` VALUES (1143, 'syslog', 5107, 4, '匹配到svc: bad direction ，表示Linux和Solaris之间不支持的NFS协议', 76, 1);
INSERT INTO `security_event_library` VALUES (1144, 'syslog', 5113, 3, '匹配到Kernel log daemon terminating，表示系统正在关闭', 76, 1);
INSERT INTO `security_event_library` VALUES (1145, 'syslog', 5131, 4, '匹配到ADSL line is up，表示监视器ADSL线路已接通', 76, 1);
INSERT INTO `security_event_library` VALUES (1146, 'syslog', 5136, 4, '程序名为^mdadm，表示RAID普通管理事件', 76, 1);
INSERT INTO `security_event_library` VALUES (1147, 'syslog', 5140, 4, '程序名为^zpool，表示一般ZFS池事件', 76, 1);
INSERT INTO `security_event_library` VALUES (1148, 'syslog', 5200, 4, '匹配到^hpiod: unable to ParDevice，表示忽略生成无用日志的hpiod', 76, 1);
INSERT INTO `security_event_library` VALUES (1149, 'syslog', 2830, 4, '程序名为crond|crontab，表示Crontab规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1150, 'syslog', 2834, 3, '匹配到BEGIN EDIT，表示打开Crontab进行编辑', 76, 1);
INSERT INTO `security_event_library` VALUES (1151, 'syslog', 2832, 3, '匹配到REPLACE，表示Crontab条目更改', 76, 1);
INSERT INTO `security_event_library` VALUES (1152, 'syslog', 2833, 2, '匹配到REPLACE (root)，表示Root的crontab条目发生了变化', 76, 1);
INSERT INTO `security_event_library` VALUES (1153, 'syslog', 5300, 4, '解码为su，表示su消息的初始分组', 76, 1);
INSERT INTO `security_event_library` VALUES (1154, 'syslog', 5301, 3, '匹配到authentication failure; |failed|BAD su|^-，表示用户忘记密码后修改UID (user id)', 76, 1);
INSERT INTO `security_event_library` VALUES (1155, 'syslog', 5302, 2, '用户忘记密码后修改UID (user id)，将UID修改为root', 76, 1);
INSERT INTO `security_event_library` VALUES (1156, 'syslog', 5303, 4, '匹配到session opened for user root|^\'su root\'|^+ \\S+ \\S+\\proot$|^\\S+ to root on|^SU \\S+ \\S+ + \\S+ \\S+-root$，表示用户成功将UID修改为root', 76, 1);
INSERT INTO `security_event_library` VALUES (1157, 'syslog', 5304, 4, '匹配到session opened for user|succeeded for|^+|^\\S+ to |^SU \\S+ \\S+ + ，表示用户成功修改UID', 76, 1);
INSERT INTO `security_event_library` VALUES (1158, 'syslog', 5305, 4, '用户成功修改UID后第一次执行(su)', 76, 1);
INSERT INTO `security_event_library` VALUES (1159, 'syslog', 5306, 4, '匹配到unknown class，表示OpenBSD使用登录类，但使用了不合适的登录类。用户试图su到未知的类', 76, 1);
INSERT INTO `security_event_library` VALUES (1160, 'syslog', 5901, 2, '匹配到^new group，表示将新的分组添加到系统中', 76, 1);
INSERT INTO `security_event_library` VALUES (1161, 'syslog', 5902, 2, '匹配到^new user|^new account added，表示将新用户添加到系统中', 76, 1);
INSERT INTO `security_event_library` VALUES (1162, 'syslog', 5903, 4, '匹配到^delete user|^account deleted|^remove group，表示从系统中删除组或者用户', 76, 1);
INSERT INTO `security_event_library` VALUES (1163, 'syslog', 5904, 2, '匹配到^changed user，表示修改用户信息', 76, 1);
INSERT INTO `security_event_library` VALUES (1164, 'syslog', 5400, 4, '解码为sudo，表示sudo消息初始组', 76, 1);
INSERT INTO `security_event_library` VALUES (1165, 'syslog', 5402, 4, '匹配到; USER=root ; COMMAND=| ; USER=root ; TSID=\\S+ ; COMMAND=，表示sudo to ROOT执行成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1166, 'syslog', 5407, 4, '匹配到 ; USER=\\S+ ; COMMAND=| ; USER=\\S+ ; TSID=\\S+ ; COMMAND=，表示sudo执行成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1167, 'syslog', 5403, 3, '用户第一次执行sudo', 76, 1);
INSERT INTO `security_event_library` VALUES (1168, 'syslog', 9100, 4, '程序名为^pptpd，表示PPTPD消息', 76, 1);
INSERT INTO `security_event_library` VALUES (1169, 'syslog', 10100, 3, '属于身份验证成功分组，用户第一次成功登录', 76, 1);
INSERT INTO `security_event_library` VALUES (1170, 'syslog', 9200, 4, '程序名为^squid，表示Squid syslog消息组', 76, 1);
INSERT INTO `security_event_library` VALUES (1171, 'syslog', 9201, 4, '匹配到^ctx: enter level|^sslRead|^urlParse: Illegal |^httpReadReply: Request not yet |^httpReadReply: Excess data，表示Squid调试消息', 76, 1);
INSERT INTO `security_event_library` VALUES (1172, 'syslog', 2900, 4, '解码为dpkg-decoder，表示Dpkg (Debian Package)日志', 76, 1);
INSERT INTO `security_event_library` VALUES (1173, 'syslog', 2901, 4, '字段\"dpkg_status\"中有^install$，表示要求安装新的dpkg (Debian软件包)', 76, 1);
INSERT INTO `security_event_library` VALUES (1174, 'syslog', 2902, 3, '字段\"dpkg_status\"中有^status installed$，表示安装了新的dpkg (Debian软件包)', 76, 1);
INSERT INTO `security_event_library` VALUES (1175, 'syslog', 2903, 3, '字段\"dpkg_status\"中有^remove$|^purge$，表示删除了Dpkg (Debian软件包)', 76, 1);
INSERT INTO `security_event_library` VALUES (1176, 'syslog', 2904, 3, '字段\"dpkg_status\"中有^status half-configured$，表示Dpkg (Debian Package)配置了一半', 76, 1);
INSERT INTO `security_event_library` VALUES (1177, 'syslog', 2930, 4, '程序名为^yum，表示Yum日志', 76, 1);
INSERT INTO `security_event_library` VALUES (1178, 'syslog', 2931, 4, 'hostname为yum.log$，匹配到^Installed|^Updated|^Erased，表示Yum日志', 76, 1);
INSERT INTO `security_event_library` VALUES (1179, 'syslog', 2932, 3, '匹配到^Installed，表示安装新的Yum包', 76, 1);
INSERT INTO `security_event_library` VALUES (1180, 'syslog', 2933, 3, '匹配到^Updated，表示更新Yum包', 76, 1);
INSERT INTO `security_event_library` VALUES (1181, 'syslog', 2934, 3, '匹配到^Erased，表示删除Yum包', 76, 1);
INSERT INTO `security_event_library` VALUES (1182, 'syslog', 2935, 4, 'id为mptscsih，表示mptscrih规则分组', 76, 1);
INSERT INTO `security_event_library` VALUES (1183, 'syslog', 2936, 4, 'id为mptbase，表示mptbase规则分组', 76, 1);
INSERT INTO `security_event_library` VALUES (1184, 'syslog', 2940, 4, '程序名为^NetworkManager，表示NetworkManager分组', 76, 1);
INSERT INTO `security_event_library` VALUES (1185, 'syslog', 2942, 4, '匹配到g_slice_set_config: assertion `sys_page_size == 0\' failed，表示系统中存在未知问题时，匹配的表示不重要的GNU错误', 76, 1);
INSERT INTO `security_event_library` VALUES (1186, 'syslog', 2943, 4, '匹配到^nouveau ，表示新驱动分组', 76, 1);
INSERT INTO `security_event_library` VALUES (1187, 'syslog', 2944, 4, '匹配到DATA_ERROR BEGIN_END_ACTIVE$| DATA_ERROR$，表示新驱动分组中不重要的GNU错误', 76, 1);
INSERT INTO `security_event_library` VALUES (1188, 'syslog', 2960, 4, '解码为gpasswd，匹配到added by，表示用户加入组', 76, 1);
INSERT INTO `security_event_library` VALUES (1189, 'syslog', 2961, 3, '字段\"group\"中有sudo，表示用户加入sudo组', 76, 1);
INSERT INTO `security_event_library` VALUES (1190, 'syslog', 2962, 4, '解码为perdition，表示摧毁自定义应用组', 76, 1);
INSERT INTO `security_event_library` VALUES (1191, 'syslog', 2963, 4, '匹配到Connect: ，表示摧毁自定义应用组中，建立新的连接', 76, 1);
INSERT INTO `security_event_library` VALUES (1192, 'powershell', 91801, 4, '字段\"win.system.channel\"中有^Microsoft-Windows-PowerShell/Operational$，选项中没有日志，表示Powershell/操作通道的一组Windows规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1193, 'powershell', 91802, 4, '字段\"win.eventdata.ScriptBlockId\" 中有.+，type为\"pcre2\"，选项中没有日志，表示Powershell/操作通道的一组Windows规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1194, 'powershell', 91803, 1, '字段\"win.system.message\"中有CopyFromScreen，type为\"pcre2\"，选项中没有日志，表示PowerShell脚本调用的屏幕捕获方法', 76, 1);
INSERT INTO `security_event_library` VALUES (1195, 'powershell', 91805, 4, '字段win.eventdata.scriptBlockText\"中有(?i)Get-ADComputer，type为\"pcre2\"，选项中没有日志，表示已执行Powershell脚本Get-ADComputer', 76, 1);
INSERT INTO `security_event_library` VALUES (1196, 'powershell', 91806, 4, '字段win.eventdata.scriptBlockText\"中有(?i)Get-NetUser，type为\"pcre2\"，选项中没有日志，表示Powershell脚本“Get-NetUser executed”', 76, 1);
INSERT INTO `security_event_library` VALUES (1197, 'powershell', 91807, 4, '字段win.eventdata.scriptBlockText\"中有(?i)Get-ItemProperty，type为\"pcre2\"，选项中没有日志，表示Powershell脚本使用Get-ItemProperty进行查询', 76, 1);
INSERT INTO `security_event_library` VALUES (1198, 'powershell', 91808, 4, '字段win.eventdata.scriptBlockText\"中有(?i)-Path\\sHK(CU|LM)，type为\"pcre2\"，选项中没有日志，表示Powershell脚本查询注册表值', 76, 1);
INSERT INTO `security_event_library` VALUES (1199, 'powershell', 91809, 2, '字段win.eventdata.scriptBlockText\"中有(?i)FromBase64String，type为\"pcre2\"，选项中没有日志，表示Powershell脚本可能使用Base64解码方法', 76, 1);
INSERT INTO `security_event_library` VALUES (1200, 'powershell', 91810, 2, '字段win.eventdata.scriptBlockText\"中有(?i)CreateThread，type为\"pcre2\"，选项中没有日志，表示Powershell脚本可能使用CreateThread API执行可疑代码', 76, 1);
INSERT INTO `security_event_library` VALUES (1201, 'powershell', 91811, 3, '字段win.eventdata.scriptBlockText\"中有(?i)Expand-Archive，type为\"pcre2\"，选项中没有日志，表示Powershell脚本执行\"Expand-Archive\"', 76, 1);
INSERT INTO `security_event_library` VALUES (1202, 'powershell', 91812, 4, '字段win.eventdata.scriptBlockText\"中有(?i)Remove-Item\\s-Path，type为\"pcre2\"，选项中没有日志，表示Powershell脚本执行对象删除', 76, 1);
INSERT INTO `security_event_library` VALUES (1203, 'powershell', 91813, 4, '字段win.eventdata.scriptBlockText\"中有(?i)\\sHK(LM|CU)\\:，type为\"pcre2\"，选项中没有日志，表示Powershell脚本从对象中删除注册表项', 76, 1);
INSERT INTO `security_event_library` VALUES (1204, 'powershell', 91814, 3, '字段win.eventdata.scriptBlockText\"中有(?i)\\\\\\\\Software\\\\\\\\Classes\\\\\\\\Folder，type为\"pcre2\"，选项中没有日志，表示Powershell脚本删除一个自动开始条目注册表项', 76, 1);
INSERT INTO `security_event_library` VALUES (1205, 'powershell', 91815, 3, '字段win.eventdata.scriptBlockText\"中有(?i)Get-Process，type为\"pcre2\"，选项中没有日志，表示Powershell执行进程发现', 76, 1);
INSERT INTO `security_event_library` VALUES (1206, 'powershell', 91816, 3, '字段win.eventdata.scriptBlockText\"中有(?i)(\\$env\\:TEMP|\\$env\\:USERNAME|\\$env\\:COMPUTERNAME|\\$env\\:USERDOMAIN|\\$PID)，type为\"pcre2\"，选项中没有日志，表示Powershell脚本查询系统环境变量', 76, 1);
INSERT INTO `security_event_library` VALUES (1207, 'powershell', 91817, 3, '字段win.eventdata.scriptBlockText\"中有(?i)ConvertSidToStringSid，type为\"pcre2\"，选项中没有日志，表示Powershell脚本执行“ConvertSidToStringSid”API。可能的域SID枚举', 76, 1);
INSERT INTO `security_event_library` VALUES (1208, 'powershell', 91818, 3, '字段win.eventdata.scriptBlockText\"中有(?i)new-service，type为\"pcre2\"，选项中没有日志，表示Powershell脚本执行“New-Service”命令', 76, 1);
INSERT INTO `security_event_library` VALUES (1209, 'powershell', 91819, 3, '字段win.eventdata.scriptBlockText\"中有(?i)ChildItem，type为\"pcre2\"，选项中没有日志，表示Powershell脚本搜索文件系统', 76, 1);
INSERT INTO `security_event_library` VALUES (1210, 'powershell', 91820, 3, '字段win.eventdata.scriptBlockText\"中有(?i)recurse，type为\"pcre2\"，选项中没有日志，表示Powershell脚本递归地从文件系统搜索中收集文件', 76, 1);
INSERT INTO `security_event_library` VALUES (1211, 'powershell', 91821, 3, '字段win.eventdata.scriptBlockText\"中有(?i)Compress-Archive，type为\"pcre2\"，选项中没有日志，表示Powershell脚本根据文件系统搜索的结果创建一个压缩文件', 76, 1);
INSERT INTO `security_event_library` VALUES (1212, 'powershell', 91822, 1, '字段win.eventdata.scriptBlockText\"中有(?i)Invoke-Command，type为\"pcre2\"，选项中没有日志，表示Powershell脚本使用“Invoke-command”cmdlet来执行子脚本', 76, 1);
INSERT INTO `security_event_library` VALUES (1213, 'powershell', 91823, 1, '字段win.eventdata.scriptBlockText\"中有(?i)(ComputerName|Cn)，type为\"pcre2\"，选项中没有日志，表示Powershell脚本使用“Invoke-command”cmdlet在远程计算机上执行代码', 76, 1);
INSERT INTO `security_event_library` VALUES (1214, 'powershell', 91824, 3, '字段win.eventdata.scriptBlockText\"中有(?i)Get-Clipboard，type为\"pcre2\"，选项中没有日志，表示Powershell收集剪贴板数据', 76, 1);
INSERT INTO `security_event_library` VALUES (1215, 'powershell', 91825, 3, '字段win.eventdata.scriptBlockText\"中有(?i)Compress-7Zip，type为\"pcre2\"，选项中没有日志，表示Powershell执行文件压缩', 76, 1);
INSERT INTO `security_event_library` VALUES (1216, 'powershell', 91826, 3, '字段win.eventdata.scriptBlockText\"中有(?i)Copy-Item，type为\"pcre2\"，选项中没有日志，表示Powershell执行“Copy-Item”命令', 76, 1);
INSERT INTO `security_event_library` VALUES (1217, 'powershell', 91827, 3, '字段win.eventdata.scriptBlockText\"中有(?i)Set-WmiInstance，type为\"pcre2\"，选项中没有日志，表示Powershell执行Set-WmiInstance命令。可能创建或更新WMI实例', 76, 1);
INSERT INTO `security_event_library` VALUES (1218, 'powershell', 91828, 3, '字段win.eventdata.scriptBlockText\"中有(?i)Text\\.Encoding，type为\"pcre2\"，选项中没有日志，表示Powershell使用编码后的值创建或更新WMI实例', 76, 1);
INSERT INTO `security_event_library` VALUES (1219, 'powershell', 91829, 3, '字段win.eventdata.scriptBlockText\"中有(?i)GetComputerNameEx，type为\"pcre2\"，选项中没有日志，表示Powershell执行“GetComputerNameEx”。可能的系统配置发现', 76, 1);
INSERT INTO `security_event_library` VALUES (1220, 'powershell', 91830, 3, '字段win.eventdata.scriptBlockText\"中有(?i)NetWkstaGetInfo，type为\"pcre2\"，选项中没有日志，表示Powershell执行“networkstagetinfo”。可能的网络配置发现', 76, 1);
INSERT INTO `security_event_library` VALUES (1221, 'powershell', 91831, 3, '字段win.eventdata.scriptBlockText\"中有(?i)GetUserNameEx，type为\"pcre2\"，选项中没有日志，表示Powershell执行“GetUserNameEx”。可能的用户信息发现', 76, 1);
INSERT INTO `security_event_library` VALUES (1222, 'powershell', 91832, 3, '字段win.eventdata.scriptBlockText\"中有(?i)CreateToolhelp32Snapshot，type为\"pcre2\"，选项中没有日志，表示Powershell执行“CreateToolhelp32Snapshot”。可能的过程发现', 76, 1);
INSERT INTO `security_event_library` VALUES (1223, 'powershell', 91833, 4, '字段win.eventdata.scriptBlockText\"中有(?i)(Get-ChildItem|gci).+(env\\:windir|system32|windows)，type为\"pcre2\"，选项中没有日志，表示可能的发现活动：Powershell在系统文件夹上执行“Get-ChildItem”命令', 76, 1);
INSERT INTO `security_event_library` VALUES (1224, 'powershell', 91834, 3, '字段win.eventdata.scriptBlockText\"中有(?i)(SetCreationTime|SetLastAccessTime|SetLastWriteTime)，type为\"pcre2\"，选项中没有日志，表示Powershell执行命令修改文件时间戳，可能的timestomp尝试', 76, 1);
INSERT INTO `security_event_library` VALUES (1225, 'powershell', 91835, 3, '字段win.eventdata.scriptBlockText\"中有(?i)AntiVirusProduct，type为\"pcre2\"，选项中没有日志，表示Powershell篡改WMI杀毒产品类-杀毒软件发现', 76, 1);
INSERT INTO `security_event_library` VALUES (1226, 'powershell', 91836, 4, '字段win.eventdata.scriptBlockText\"中有(?i)\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Uninstall，type为\"pcre2\"，选项中没有日志，表示Powershell篡改软件安装信息系统注册表', 76, 1);
INSERT INTO `security_event_library` VALUES (1227, 'powershell', 91837, 3, '字段win.eventdata.scriptBlockText\"中有(?i)(Get-Content.+\\-Stream|IEX|Invoke-Expresion)，type为\"pcre2\"，选项中没有日志，表示Powershell执行“Get-Content -Stream or invoke - expression”。可能的字符串作为代码执行', 76, 1);
INSERT INTO `security_event_library` VALUES (1228, 'powershell', 91838, 3, '字段win.eventdata.scriptBlockText\"中有(?i)(gwmi|get-wmiobject).+ Win32_BIOS，type为\"pcre2\"，选项中没有日志，表示Powershell查询Win32_BIOS。可能的沙盒检测活动', 76, 1);
INSERT INTO `security_event_library` VALUES (1229, 'powershell', 91839, 3, '字段win.eventdata.scriptBlockText\"中有(?i)(gwmi|get-wmiobject).+ Win32_ComputerSystem，type为\"pcre2\"，选项中没有日志，表示Powershell查询Win32_ComputerSystem。可能的系统发现活动', 76, 1);
INSERT INTO `security_event_library` VALUES (1230, 'powershell', 91840, 3, '字段win.eventdata.scriptBlockText\"中有(?i)(gwmi|get-wmiobject).+ Win32_PnPEntity，type为\"pcre2\"，选项中没有日志，表示Powershell查询Win32_PnPEntity。可能的设备/适配器发现活动', 76, 1);
INSERT INTO `security_event_library` VALUES (1231, 'powershell', 91841, 3, '字段win.eventdata.scriptBlockText\"中有(?i)(gwmi|get-wmiobject).+ Win32_Process，type为\"pcre2\"，选项中没有日志，表示Powershell查询Win32_Process。可能的过程发现活动', 76, 1);
INSERT INTO `security_event_library` VALUES (1232, 'powershell', 91842, 3, '字段win.eventdata.scriptBlockText\"中有(?i)Get-Item \\-Path，type为\"pcre2\"，选项中没有日志，表示Powershell执行“Get-Item -Path”。尝试查看路径中的文件的脚本', 76, 1);
INSERT INTO `security_event_library` VALUES (1233, 'powershell', 91843, 4, '字段win.eventdata.scriptBlockText\"中有(?i)New-ItemProperty.+\\-Path，type为\"pcre2\"，选项中没有日志，表示Powershell执行“New-ItemProperty -Path”。可能添加新项目到注册表', 76, 1);
INSERT INTO `security_event_library` VALUES (1234, 'powershell', 91844, 1, '字段win.eventdata.scriptBlockText\"中有(?i)SOFTWARE\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Run，type为\"pcre2\"，选项中没有日志，表示可能添加新的项目到Windows启动注册表', 76, 1);
INSERT INTO `security_event_library` VALUES (1235, 'powershell', 91845, 2, '字段win.eventdata.scriptBlockText\"中有(?i)Microsoft.Office.Interop.Outlook，type为\"pcre2\"，选项中没有日志，表示Outlook插件是由powershell加载的，可能用于收集电子邮件', 76, 1);
INSERT INTO `security_event_library` VALUES (1236, 'powershell', 91846, 2, '字段win.eventdata.scriptBlockText\"中有(?i)::CreateFromDirectory，type为\"pcre2\"，选项中没有日志，表示Powershell采用。net压缩方法，进行可能的数据提取操作', 76, 1);
INSERT INTO `security_event_library` VALUES (1237, 'audit_detections', 92600, 4, '字段\"audit.exe\"中有python，type为\"pcre2\"，表示执行python脚本', 76, 1);
INSERT INTO `security_event_library` VALUES (1238, 'audit_detections', 92601, 3, '匹配type\"pcre2\"中有a\\d=\"\\w+\\.py\".+cwd=\"/tmp\"|a\\d=\"/tmp/.+\\.py，表示在/tmp/文件夹下执行python脚本', 76, 1);
INSERT INTO `security_event_library` VALUES (1239, 'audit_detections', 92603, 3, '字段\"audit.command\"，type为\"pcre2\"中有scp，字段\"audit.file.name\"，type为\"pcre2\"中有.+，表示使用SCP通过SSH将一个文件复制到这个系统', 76, 1);
INSERT INTO `security_event_library` VALUES (1240, 'audit_detections', 92604, 3, '字段\"audit.execve.a0\"，type为\"pcre2\"中有ps，字段\"audit.execve.a1\"，type为\"pcre2\"中有^(?=.*a)(?=.*x)，表示使用ps命令查询所有用户运行的进程', 76, 1);
INSERT INTO `security_event_library` VALUES (1241, 'audit_detections', 92605, 3, '字段\"audit.execve.a0\"，type为\"pcre2\"中有ls，字段\"audit.execve.a2\"，type为\"pcre2\"中有^(?=.*a)(?=.*R)，表示使用ls命令对所有文件进行递归查询', 76, 1);
INSERT INTO `security_event_library` VALUES (1242, 'windows', 64100, 4, '字段\"win.system.channel\"中有^File Replication Service$，表示用于远程访问的Windows规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1243, 'windows', 64102, 4, '字段\"win.system.eventID\"中有^20158$，表示远程访问登录成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1244, 'windows', 64104, 4, '字段\"win.system.channel\"中有^Microsoft-Windows-TerminalServices$，表示终端服务的Windows规则', 76, 1);
INSERT INTO `security_event_library` VALUES (1245, 'windows', 64105, 4, '字段\"win.system.eventID\"中有^200$|^300$|^302$，表示TS网关登录成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1246, 'windows', 64106, 4, '字段\"win.system.message\"中有\\.*The database engine has successfully completed recovery steps|The database engine is initiating recovery steps|A missing flash device has reappeared，表示忽略规则64105:与TS网关无关', 76, 1);
INSERT INTO `security_event_library` VALUES (1247, 'windows_firewall', 67001, 4, '字段\"win.system.severityValue\"中有^INFORMATION$，表示Windows防火墙与高级安全信息事件', 76, 1);
INSERT INTO `security_event_library` VALUES (1248, 'windows_firewall', 67004, 3, '字段\"win.system.eventID\"中有^2003$，字段\"win.eventdata.settingType\"中有^1$，字段\"win.eventdata.settingValueString\"中有^Yes$，表示高级安全的Windows防火墙：启用Windows防御防火墙', 76, 1);
INSERT INTO `security_event_library` VALUES (1249, 'windows_firewall', 67005, 3, '字段\"win.system.eventID\"中有^2003$，字段\"win.eventdata.settingType\"中有^1$，字段\"win.eventdata.settingValueString\"中有^No$，表示高级安全的Windows防火墙：禁用Windows防御防火墙', 76, 1);
INSERT INTO `security_event_library` VALUES (1250, 'windows_firewall', 67006, 3, '字段\"win.system.eventID\"中有^2004$，表示高级安全Windows防火墙:$(win.eventdata.ruleId)规则已添加到Windows防御防火墙例外列表', 76, 1);
INSERT INTO `security_event_library` VALUES (1251, 'windows_firewall', 67007, 3, '字段\"win.system.eventID\"中有^2005$，表示高级安全Windows防火墙:$(win.eventdata.ruleId)规则已在Windows防御防火墙例外列表中被修改', 76, 1);
INSERT INTO `security_event_library` VALUES (1252, 'windows_firewall', 67008, 3, '字段\"win.system.eventID\"中有^2006$，表示高级安全Windows防火墙:$(win.eventdata.ruleId)规则已在Windows防御防火墙例外列表中删除', 76, 1);
INSERT INTO `security_event_library` VALUES (1253, 'windows_system', 61100, 4, '字段\"win.system.severityValue\"中有^INFORMATION$，表示Windows系统信息事件', 76, 1);
INSERT INTO `security_event_library` VALUES (1254, 'windows_system', 61104, 4, '字段\"win.system.eventID\"中有^7040$，表示修改服务启动类型', 76, 1);
INSERT INTO `security_event_library` VALUES (1255, 'windows_system', 61108, 3, '字段\"win.system.eventID\"中有^7022$，字段\"win.system.providerName\"中有^Service Control Manager$，表示Windows搜索服务在启动时挂起', 76, 1);
INSERT INTO `security_event_library` VALUES (1256, 'windows_system', 61113, 4, '字段\"win.system.providerName\"中有^Browser$，表示浏览器事件', 76, 1);
INSERT INTO `security_event_library` VALUES (1257, 'windows_system', 61115, 4, '字段\"win.system.eventID\"中有^2550$，表示日志含义浏览器服务设置为“MaintainServerList=No”', 76, 1);
INSERT INTO `security_event_library` VALUES (1258, 'windows_system', 61116, 4, '字段\"win.system.eventID\"中有^8003$，表示浏览器收到了服务器通知', 76, 1);
INSERT INTO `security_event_library` VALUES (1259, 'windows_system', 61117, 3, '字段\"win.system.eventID\"中有^8004$，表示当它已经是一个主浏览器时，已经提交了一个请求来促进计算机备份', 76, 1);
INSERT INTO `security_event_library` VALUES (1260, 'windows_system', 61118, 3, '字段\"win.system.eventID\"中有^8005$，表示浏览器已收到服务器通知，表明该计算机是主浏览器，但此计算机不是主浏览器', 76, 1);
INSERT INTO `security_event_library` VALUES (1261, 'windows_system', 61125, 3, '字段\"win.system.eventID\"中有^8012$，表示浏览器驱动程序收到了一个选举包', 76, 1);
INSERT INTO `security_event_library` VALUES (1262, 'windows_system', 61135, 3, '字段\"win.system.eventID\"中有^8033$，表示由于主浏览器被停止，浏览器在网络上强制进行选举', 76, 1);
INSERT INTO `security_event_library` VALUES (1263, 'windows_system', 61136, 3, '字段\"win.system.eventID\"中有^8035$，表示浏览器在网络上强制进行选举，因为域控制器(或服务器)改变了它的角色', 76, 1);
INSERT INTO `security_event_library` VALUES (1264, 'windows_system', 61138, 3, '字段\"win.system.eventID\"中有^7045$，表示创建新的Windows服务', 76, 1);
INSERT INTO `security_event_library` VALUES (1265, 'win_wdefender', 62100, 4, 'Windows系统通道字段匹配到\"Microsoft-Windows-Windows Defender/Operational:\"&&win.system.severityValue字段匹配到\"INFORMATION\"---Windows Defender信息事件', 76, 1);
INSERT INTO `security_event_library` VALUES (1266, 'win_wdefender', 62107, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"1000\"---Windows Defender扫描反恶意扫描已开始', 76, 1);
INSERT INTO `security_event_library` VALUES (1267, 'win_wdefender', 62108, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"1001\"---Windows Defender扫描反恶意扫描已完成', 76, 1);
INSERT INTO `security_event_library` VALUES (1268, 'win_wdefender', 62109, 3, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"1002\"---Windows Defender扫描反恶意扫描在完成前已停止', 76, 1);
INSERT INTO `security_event_library` VALUES (1269, 'win_wdefender', 62110, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"1003\"---Windows Defender扫描反恶意扫描已暂停', 76, 1);
INSERT INTO `security_event_library` VALUES (1270, 'win_wdefender', 62111, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"1004\"---Windows Defender扫描反恶意扫描继续', 76, 1);
INSERT INTO `security_event_library` VALUES (1271, 'win_wdefender', 62114, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"1007\"---Windows Defender反恶意软件采取措施保护系统免受恶意软件或可能有害软件的攻击', 76, 1);
INSERT INTO `security_event_library` VALUES (1272, 'win_wdefender', 62116, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"1009\"---Windows Defender反恶意软件从软件隔离区恢复了项目', 76, 1);
INSERT INTO `security_event_library` VALUES (1273, 'win_wdefender', 62118, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"1011\"---Windows Defender反恶意软件从软件隔离区删除了项目', 76, 1);
INSERT INTO `security_event_library` VALUES (1274, 'win_wdefender', 62120, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"1013\"---Windows Defender反恶意软件删除了恶意软件或可能有害软件的历史记录', 76, 1);
INSERT INTO `security_event_library` VALUES (1275, 'win_wdefender', 62124, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"1117\"---Windows Defender反恶意软件采取操作保护您免受可能有害的软件的攻击', 76, 1);
INSERT INTO `security_event_library` VALUES (1276, 'win_wdefender', 62127, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"1120\"---Windows Defender反恶意软件已推断出威胁资源的哈希值', 76, 1);
INSERT INTO `security_event_library` VALUES (1277, 'win_wdefender', 62128, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"1150\"---Windows Defender反恶意软件正在运行且处于健康状态', 76, 1);
INSERT INTO `security_event_library` VALUES (1278, 'win_wdefender', 62129, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"1151\"---Windows Defender Endpoint Protection终端健康报告', 76, 1);
INSERT INTO `security_event_library` VALUES (1279, 'win_wdefender', 62130, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"2000\"---Windows Defender反恶意软件定义已成功更新', 76, 1);
INSERT INTO `security_event_library` VALUES (1280, 'win_wdefender', 62132, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"2002\"---Windows Defender反恶意软件引擎已成功更新', 76, 1);
INSERT INTO `security_event_library` VALUES (1281, 'win_wdefender', 62137, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"2007\"---Windows Defender反恶意软件平台即将更新', 76, 1);
INSERT INTO `security_event_library` VALUES (1282, 'win_wdefender', 62138, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"2010\"---Windows Defender反恶意软件引擎使用动态签名服务来获取其他定义', 76, 1);
INSERT INTO `security_event_library` VALUES (1283, 'win_wdefender', 62139, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"2011\"---Windows Defender DSS删除了过时的动态定义', 76, 1);
INSERT INTO `security_event_library` VALUES (1284, 'win_wdefender', 62141, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"2013\"---Windows Defender动态签名服务删除了所有动态定义', 76, 1);
INSERT INTO `security_event_library` VALUES (1285, 'win_wdefender', 62142, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"2020\"---Windows Defender反恶意软件引擎下载了干净的文件', 76, 1);
INSERT INTO `security_event_library` VALUES (1286, 'win_wdefender', 62144, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"2030\"---Windows Defender已下载反恶意软件引擎并配置为在下次系统重新启动时脱机运行', 76, 1);
INSERT INTO `security_event_library` VALUES (1287, 'win_wdefender', 62146, 3, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"2040\"---Windows Defender对此操作系统的反恶意软件支持即将结束', 76, 1);
INSERT INTO `security_event_library` VALUES (1288, 'win_wdefender', 62147, 2, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"2041\"---Windows Defender对此操作系统的反恶意软件支持已结束', 76, 1);
INSERT INTO `security_event_library` VALUES (1289, 'win_wdefender', 62148, 2, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"2042\"---Windows Defender反恶意软件引擎不再支持此操作系统', 76, 1);
INSERT INTO `security_event_library` VALUES (1290, 'win_wdefender', 62151, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"5000\"---Windows Defender防病毒实时保护已启用', 76, 1);
INSERT INTO `security_event_library` VALUES (1291, 'win_wdefender', 62152, 3, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"5001\"---Windows Defender防病毒实时保护已禁用', 76, 1);
INSERT INTO `security_event_library` VALUES (1292, 'win_wdefender', 62153, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"5004\"---Windows Defender防病毒实时保护功能配置已更改', 76, 1);
INSERT INTO `security_event_library` VALUES (1293, 'win_wdefender', 62154, 3, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"5007\"---Windows Defender反恶意软件平台功能配置已更改', 76, 1);
INSERT INTO `security_event_library` VALUES (1294, 'win_wdefender', 62156, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"5009\"---Windows Defender启用针对恶意软件和其他可能有害的软件的防病毒扫描', 76, 1);
INSERT INTO `security_event_library` VALUES (1295, 'win_wdefender', 62157, 2, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"5010\"---Windows Defender禁用针对恶意软件和其他可能有害的软件的防病毒扫描', 76, 1);
INSERT INTO `security_event_library` VALUES (1296, 'win_wdefender', 62158, 4, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"5011\"---Windows Defender已启用病毒防病毒扫描', 76, 1);
INSERT INTO `security_event_library` VALUES (1297, 'win_wdefender', 62159, 2, 'Windows系统通道信息事件&&win.system.eventID字段匹配到\"5012\"---Windows Defender已禁用病毒防病毒扫描', 76, 1);
INSERT INTO `security_event_library` VALUES (1298, 'win_wdefender', 62160, 3, 'Windows系统通道警告事件&&win.system.eventID字段匹配到\"5100\"---Windows Defender反恶意软件平台即将到期', 76, 1);
INSERT INTO `security_event_library` VALUES (1299, 'ms_logs', 83200, 3, 'Windows系统日志事件&&WinEvtLog日志内容匹配到\"Security: INFORMATION(1102)\"---审核日志已清除', 76, 1);
INSERT INTO `security_event_library` VALUES (1300, 'ms_logs', 83201, 3, 'Windows系统日志事件&&WinEvtLog日志内容匹配到\"System: INFORMATION(104): Microsoft-Windows-Eventlog:\"---Windows 日志文件已清除', 76, 1);
INSERT INTO `security_event_library` VALUES (1301, 'ms_logs', 83202, 3, 'Windows系统日志事件&&WinEvtLog日志内容匹配到\"System: INFORMATION(6005): \"---事件日志服务已启动', 76, 1);
INSERT INTO `security_event_library` VALUES (1302, 'win-ms_logs', 63100, 4, 'Windows系统通道字段匹配到\"Eventlog\"或者\"Microsoft-Windows-Eventlog\"&&win.system.severityValue字段匹配到\"INFORMATION\"---Windows 事件日志信息事件', 76, 1);
INSERT INTO `security_event_library` VALUES (1303, 'win-ms_logs', 63108, 4, 'Windows系统通道字段匹配到\"Microsoft-Windows-Eventlog\"&&win.system.severityValue字段匹配到\"INFORMATION\"---Windows 事件日志信息事件', 76, 1);
INSERT INTO `security_event_library` VALUES (1304, 'win-ms_logs', 63103, 3, 'Windows系统日志信息事件&&win.system.eventID字段匹配到\"1102\"---审核日志已清除', 76, 1);
INSERT INTO `security_event_library` VALUES (1305, 'win-ms_logs', 63104, 3, 'Windows系统日志信息事件&&win.system.eventID字段匹配到\"104\"---Windows 日志文件已清除', 76, 1);
INSERT INTO `security_event_library` VALUES (1306, 'win-ms_logs', 63105, 3, 'Windows系统日志信息事件&&win.system.eventID字段匹配到\"6005\"---事件日志服务已启动', 76, 1);
INSERT INTO `security_event_library` VALUES (1307, 'ms_ipsec', 18651, 2, 'Windows审核成功事件&&ID匹配到\"4646\"---IKE DoS 防护模式已启动', 76, 1);
INSERT INTO `security_event_library` VALUES (1308, 'ms_ipsec', 18655, 3, 'Windows审核成功事件&&ID匹配到\"4960\"---IPsec 丢弃未通过完整性检查的入站数据包', 76, 1);
INSERT INTO `security_event_library` VALUES (1309, 'ms_ipsec', 18656, 2, 'Windows审核成功事件&&ID匹配到\"4961\"或者\"4962\"---IPsec 丢弃重播检查失败的入站数据包', 76, 1);
INSERT INTO `security_event_library` VALUES (1310, 'ms_ipsec', 18659, 3, 'Windows审核成功事件&&ID匹配到\"4976\"---主模式协商时，IPsec收到无效协商报文', 76, 1);
INSERT INTO `security_event_library` VALUES (1311, 'ms_ipsec', 18660, 3, 'Windows审核成功事件&&ID匹配到\"4977\"---快速模式协商时，IPsec收到无效协商报文', 76, 1);
INSERT INTO `security_event_library` VALUES (1312, 'ms_ipsec', 18661, 3, 'Windows审核成功事件&&ID匹配到\"4978\"---扩展模式协商时，IPsec收到无效协商报文', 76, 1);
INSERT INTO `security_event_library` VALUES (1313, 'ms_ipsec', 18662, 2, 'Windows审核成功事件&&ID匹配到\"5453\"---扩展模式协商时，IPsec收到无效协商报文', 76, 1);
INSERT INTO `security_event_library` VALUES (1314, 'win-base', 60000, 4, 'Windows规则组', 76, 1);
INSERT INTO `security_event_library` VALUES (1315, 'win-base', 60001, 4, 'Windows规则组&&win.system.channel匹配到\"Security\"---Windows安全通道规则组', 76, 1);
INSERT INTO `security_event_library` VALUES (1316, 'win-base', 60002, 4, 'Windows规则组&&win.system.channel匹配到\"System\"---Windows系统通道规则组', 76, 1);
INSERT INTO `security_event_library` VALUES (1317, 'win-base', 60003, 4, 'Windows规则组&&win.system.channel匹配到\"Application\"---Windows应用通道规则组', 76, 1);
INSERT INTO `security_event_library` VALUES (1318, 'win-base', 60004, 4, 'Windows规则组&&win.system.channel匹配到\"Microsoft-Windows-Sysmon/Operational\"---Windows监控通道规则组', 76, 1);
INSERT INTO `security_event_library` VALUES (1319, 'win-base', 60005, 4, 'Windows规则组&&win.system.channel匹配到\"Microsoft-Windows-Windows Defender/Operational\"---Windows Defender通道规则组', 76, 1);
INSERT INTO `security_event_library` VALUES (1320, 'win-base', 60016, 4, 'Windows规则组&&win.system.channel匹配到\"Microsoft-Windows-Windows Firewall With Advanced Security/Firewall\"---Windows高级防火墙通道规则组', 76, 1);
INSERT INTO `security_event_library` VALUES (1321, 'win-base', 60006, 4, 'Windows应用通道规则组&&win.system.providerName匹配到“McLogEvent”---Windows McAfee通道规则组', 76, 1);
INSERT INTO `security_event_library` VALUES (1322, 'win-base', 60017, 4, 'Windows安全通道规则组&&win.system.providerName匹配到“Microsoft-Windows-Eventlog”---Windows 安全通道事件日志规则组', 76, 1);
INSERT INTO `security_event_library` VALUES (1323, 'win-base', 60007, 4, 'Windows系统通道规则组&&win.system.providerName匹配到“Eventlog”或者“Microsoft-Windows-Eventlog”---Windows 系统通道事件日志规则组', 76, 1);
INSERT INTO `security_event_library` VALUES (1324, 'win-base', 60008, 4, 'Windows系统通道规则组&&win.system.providerName匹配到“Microsoft Antimalware”---Microsoft 安全要点规则组', 76, 1);
INSERT INTO `security_event_library` VALUES (1325, 'win-base', 60018, 4, 'Windows系统通道规则组&&win.system.providerName匹配到“Microsoft-Windows-WMI-Activity”---WMI规则组', 76, 1);
INSERT INTO `security_event_library` VALUES (1326, 'win-base', 60009, 4, 'Windows规则组&&win.system.severityValue匹配到\"INFORMATION\"---Windows信息事件', 76, 1);
INSERT INTO `security_event_library` VALUES (1327, 'win_event_channel', 92651, 4, 'Windows审计登录成功事件&&win.eventdata.ipAddress匹配到\"xxx.xxx.xxx.xxx\"&&无效地址匹配到\"127.0.0.1\"---远程用户登录成功，IP地址为xxx.xxx.xxx.xxx', 76, 1);
INSERT INTO `security_event_library` VALUES (1328, 'win_event_channel', 92653, 4, 'Windows远程用户登录&&win.eventdata.logonType匹配到\"10\"---用户使用RDP远程登录', 76, 1);
INSERT INTO `security_event_library` VALUES (1329, 'win_event_channel', 92654, 3, 'Windows WMI规则组&&win.system.eventID匹配到“5857”&&win.operation_StartedOperational.providerName匹配到“CIMWin32”---用于系统信息发现的 WMI 查询事件', 76, 1);
INSERT INTO `security_event_library` VALUES (1330, 'win-security', 60100, 4, 'Windows安全信息事件', 76, 1);
INSERT INTO `security_event_library` VALUES (1331, 'win-security', 60103, 4, 'Windows审计成功事件', 76, 1);
INSERT INTO `security_event_library` VALUES (1332, 'win-security', 60106, 4, 'Windows审计成功事件&&win.system.eventID匹配到\"528\"|\"540\"|\"673\"|\"4624\"|\"4769\"---Windows登录成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1333, 'win-security', 60108, 4, 'Windows审计成功事件&&win.system.eventID匹配到\"682\"|\"683\"|\"4778\"|\"4779\"---Window会话重新连接/断开', 76, 1);
INSERT INTO `security_event_library` VALUES (1334, 'win-security', 60109, 2, 'Windows审计成功事件&&win.system.eventID匹配到\"624\"|\"626\"|\"4720\"|\"4722\"---用户帐户已启用或已创建', 76, 1);
INSERT INTO `security_event_library` VALUES (1335, 'win-security', 60110, 2, 'Windows审计成功事件&&win.system.eventID匹配到\"628\"|\"642\"|\"685\"|\"4738\"|\"4781\"---用户帐户已更改', 76, 1);
INSERT INTO `security_event_library` VALUES (1336, 'win-security', 60111, 2, 'Windows审计成功事件&&win.system.eventID匹配到\"630\"|\"629\"|\"4725\"|\"4726\"---用户帐户已禁用或删除', 76, 1);
INSERT INTO `security_event_library` VALUES (1337, 'win-security', 60112, 2, 'Windows审计成功事件&&win.system.eventID匹配到\"612\"|\"643\"|\"4719\"|\"4907\"|\"4912\"|\"4719\"---Windows审计策略已更改', 76, 1);
INSERT INTO `security_event_library` VALUES (1338, 'win-security', 60113, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"632\"|\"4728\"|\"633\"|\"4729\"|\"636\"|\"4732\"|\"637\"|\"4733\"|\"639\"|\"4735\"|\"641\"|\"4737\"|\"637\"|\"4733\"|\"659\"|\"4755\"|\"660\"|\"4766\"|\"668\"|\"4764\"|\"649\"|\"4745\"|\"650\"|\"4746\"|\"651\"|\"4747\"|\"654\"|\"4750\"|\"655\"|\"4751\"|\"656\"|\"4752\"|\"659\"|\"4755\"|\"660\"|\"4756\"|\"661\"|\"4757\"|\"664\"|\"4760\"|\"665\"|\"4761\"|\"666\"|\"4762\"---群组帐号已更改', 76, 1);
INSERT INTO `security_event_library` VALUES (1339, 'win-security', 60114, 2, 'Windows审计成功事件&&win.system.eventID匹配到\"640\"---普通账户数据库已更改', 76, 1);
INSERT INTO `security_event_library` VALUES (1340, 'win-security', 60116, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"513\"|\"4609\"---Windows 正在关闭', 76, 1);
INSERT INTO `security_event_library` VALUES (1341, 'win-security', 60117, 2, 'Windows安全信息事件&&win.system.eventID匹配到\"1102\"---Windows审核日志已清除', 76, 1);
INSERT INTO `security_event_library` VALUES (1342, 'win-security', 60118, 4, 'Windows登录成功&&win.eventdata.workstationName匹配到字段&&win.eventdata.logonType匹配到\"2\"---Windows工作站登录成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1343, 'win-security', 60119, 4, 'Windows登录成功---用户首次登录系统', 76, 1);
INSERT INTO `security_event_library` VALUES (1344, 'win-security', 60121, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"646\"|\"645\"|\"647\"|\"4741\"|\"4742\"|\"4743\"---计算机帐户添加/更改/删除', 76, 1);
INSERT INTO `security_event_library` VALUES (1345, 'win-security', 60132, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"520\"|\"4616\"---系统时间已更改', 76, 1);
INSERT INTO `security_event_library` VALUES (1346, 'win-security', 60133, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"671\"|\"4767\"---用户帐户已解锁', 76, 1);
INSERT INTO `security_event_library` VALUES (1347, 'win-security', 60134, 2, 'Windows审计成功事件&&win.system.eventID匹配到\"631\"|\"635\"|\"658\"---已创建安全启用组', 76, 1);
INSERT INTO `security_event_library` VALUES (1348, 'win-security', 60135, 2, 'Windows审计成功事件&&win.system.eventID匹配到\"634\"|\"638\"|\"662\"---已删除安全启用组', 76, 1);
INSERT INTO `security_event_library` VALUES (1349, 'win-security', 60136, 4, 'Windows审计成功事件&&win.system.eventID匹配到\"4608\"---Windows 正在启动', 76, 1);
INSERT INTO `security_event_library` VALUES (1350, 'win-security', 60137, 4, 'Windows审计成功事件&&win.system.eventID匹配到\"538\"|\"551\"|\"4634\"|\"4647\"---Windows 用户注销', 76, 1);
INSERT INTO `security_event_library` VALUES (1351, 'win-security', 60138, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"631\"|\"4727\"|\"635\"|\"4731\"|\"658\"|\"4754\"|\"648\"|\"4744\"|\"653\"|\"4749\"|\"663\"|\"4759\"---已创建群组帐户', 76, 1);
INSERT INTO `security_event_library` VALUES (1352, 'win-security', 60139, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"634\"|\"4730\"|\"638\"|\"4734\"|\"662\"|\"4758\"|\"652\"|\"4748\"|\"657\"|\"4753\"|\"667\"|\"4763\"---已删除群组帐户', 76, 1);
INSERT INTO `security_event_library` VALUES (1353, 'win-security', 60140, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"631\"|\"4727\"---已创建启用安全性的群组账户', 76, 1);
INSERT INTO `security_event_library` VALUES (1354, 'win-security', 60141, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"632\"|\"4728\"---添加了启用安全性的全局组成员', 76, 1);
INSERT INTO `security_event_library` VALUES (1355, 'win-security', 60142, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"633\"|\"4729\"---移除了启用安全性的全局组成员', 76, 1);
INSERT INTO `security_event_library` VALUES (1356, 'win-security', 60143, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"635\"|\"4731\"---已创建启用安全性的本地组', 76, 1);
INSERT INTO `security_event_library` VALUES (1357, 'win-security', 60144, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"636\"|\"4732\"---添加了启用安全性的本地组成员', 76, 1);
INSERT INTO `security_event_library` VALUES (1358, 'win-security', 60145, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"637\"|\"4733\"---移除了启用安全性的本地组成员', 76, 1);
INSERT INTO `security_event_library` VALUES (1359, 'win-security', 60146, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"638\"|\"4734\"---已删除启用安全性的本地组', 76, 1);
INSERT INTO `security_event_library` VALUES (1360, 'win-security', 60147, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"639\"|\"4735\"---已更改启用安全性的本地组', 76, 1);
INSERT INTO `security_event_library` VALUES (1361, 'win-security', 60148, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"641\"|\"4737\"---已更改群组帐户', 76, 1);
INSERT INTO `security_event_library` VALUES (1362, 'win-security', 60149, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"658\"|\"4754\"---已创建启用安全性的通用组', 76, 1);
INSERT INTO `security_event_library` VALUES (1363, 'win-security', 60150, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"658\"|\"4754\"---已更改启用安全性的通用组', 76, 1);
INSERT INTO `security_event_library` VALUES (1364, 'win-security', 60151, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"660\"|\"4756\"---添加了启用安全性的通用组成员', 76, 1);
INSERT INTO `security_event_library` VALUES (1365, 'win-security', 60152, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"661\"|\"4757\"---移除了启用安全性的通用组成员', 76, 1);
INSERT INTO `security_event_library` VALUES (1366, 'win-security', 60153, 3, 'Windows审计成功事件&&win.system.eventID匹配到\"662\"|\"4758\"---已删除启用安全性的通用组', 76, 1);
INSERT INTO `security_event_library` VALUES (1367, 'win-security', 60154, 1, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-32-544\"---已更改管理员组', 76, 1);
INSERT INTO `security_event_library` VALUES (1368, 'win-security', 60155, 3, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-1-0\"---已更改所有组', 76, 1);
INSERT INTO `security_event_library` VALUES (1369, 'win-security', 60156, 1, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-9\"---已更改企业域控制器组', 76, 1);
INSERT INTO `security_event_library` VALUES (1370, 'win-security', 60157, 3, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-11\"---已更改经过身份验证的用户组', 76, 1);
INSERT INTO `security_event_library` VALUES (1371, 'win-security', 60158, 3, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-13\"---已更改终端服务器用户组', 76, 1);
INSERT INTO `security_event_library` VALUES (1372, 'win-security', 60159, 1, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-512\"---已更改域管理员组', 76, 1);
INSERT INTO `security_event_library` VALUES (1373, 'win-security', 60160, 3, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-513\"---已更改域用户组', 76, 1);
INSERT INTO `security_event_library` VALUES (1374, 'win-security', 60161, 4, 'Windows审计成功事件&&win.eventdata.subjectAccountName匹配到\"None\"---创建时添加的虚假组用户', 76, 1);
INSERT INTO `security_event_library` VALUES (1375, 'win-security', 60162, 1, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-514\"---已更改域访客组', 76, 1);
INSERT INTO `security_event_library` VALUES (1376, 'win-security', 60163, 3, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-515\"---已更改域计算机组', 76, 1);
INSERT INTO `security_event_library` VALUES (1377, 'win-security', 60164, 1, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-516\"---已更改域控制器组', 76, 1);
INSERT INTO `security_event_library` VALUES (1378, 'win-security', 60165, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-517\"---已更改证书发布者组', 76, 1);
INSERT INTO `security_event_library` VALUES (1379, 'win-security', 60166, 1, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-518\"---已更改架构管理员组', 76, 1);
INSERT INTO `security_event_library` VALUES (1380, 'win-security', 60167, 1, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-519\"---已更改企业管理员组', 76, 1);
INSERT INTO `security_event_library` VALUES (1381, 'win-security', 60168, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-520\"---已更改组策略创建所有者组', 76, 1);
INSERT INTO `security_event_library` VALUES (1382, 'win-security', 60169, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-553\"---已更改RAS 和 IAS 服务器组', 76, 1);
INSERT INTO `security_event_library` VALUES (1383, 'win-security', 60170, 3, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-545\"---已更改用户组', 76, 1);
INSERT INTO `security_event_library` VALUES (1384, 'win-security', 60171, 1, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-546\"---已更改访客组', 76, 1);
INSERT INTO `security_event_library` VALUES (1385, 'win-security', 60172, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-547\"---已更改高级用户组', 76, 1);
INSERT INTO `security_event_library` VALUES (1386, 'win-security', 60173, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-548\"---已更改帐户操作者组', 76, 1);
INSERT INTO `security_event_library` VALUES (1387, 'win-security', 60174, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-549\"---已更改服务器操作者组', 76, 1);
INSERT INTO `security_event_library` VALUES (1388, 'win-security', 60175, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-550\"---已更改打印操作者组', 76, 1);
INSERT INTO `security_event_library` VALUES (1389, 'win-security', 60176, 1, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-551\"---已更改备份操作者组', 76, 1);
INSERT INTO `security_event_library` VALUES (1390, 'win-security', 60177, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-552\"---已更改复制器组', 76, 1);
INSERT INTO `security_event_library` VALUES (1391, 'win-security', 60178, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-554\"---已更改Windows 2000 之前版本的兼容访问组', 76, 1);
INSERT INTO `security_event_library` VALUES (1392, 'win-security', 60179, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-555\"---已更改远程桌面用户组', 76, 1);
INSERT INTO `security_event_library` VALUES (1393, 'win-security', 60180, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-556\"---已更改网络配置操作者组', 76, 1);
INSERT INTO `security_event_library` VALUES (1394, 'win-security', 60181, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-557\"---已更改传入的林信任创建者组', 76, 1);
INSERT INTO `security_event_library` VALUES (1395, 'win-security', 60182, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-558\"---已更改性能监视器用户组', 76, 1);
INSERT INTO `security_event_library` VALUES (1396, 'win-security', 60183, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-559\"---已更改性能日志用户组', 76, 1);
INSERT INTO `security_event_library` VALUES (1397, 'win-security', 60184, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-560\"---已更改Windows 授权访问组', 76, 1);
INSERT INTO `security_event_library` VALUES (1398, 'win-security', 60185, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-561\"---已更改终端服务许可证服务器组', 76, 1);
INSERT INTO `security_event_library` VALUES (1399, 'win-security', 60186, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-562\"---已更改分布式 COM 用户组', 76, 1);
INSERT INTO `security_event_library` VALUES (1400, 'win-security', 60187, 1, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-498\"---已更改企业只读域控制器组', 76, 1);
INSERT INTO `security_event_library` VALUES (1401, 'win-security', 60188, 1, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-521\"---已更改只读域控制器组', 76, 1);
INSERT INTO `security_event_library` VALUES (1402, 'win-security', 60189, 1, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-569\"---已更改密码运算符组', 76, 1);
INSERT INTO `security_event_library` VALUES (1403, 'win-security', 60190, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-571\"---已更改允许的 RODC 密码复制组', 76, 1);
INSERT INTO `security_event_library` VALUES (1404, 'win-security', 60191, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-572\"---已更改拒绝的 RODC 密码复制组', 76, 1);
INSERT INTO `security_event_library` VALUES (1405, 'win-security', 60192, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-573\"---已更改事件日志读者组', 76, 1);
INSERT INTO `security_event_library` VALUES (1406, 'win-security', 60193, 2, 'Windows审计成功事件&&win.eventdata.targetSid匹配到或者包含\"S-1-5-21xxx-574\"---已更改证书服务 DCOM 访问组', 76, 1);
INSERT INTO `security_event_library` VALUES (1407, 'win-security', 60194, 4, 'Windows审计成功事件&&win.system.eventID匹配到\"528\"|\"538\"|\"540\"|\"4624\"&&win.eventdata.subjectUserName匹配到LOCAL SERVICE|NETWORK SERVICE|ANONYMOUS LOGON---Windows登录成功（忽略）', 76, 1);
INSERT INTO `security_event_library` VALUES (1408, 'win-security', 60200, 4, 'Window审计成功事件&&win.system.eventID匹配到\"4624\"&&win.eventdata.logonType匹配到\"8\"---IIS 网络明文登录成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1409, 'win-security', 60201, 4, 'Window审计成功事件&&win.system.eventID匹配到\"4634\"&&win.eventdata.logonType匹配到\"8\"---MS Exchange 用户注销', 76, 1);
INSERT INTO `security_event_library` VALUES (1410, 'win-security', 60202, 3, 'Window审计成功事件&&win.system.eventID匹配到\"634\"|\"4730\"---删除了启用安全性的本地组成员', 76, 1);
INSERT INTO `security_event_library` VALUES (1411, 'win-security', 60208, 2, 'Window审计成功事件&&win.system.eventID匹配到\"4646\"---已启动IKE DoS 防护模式', 76, 1);
INSERT INTO `security_event_library` VALUES (1412, 'win-security', 60212, 3, 'Window审计成功事件&&win.system.eventID匹配到\"4960\"---IPsec 丢弃未通过完整性检查的入站数据包', 76, 1);
INSERT INTO `security_event_library` VALUES (1413, 'win-security', 60213, 2, 'Window审计成功事件&&win.system.eventID匹配到\"4961\"|\"4962\"---IPsec 丢弃未通过重放检查的入站数据包', 76, 1);
INSERT INTO `security_event_library` VALUES (1414, 'win-security', 60216, 3, 'Window审计成功事件&&win.system.eventID匹配到\"4976\"---主模式协商时IPsec收到无效协商报文', 76, 1);
INSERT INTO `security_event_library` VALUES (1415, 'win-security', 60217, 3, 'Window审计成功事件&&win.system.eventID匹配到\"4977\"---快速模式协商时IPsec收到无效协商报文', 76, 1);
INSERT INTO `security_event_library` VALUES (1416, 'win-security', 60218, 3, 'Window审计成功事件&&win.system.eventID匹配到\"4978\"---扩展模式协商时IPsec收到无效协商报文', 76, 1);
INSERT INTO `security_event_library` VALUES (1417, 'win-security', 60224, 2, 'Window审计成功事件&&win.system.eventID匹配到\"4710\"---IPsec 服务被禁用', 76, 1);
INSERT INTO `security_event_library` VALUES (1418, 'win-application', 60600, 4, 'Windows规则组&&win.system.channel匹配到\"Application\"&&win.system.severityValue匹配到\"INFORMATION\"&&选项没有完整日志--Windows应用程序信息事件', 76, 1);
INSERT INTO `security_event_library` VALUES (1419, 'win-application', 60603, 3, 'Windows应用程序错误事件&&win.system.providerName匹配到\"chromoting\"&&win.system.message匹配到\\.*Access denied for client--Chrome远程桌面尝试-访问被拒绝$(win.eventdata.data)', 76, 1);
INSERT INTO `security_event_library` VALUES (1420, 'win-application', 60604, 3, 'Windows应用程序信息事件&&win.system.providerName匹配到\"chromoting\"&&win.system.eventID匹配到”1“--Chrome远程桌面尝试-客户端连接$（win.eventdata.data）', 76, 1);
INSERT INTO `security_event_library` VALUES (1421, 'win-application', 60605, 3, 'Windows应用程序信息事件&&win.system.providerName匹配到\"chromoting\"&&win.system.eventID匹配到”2“--Chrome远程桌面尝试-断开连接（$（win.eventdata.data））', 76, 1);
INSERT INTO `security_event_library` VALUES (1422, 'win-application', 60606, 3, 'Windows应用程序信息事件&&win.system.providerName匹配到\"chromoting\"&&win.system.eventID匹配到”5“--Chrome远程桌面尝试-从$（win.eventdata.data）开始连接', 76, 1);
INSERT INTO `security_event_library` VALUES (1423, 'win-application', 60607, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Microsoft-Windows-Defrag\"&&win.system.eventID匹配到”258“--磁盘碎片整理程序已成功完成分析', 76, 1);
INSERT INTO `security_event_library` VALUES (1424, 'win-application', 60608, 3, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Windows Error Reporting\"&&win.system.eventID匹配到”1001“--报告签名的摘要事件', 76, 1);
INSERT INTO `security_event_library` VALUES (1425, 'win-application', 60609, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"MsiInstaller\"--微软安装程序事件组', 76, 1);
INSERT INTO `security_event_library` VALUES (1426, 'win-application', 60610, 4, '微软安装程序事件组&&win.system.eventID匹配到”1040“--Windows安装程序开始安装进程', 76, 1);
INSERT INTO `security_event_library` VALUES (1427, 'win-application', 60611, 4, '微软安装程序事件组&&win.system.eventID匹配到”11724“或\"1034\"--$（win.eventdata.data）应用程序已卸载', 76, 1);
INSERT INTO `security_event_library` VALUES (1428, 'win-application', 60612, 4, '微软安装程序事件组&&win.system.eventID匹配到”11707“或\"1033\"--$（win.eventdata.data）应用程序已安装', 76, 1);
INSERT INTO `security_event_library` VALUES (1429, 'win-application', 60616, 4, '微软安装程序事件组&&win.system.eventID匹配到”1005“--Windows安装程序重新启动以完成配置', 76, 1);
INSERT INTO `security_event_library` VALUES (1430, 'win-application', 60617, 3, '微软安装程序事件组&&win.system.eventID匹配到”1007“--软件限制策略不允许安装', 76, 1);
INSERT INTO `security_event_library` VALUES (1431, 'win-application', 60618, 3, '微软安装程序事件组&&win.system.eventID匹配到”1008“--由于软件限制策略处理中的错误，不允许安装', 76, 1);
INSERT INTO `security_event_library` VALUES (1432, 'win-application', 60624, 4, '微软安装程序事件组&&win.system.eventID匹配到”1019“--已成功删除更新', 76, 1);
INSERT INTO `security_event_library` VALUES (1433, 'win-application', 60626, 4, '微软安装程序事件组&&win.system.eventID匹配到”1022“--更新安装成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1434, 'win-application', 60628, 3, '微软安装程序事件组&&win.system.eventID匹配到”1025“--其他程序正在使用的文件', 76, 1);
INSERT INTO `security_event_library` VALUES (1435, 'win-application', 60630, 4, '微软安装程序事件组&&win.system.eventID匹配到”1029“--产品安装或更新需要重新启动才能使所有更改生效', 76, 1);
INSERT INTO `security_event_library` VALUES (1436, 'win-application', 60631, 3, '微软安装程序事件组&&win.system.eventID匹配到”1030“--应用程序尝试安装受保护的Windows文件的最新版本', 76, 1);
INSERT INTO `security_event_library` VALUES (1437, 'win-application', 60632, 4, '微软安装程序事件组&&win.system.eventID匹配到”1031“--装配组件正在使用', 76, 1);
INSERT INTO `security_event_library` VALUES (1438, 'win-application', 60634, 4, '微软安装程序事件组&&win.system.eventID匹配到”1033“--Windows安装程序安装了该产品', 76, 1);
INSERT INTO `security_event_library` VALUES (1439, 'win-application', 60635, 4, '微软安装程序事件组&&win.system.eventID匹配到”1035“--Windows安装程序重新配置了产品', 76, 1);
INSERT INTO `security_event_library` VALUES (1440, 'win-application', 60636, 4, '微软安装程序事件组&&win.system.eventID匹配到”1036“--Windows安装程序安装了更新', 76, 1);
INSERT INTO `security_event_library` VALUES (1441, 'win-application', 60637, 4, '微软安装程序事件组&&win.system.eventID匹配到”1037“--Windows安装程序删除了一个更新', 76, 1);
INSERT INTO `security_event_library` VALUES (1442, 'win-application', 60638, 4, '微软安装程序事件组&&win.system.eventID匹配到”1038“--Windows安装程序需要重新启动系统', 76, 1);
INSERT INTO `security_event_library` VALUES (1443, 'win-application', 60639, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"LocationNotifications\"&&win.system.eventID匹配到”1“--程序从位置传感器或默认位置访问信息', 76, 1);
INSERT INTO `security_event_library` VALUES (1444, 'win-application', 60640, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Microsoft-Windows-Security-SPP\"--SPP事件组', 76, 1);
INSERT INTO `security_event_library` VALUES (1445, 'win-application', 60642, 4, 'SPP事件组&&win.system.eventID匹配到\"16384\"--已成功安排软件保护服务', 76, 1);
INSERT INTO `security_event_library` VALUES (1446, 'win-application', 60643, 4, 'SPP事件组&&win.system.eventID匹配到\"8197\"--SLUI.exe已启动', 76, 1);
INSERT INTO `security_event_library` VALUES (1447, 'win-application', 60644, 4, 'SPP事件组&&win.system.eventID匹配到\"1016\"--购买证明安装成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1448, 'win-application', 60647, 3, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Application Hang\"&&win.system.eventID匹配到”1002“--应用程序停止响应，程序关闭', 76, 1);
INSERT INTO `security_event_library` VALUES (1449, 'win-application', 60648, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Microsoft-Windows-Search\"--Windows搜索事件组', 76, 1);
INSERT INTO `security_event_library` VALUES (1450, 'win-application', 60655, 4, 'Windows搜索事件组&&win.system.eventID匹配到\"3052\"--爬虫请求被停止', 76, 1);
INSERT INTO `security_event_library` VALUES (1451, 'win-application', 60668, 4, 'Windows搜索事件组&&win.system.eventID匹配到\"1003\"--Windows搜索服务已启动', 76, 1);
INSERT INTO `security_event_library` VALUES (1452, 'win-application', 60669, 4, 'Windows搜索事件组&&win.system.eventID匹配到\"1013\"--Windows搜索服务已启动', 76, 1);
INSERT INTO `security_event_library` VALUES (1453, 'win-application', 60671, 4, 'Windows搜索事件组&&win.system.eventID匹配到\"1010\"--Windows搜索服务成功删除了旧的搜索索引', 76, 1);
INSERT INTO `security_event_library` VALUES (1454, 'win-application', 60672, 3, 'Windows应用程序警告事件&&win.system.providerName匹配到\"Microsoft-Windows-Search\"&&win.system.eventID匹配到”1008“--Windows搜索服务试图删除旧的搜索索引', 76, 1);
INSERT INTO `security_event_library` VALUES (1455, 'win-application', 60673, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Microsoft-Windows-Search-ProfileNotify\"&&win.system.eventID匹配到”1“--响应用户配置文件删除，成功删除了用户的Windows Search Service索引数据', 76, 1);
INSERT INTO `security_event_library` VALUES (1456, 'win-application', 60674, 4, 'Windows应用程序信息事件&&win.system.eventID匹配到\"16\"&&win.system.providerNamep匹配到\"Microsoft-Windows-Search-ProfileNotify\"---为用户创建默认配置', 76, 1);
INSERT INTO `security_event_library` VALUES (1457, 'win-application', 60675, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"VSS\"---VSS事件组', 76, 1);
INSERT INTO `security_event_library` VALUES (1458, 'win-application', 60676, 4, 'VSS事件&&win.system.eventID匹配到\"8224\"---由于空闲超时，VSS 服务正在关闭', 76, 1);
INSERT INTO `security_event_library` VALUES (1459, 'win-application', 60681, 4, 'VSS事件&&win.system.eventID匹配到\"18\"---安全模式不支持卷影复制', 76, 1);
INSERT INTO `security_event_library` VALUES (1460, 'win-application', 60699, 3, 'VSS事件&&win.system.eventID匹配到\"8211\"---作者尝试在安全模式下订阅', 76, 1);
INSERT INTO `security_event_library` VALUES (1461, 'win-application', 60702, 3, 'VSS事件&&win.system.eventID匹配到\"8224\"---由于空闲超时，VSS 服务正在关闭', 76, 1);
INSERT INTO `security_event_library` VALUES (1462, 'win-application', 60703, 4, 'VSS事件&&win.system.eventID匹配到\"8225\"---由于服务控制管理器的关闭事件，VSS 服务正在关闭', 76, 1);
INSERT INTO `security_event_library` VALUES (1463, 'win-application', 60715, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"System Restore\"---系统恢复事件组', 76, 1);
INSERT INTO `security_event_library` VALUES (1464, 'win-application', 60716, 3, 'Windows系统恢复事件&&win.system.eventID匹配到\"8216\"---跳过创建还原点，因为有以前的还原点可用', 76, 1);
INSERT INTO `security_event_library` VALUES (1465, 'win-application', 60718, 3, 'Windows系统恢复事件&&win.system.eventID匹配到\"8194\"---创建还原点成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1466, 'win-application', 60719, 4, 'Windows系统恢复事件&&win.system.eventID匹配到\"8196\"---系统恢复已启用', 76, 1);
INSERT INTO `security_event_library` VALUES (1467, 'win-application', 60720, 4, 'Windows系统恢复事件&&win.system.eventID匹配到\"8198\"---成功初始化系统还原', 76, 1);
INSERT INTO `security_event_library` VALUES (1468, 'win-application', 60721, 4, 'Windows系统恢复事件&&win.system.eventID匹配到\"8201\"---系统恢复成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1469, 'win-application', 60722, 4, 'Windows系统恢复事件&&win.system.eventID匹配到\"8211\"---已成功创建计划还原点', 76, 1);
INSERT INTO `security_event_library` VALUES (1470, 'win-application', 60726, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Microsoft-Windows-WMI\"---WMI事件组', 76, 1);
INSERT INTO `security_event_library` VALUES (1471, 'win-application', 60740, 4, 'WMI事件&&win.system.eventID匹配到\"63\"---提供程序已在 WMI 命名空间中注册', 76, 1);
INSERT INTO `security_event_library` VALUES (1472, 'win-application', 60747, 4, 'WMI事件&&win.system.eventID匹配到\"5615\"---WMI 服务启动成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1473, 'win-application', 60748, 4, 'WMI事件&&win.system.eventID匹配到\"5616\"---WMI 核心、提供程序子系统和事件子系统已成功初始化', 76, 1);
INSERT INTO `security_event_library` VALUES (1474, 'win-application', 60749, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Microsoft-Windows-EventSystem\"---EventSystem 事件组', 76, 1);
INSERT INTO `security_event_library` VALUES (1475, 'win-application', 60750, 4, 'EventSystem事件&&win.system.eventID匹配到\"4625\"&&win.eventdata.param1匹配到字段---EventSystem 抑制了重复的日志条目 $(win.eventdata.param1) 秒', 76, 1);
INSERT INTO `security_event_library` VALUES (1476, 'win-application', 60770, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Desktop Window Manager\"---桌面窗口管理器事件组', 76, 1);
INSERT INTO `security_event_library` VALUES (1477, 'win-application', 60774, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Microsoft-Windows-Winlogon\"---Winlogon 事件组', 76, 1);
INSERT INTO `security_event_library` VALUES (1478, 'win-application', 60777, 4, 'Winlogon事件&&win.system.eventID匹配到\"7002\"---客户体验改善计划的用户注销通知', 76, 1);
INSERT INTO `security_event_library` VALUES (1479, 'win-application', 60779, 3, 'Winlogon事件&&win.system.eventID匹配到\"4002\"---应用于登录用户的登录时间限制策略', 76, 1);
INSERT INTO `security_event_library` VALUES (1480, 'win-application', 60790, 3, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Microsoft-Windows-User Profiles Service\"&&win.system.eventID匹配到\"1530\"---注册表文件仍在被其他应用程序或服务使用', 76, 1);
INSERT INTO `security_event_library` VALUES (1481, 'win-application', 60791, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"User Profile\"---用户配置事件组', 76, 1);
INSERT INTO `security_event_library` VALUES (1482, 'win-application', 60793, 3, '用户配置事件&&win.system.eventID匹配到\"1511\"---找不到本地配置文件，使用临时配置文件进行日志记录', 76, 1);
INSERT INTO `security_event_library` VALUES (1483, 'win-application', 60794, 4, '用户配置事件&&win.system.eventID匹配到\"1525\"---在漫游配置文件共享上启用离线缓存', 76, 1);
INSERT INTO `security_event_library` VALUES (1484, 'win-application', 60795, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"ESENT\"---ESENT 事件组', 76, 1);
INSERT INTO `security_event_library` VALUES (1485, 'win-application', 60796, 3, 'ESENT事件&&win.system.eventID匹配到\"103\"---数据库引擎停止了实例', 76, 1);
INSERT INTO `security_event_library` VALUES (1486, 'win-application', 60797, 4, 'ESENT事件&&win.system.eventID匹配到\"327\"---数据库引擎分离了数据库', 76, 1);
INSERT INTO `security_event_library` VALUES (1487, 'win-application', 60798, 4, 'ESENT事件&&win.system.eventID匹配到\"326\"---数据库引擎附加了一个数据库', 76, 1);
INSERT INTO `security_event_library` VALUES (1488, 'win-application', 60803, 4, 'ESENT事件&&win.system.eventID匹配到\"100\"---数据库引擎已启动', 76, 1);
INSERT INTO `security_event_library` VALUES (1489, 'win-application', 60804, 3, 'ESENT事件&&win.system.eventID匹配到\"101\"---数据库引擎已停止', 76, 1);
INSERT INTO `security_event_library` VALUES (1490, 'win-application', 60805, 4, 'ESENT事件&&win.system.eventID匹配到\"102\"---数据库引擎正在启动一个新实例', 76, 1);
INSERT INTO `security_event_library` VALUES (1491, 'win-application', 60806, 4, 'ESENT事件&&win.system.eventID匹配到\"210\"---开始完整备份', 76, 1);
INSERT INTO `security_event_library` VALUES (1492, 'win-application', 60807, 4, 'ESENT事件&&win.system.eventID匹配到\"300\"---数据库引擎正在启动恢复步骤', 76, 1);
INSERT INTO `security_event_library` VALUES (1493, 'win-application', 60808, 4, 'ESENT事件&&win.system.eventID匹配到\"301\"---数据库引擎正在重播日志文件 C:\\Winnt\\system32\\wins\\j50.log', 76, 1);
INSERT INTO `security_event_library` VALUES (1494, 'win-application', 60809, 4, 'ESENT事件&&win.system.eventID匹配到\"302\"---数据库引擎已完成恢复步骤', 76, 1);
INSERT INTO `security_event_library` VALUES (1495, 'win-application', 60817, 4, 'ESENT事件&&win.system.eventID匹配到\"609\"---数据库索引清理启动', 76, 1);
INSERT INTO `security_event_library` VALUES (1496, 'win-application', 60819, 3, 'ESENT事件&&win.system.eventID匹配到\"623\"---此实例的版本存储已达到其最大值', 76, 1);
INSERT INTO `security_event_library` VALUES (1497, 'win-application', 60820, 4, 'ESENT事件&&win.system.eventID匹配到\"2001\"---卷影复制 2 冻结开始', 76, 1);
INSERT INTO `security_event_library` VALUES (1498, 'win-application', 60821, 3, 'ESENT事件&&win.system.eventID匹配到\"2003\"---卷影复制 2 冻结已停止', 76, 1);
INSERT INTO `security_event_library` VALUES (1499, 'win-application', 60823, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Microsoft-Windows-CEIP\"&&win.system.eventID匹配到\"1005\"---客户体验改善计划数据成功整合', 76, 1);
INSERT INTO `security_event_library` VALUES (1500, 'win-application', 60824, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Microsoft-Windows-CAPI2\"---CAPI2 事件组', 76, 1);
INSERT INTO `security_event_library` VALUES (1501, 'win-application', 60828, 4, 'CAPI2事件&&win.system.eventID匹配到\"1\"---第三方根证书自动更新成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1502, 'win-application', 60829, 4, 'CAPI2事件&&win.system.eventID匹配到\"2\"---成功自动更新检索第三方根列表 cab', 76, 1);
INSERT INTO `security_event_library` VALUES (1503, 'win-application', 60830, 4, 'CAPI2事件&&win.system.eventID匹配到\"4\"---成功自动更新检索第三方根证书', 76, 1);
INSERT INTO `security_event_library` VALUES (1504, 'win-application', 60832, 4, 'CAPI2事件&&win.system.eventID匹配到\"7\"---成功自动更新检索第三方根列表序列号', 76, 1);
INSERT INTO `security_event_library` VALUES (1505, 'win-application', 60833, 4, 'CAPI2事件&&win.system.eventID匹配到\"12\"---第三方根证书自动删除成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1506, 'win-application', 60834, 4, 'CAPI2事件&&win.system.eventID匹配到\"13\"---第三方根证书自动属性更新成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1507, 'win-application', 60838, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Microsoft-Windows-MSDTC\"---MSDTC事件组', 76, 1);
INSERT INTO `security_event_library` VALUES (1508, 'win-application', 60842, 3, 'MSDTC事件&&win.system.eventID匹配到\"4101\"---MS DTC 与本地 DTC 具有相同的唯一标识', 76, 1);
INSERT INTO `security_event_library` VALUES (1509, 'win-application', 60846, 4, 'MSDTC事件&&win.system.eventID匹配到\"4105\"---Microsoft 分布式事务协调器服务已成功删除', 76, 1);
INSERT INTO `security_event_library` VALUES (1510, 'win-application', 60861, 4, 'MSDTC事件&&win.system.eventID匹配到\"4129\"---XA 事务管理器尝试定位“GetXaSwitch”函数', 76, 1);
INSERT INTO `security_event_library` VALUES (1511, 'win-application', 60868, 3, 'MSDTC事件&&win.system.eventID匹配到\"4142\"---日志客户端已报告采取检查点', 76, 1);
INSERT INTO `security_event_library` VALUES (1512, 'win-application', 60869, 4, 'MSDTC事件&&win.system.eventID匹配到\"4147\"---MS DTC 已确定群集服务已配置', 76, 1);
INSERT INTO `security_event_library` VALUES (1513, 'win-application', 60885, 4, 'MSDTC事件&&win.system.eventID匹配到\"4193\"---资源管理器执行恢复', 76, 1);
INSERT INTO `security_event_library` VALUES (1514, 'win-application', 60908, 3, 'MSDTC事件&&win.system.eventID匹配到\"4240\"|\"4241\"---MS DTC 事务管理器发送了一个提示“ABORT COMMAND”', 76, 1);
INSERT INTO `security_event_library` VALUES (1515, 'win-application', 60909, 4, 'MSDTC事件&&win.system.eventID匹配到\"4242\"|\"4243\"---MS DTC 事务管理器发送了一个提示“PUSH COMMAND”', 76, 1);
INSERT INTO `security_event_library` VALUES (1516, 'win-application', 60910, 4, 'MSDTC事件&&win.system.eventID匹配到\"4244\"|\"4245\"---MS DTC 事务管理器发送了一个提示“PREPARE COMMAND”', 76, 1);
INSERT INTO `security_event_library` VALUES (1517, 'win-application', 60911, 4, 'MSDTC事件&&win.system.eventID匹配到\"4246\"|\"4247\"---MS DTC 事务管理器发送了一个提示“COMMIT, QUERY, or RECONNECT COMMAND”', 76, 1);
INSERT INTO `security_event_library` VALUES (1518, 'win-application', 60912, 3, 'MSDTC事件&&win.system.eventID匹配到\"4248\"---MS DTC 事务管理器收到提示“错误命令”', 76, 1);
INSERT INTO `security_event_library` VALUES (1519, 'win-application', 60913, 3, 'MSDTC事件&&win.system.eventID匹配到\"4249\"---MS DTC 事务管理器收到活动事务的未知或意外 TIP 命令', 76, 1);
INSERT INTO `security_event_library` VALUES (1520, 'win-application', 60914, 3, 'MSDTC事件&&win.system.eventID匹配到\"4352\"---MS DTC 事务管理器收到“已准备”事务的提示“错误命令”', 76, 1);
INSERT INTO `security_event_library` VALUES (1521, 'win-application', 60915, 3, 'MSDTC事件&&win.system.eventID匹配到\"4353\"---MS DTC 事务管理器收到“已准备”事务的意外或未知 TIP 命令', 76, 1);
INSERT INTO `security_event_library` VALUES (1522, 'win-application', 60927, 4, 'MSDTC事件&&win.system.eventID匹配到\"4371\"|\"4372\"---MS DTC 事务管理器向另一个事务管理器发送了 TIP“PULL COMMAND”', 76, 1);
INSERT INTO `security_event_library` VALUES (1523, 'win-application', 60928, 3, 'MSDTC事件&&win.system.eventID匹配到\"4374\"---MS DTC 事务管理器收到活动事务的 TIP“错误命令”', 76, 1);
INSERT INTO `security_event_library` VALUES (1524, 'win-application', 60929, 3, 'MSDTC事件&&win.system.eventID匹配到\"4375\"---MS DTC 事务管理器收到活动事务的未知或意外 TIP 命令', 76, 1);
INSERT INTO `security_event_library` VALUES (1525, 'win-application', 60931, 4, 'MSDTC事件&&win.system.eventID匹配到\"4379\"---MS DTC 的现有群集资源是较新版本', 76, 1);
INSERT INTO `security_event_library` VALUES (1526, 'win-application', 60932, 3, 'MSDTC事件&&win.system.eventID匹配到\"4380\"---此节点上安装的 MS DTC 曾经是安装了较新版本的 MS DTC 的群集的一部分', 76, 1);
INSERT INTO `security_event_library` VALUES (1527, 'win-application', 60987, 4, 'MSDTC事件&&win.system.eventID匹配到\"53256\"---MS DTC 在恢复时检测到脏日志页，并且正在从其他页重建状态', 76, 1);
INSERT INTO `security_event_library` VALUES (1528, 'win-application', 60988, 2, 'MSDTC事件&&win.system.eventID匹配到\"53257\"---收到未知的连接管理器消息类型', 76, 1);
INSERT INTO `security_event_library` VALUES (1529, 'win-application', 60989, 4, 'MSDTC事件&&win.system.eventID匹配到\"53374\"---MS DTC XA 事务管理器调用 XA 资源管理器的 xa_commit 函数', 76, 1);
INSERT INTO `security_event_library` VALUES (1530, 'win-application', 60990, 4, 'MSDTC事件&&win.system.eventID匹配到\"53281\"---MS DTC XA 事务管理器调用 XA 资源管理器的 xa_open函数', 76, 1);
INSERT INTO `security_event_library` VALUES (1531, 'win-application', 60991, 4, 'MSDTC事件&&win.system.eventID匹配到\"53283\"---DTC 参数值信息', 76, 1);
INSERT INTO `security_event_library` VALUES (1532, 'win-application', 60992, 4, 'MSDTC事件&&win.system.eventID匹配到\"53284\"---XA 事务管理器尝试加载 XA 资源管理器 DLL', 76, 1);
INSERT INTO `security_event_library` VALUES (1533, 'win-application', 60993, 4, 'MSDTC事件&&win.system.eventID匹配到\"53285\"---XA 事务管理器尝试在 XA 资源管理器 DLL 中定位“GetXaSwitch”函数', 76, 1);
INSERT INTO `security_event_library` VALUES (1534, 'win-application', 60997, 3, 'MSDTC事件&&win.system.eventID匹配到\"53297\"---XA 事务管理器尝试使用 XA 资源管理器执行恢复', 76, 1);
INSERT INTO `security_event_library` VALUES (1535, 'win-application', 60998, 4, 'MSDTC事件&&win.system.eventID匹配到\"53298\"---XA事务管理器调用XA资源管理器的xa_open函数', 76, 1);
INSERT INTO `security_event_library` VALUES (1536, 'win-application', 60999, 4, 'MSDTC事件&&win.system.eventID匹配到\"53299\"---XA事务管理器调用XA资源管理器的xa_close函数', 76, 1);
INSERT INTO `security_event_library` VALUES (1537, 'win-application', 61000, 4, 'MSDTC事件&&win.system.eventID匹配到\"53300\"---MS DTC XA事务管理器调用XA资源管理器的xa_recover函数', 76, 1);
INSERT INTO `security_event_library` VALUES (1538, 'win-application', 61001, 4, 'MSDTC事件&&win.system.eventID匹配到\"53301\"---MS DTC XA事务管理器调用XA资源管理器的xa_commit函数', 76, 1);
INSERT INTO `security_event_library` VALUES (1539, 'win-application', 61002, 4, 'MSDTC事件&&win.system.eventID匹配到\"53302\"---MS DTC XA事务管理器调用XA资源管理器的xa_rollback函数', 76, 1);
INSERT INTO `security_event_library` VALUES (1540, 'win-application', 61003, 4, 'MSDTC事件&&win.system.eventID匹配到\"53303\"---MS DTC XA事务管理器调用XA资源管理器的xa_prepare函数', 76, 1);
INSERT INTO `security_event_library` VALUES (1541, 'win-application', 61004, 4, 'MSDTC事件&&win.system.eventID匹配到\"53304\"---XA事务管理器调用XA资源管理器DLL中的“GetXaSwitch”函数', 76, 1);
INSERT INTO `security_event_library` VALUES (1542, 'win-application', 61006, 4, 'MSDTC事件&&win.system.eventID匹配到\"53315\"---MS DTC XA事务管理器调用XA资源管理器的xa_commit函数', 76, 1);
INSERT INTO `security_event_library` VALUES (1543, 'win-application', 61007, 4, 'MSDTC事件&&win.system.eventID匹配到\"53316\"---MS DTC XA事务管理器调用XA资源管理器的xa_rollback函数', 76, 1);
INSERT INTO `security_event_library` VALUES (1544, 'win-application', 61008, 4, 'MSDTC事件&&win.system.eventID匹配到\"53317\"---MS DTC XA事务管理器调用XA资源管理器的xa_prepare函数', 76, 1);
INSERT INTO `security_event_library` VALUES (1545, 'win-application', 61009, 4, 'MSDTC事件&&win.system.eventID匹配到\"53318\"---MS DTC XA 事务管理器调用 XA 资源管理器的 xa_commit 函数并设置了 TMONEPHASE 标志', 76, 1);
INSERT INTO `security_event_library` VALUES (1546, 'win-application', 61016, 4, 'Windows应用程序错误事件&&win.system.providerName匹配到\".NET Runtime\"---.NET Runtime事件组', 76, 1);
INSERT INTO `security_event_library` VALUES (1547, 'win-application', 61023, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Microsoft-Windows-LocationProvider\"&&win.system.eventID匹配到\"2001\"---Windows 位置提供程序已启动', 76, 1);
INSERT INTO `security_event_library` VALUES (1548, 'win-application', 61024, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Microsoft-Windows-LocationProvider\"&&win.system.eventID匹配到\"2003\"---Windows 位置提供程序已关闭', 76, 1);
INSERT INTO `security_event_library` VALUES (1549, 'win-application', 61025, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Microsoft-Windows-UserModePowerService\"&&win.system.eventID匹配到\"12\"---进程重置策略方案', 76, 1);
INSERT INTO `security_event_library` VALUES (1550, 'win-application', 61026, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Application Management\"---应用程序管理事件组', 76, 1);
INSERT INTO `security_event_library` VALUES (1551, 'win-application', 61029, 4, '应用程序管理事件&&win.system.eventID匹配到\"106\"---应用程序已配置为升级另一个应用程序', 76, 1);
INSERT INTO `security_event_library` VALUES (1552, 'win-application', 61032, 4, '应用程序管理事件&&win.system.eventID匹配到\"301\"---申请应用程序的分配成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1553, 'win-application', 61033, 4, '应用程序管理事件&&win.system.eventID匹配到\"303\"---删除应用程序的分配成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1554, 'win-application', 61034, 4, '应用程序管理事件&&win.system.eventID匹配到\"307\"---程序的安装命令启动成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1555, 'win-application', 61036, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Disk\"---磁盘事件组', 76, 1);
INSERT INTO `security_event_library` VALUES (1556, 'win-application', 61043, 4, '磁盘事件&&win.system.eventID匹配到\"15\"---镜像初始化或同步开始', 76, 1);
INSERT INTO `security_event_library` VALUES (1557, 'win-application', 61047, 3, '磁盘事件&&win.system.eventID匹配到\"26\"---C:盘的 Diskeeper 碎片整理已停止', 76, 1);
INSERT INTO `security_event_library` VALUES (1558, 'win-application', 61049, 3, '磁盘事件&&win.system.eventID匹配到\"32\"---驱动程序检测到设备 \\Device\\Harddisk2\\DR2 已启用写入缓存', 76, 1);
INSERT INTO `security_event_library` VALUES (1559, 'win-application', 61050, 4, '磁盘事件&&win.system.eventID匹配到\"33\"---使用设备 \\device\\harddisk0\\partition2 上的纠错代码恢复数据', 76, 1);
INSERT INTO `security_event_library` VALUES (1560, 'win-application', 61053, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"EventCreate\"&&win.system.eventID匹配到\"100\"---在应用程序日志中创建的事件', 76, 1);
INSERT INTO `security_event_library` VALUES (1561, 'win-application', 61054, 4, 'Windows应用程序信息事件&&win.system.providerName匹配到\"Microsoft-Windows-EventCollector\"---EventCollector 事件组', 76, 1);
INSERT INTO `security_event_library` VALUES (1562, 'win-application', 61063, 4, 'Windows应用程序警告事件&&win.system.providerName匹配到\"Microsoft-Windows-SoftwareRestrictionPolicies\"---Microsoft-Windows-SoftwareRestrictionPolicies 事件组', 76, 1);
INSERT INTO `security_event_library` VALUES (1563, 'win-application', 61064, 3, 'Microsoft-Windows-SoftwareRestrictionPolicies 事件&&win.system.eventID匹配到\"865\"---管理员通过默认软件限制策略级别限制访问', 76, 1);
INSERT INTO `security_event_library` VALUES (1564, 'win-application', 61065, 3, 'Microsoft-Windows-SoftwareRestrictionPolicies 事件&&win.system.eventID匹配到\"866\"---管理员通过策略规则按位置限制访问', 76, 1);
INSERT INTO `security_event_library` VALUES (1565, 'win-application', 61066, 3, 'Microsoft-Windows-SoftwareRestrictionPolicies 事件&&win.system.eventID匹配到\"867\"---管理员根据软件发行商策略限制访问', 76, 1);
INSERT INTO `security_event_library` VALUES (1566, 'win-application', 61067, 3, 'Microsoft-Windows-SoftwareRestrictionPolicies 事件&&win.system.eventID匹配到\"868\"---管理员通过策略规则限制访问', 76, 1);
INSERT INTO `security_event_library` VALUES (1567, 'win-application', 61068, 3, 'Microsoft-Windows-SoftwareRestrictionPolicies 事件&&win.system.eventID匹配到\"882\"---管理员通过策略规则限制访问', 76, 1);
INSERT INTO `security_event_library` VALUES (1568, 'win-application', 61070, 4, 'Windows应用程序错误事件&&win.system.severityValue匹配到\"AUDIT_SUCCESS\"---Windows应用程序审核成功事件', 76, 1);
INSERT INTO `security_event_library` VALUES (1569, 'win-application', 61072, 4, 'Windows应用程序审核成功事件&&win.system.eventID匹配到\"18454\"|\"18453\"---MS SQL 服务器登录成功', 76, 1);
INSERT INTO `security_event_library` VALUES (1570, 'win-application', 60653, 3, 'Windows搜索事件组&&win.system.eventID匹配到\"3026\"--通知状态改变失败:系统资源不足', 52, 1);
INSERT INTO `security_event_library` VALUES (1571, 'syslog,arpwatch', 7200, 4, '解码为arpwatch，表示Arpwatch消息', 76, 1);
INSERT INTO `security_event_library` VALUES (1572, 'syslog,arpwatch', 7201, 3, 'Arpwatch发现新主机', 76, 1);
INSERT INTO `security_event_library` VALUES (1573, 'syslog,arpwatch', 7203, 4, '匹配到reaper: pid ，表示Arpwatch:退出', 76, 1);
INSERT INTO `security_event_library` VALUES (1574, 'syslog,arpwatch', 7204, 2, '匹配到changed ethernet address，表示Arpwatch:更改ip地址的网络接口', 76, 1);
INSERT INTO `security_event_library` VALUES (1575, 'syslog,arpwatch', 7205, 4, '匹配到bad interface eth0|exiting|Running as，表示Arpwatch:启动/退出消息', 76, 1);
INSERT INTO `security_event_library` VALUES (1576, 'syslog,arpwatch', 7208, 4, '匹配到reused old ethernet address，表示Arpwatch: IP被还原为旧的以太网地址', 76, 1);

-- ----------------------------
-- Table structure for security_event_safety
-- ----------------------------
DROP TABLE IF EXISTS `security_event_safety`;
CREATE TABLE `security_event_safety`  (
                                          `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                          `device_type_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '安全设备类型编码',
                                          `device_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '安全设备类型',
                                          `primary_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                          `sub_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                          `level` int(11) NULL DEFAULT NULL COMMENT '风险等级',
                                          `manufacturer` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '厂商',
                                          `log_type_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志类型编码',
                                          `log_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志类型',
                                          `condition` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联条件',
                                          `status` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 1 COMMENT '状态',
                                          `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
                                          `event_category_id` int(11) NULL DEFAULT NULL COMMENT '关联表：事件分类id',
                                          PRIMARY KEY (`id`) USING BTREE,
                                          INDEX `device_type`(`device_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of security_event_safety
-- ----------------------------
INSERT INTO `security_event_safety` VALUES (1, 'EVENT_USB', 'USB', 'MP', 'MPEVIR', 2, 'netvine', 'ALARMLOG', '告警日志', 'type:2', 1, 'USB-告警日志', 1);
INSERT INTO `security_event_safety` VALUES (2, 'EVENT_USB', 'USB', 'ST', 'STOPLOG', 4, 'netvine', 'OPERATIONLOG', '操作日志', 'type:1', 1, 'USB-操作日志', 77);
INSERT INTO `security_event_safety` VALUES (3, 'EVENT_AUDIT', '安全审计', 'NA', 'NATDLOG', 1, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:0,type:1', 1, NULL, 99);
INSERT INTO `security_event_safety` VALUES (4, 'EVENT_AUDIT', '安全审计', 'NA', 'NATDLOG', 1, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:0,type:2', 1, NULL, 99);
INSERT INTO `security_event_safety` VALUES (5, 'EVENT_AUDIT', '安全审计', 'NA', 'NATDLOG', 1, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:0,type:3', 1, NULL, 99);
INSERT INTO `security_event_safety` VALUES (6, 'EVENT_AUDIT', '安全审计', 'NA', 'NADOS', 1, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:1,type:1', 1, NULL, 18);
INSERT INTO `security_event_safety` VALUES (7, 'EVENT_AUDIT', '安全审计', 'NA', 'NAEXPLOIT', 1, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:1,type:2', 1, NULL, 13);
INSERT INTO `security_event_safety` VALUES (8, 'EVENT_AUDIT', '安全审计', 'NA', 'ENABNET', 2, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:1,type:3', 1, NULL, 90);
INSERT INTO `security_event_safety` VALUES (9, 'EVENT_AUDIT', '安全审计', 'NA', 'NADOS', 1, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:2,type:1', 1, NULL, 18);
INSERT INTO `security_event_safety` VALUES (10, 'EVENT_AUDIT', '安全审计', 'NA', 'NAEXPLOIT', 1, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:2,type:2', 1, NULL, 13);
INSERT INTO `security_event_safety` VALUES (11, 'EVENT_AUDIT', '安全审计', 'NA', 'ENABNET', 2, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:2,type:3', 1, NULL, 90);
INSERT INTO `security_event_safety` VALUES (12, 'EVENT_AUDIT', '安全审计', 'AB', 'ABACCESS', 2, 'netvine', 'WHITELISTSYSLOG', '白名单日志', 'action:0', 1, NULL, 68);
INSERT INTO `security_event_safety` VALUES (13, 'EVENT_AUDIT', '安全审计', 'AB', 'ABACCESS', 2, 'netvine', 'WHITELISTSYSLOG', '白名单日志', 'action:1', 1, NULL, 68);
INSERT INTO `security_event_safety` VALUES (14, 'EVENT_AUDIT', '安全审计', 'AB', 'ABACCESS', 2, 'netvine', 'WHITELISTSYSLOG', '白名单日志', 'action:2', 1, NULL, 68);
INSERT INTO `security_event_safety` VALUES (15, 'EVENT_AUDIT', '安全审计', 'AB', 'ABICPRO', 2, 'netvine', 'BEHAVIORSYSLOG', '行为监测日志', 'action:0', 1, NULL, 85);
INSERT INTO `security_event_safety` VALUES (16, 'EVENT_AUDIT', '安全审计', 'AB', 'ABICPRO', 2, 'netvine', 'BEHAVIORSYSLOG', '行为监测日志', 'action:1', 1, NULL, 85);
INSERT INTO `security_event_safety` VALUES (17, 'EVENT_AUDIT', '安全审计', 'AB', 'ABICPRO', 2, 'netvine', 'BEHAVIORSYSLOG', '行为监测日志', 'action:2', 1, NULL, 85);
INSERT INTO `security_event_safety` VALUES (18, 'EVENT_AUDIT', '安全审计', 'ST', 'STSELOG', 2, 'netvine', 'SYSTEMSYSLOG', '系统日志', 'warningLevel:1', 1, NULL, 89);
INSERT INTO `security_event_safety` VALUES (19, 'EVENT_AUDIT', '安全审计', 'ST', 'STWNLOG', 3, 'netvine', 'SYSTEMSYSLOG', '系统日志', 'warningLevel:2', 1, NULL, 78);
INSERT INTO `security_event_safety` VALUES (20, 'EVENT_AUDIT', '安全审计', 'ST', 'STIFLOG', 4, 'netvine', 'SYSTEMSYSLOG', '系统日志', 'warningLevel:3', 1, NULL, 79);
INSERT INTO `security_event_safety` VALUES (21, 'EVENT_AUDIT', '安全审计', 'ENAB', 'ENABBLLOG', 3, 'netvine', 'BASELINESYSLOG', '基线日志', '', 1, NULL, 91);
INSERT INTO `security_event_safety` VALUES (22, 'EVENT_AUDIT', '安全审计', 'OTH', 'OTHPRAULOG', 4, 'netvine', 'AUDITSYSLOG', '协议审计日志', '', 1, NULL, 92);
INSERT INTO `security_event_safety` VALUES (23, 'EVENT_IDS', '入侵', 'NA', 'NADOS', 2, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:0,type:1', 1, NULL, 18);
INSERT INTO `security_event_safety` VALUES (24, 'EVENT_IDS', '入侵', 'NA', 'NAEXPLOIT', 1, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:0,type:2', 1, NULL, 13);
INSERT INTO `security_event_safety` VALUES (25, 'EVENT_IDS', '入侵', 'ENAB', 'ENABNET', 2, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:0,type:3', 1, NULL, 90);
INSERT INTO `security_event_safety` VALUES (26, 'EVENT_IDS', '入侵', 'NA', 'NADOS', 1, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:1,type:1', 1, NULL, 18);
INSERT INTO `security_event_safety` VALUES (27, 'EVENT_IDS', '入侵', 'NA', 'NAEXPLOIT', 1, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:1,type:2', 1, NULL, 13);
INSERT INTO `security_event_safety` VALUES (28, 'EVENT_IDS', '入侵', 'ENAB', 'ENABNET', 2, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:1,type:3', 1, NULL, 90);
INSERT INTO `security_event_safety` VALUES (29, 'EVENT_IDS', '入侵', 'NA', 'NADOS', 1, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:2,type:1', 1, NULL, 18);
INSERT INTO `security_event_safety` VALUES (30, 'EVENT_IDS', '入侵', 'NA', 'NAEXPLOIT', 1, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:2,type:2', 1, NULL, 13);
INSERT INTO `security_event_safety` VALUES (31, 'EVENT_IDS', '入侵', 'ENAB', 'ENABNET', 2, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:2,type:3', 1, NULL, 90);
INSERT INTO `security_event_safety` VALUES (32, 'EVENT_IDS', '入侵', 'AB', 'ABBLEVENT', 2, 'netvine', 'BEHAVIORSYSLOG', '行为监测日志', 'action:0', 1, NULL, 86);
INSERT INTO `security_event_safety` VALUES (33, 'EVENT_IDS', '入侵', 'AB', 'ABBLEVENT', 2, 'netvine', 'BEHAVIORSYSLOG', '行为监测日志', 'action:1', 1, NULL, 86);
INSERT INTO `security_event_safety` VALUES (34, 'EVENT_IDS', '入侵', 'AB', 'ABBLEVENT', 2, 'netvine', 'BEHAVIORSYSLOG', '行为监测日志', 'action:2', 1, NULL, 86);
INSERT INTO `security_event_safety` VALUES (35, 'EVENT_IDS', '入侵', 'ST', 'STSELOG', 2, 'netvine', 'SYSTEMSYSLOG', '系统日志', 'warningLevel:1', 1, NULL, 89);
INSERT INTO `security_event_safety` VALUES (36, 'EVENT_IDS', '入侵', 'ST', 'STWNLOG', 3, 'netvine', 'SYSTEMSYSLOG', '系统日志', 'warningLevel:2', 1, NULL, 78);
INSERT INTO `security_event_safety` VALUES (37, 'EVENT_IDS', '入侵', 'ST', 'STIFLOG', 4, 'netvine', 'SYSTEMSYSLOG', '系统日志', 'warningLevel:3', 1, NULL, 79);
INSERT INTO `security_event_safety` VALUES (38, 'EVENT_IDS', '入侵', 'OTH', 'OTHDDTEVENT', 4, 'netvine', 'DATADETECTIONSYSLOG', '数据检测日志', '', 1, NULL, 94);
INSERT INTO `security_event_safety` VALUES (39, 'EVENT_IDS', '入侵', 'OTH', 'OTHFDTEVENT', 4, 'netvine', 'FILEDETECTIONSYSLOG', '文件检测日志', '', 1, NULL, 95);
INSERT INTO `security_event_safety` VALUES (40, 'EVENT_IDS', '入侵', 'OTH', 'OTHAIOATEVENT', 2, 'netvine', 'AIONEANTIVIRUSSYSLOG', '一体化防病毒日志', '', 1, NULL, 96);
INSERT INTO `security_event_safety` VALUES (41, 'EVENT_IDS', '入侵', 'OTH', 'OTHINDTEVENT', 2, 'netvine', 'INTRUSIONDETECTIONSYSLOG', '入侵检测日志', '', 1, NULL, 97);
INSERT INTO `security_event_safety` VALUES (42, 'EVENT_FIREWALL', '防火墙', 'SHE', 'SHEABCM', 2, 'netvine', 'BASEPOLICYSYSLOG', '基础策略日志', 'action:0', 1, NULL, 88);
INSERT INTO `security_event_safety` VALUES (43, 'EVENT_FIREWALL', '防火墙', 'SHE', 'SHEABCM', 2, 'netvine', 'BASEPOLICYSYSLOG', '基础策略日志', 'action:1', 1, NULL, 88);
INSERT INTO `security_event_safety` VALUES (44, 'EVENT_FIREWALL', '防火墙', 'SHE', 'SHEABCM', 2, 'netvine', 'BASEPOLICYSYSLOG', '基础策略日志', 'action:2', 1, NULL, 88);
INSERT INTO `security_event_safety` VALUES (45, 'EVENT_FIREWALL', '防火墙', 'SHE', 'SHEABCM', 2, 'netvine', 'DPISYSLOG', '工业协议策略日志', 'action:0', 1, NULL, 88);
INSERT INTO `security_event_safety` VALUES (46, 'EVENT_FIREWALL', '防火墙', 'AB', 'ABICPRO', 2, 'netvine', 'DPISYSLOG', '工业协议策略日志', 'action:1', 1, NULL, 85);
INSERT INTO `security_event_safety` VALUES (47, 'EVENT_FIREWALL', '防火墙', 'AB', 'ABICPRO', 2, 'netvine', 'DPISYSLOG', '工业协议策略日志', 'action:2', 1, NULL, 85);
INSERT INTO `security_event_safety` VALUES (48, 'EVENT_FIREWALL', '防火墙', 'NA', 'NADOS', 1, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:0,type:1', 1, NULL, 18);
INSERT INTO `security_event_safety` VALUES (49, 'EVENT_FIREWALL', '防火墙', 'NA', 'NAEXPLOIT', 1, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:0,type:2', 1, NULL, 13);
INSERT INTO `security_event_safety` VALUES (50, 'EVENT_FIREWALL', '防火墙', 'ENAB', 'ENABNET', 2, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:0,type:3', 1, NULL, 90);
INSERT INTO `security_event_safety` VALUES (51, 'EVENT_FIREWALL', '防火墙', 'ENAB', 'ENABNET', 2, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:0,type:4', 1, NULL, 90);
INSERT INTO `security_event_safety` VALUES (52, 'EVENT_FIREWALL', '防火墙', 'NA', 'NADOS', 1, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:1,type:1', 1, NULL, 18);
INSERT INTO `security_event_safety` VALUES (53, 'EVENT_FIREWALL', '防火墙', 'NA', 'NAEXPLOIT', 1, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:1,type:2', 1, NULL, 13);
INSERT INTO `security_event_safety` VALUES (54, 'EVENT_FIREWALL', '防火墙', 'ENAB', 'ENABNET', 2, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:1,type:3', 1, NULL, 90);
INSERT INTO `security_event_safety` VALUES (55, 'EVENT_FIREWALL', '防火墙', 'ENAB', 'ENABNET', 2, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:1,type:4', 1, NULL, 90);
INSERT INTO `security_event_safety` VALUES (56, 'EVENT_FIREWALL', '防火墙', 'NA', 'NADOS', 1, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:2,type:1', 1, NULL, 18);
INSERT INTO `security_event_safety` VALUES (57, 'EVENT_FIREWALL', '防火墙', 'NA', 'NAEXPLOIT', 1, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:2,type:2', 1, NULL, 13);
INSERT INTO `security_event_safety` VALUES (58, 'EVENT_FIREWALL', '防火墙', 'ENAB', 'ENABNET', 2, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:2,type:3', 1, NULL, 90);
INSERT INTO `security_event_safety` VALUES (59, 'EVENT_FIREWALL', '防火墙', 'ENAB', 'ENABNET', 2, 'netvine', 'THREATSYSLOG', '威胁日志', 'action:2,type:4', 1, NULL, 90);
INSERT INTO `security_event_safety` VALUES (60, 'EVENT_FIREWALL', '防火墙', 'SHE', 'SHEOTH', 3, 'netvine', 'WHITELISTSYSLOG', '白名单日志', 'action:0', 1, NULL, 67);
INSERT INTO `security_event_safety` VALUES (61, 'EVENT_FIREWALL', '防火墙', 'AB', 'ABBLEVENT', 2, 'netvine', 'WHITELISTSYSLOG', '白名单日志', 'action:1', 1, NULL, 86);
INSERT INTO `security_event_safety` VALUES (62, 'EVENT_FIREWALL', '防火墙', 'AB', 'ABBLEVENT', 2, 'netvine', 'WHITELISTSYSLOG', '白名单日志', 'action:2', 1, NULL, 86);
INSERT INTO `security_event_safety` VALUES (63, 'EVENT_FIREWALL', '防火墙', 'SHE', 'SHEOTH', 3, 'netvine', 'BEHAVIORSYSLOG', '行为监测日志', 'action:0', 1, NULL, 67);
INSERT INTO `security_event_safety` VALUES (64, 'EVENT_FIREWALL', '防火墙', 'AB', 'ABBLEVENT', 2, 'netvine', 'BEHAVIORSYSLOG', '行为监测日志', 'action:1', 1, NULL, 86);
INSERT INTO `security_event_safety` VALUES (65, 'EVENT_FIREWALL', '防火墙', 'AB', 'ABBLEVENT', 2, 'netvine', 'BEHAVIORSYSLOG', '行为监测日志', 'action:2', 1, NULL, 86);
INSERT INTO `security_event_safety` VALUES (66, 'EVENT_FIREWALL', '防火墙', 'OTH', 'OTHCNMEVENT', 4, 'netvine', 'CONNECTIONMANAGEMENTSYSLOG', '连接管理日志', '', 1, NULL, 93);
INSERT INTO `security_event_safety` VALUES (67, 'EVENT_FIREWALL', '防火墙', 'ST', 'STSELOG', 2, 'netvine', 'SYSTEMSYSLOG', '系统日志', 'warningLevel:1', 1, NULL, 89);
INSERT INTO `security_event_safety` VALUES (68, 'EVENT_FIREWALL', '防火墙', 'ST', 'STWNLOG', 3, 'netvine', 'SYSTEMSYSLOG', '系统日志', 'warningLevel:2', 1, NULL, 78);
INSERT INTO `security_event_safety` VALUES (69, 'EVENT_FIREWALL', '防火墙', 'ST', 'STIFLOG', 4, 'netvine', 'SYSTEMSYSLOG', '系统日志', 'warningLevel:3', 1, NULL, 79);
INSERT INTO `security_event_safety` VALUES (70, 'EVENT_FIREWALL', '防火墙', 'SHE', 'SHEOTH', 3, 'netvine', 'TRAFFICSYSLOG', '流量日志', '', 1, NULL, 67);
INSERT INTO `security_event_safety` VALUES (71, 'EVENT_HOST', '主机', 'ST', 'STPRWNEVENT', 3, 'netvine', 'SYSLOGALARMPROGRAM', '程序运行告警日志', 'type:0', 1, NULL, 82);
INSERT INTO `security_event_safety` VALUES (72, 'EVENT_HOST', '主机', 'ST', 'STPRLOGEVENT', 4, 'netvine', 'SYSLOGAUDITPROGRAM', '程序运行日志审计', 'type:1', 1, NULL, 83);
INSERT INTO `security_event_safety` VALUES (73, 'EVENT_HOST', '主机', 'RO', 'ROPBY', 1, 'netvine', 'SYSLOGALARMUDISK', '外设管理告警日志', 'type:0', 1, NULL, 87);
INSERT INTO `security_event_safety` VALUES (74, 'EVENT_HOST', '主机', 'RO', 'ROPBY', 1, 'netvine', 'SYSLOGAUDITUDISK', '外设管理日志审计', 'type:1', 1, NULL, 87);
INSERT INTO `security_event_safety` VALUES (75, 'EVENT_HOST', '主机', 'AB', 'ABACCESS', 2, 'netvine', 'SYSLOGALARMAPPGUARD', '防疫卫士告警日志', 'type:0', 1, NULL, 68);
INSERT INTO `security_event_safety` VALUES (76, 'EVENT_HOST', '主机', 'AB', 'ABACCESS', 2, 'netvine', 'SYSLOGAUDITAPPGUARD', '防疫卫士日志审计', 'type:1', 1, NULL, 68);
INSERT INTO `security_event_safety` VALUES (77, 'EVENT_HOST', '主机', 'SHE', 'SHEOTH', 3, 'netvine', 'SYSLOGALARMACCESSCONTROLLER', '访问控制告警日志', 'type:0', 1, NULL, 67);
INSERT INTO `security_event_safety` VALUES (78, 'EVENT_HOST', '主机', 'SHE', 'SHEOTH', 3, 'netvine', 'SYSLOGAUDITACCESSCONTROLLER', '访问控制日志审计', 'type:1', 1, NULL, 67);
INSERT INTO `security_event_safety` VALUES (79, 'EVENT_HOST', '主机', 'SHE', 'SHEOTH', 3, 'netvine', 'SYSLOGALARMFILEGUARD', '文件保护告警日志', 'type:0', 1, NULL, 67);
INSERT INTO `security_event_safety` VALUES (80, 'EVENT_HOST', '主机', 'SHE', 'SHEOTH', 3, 'netvine', 'SYSLOGAUDITFILEGUARD', '文件保护日志审计', 'type:1', 1, NULL, 67);
INSERT INTO `security_event_safety` VALUES (81, 'EVENT_HOST', '主机', 'SHE', 'SHEOTH', 3, 'netvine', 'SYSLOGALARMREGGUARD', '注册表保护告警日志', 'type:0', 1, NULL, 67);
INSERT INTO `security_event_safety` VALUES (82, 'EVENT_HOST', '主机', 'SHE', 'SHEOTH', 3, 'netvine', 'SYSLOGAUDITREGGUARD', '注册表保护日志审计', 'type:1', 1, NULL, 67);
INSERT INTO `security_event_safety` VALUES (83, 'EVENT_HOST', '主机', 'ST', 'STPRLOGEVENT', 4, 'netvine', 'SYSLOGAUDITUSER', '系统操作日志审计', 'type:1', 1, NULL, 83);

-- ----------------------------
-- Table structure for asset_engine
-- ----------------------------
CREATE TABLE IF NOT EXISTS asset_engine
(
    id             INT AUTO_INCREMENT PRIMARY KEY COMMENT '自增ID号',
    name           VARCHAR(100) COMMENT '资产名称',
    dev_mac        VARCHAR(17) UNIQUE COMMENT '设备MAC地址',
    dev_ip         VARCHAR(15) COMMENT '设备IP地址',
    dev_brand_code VARCHAR(50) COMMENT '厂商编码',
    dev_brand_name VARCHAR(50) COMMENT '厂商名称',
    dev_category   VARCHAR(50) COMMENT '设备大类：UNKNOWN_DEVICE PC PAD SERVER ROUTER ...',
    dev_model      VARCHAR(100) COMMENT '设备型号',
    friendly_name  VARCHAR(100) COMMENT '标准化名称 厂商中文名_型号',
    os             VARCHAR(50) COMMENT '操作系统',
    os_detail      VARCHAR(50) COMMENT '操作系统版本号详情',
    status         TINYINT(1) DEFAULT 0 COMMENT '状态：0未锁定1锁定',
    user_id        INT(11) COMMENT '用户id',
    dept_id        INT(11) COMMENT '部门id',
    asset_type_id  INT COMMENT '资产类型id',
    level          int(11)    DEFAULT NULL COMMENT '等级，1-3，低中高',
    active_status  int(11)    DEFAULT 1 COMMENT '活跃状态，0-3，未知、活跃、休眠、僵尸',
    active_time    TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '活跃时间',
    create_time    TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time    TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '资产信息表';

-- ----------------------------
-- Table structure for vulnerability_library_mapping
-- ----------------------------
DROP TABLE IF EXISTS `vulnerability_library_mapping`;
CREATE TABLE `vulnerability_library_mapping`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID号',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '漏洞库映射' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vulnerability_library_mapping
-- ----------------------------
INSERT INTO `vulnerability_library_mapping` VALUES (1, 'CVE', 'CVE');
INSERT INTO `vulnerability_library_mapping` VALUES (2, 'CNVD', 'CNVD');
INSERT INTO `vulnerability_library_mapping` VALUES (3, 'CNNVD', 'CNNVD');
INSERT INTO `vulnerability_library_mapping` VALUES (4, 'CWE', 'CWE');

-- ----------------------------
-- Records of asset_type
-- ----------------------------
CREATE TABLE IF NOT EXISTS asset_type
(
    id          INT AUTO_INCREMENT PRIMARY KEY COMMENT '自增ID号',
    code        varchar(100) DEFAULT NULL COMMENT 'code',
    name        VARCHAR(100) COMMENT '名称',
    description VARCHAR(100) COMMENT '描述',
    parent_id   INT COMMENT '父id',
    `system`      tinyint(1)   DEFAULT NULL COMMENT '0系统数据 1用户数据',
    create_time TIMESTAMP    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '资产类型';

-- ----------------------------
-- Records of asset_vuln
-- ----------------------------
CREATE TABLE IF NOT EXISTS `asset_vuln`
(
    `id`              varchar(50) NOT NULL,
    `cve_id`          varchar(50) NOT NULL COMMENT '漏洞唯一标识号',
    `cnnvd_vuln_type` varchar(50)          DEFAULT NULL COMMENT '漏洞类型',
    `cnnvd_severity`  varchar(50)          DEFAULT NULL COMMENT '漏洞级别',
    `ip`              varchar(100)         DEFAULT NULL COMMENT '设备ip',
    `status`          int(10)              DEFAULT NULL COMMENT '状态 0当天新增漏洞 1过往漏洞 2已解决漏洞',
    `created_at`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='漏洞数据同步表';

-- ----------------------------
-- Records of asset_vuln_trend
-- ----------------------------
DROP TABLE IF EXISTS asset_vuln_trend;
CREATE TABLE `asset_vuln_trend`
(
    `id`          int(10) unsigned NOT NULL AUTO_INCREMENT,
    `count`       int(11)          NOT NULL COMMENT '漏洞数量',
    `dev_ip`      varchar(100)     NOT NULL COMMENT '设备ip',
    `report_time` DATE             NOT NULL COMMENT '上报日期',
    `create_time` timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='资产漏洞趋势';


-- ----------------------------
-- Table structure for device_fingerprint_category_info
-- ----------------------------
DROP TABLE IF EXISTS `device_fingerprint_category_info`;
CREATE TABLE `device_fingerprint_category_info`  (
                                                     `id` int(11) NOT NULL AUTO_INCREMENT,
                                                     `category_tag` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                                     `category_info` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                                     `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 172 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_fingerprint_category_info
-- ----------------------------
INSERT INTO `device_fingerprint_category_info` VALUES (1, 'ROUTER', ' ROUTER', '路由器');
INSERT INTO `device_fingerprint_category_info` VALUES (2, 'ACCESS_POINT', ' ACCESS_POINT', '无线路由器');
INSERT INTO `device_fingerprint_category_info` VALUES (3, 'WIFI_EXTENDER', ' WIFI_EXTENDER', 'Wi-Fi 扩展器');
INSERT INTO `device_fingerprint_category_info` VALUES (4, 'MOBILE_HOTSPOT', ' MOBILE_HOTSPOT', '移动热点');
INSERT INTO `device_fingerprint_category_info` VALUES (5, 'LAN_CONTROLLER', ' LAN_CONTROLLER', '局域网控制器');
INSERT INTO `device_fingerprint_category_info` VALUES (6, 'IOS_MOBILE', ' IOS_MOBILE', 'IOS移动设备');
INSERT INTO `device_fingerprint_category_info` VALUES (7, 'IPOD', ' IPOD', 'iPod');
INSERT INTO `device_fingerprint_category_info` VALUES (8, 'IPHONE', ' IPHONE', 'iPhone');
INSERT INTO `device_fingerprint_category_info` VALUES (9, 'IPAD', ' IPAD', 'iPad');
INSERT INTO `device_fingerprint_category_info` VALUES (10, 'ANDROID_MOBILE', ' ANDROID_MOBILE', '安卓移动设备');
INSERT INTO `device_fingerprint_category_info` VALUES (11, 'ANDROID_PHONE', ' ANDROID_PHONE', '安卓手机');
INSERT INTO `device_fingerprint_category_info` VALUES (12, 'ANDROID_TABLET', ' ANDROID_TABLET', '安卓平板');
INSERT INTO `device_fingerprint_category_info` VALUES (13, 'GENERIC_MOBILE', ' GENERIC_MOBILE', '通用移动设备');
INSERT INTO `device_fingerprint_category_info` VALUES (15, 'GENERIC_TABLET', ' GENERIC_TABLET', '通用平板设备');
INSERT INTO `device_fingerprint_category_info` VALUES (16, 'GENERIC_PHONE', ' GENERIC_PHONE', '通用手机设备');
INSERT INTO `device_fingerprint_category_info` VALUES (17, 'MAC_COMPUTER', ' MAC_COMPUTER', 'MAC电脑');
INSERT INTO `device_fingerprint_category_info` VALUES (18, '\nMAC_DESKTOP', ' MAC_DESKTOP', 'MAC台式电脑');
INSERT INTO `device_fingerprint_category_info` VALUES (19, '\nMAC_BOOK', ' MAC_BOOK', '苹果笔记本电脑');
INSERT INTO `device_fingerprint_category_info` VALUES (20, 'PC_COMPUTER', ' PC_COMPUTER', '个人电脑');
INSERT INTO `device_fingerprint_category_info` VALUES (21, '\nDESKTOP', ' DESKTOP', '台式电脑');
INSERT INTO `device_fingerprint_category_info` VALUES (22, 'LAPTOP', ' LAPTOP', '笔记本电脑');
INSERT INTO `device_fingerprint_category_info` VALUES (23, 'TERMINAL', ' TERMINAL', '终端');
INSERT INTO `device_fingerprint_category_info` VALUES (24, 'CHROMEBOOK', ' CHROMEBOOK', 'Chromebook笔记本电脑');
INSERT INTO `device_fingerprint_category_info` VALUES (25, 'GENERIC_CAMERA', ' GENERIC_CAMERA', '通用的相机模型');
INSERT INTO `device_fingerprint_category_info` VALUES (26, 'IP_CAMERA', ' IP_CAMERA', '网络摄像头');
INSERT INTO `device_fingerprint_category_info` VALUES (27, 'BABY_MONITOR', ' BABY_MONITOR', '婴儿监护器');
INSERT INTO `device_fingerprint_category_info` VALUES (28, 'PET_MONITOR', ' PET_MONITOR', '宠物监护器');
INSERT INTO `device_fingerprint_category_info` VALUES (29, 'SECURITY_CAMERA', ' SECURITY_CAMERA', '安全摄像头');
INSERT INTO `device_fingerprint_category_info` VALUES (30, 'VIDEO_CAMERA_DV', ' VIDEO_CAMERA_DV', '数字视频摄像机');
INSERT INTO `device_fingerprint_category_info` VALUES (31, 'DIGITAL_CAMERA_DC', ' DIGITAL_CAMERA_DC', '数码相机');
INSERT INTO `device_fingerprint_category_info` VALUES (32, 'DOORBELL_CAMERA', ' DOORBELL_CAMERA', '门铃摄像头');
INSERT INTO `device_fingerprint_category_info` VALUES (33, 'SECURITY_CAMERA_SYSTEM', ' SECURITY_CAMERA_SYSTEM', '安全摄像头系统');
INSERT INTO `device_fingerprint_category_info` VALUES (34, 'GENERIC_VIDEO_MEDIA', ' GENERIC_VIDEO_MEDIA', '通用视频媒体');
INSERT INTO `device_fingerprint_category_info` VALUES (35, 'VIDEO_RECORDER', ' VIDEO_RECORDER', '录像机');
INSERT INTO `device_fingerprint_category_info` VALUES (36, 'DISC_PLAYER', ' DISC_PLAYER', '光盘播放器');
INSERT INTO `device_fingerprint_category_info` VALUES (37, 'STB', ' STB', '机顶盒');
INSERT INTO `device_fingerprint_category_info` VALUES (38, 'APPLE_TV', ' APPLE_TV', '苹果机顶盒');
INSERT INTO `device_fingerprint_category_info` VALUES (39, 'SATELLITE', ' SATELLITE', '卫星电视');
INSERT INTO `device_fingerprint_category_info` VALUES (40, 'STREAMING_DONGLE', ' STREAMING_DONGLE', '流媒体棒');
INSERT INTO `device_fingerprint_category_info` VALUES (41, 'HOME_MEDIA_SERVER', ' HOME_MEDIA_SERVER', '家庭媒体服务器');
INSERT INTO `device_fingerprint_category_info` VALUES (42, 'GENERIC_THEATER_AUDIO', ' GENERIC_THEATER_AUDIO', '通用剧院音频');
INSERT INTO `device_fingerprint_category_info` VALUES (43, 'MICROPHONE', ' MICROPHONE', '麦克风');
INSERT INTO `device_fingerprint_category_info` VALUES (44, 'WIFI_SPEAKER', ' WIFI_SPEAKER', '无线扬声器');
INSERT INTO `device_fingerprint_category_info` VALUES (45, 'AV_RECEIVER', ' AV_RECEIVER', '音视频接收器');
INSERT INTO `device_fingerprint_category_info` VALUES (46, 'PROJECTOR', ' PROJECTOR', '投影仪');
INSERT INTO `device_fingerprint_category_info` VALUES (47, 'WIRELESS_AUDIO_HUB', ' WIRELESS_AUDIO_HUB', '无线音频中心');
INSERT INTO `device_fingerprint_category_info` VALUES (48, 'AMPLIFIER', ' AMPLIFIER', '扬声器');
INSERT INTO `device_fingerprint_category_info` VALUES (49, 'AUDIO_PLAYER', ' AUDIO_PLAYER', '音频播放器');
INSERT INTO `device_fingerprint_category_info` VALUES (50, 'INTERNET_RADIO', ' INTERNET_RADIO', '互联网收音机');
INSERT INTO `device_fingerprint_category_info` VALUES (51, 'WIRELESS_VIDEO_HUB', ' WIRELESS_VIDEO_HUB', '无线视频中心');
INSERT INTO `device_fingerprint_category_info` VALUES (52, 'SMART_SPEAKER', ' SMART_SPEAKER', '智能音箱');
INSERT INTO `device_fingerprint_category_info` VALUES (53, 'SMART_SPEAKER_WITH_SCREEN', ' SMART_SPEAKER_WITH_SCREEN', '有屏幕的智能音箱');
INSERT INTO `device_fingerprint_category_info` VALUES (54, 'SMART_HOME_CONTROLLER', ' SMART_HOME_CONTROLLER', '智能家居控制器');
INSERT INTO `device_fingerprint_category_info` VALUES (55, 'SMART_BUTTON', ' SMART_BUTTON', '智能按钮');
INSERT INTO `device_fingerprint_category_info` VALUES (56, 'GESTURE_CONTROL', ' GESTURE_CONTROL', '手势控制器');
INSERT INTO `device_fingerprint_category_info` VALUES (57, 'REMOTE_CONTROL', ' REMOTE_CONTROL', '遥控器');
INSERT INTO `device_fingerprint_category_info` VALUES (58, 'SMART_TV', ' SMART_TV', '智能电视');
INSERT INTO `device_fingerprint_category_info` VALUES (59, 'GAME_CONSOLE', ' GAME_CONSOLE', '游戏机');
INSERT INTO `device_fingerprint_category_info` VALUES (60, 'E_READER', ' E_READER', '电子阅读器');
INSERT INTO `device_fingerprint_category_info` VALUES (61, 'WRISTBAND', ' WRISTBAND', '智能手环·');
INSERT INTO `device_fingerprint_category_info` VALUES (62, 'WATCH', ' WATCH', '智能手表');
INSERT INTO `device_fingerprint_category_info` VALUES (63, 'AR', ' AR', '增强现实设备');
INSERT INTO `device_fingerprint_category_info` VALUES (66, 'DRONE', ' DRONE', '无人机');
INSERT INTO `device_fingerprint_category_info` VALUES (67, 'ADSB_RECEIVER', ' ADSB_RECEIVER', 'ADS-B接收放大器');
INSERT INTO `device_fingerprint_category_info` VALUES (68, 'SMART_PLUG', ' SMART_PLUG', '智能插座');
INSERT INTO `device_fingerprint_category_info` VALUES (69, 'SMART_SWITCH', ' SMART_SWITCH', '智能开关');
INSERT INTO `device_fingerprint_category_info` VALUES (70, 'UPS', ' UPS', '不间断电源');
INSERT INTO `device_fingerprint_category_info` VALUES (71, 'SOLAR_PANEL', ' SOLAR_PANEL', '太阳能板');
INSERT INTO `device_fingerprint_category_info` VALUES (72, 'SMART_METER', ' SMART_METER', '智能电表');
INSERT INTO `device_fingerprint_category_info` VALUES (73, 'SMART_LIGHTING', ' SMART_LIGHTING', '智能灯具');
INSERT INTO `device_fingerprint_category_info` VALUES (74, 'VACUUM_CLEANER', ' VACUUM_CLEANER', '真空吸尘器');
INSERT INTO `device_fingerprint_category_info` VALUES (76, 'THERMOSTAT', ' THERMOSTAT', '温度调节器');
INSERT INTO `device_fingerprint_category_info` VALUES (77, 'HUMIDIFIER', ' HUMIDIFIER', '加湿器');
INSERT INTO `device_fingerprint_category_info` VALUES (78, 'DEHUMIDIFIER', ' DEHUMIDIFIER', '除湿器');
INSERT INTO `device_fingerprint_category_info` VALUES (79, 'HEATING', ' HEATING', '加热器');
INSERT INTO `device_fingerprint_category_info` VALUES (80, 'AIR_CONDITIONER', ' AIR_CONDITIONER', '空调');
INSERT INTO `device_fingerprint_category_info` VALUES (81, 'CLIMATE_SENSOR', ' CLIMATE_SENSOR', '天气传感器');
INSERT INTO `device_fingerprint_category_info` VALUES (82, 'GENERIC_HOME_APPLIANCE', ' GENERIC_HOME_APPLIANCE', '通用家用电器');
INSERT INTO `device_fingerprint_category_info` VALUES (92, 'CLOCK', ' CLOCK', '时钟');
INSERT INTO `device_fingerprint_category_info` VALUES (96, 'SMOKE_DETECTOR', ' SMOKE_DETECTOR', '烟雾检测器');
INSERT INTO `device_fingerprint_category_info` VALUES (98, 'FIRE_SENSOR', ' FIRE_SENSOR', '火警检测器');
INSERT INTO `device_fingerprint_category_info` VALUES (110, 'NAS', ' NAS', '网络附加存储');
INSERT INTO `device_fingerprint_category_info` VALUES (111, 'WIRELESS_STICK_USB_STORAGE', ' WIRELESS_STICK_USB_STORAGE', '无线U盘存储设备');
INSERT INTO `device_fingerprint_category_info` VALUES (112, 'USB_HUB', ' USB_HUB', 'USB转接坞');
INSERT INTO `device_fingerprint_category_info` VALUES (113, 'PRINTER_SCANNER', ' PRINTER_SCANNER', '扫描打印机');
INSERT INTO `device_fingerprint_category_info` VALUES (114, 'PRINTER', ' PRINTER', '打印机');
INSERT INTO `device_fingerprint_category_info` VALUES (115, 'SCANNER', ' SCANNER', '扫描仪');
INSERT INTO `device_fingerprint_category_info` VALUES (116, 'LABEL_PRINTER', ' LABEL_PRINTER', '标签打印机');
INSERT INTO `device_fingerprint_category_info` VALUES (117, 'THREE_D_PRINTER', ' THREE_D_PRINTER', '3D打印机');
INSERT INTO `device_fingerprint_category_info` VALUES (118, 'GENERIC_NETWORKING', ' GENERIC_NETWORKING', '通用网络服务');
INSERT INTO `device_fingerprint_category_info` VALUES (119, 'SWITCH', ' SWITCH', '交换机');
INSERT INTO `device_fingerprint_category_info` VALUES (120, 'BRIDGE', ' BRIDGE', '桥接器');
INSERT INTO `device_fingerprint_category_info` VALUES (121, 'HUB', ' HUB', '集线器');
INSERT INTO `device_fingerprint_category_info` VALUES (123, 'POE_PLUG', ' POE_PLUG', 'PoE插头');
INSERT INTO `device_fingerprint_category_info` VALUES (124, 'NETWORK_ADAPTER', ' NETWORK_ADAPTER', '网络适配器');
INSERT INTO `device_fingerprint_category_info` VALUES (125, 'POWERLINE_ADAPTER', ' POWERLINE_ADAPTER', '电源线适配器');
INSERT INTO `device_fingerprint_category_info` VALUES (126, 'WWAN_M2M', ' WWAN_M2M', '无线广域网机器对机器通信');
INSERT INTO `device_fingerprint_category_info` VALUES (127, 'HOME_NETWORK_SECURITY', ' HOME_NETWORK_SECURITY', '家庭网络安全设备');
INSERT INTO `device_fingerprint_category_info` VALUES (128, 'FIREWALL', ' FIREWALL', '防火墙');
INSERT INTO `device_fingerprint_category_info` VALUES (129, 'VPN', ' VPN', '虚拟专用网络');
INSERT INTO `device_fingerprint_category_info` VALUES (130, 'ROBOT', ' ROBOT', '机器人');
INSERT INTO `device_fingerprint_category_info` VALUES (155, 'SERVER', ' SERVER', '服务器');
INSERT INTO `device_fingerprint_category_info` VALUES (156, 'LOAD_BALANCER', ' LOAD_BALANCER', '负载均衡器');
INSERT INTO `device_fingerprint_category_info` VALUES (157, 'KVM_SWITCH', ' KVM_SWITCH', 'KVM切换系统');
INSERT INTO `device_fingerprint_category_info` VALUES (158, 'MACBOOK_PRO', 'MACBOOK_PRO', 'MacBook Pro笔记本电脑');
INSERT INTO `device_fingerprint_category_info` VALUES (159, 'MACBOOK_AIR', 'MACBOOK_AIR', 'MacBook Air笔记本电脑');
INSERT INTO `device_fingerprint_category_info` VALUES (160, 'MAC', 'MAC', 'mac电脑');
INSERT INTO `device_fingerprint_category_info` VALUES (161, 'MAC_MINI', 'MAC_MINI', 'Mac mini台式电脑');
INSERT INTO `device_fingerprint_category_info` VALUES (162, 'MAC_PRO', 'MAC_PRO', 'Mac Pro台式电脑');
INSERT INTO `device_fingerprint_category_info` VALUES (163, 'IMAC', 'IMAC', 'iMac台式电脑');
INSERT INTO `device_fingerprint_category_info` VALUES (164, 'WINDOWS_CPMPUTER', 'WINDOWS_CPMPUTER', 'windows电脑');
INSERT INTO `device_fingerprint_category_info` VALUES (165, 'WINDOWS_DESKTOP', 'WINDOWS_DESKTOP', 'windows台式电脑');
INSERT INTO `device_fingerprint_category_info` VALUES (166, 'LINUX_SERVER', 'LINUX_SERVER', 'linux服务器');
INSERT INTO `device_fingerprint_category_info` VALUES (167, 'UBUNTU_SERVER', 'UBUNTU_SERVER', 'ubuntu服务器');
INSERT INTO `device_fingerprint_category_info` VALUES (168, 'CENTOS_SERVER', 'CENTOS_SERVER', 'centos服务器');
INSERT INTO `device_fingerprint_category_info` VALUES (169, 'COMPUTER', 'COMPUTER', '电脑');
INSERT INTO `device_fingerprint_category_info` VALUES (170, 'DESKTOP', 'DESKTOP', '台式电脑');
INSERT INTO `device_fingerprint_category_info` VALUES (171, 'PC', 'PC', '个人电脑');


-- ----------------------------
-- Table structure for sys_setting
-- ----------------------------
DROP TABLE IF EXISTS `sys_setting`;
CREATE TABLE `sys_setting`  (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                `group_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置组code',
                                `setting_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置code',
                                `setting_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置名称',
                                `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
                                `args` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置参数',
                                `default_args` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认配置参数',
                                `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户配置参数' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_setting
-- ----------------------------
INSERT INTO `sys_setting` VALUES (1, 'ASSET_DAYS', 'ACTIVE_DAYS', '活跃资产', 'Number of days for active assets', '3', '3', '2024-01-29 16:15:38', '2024-01-29 16:15:38');
INSERT INTO `sys_setting` VALUES (2, 'ASSET_DAYS', 'DORMANT_DAYS_LOW', '休眠资产下限', 'Number of days for dormant assets', '4', '4', '2024-01-29 16:15:38', '2024-01-30 10:40:19');
INSERT INTO `sys_setting` VALUES (3, 'ASSET_DAYS', 'DORMANT_DAYS_HIGH', '休眠资产上限', 'Number of days for dormant assets', '29', '29', '2024-01-30 10:38:15', '2024-01-30 10:41:34');
INSERT INTO `sys_setting` VALUES (4, 'ASSET_DAYS', 'ZOMBIE_DAYS', '僵尸资产', 'Number of days for zombie assets', '30', '30', '2024-01-29 16:15:38', '2024-01-30 10:41:31');

-- ----------------------------
-- Table structure for denoise_rule
-- ----------------------------
DROP TABLE IF EXISTS `denoise_rule`;
CREATE TABLE `denoise_rule`
(
    `id`               INT   UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`             VARCHAR(255)   COMMENT '规则名称',
    `description`      VARCHAR(255)   COMMENT '规则描述',
    `rule`             VARCHAR(500)   COMMENT '规则内容',
    `time_range`       VARCHAR(100)   COMMENT '匹配时间范围',
    `deduplicate_time` VARCHAR(100)   COMMENT '去重时间范围',
    `scenario_id`      INT            COMMENT '场景id',
    `hit_count`        INT            COMMENT '触发次数',
    `total_count`      INT            COMMENT '总次数',
    `task_id`          INT            COMMENT '任务id',
    `sql_info`              VARCHAR(500)   COMMENT 'sql信息',
    `status`           INT            COMMENT '1 启用 0 禁用',
    `created_at`       TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`       TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4 COMMENT ='降噪规则';

-- ----------------------------
-- Table structure for scenario_conf
-- ----------------------------
DROP TABLE IF EXISTS `scenario_conf`;
CREATE TABLE `scenario_conf`
(
    `id`              INT    UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`            VARCHAR(255)   COMMENT '场景名称',
    `description`     VARCHAR(255)   COMMENT '场景描述',
    `rule_count`      INT            COMMENT '规则数量',
    `created_at`      TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`      TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4 COMMENT ='场景配置';

SET FOREIGN_KEY_CHECKS = 1;
