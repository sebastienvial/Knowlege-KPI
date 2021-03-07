-- Table: knowledge.kpi_role

-- DROP TABLE kpi_role;

CREATE TABLE knowledge.kpi_role
(
    email character varying(100) COLLATE pg_catalog."default",
    password character varying(100) COLLATE pg_catalog."default",
    role character varying(20) COLLATE pg_catalog."default"
)

TABLESPACE pg_default;

ALTER TABLE kpi_role
    OWNER to postgres;


INSERT INTO KPI_ROLE (EMAIL, PASSWORD, ROLE) VALUES ('SEBASTIEN.VIAL@BOBST.COM', '$2a$10$zxvEq8XzYEYtNjbkRsJEbukHeRx3XS6MDXHMu8cNuNsRfZJWwswDy', 'ROLE_ADMIN');

COMMIT TRANSACTION;
