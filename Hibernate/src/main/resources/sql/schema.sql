DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(100) NULL,
`description` VARCHAR(800) NULL,
`createTime` DATETIME,
`updateTime` DATETIME,
`price` DECIMAL(10,2),
`type` VARCHAR(10),
PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `review`;
CREATE TABLE IF NOT EXISTS `review` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`product_id` BIGINT NOT NULL,
`content` VARCHAR(400) NULL,
`rating` INT NULL,
PRIMARY KEY (`id`),
CONSTRAINT `fk_review_product`
FOREIGN KEY (`product_id`)
REFERENCES `product` (`id`)
ON DELETE CASCADE
ON UPDATE CASCADE
);

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(100) NULL,
`description` VARCHAR(800) NULL,
PRIMARY KEY (`id`),
);

ALTER TABLE `product`
ADD COLUMN `category_id` BIGINT NULL;

ALTER TABLE `product`
ADD CONSTRAINT `fk_product_category`
FOREIGN KEY (`category_id`)
REFERENCES `category` (`id`);

CREATE TABLE `attribute` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(100) NULL,
`value` VARCHAR(800) NULL,
PRIMARY KEY (`id`)
);

CREATE TABLE `product_attribute` (
`product_id` BIGINT NOT NULL,
`attribute_id` BIGINT NOT NULL,
PRIMARY KEY (`product_id`, `attribute_id`),
CONSTRAINT `fk_product_attribute_product_id`
FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
CONSTRAINT `fk_product_attribute_attribute_id`
FOREIGN KEY (`attribute_id`) REFERENCES `attribute` (`id`)
);

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`firstname` VARCHAR(100) NOT NULL,
`lastname` VARCHAR(800) NOT NULL,
`created` DATETIME,
`updated` DATETIME,
PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`customer_id` BIGINT NOT NULL,
`created` DATETIME,
`total` DECIMAL(5,2),
PRIMARY KEY (`id`),
CONSTRAINT `fk_order_customer_id`
FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
);

DROP TABLE IF EXISTS `order_row`;
CREATE TABLE `order_row` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`order_id` BIGINT NOT NULL,
`product_id` BIGINT NOT NULL,
`price` DECIMAL(5,2),
PRIMARY KEY (`id`),
CONSTRAINT `fk_order_row_order_id`
FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
CONSTRAINT `fk_order_row_product_id`
FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
);

DROP TABLE IF EXISTS `customer_address`;
CREATE TABLE `customer_address` (
	`customer_id` BIGINT NOT NULL,
    `address_type` VARCHAR(12) NOT NULL,
	`postal code` VARCHAR(6) NOT NULL,
	`street` VARCHAR(120) NOT NULL,
    `city` VARCHAR(80) NOT NULL
);

DROP TABLE IF EXISTS `customer_details`;
CREATE TABLE `customer_details` (
	`id` BIGINT NOT NULL,
    `birth_place` VARCHAR(100) NOT NULL,
    `birth_day` DATETIME NOT NULL,
    `father_name` VARCHAR(50),
    `mother_name` VARCHAR(50),
    `pesel` VARCHAR(11),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id`) REFERENCES customer(`id`)
);

ALTER TABLE `order_row` DROP FOREIGN KEY `fk_order_row_order_id`;
ALTER TABLE `order_row` CHANGE COLUMN `order_id` `order_id` BIGINT NULL;
ALTER TABLE `order_row` ADD CONSTRAINT `fk_order_row_order_id`
FOREIGN KEY (`order_id`) REFERENCES `order` (`id`);

DROP TABLE IF EXISTS `base_product`;
CREATE TABLE `base_product` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `description` VARCHAR(5000) NOT NULL,
    `created` DATETIME NOT NULL,
    `product_type` VARCHAR(15) NOT NULL,
    `weight` DECIMAL(5,2),
    `width` INT,
    `length` INT,
    `height` INT,
    `downloadable` boolean,
    `file_path` VARCHAR(100),
    `file_name` VARCHAR(100),
    PRIMARY KEY (`id`)
);

ALTER TABLE `order` ADD COLUMN uuid VARCHAR(36) NULL AFTER total;

ALTER TABLE `review` ADD COLUMN `customer_id` BIGINT NULL AFTER `rating`;
ALTER TABLE `review` ADD CONSTRAINT `fk_review_customer_id`
FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `order` ADD COLUMN `version` BIGINT NULL DEFAULT 0 AFTER `uuid`;