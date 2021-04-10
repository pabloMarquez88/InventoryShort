
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
ENGINE=InnoDB
;