CREATE DATABASE IF NOT EXISTS `transaction_db`;

USE transaction_db;

CREATE TABLE IF NOT EXISTS `account` (
  `accountId` BIGINT(20) NOT NULL COMMENT 'id',
  `accountType` VARCHAR(50) NOT NULL COMMENT '账户类型',
  `memberId` BIGINT(20) NOT NULL COMMENT '会员id',
  `balance` DECIMAL(36,18) NOT NULL DEFAULT '0' COMMENT '可用金额',
  `freeze` DECIMAL(36,18) NOT NULL  DEFAULT '0' COMMENT '冻结金额',
  `total` DECIMAL(36,18) NOT NULL  DEFAULT '0' COMMENT '余额',
  `version` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '乐观锁版本',
  `createdTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
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
  `createdTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`accountHistoryId`),
  KEY `idx_account_id` (`accountId`) USING BTREE
  ) COMMENT '账户表';