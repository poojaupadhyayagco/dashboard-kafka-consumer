--
-- PostgreSQL database dump
--

-- Dumped from database version 10.6 (Debian 10.6-1.pgdg90+1)
-- Dumped by pg_dump version 10.6 (Debian 10.6-1.pgdg90+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;
SET default_tablespace = '';
SET default_with_oids = false;

--
-- Name: device_reference_data; Type: TABLE; Schema: public; Owner: dashboard_app_user
--

CREATE TABLE public.device_reference_data (
    primaryId uuid NOT NULL,
    dateCreated timestamp without time zone,
    dateUpdated timestamp without time zone,
    activated boolean NOT NULL,
    allowdatasharing boolean,
    description character varying(255),
    deviceid character varying(255),
    devicetype character varying(255),
    engineering boolean NOT NULL,
    equipmentidentificationnumber character varying(255),
    hasseenbefore boolean,
    lastupdatedtime character varying(255),
    revision character varying(255),
    serialnumber character varying(255),
    servicelevel integer,
    softwareversion character varying(255)
);


-- ALTER TABLE public.device_reference_data OWNER TO dashboard_app_user;

--
-- Name: device_reference_data device_reference_data_pkey; Type: CONSTRAINT; Schema: public; Owner: dashboard_app_user
--

ALTER TABLE ONLY public.device_reference_data ADD CONSTRAINT device_reference_data_pkey PRIMARY KEY (primaryId);

--
-- PostgreSQL database dump complete
--

CREATE TABLE public.activation_event
(
    primaryId uuid NOT NULL,
    dateCreated timestamp without time zone NOT NULL,
    dateUpdated timestamp without time zone NOT NULL,
    serialNumber character varying(255) NOT NULL,
    currentStepNumber character varying(255) NOT NULL,
    currentStepName character varying(255) NOT NULL,
    message character varying(255),
    type character varying(255) NOT NULL,
    id character varying(255) NOT NULL,
    isComplete character varying(255) NOT NULL,
    hasError character varying(255) NOT NULL,
    created character varying(255) NOT NULL,
    updated character varying(255) NOT NULL,
    deviceReferenceId uuid NOT NULL
);

ALTER TABLE ONLY public.activation_event ADD CONSTRAINT activation_event_pkey PRIMARY KEY (primaryId);

ALTER TABLE ONLY public.activation_event ADD CONSTRAINT activation_event_fkey FOREIGN KEY (deviceReferenceId) REFERENCES public.device_reference_data (primaryId);
