
CREATE TABLE `camera` (
  `camera_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `camera_make` VARCHAR(255) NOT NULL,
  `camera_model` VARCHAR(255) NOT NULL,
  `megapixels` INT(10),
  PRIMARY KEY (`camera_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;