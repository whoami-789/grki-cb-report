create table mahalla
(
    id                  bigint identity(1,1) primary key,

    dict_code           int not null,

    mahalla_inn         nvarchar(32) not null,

    mahalla_name        nvarchar(255) not null,

    mahalla_code        nvarchar(32) not null,

    region_name         nvarchar(255) null,

    region_cb_code      nvarchar(255) null,

    district_name       nvarchar(255) null,

    sector_no           int null,

    district_soato_code nvarchar(255) null,

    district_cb_code    nvarchar(255) null,

    date_active         date null,

    date_end            date null,

    active_flag         nchar(1) null,

    is_active           bit null,

    name_norm           nvarchar(255) null
);