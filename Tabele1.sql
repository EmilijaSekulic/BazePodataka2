-- -----------------------------------------------------
-- Table `baze2`.`SKorisnik`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baze2`.`SKorisnik` (
  `korisnikId` INT NOT NULL AUTO_INCREMENT,
  `korisnikIme` VARCHAR(20) NULL DEFAULT NULL,
  `korisnikPrezime` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`korisnikId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `baze2`.`SPoruka`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baze2`.`SPoruka` (
  `porukaId` INT NOT NULL AUTO_INCREMENT,
  `korisnikIdSalje` INT NULL DEFAULT NULL,
  `korisnikIdPrima` INT NULL DEFAULT NULL,
  `datum` VARCHAR(15) NULL DEFAULT NULL,
  `tekst` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`porukaId`),
  INDEX `FK_prima` (`korisnikIdPrima` ASC) VISIBLE,
  INDEX `FK_salje` (`korisnikIdSalje` ASC) VISIBLE,
  CONSTRAINT `FK_salje`
    FOREIGN KEY (`korisnikIdSalje`)
    REFERENCES `baze2`.`SKorisnik` (`korisnikId`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT, 
CONSTRAINT `FK_prima`
    FOREIGN KEY (`korisnikIdPrima`)
    REFERENCES `baze2`.`SKorisnik` (`korisnikId`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)