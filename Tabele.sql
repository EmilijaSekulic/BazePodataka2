drop table if exists SKorisnik;

drop table if exists SPoruka;

/*==============================================================*/
/* Table: SKorisnik                                            */
/*==============================================================*/
create table SKorisnik
(
   korisnikId                   int not null auto_increment,
   korisnikIme                  varchar(20),
   korisnikPrezime              varchar(20),
   primary key (korisnikId)
);

/*==============================================================*/
/* Table: SPoruka                                               */
/*==============================================================*/
create table SPoruka
(
   porukaId             int not null auto_increment,
   korisnikIdSalje        int,
   korisnikIdPrima        int,
   datum                varchar(15),
   tekst                varchar(200),
   primary key (porukaId)
);

alter table SPoruka add constraint FK_PRIMA foreign key (korisnikIdPrima)
      references SKorisnik (korisnikId) on delete restrict on update restrict;

alter table SPoruka add constraint FK_SALJE foreign key (korisnikIdSalje)
      references SKorisnik (korisnikId) on delete restrict on update restrict;




