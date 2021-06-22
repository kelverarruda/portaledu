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


    INSERT INTO public.users(
	id, email, fullname, password, status, username, usertype)
	VALUES (1, 'arrudakelver@gmail.com', 'Kelver Arruda', encode(SHA256('admin'), 'hex'), 'ACTIVE', 'admin', 'ADMIN');




SELECT	f.numcad, f.nomfun, 
	CASE
		WHEN f.numcpf = '0' THEN '00000000000'
		ELSE f.numcpf
	END AS 'numcpf',
		f.datnas, c.endrua, c.emapar,
	CASE
		WHEN c.numtel = '' THEN 'S/NUM'
		ELSE c.numtel
	END AS 'numtel'

	FROM r034fun f
LEFT JOIN r034cpl c
	ON f.numcad = c.numcad
WHERE f.numemp = 1



select * from r034fun

select * from r034cpl


SELECT	f.numcad, f.nomfun, 
	CASE
		WHEN f.numcpf = '0' THEN '00000000000'
		ELSE f.numcpf
	END AS 'numcpf',
		f.datnas, 
	CASE
		WHEN d.grapar = 3 AND d.tipsex = 'F' THEN d.nomdep
		ELSE ''
		END AS 'nommae',
	CASE
		WHEN d.grapar = 3 AND d.tipsex = 'M' THEN d.nomdep
		ELSE ''
		END AS 'nompai',
	 c.endrua, c.emapar,
	CASE
		WHEN c.numtel = '' THEN 'S/NUM'
		ELSE c.numtel
	END AS 'numtel'
	FROM r034fun f
INNER JOIN r034cpl c
	ON f.numcad = c.numcad
INNER JOIN r036dep d
	ON f.numcad = d.numcad
WHERE f.numemp = 1 and f.tipcol = 1



select * from r034fun

select * from r034cpl


select * from r036dep