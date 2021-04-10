
CREATE DATABASE `Inventario` /*!40100 COLLATE 'latin1_swedish_ci' */


CREATE TABLE `item` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`count` INT(11) NOT NULL DEFAULT '0',
	`code` VARCHAR(50) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`name` VARCHAR(250) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`description` VARCHAR(250) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`status` VARCHAR(50) NULL DEFAULT 'ALIVE',
	PRIMARY KEY (`id`) USING BTREE,
	INDEX `id` (`id`) USING BTREE
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;

CREATE TABLE `history_item` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`item_id` INT(11) NULL DEFAULT NULL,
	`date` DATETIME NULL DEFAULT NULL,
	`pcName` VARCHAR(50) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`oldAmount` INT(11) NULL DEFAULT NULL,
	`newAmount` INT(11) NULL DEFAULT NULL,
	`action` VARCHAR(50) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	PRIMARY KEY (`id`) USING BTREE,
	INDEX `id` (`id`) USING BTREE
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;
