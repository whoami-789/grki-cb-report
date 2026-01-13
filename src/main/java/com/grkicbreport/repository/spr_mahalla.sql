CREATE TABLE spr_mahalla
(
    id             BIGINT IDENTITY (1,1) NOT NULL PRIMARY KEY,

    uz_kad       BIGINT                NULL, -- Код УзКад (колонка 1)
    code_1c        BIGINT                NULL, -- Код 1C  (колонка 2)
    uz_kad_2       BIGINT                NULL, -- Код УзКад (колонка 3, дубль)

    mahalla_inn    VARCHAR(32)           NULL, -- ИНН махалли
    soato_region   INT                   NULL, -- Код области СОАТО
    soato_district INT                   NULL, -- Код района СОАТО
    cb_district    VARCHAR(32)           NULL, -- Код района махалли (ЦБ)

    name_uz        NVARCHAR(255)         NULL, -- Наименование махалли Uz
    name_ru        NVARCHAR(255)         NULL, -- Наименование махалли Ru
    name_en        NVARCHAR(255)         NULL, -- Наименование махалли En

    date_active    DATE                  NULL, -- Дата активизации
    date_end       DATE                  NULL, -- Дата деактивизации
    active_flag    CHAR(1)               NULL  -- Признак активности
);