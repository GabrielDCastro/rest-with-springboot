CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `person` (`id`, `address`, `first_name`, `gender`, `last_name`) VALUES
	(1, '303', 'gabriel', 'M', 'rangel'),
	(3, '303', 'gabriel', 'M', 'castro');
