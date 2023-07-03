--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

-- Started on 2023-06-04 21:47:37

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
-- TOC entry 3340 (class 1262 OID 16423)
-- Name: contacud; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE contacud WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';


ALTER DATABASE contacud OWNER TO postgres;

\connect contacud

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
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 3341 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 214 (class 1259 OID 16425)
-- Name: tb_conta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_conta (
    numero bigint NOT NULL,
    id_cliente character varying NOT NULL,
    id_gerente character varying NOT NULL,
    saldo numeric,
    data_criacao date,
    limite numeric
);


ALTER TABLE public.tb_conta OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16524)
-- Name: tb_conta_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_conta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_conta_seq OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16430)
-- Name: tb_movimentacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_movimentacao (
    id bigint NOT NULL,
    tipo_movimentacao numeric,
    valor numeric,
    data_hora_criacao date,
    numero_conta_origem bigint,
    numero_conta_destino bigint
);


ALTER TABLE public.tb_movimentacao OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16435)
-- Name: tb_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_user (
    id character varying NOT NULL,
    cpf character varying,
    salario numeric
);


ALTER TABLE public.tb_user OWNER TO postgres;

--
-- TOC entry 3331 (class 0 OID 16425)
-- Dependencies: 214
-- Data for Name: tb_conta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_conta (numero, id_cliente, id_gerente, saldo, data_criacao, limite) FROM stdin;
1	f72d2101-5a26-4773-9d5a-e7b4ab4c3168	8e027784-f0c2-4660-a021-9a4ea8b3f87a	0	2023-06-04	0
\.


--
-- TOC entry 3332 (class 0 OID 16430)
-- Dependencies: 215
-- Data for Name: tb_movimentacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_movimentacao (id, tipo_movimentacao, valor, data_hora_criacao, numero_conta_origem, numero_conta_destino) FROM stdin;
\.


--
-- TOC entry 3333 (class 0 OID 16435)
-- Dependencies: 216
-- Data for Name: tb_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_user (id, cpf, salario) FROM stdin;
f143ac15-b062-4639-afad-73fbc0ed8cd8	00000000002	555.549999999999954525264911353588104248046875
5d5e8e82-47bc-44bf-8049-c6b2fcf1d165	00000000003	555.549999999999954525264911353588104248046875
276a0262-1cd9-486a-9a33-f767ced837aa	00000000004	555.549999999999954525264911353588104248046875
c9b8d0d6-6d07-4498-8c89-08f6d20b817a	00000000004	555.549999999999954525264911353588104248046875
4ae367b7-21ae-4b67-9935-02df78c3009d	00000000005	555.549999999999954525264911353588104248046875
f72d2101-5a26-4773-9d5a-e7b4ab4c3168	00000000006	555.549999999999954525264911353588104248046875
8e027784-f0c2-4660-a021-9a4ea8b3f87a	00000000007	555.549999999999954525264911353588104248046875
8e843879-3454-4954-b3dd-f3544d27775b	00000000008	555.549999999999954525264911353588104248046875
887036ed-7c4a-4520-9791-9967bdb418f2	00000000009	555.549999999999954525264911353588104248046875
cfcf59a5-b8af-4873-ac84-8eee5b79efd3	00000000010	555.549999999999954525264911353588104248046875
27c38e6c-8e8b-4db9-8f78-a83c8f61e230	00000000011	555.549999999999954525264911353588104248046875
770b8849-d95b-4366-8b49-9df7bae118c3	00000000012	555.549999999999954525264911353588104248046875
d5f93c32-956c-440c-8319-12c7cbd2e825	00000000013	555.549999999999954525264911353588104248046875
1e0e1eeb-91c1-48ff-80f6-d08871ab82a6	00000000014	555.549999999999954525264911353588104248046875
\.


--
-- TOC entry 3342 (class 0 OID 0)
-- Dependencies: 217
-- Name: tb_conta_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_conta_seq', 1, true);


--
-- TOC entry 3182 (class 2606 OID 16441)
-- Name: tb_conta tb_conta_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_conta
    ADD CONSTRAINT tb_conta_pk PRIMARY KEY (numero);


--
-- TOC entry 3184 (class 2606 OID 16445)
-- Name: tb_conta tb_conta_un; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_conta
    ADD CONSTRAINT tb_conta_un UNIQUE (numero);


--
-- TOC entry 3186 (class 2606 OID 16461)
-- Name: tb_movimentacao tb_movimentacao_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_movimentacao
    ADD CONSTRAINT tb_movimentacao_pk PRIMARY KEY (id);


--
-- TOC entry 3188 (class 2606 OID 16510)
-- Name: tb_user tb_user_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_user
    ADD CONSTRAINT tb_user_pk PRIMARY KEY (id);


-- Completed on 2023-06-04 21:47:37

--
-- PostgreSQL database dump complete
--

