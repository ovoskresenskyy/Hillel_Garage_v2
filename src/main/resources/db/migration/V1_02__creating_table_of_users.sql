CREATE TABLE public.session_user
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 ),
    name text,
    email text,
    password text,
    role text,
    PRIMARY KEY (id)
);