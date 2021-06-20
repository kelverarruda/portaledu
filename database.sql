CREATE TABLE public.users
(
    id bigserial NOT NULL,
    fullname character varying(80) NOT NULL,
    email character varying(80) NOT NULL,
    username character varying(30) NOT NULL,
    password character varying(255) NOT NULL,
    usertype character varying(10),
    status character(10),
    CONSTRAINT pk_userid PRIMARY KEY (id),
    CONSTRAINT unq_email UNIQUE (email),
    CONSTRAINT unq_username UNIQUE (username)
);

ALTER TABLE public.users
    OWNER to postgres;