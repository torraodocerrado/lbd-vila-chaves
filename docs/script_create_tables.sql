CREATE TABLE IF NOT EXISTS public.persona
(
    name character varying NOT NULL,
    gender character varying NOT NULL,
    age integer NOT NULL,
    CONSTRAINT persona_pkey PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS public.catchphrase
(
    name character varying NOT NULL,
    phrase character varying NOT NULL,
    mood character varying NOT NULL,
    CONSTRAINT catchphrase_pkey PRIMARY KEY (name, phrase),
    CONSTRAINT catchphrase_persona_fk FOREIGN KEY (name)
        REFERENCES public.persona (name) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
