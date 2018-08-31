CREATE TABLE `registration`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `role_id` INT NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `mobile_no` BIGINT(10) NOT NULL,
  `password` VARCHAR(25) NOT NULL,
  `gender` ENUM('male', 'female') NOT NULL,
  `date_of_birth` DATE NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `mobile_no_UNIQUE` (`mobile_no` ASC) VISIBLE);
