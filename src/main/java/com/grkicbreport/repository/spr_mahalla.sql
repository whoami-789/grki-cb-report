CREATE TABLE dbo.spr_mahalla
(
    id             BIGINT IDENTITY (1,1) NOT NULL PRIMARY KEY,

    uz_kad         NVARCHAR(16)          NULL, -- Код УзКад (колонка 1)
    code_1c        NVARCHAR(16)          NULL, -- Код 1C (колонка 2)
    uz_kad_2       NVARCHAR(16)          NULL, -- второй "Код УзКад" (колонка 3)

    mahalla_inn    NVARCHAR(32)          NULL, -- ИНН махалли (колонка 4)
    soato_region   NVARCHAR(16)          NULL, -- СОАТО область (колонка 5)
    soato_district NVARCHAR(16)          NULL, -- СОАТО район (колонка 6)
    cb_district    NVARCHAR(16)          NULL, -- Код района ЦБ (колонка 7)

    name_uz        NVARCHAR(256)         NULL, -- (колонка 8)
    name_ru        NVARCHAR(256)         NULL, -- (колонка 9)
    name_en        NVARCHAR(256)         NULL, -- (колонка 10)

    date_active    DATE                  NULL, -- (колонка 11)
    date_end       DATE                  NULL, -- (колонка 12)

    active_flag    NVARCHAR(1)           NULL  -- (колонка 13)
);