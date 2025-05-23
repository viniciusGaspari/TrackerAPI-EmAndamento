--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3 (Debian 16.3-1.pgdg120+1)
-- Dumped by pg_dump version 16.3

-- Started on 2025-05-23 18:41:23 UTC

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
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 3419 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 17136)
-- Name: chip; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.chip (
    linha numeric(38,2) NOT NULL,
    data_atualizacao timestamp(6) without time zone,
    data_cadastro timestamp(6) without time zone,
    id uuid NOT NULL,
    nome character varying(255) NOT NULL,
    operadora character varying,
    conta uuid
);


ALTER TABLE public.chip OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 17116)
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    data_atualizacao timestamp(6) without time zone,
    data_cadastro timestamp(6) without time zone,
    id uuid NOT NULL,
    scope character varying(50) NOT NULL,
    client_id character varying(150) NOT NULL,
    redirect_uri character varying(200) NOT NULL,
    client_secret character varying(400) NOT NULL
);


ALTER TABLE public.client OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 17313)
-- Name: conta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.conta (
    id uuid NOT NULL,
    username character varying NOT NULL,
    password character varying NOT NULL,
    role uuid NOT NULL,
    data_cadastro timestamp without time zone,
    data_atualizacao timestamp without time zone
);


ALTER TABLE public.conta OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 17127)
-- Name: contrato; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contrato (
    preco numeric(38,2) NOT NULL,
    data_atualizacao timestamp(6) without time zone,
    data_cadastro timestamp(6) without time zone,
    id uuid NOT NULL,
    nome character varying(255) NOT NULL,
    quantidade integer NOT NULL,
    conta uuid
);


ALTER TABLE public.contrato OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 17372)
-- Name: rastreador; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rastreador (
    id uuid NOT NULL,
    nome character varying NOT NULL,
    data_cadastro timestamp without time zone,
    data_atualizacao timestamp without time zone,
    usuario uuid,
    chip uuid NOT NULL
);


ALTER TABLE public.rastreador OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 17154)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    data_atualizacao timestamp(6) without time zone,
    data_cadastro timestamp(6) without time zone,
    id uuid NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 17345)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id uuid NOT NULL,
    nome character varying NOT NULL,
    cidade character varying NOT NULL,
    conta uuid,
    data_cadastro timestamp without time zone,
    data_atualizacao timestamp without time zone,
    contrato uuid NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 3409 (class 0 OID 17136)
-- Dependencies: 218
-- Data for Name: chip; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3407 (class 0 OID 17116)
-- Dependencies: 216
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.client VALUES ('2025-05-15 14:12:03.011941', '2025-05-15 14:12:03.011941', '4ed32d8c-ea58-4dbf-a272-e10c43039331', 'ADMIN', 'id', 'http://localhost:8080/callback2', '$2a$10$TSZzMagBoi3V2B/Jc0XMwek8GroWJjQBQENT8U4ASwteA74qd5.IO');


--
-- TOC entry 3411 (class 0 OID 17313)
-- Dependencies: 220
-- Data for Name: conta; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.conta VALUES ('b3803d8e-27bf-43c3-bf5f-65da8beb9fd0', 'admin', '$2a$10$hYpZ8ZaX.G4IlxoPolUu0uDh0/llBkABSpZu40VsH08x5zldQBDde', '86785e08-56d3-4aa0-b879-2f8ed3136031', '2025-05-16 17:26:33.405467', '2025-05-16 17:26:33.405467');
INSERT INTO public.conta VALUES ('f59ebc11-7c43-445c-b876-bb44d9cee62c', 'assistente', '$2a$10$lOWlJSOTLh6WdtUtuSF7bepj2LYXgjHYSI1wAW9.Uc8vxY.WIb/Ra', 'cd6dbc8e-9f7f-434d-8ed6-5721791c97b3', '2025-05-16 17:44:45.515253', '2025-05-16 17:44:45.515253');
INSERT INTO public.conta VALUES ('40ace0dd-724a-438c-bc00-aac9929bfcda', 'usuario', '$2a$10$IWRGNghGH0ZChER4nYl4JOKzqo2YgsZW84jYC3THyXaBGTepeymau', 'cd6dbc8e-9f7f-434d-8ed6-5721791c97b3', '2025-05-21 15:50:26.44491', '2025-05-21 15:50:26.44491');


--
-- TOC entry 3408 (class 0 OID 17127)
-- Dependencies: 217
-- Data for Name: contrato; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.contrato VALUES (29.90, '2025-05-13 19:31:09.590636', '2025-05-13 19:31:09.590636', '7183107d-c538-45f3-bd1a-97bb862937cf', 'basico', 5, 'b3803d8e-27bf-43c3-bf5f-65da8beb9fd0');
INSERT INTO public.contrato VALUES (69.90, '2025-05-13 19:31:16.564219', '2025-05-13 19:31:16.564219', '740dd3f2-7fc5-4769-a58d-f6423ca89aa6', 'avançado', 20, 'b3803d8e-27bf-43c3-bf5f-65da8beb9fd0');
INSERT INTO public.contrato VALUES (99.90, '2025-05-13 19:31:19.536241', '2025-05-13 19:31:19.536241', '5d450422-89fe-4d4f-ad58-21bd8453b201', 'empresa', 30, 'b3803d8e-27bf-43c3-bf5f-65da8beb9fd0');


--
-- TOC entry 3413 (class 0 OID 17372)
-- Dependencies: 222
-- Data for Name: rastreador; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3410 (class 0 OID 17154)
-- Dependencies: 219
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.role VALUES ('2025-05-13 19:31:27.980246', '2025-05-13 19:31:27.980246', '86785e08-56d3-4aa0-b879-2f8ed3136031', 'admin');
INSERT INTO public.role VALUES ('2025-05-13 19:31:30.348809', '2025-05-13 19:31:30.348809', 'cd6dbc8e-9f7f-434d-8ed6-5721791c97b3', 'assistente');
INSERT INTO public.role VALUES ('2025-05-13 19:31:32.159413', '2025-05-13 19:31:32.159413', '74a67fa0-d144-45b1-b198-2c2877da8bad', 'usuario');


--
-- TOC entry 3412 (class 0 OID 17345)
-- Dependencies: 221
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3238 (class 2606 OID 17124)
-- Name: client client_client_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_client_id_key UNIQUE (client_id);


--
-- TOC entry 3240 (class 2606 OID 17122)
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- TOC entry 3242 (class 2606 OID 17126)
-- Name: client client_redirect_uri_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_redirect_uri_key UNIQUE (redirect_uri);


--
-- TOC entry 3254 (class 2606 OID 17319)
-- Name: conta conta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conta
    ADD CONSTRAINT conta_pkey PRIMARY KEY (id);


--
-- TOC entry 3244 (class 2606 OID 17135)
-- Name: contrato contrato_nome_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contrato
    ADD CONSTRAINT contrato_nome_key UNIQUE (nome);


--
-- TOC entry 3246 (class 2606 OID 17133)
-- Name: contrato contrato_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contrato
    ADD CONSTRAINT contrato_pkey PRIMARY KEY (id);


--
-- TOC entry 3248 (class 2606 OID 17144)
-- Name: chip operadora_nome_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chip
    ADD CONSTRAINT operadora_nome_key UNIQUE (nome);


--
-- TOC entry 3250 (class 2606 OID 17140)
-- Name: chip operadora_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chip
    ADD CONSTRAINT operadora_pkey PRIMARY KEY (id);


--
-- TOC entry 3258 (class 2606 OID 17378)
-- Name: rastreador rastreador_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rastreador
    ADD CONSTRAINT rastreador_pkey PRIMARY KEY (id);


--
-- TOC entry 3252 (class 2606 OID 17158)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- TOC entry 3256 (class 2606 OID 17351)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 3260 (class 2606 OID 17413)
-- Name: chip chip_conta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chip
    ADD CONSTRAINT chip_conta_fkey FOREIGN KEY (conta) REFERENCES public.conta(id);


--
-- TOC entry 3261 (class 2606 OID 17320)
-- Name: conta conta_role_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conta
    ADD CONSTRAINT conta_role_fkey FOREIGN KEY (role) REFERENCES public.role(id);


--
-- TOC entry 3259 (class 2606 OID 17404)
-- Name: contrato contrato_conta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contrato
    ADD CONSTRAINT contrato_conta_fkey FOREIGN KEY (conta) REFERENCES public.conta(id);


--
-- TOC entry 3263 (class 2606 OID 17379)
-- Name: rastreador rastreador_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rastreador
    ADD CONSTRAINT rastreador_usuario_fkey FOREIGN KEY (usuario) REFERENCES public.usuario(id);


--
-- TOC entry 3262 (class 2606 OID 17352)
-- Name: usuario usuario_conta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_conta_fkey FOREIGN KEY (conta) REFERENCES public.conta(id);


-- Completed on 2025-05-23 18:41:23 UTC

--
-- PostgreSQL database dump complete
--

