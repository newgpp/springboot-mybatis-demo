CREATE DATABASE IF NOT EXISTS `transaction_db`;

USE transaction_db;

CREATE TABLE IF NOT EXISTS `member` (
  `memberId` BIGINT(20) NOT NULL COMMENT 'id',
  `memberName` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '名字',
  `mobile` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '电话',
  `email` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '邮箱',
  `birthday` DATE DEFAULT NULL COMMENT '生日',
  `createdTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`memberId`),
  UNIQUE KEY `idx_mobile` (`mobile`) USING BTREE,
  UNIQUE KEY `idx_email` (`email`) USING BTREE
  ) COMMENT '会员表';


CREATE TABLE IF NOT EXISTS `account` (
  `accountId` BIGINT(20) NOT NULL COMMENT 'id',
  `accountType` VARCHAR(50) NOT NULL COMMENT '账户类型',
  `memberId` BIGINT(20) NOT NULL COMMENT '会员id',
  `balance` DECIMAL(36,18) NOT NULL DEFAULT '0' COMMENT '可用金额',
  `freeze` DECIMAL(36,18) NOT NULL  DEFAULT '0' COMMENT '冻结金额',
  `total` DECIMAL(36,18) NOT NULL  DEFAULT '0' COMMENT '余额',
  `version` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '乐观锁版本',
  `createdTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`accountId`),
  KEY `idx_member_id` (`memberId`) USING BTREE
  ) COMMENT '账户表';


CREATE TABLE IF NOT EXISTS `accountHistory` (
  `accountHistoryId` BIGINT(20) NOT NULL COMMENT 'id',
  `transactionType` VARCHAR(50) NOT NULL COMMENT '交易类型',
  `accountId` BIGINT(20) NOT NULL COMMENT 'id',
  `balance` DECIMAL(36,18) NOT NULL DEFAULT '0' COMMENT '可用金额',
  `freeze` DECIMAL(36,18) NOT NULL  DEFAULT '0' COMMENT '冻结金额',
  `total` DECIMAL(36,18) NOT NULL  DEFAULT '0' COMMENT '余额',
  `amount` DECIMAL(36,18) NOT NULL  DEFAULT '0' COMMENT '变动金额',
  `ts` BIGINT(20) NOT NULL COMMENT '毫秒时间戳',
  `createdTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`accountHistoryId`),
  KEY `idx_account_id` (`accountId`) USING BTREE
  ) COMMENT '账户流水表';