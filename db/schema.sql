SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

-- *not* creating schema, since initdb creates it


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: audit_entity; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.audit_entity (
    audit_id bigint NOT NULL,
    method character varying(255),
    path character varying(255),
    response jsonb
);


--
-- Name: audit_entity_audit_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.audit_entity_audit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: audit_entity_audit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.audit_entity_audit_id_seq OWNED BY public.audit_entity.audit_id;


--
-- Name: audit_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.audit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


--
-- Name: percentage_entity; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.percentage_entity (
    percentage_id bigint NOT NULL,
    percentage double precision
);


--
-- Name: percentage_entity_percentage_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.percentage_entity_percentage_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: percentage_entity_percentage_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.percentage_entity_percentage_id_seq OWNED BY public.percentage_entity.percentage_id;


--
-- Name: percentage_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.percentage_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


--
-- Name: schema_migrations; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.schema_migrations (
    version character varying(128) NOT NULL
);


--
-- Name: audit_entity audit_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.audit_entity ALTER COLUMN audit_id SET DEFAULT nextval('public.audit_entity_audit_id_seq'::regclass);


--
-- Name: percentage_entity percentage_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.percentage_entity ALTER COLUMN percentage_id SET DEFAULT nextval('public.percentage_entity_percentage_id_seq'::regclass);


--
-- Name: audit_entity audit_entity_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.audit_entity
    ADD CONSTRAINT audit_entity_pkey PRIMARY KEY (audit_id);


--
-- Name: percentage_entity percentage_entity_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.percentage_entity
    ADD CONSTRAINT percentage_entity_pkey PRIMARY KEY (percentage_id);


--
-- Name: schema_migrations schema_migrations_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.schema_migrations
    ADD CONSTRAINT schema_migrations_pkey PRIMARY KEY (version);


--
-- PostgreSQL database dump complete
--


--
-- Dbmate schema migrations
--

INSERT INTO public.schema_migrations (version) VALUES
    ('20230320180951');
