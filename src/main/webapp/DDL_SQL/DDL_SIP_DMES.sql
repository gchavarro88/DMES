--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.10
-- Dumped by pg_dump version 9.3.10
-- Started on 2016-04-28 11:54:46 COT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 6 (class 2615 OID 305317)
-- Name: dmes; Type: SCHEMA; Schema: -; Owner: sipPrueba
--

CREATE SCHEMA dmes;


ALTER SCHEMA dmes OWNER TO "sipPrueba";

--
-- TOC entry 338 (class 3079 OID 11829)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 3038 (class 0 OID 0)
-- Dependencies: 338
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = dmes, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 336 (class 1259 OID 313508)
-- Name: ot_log_production; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE ot_log_production (
    id_log numeric(18,0) NOT NULL,
    id_process_product numeric(18,0) NOT NULL,
    id_order numeric(18,0) NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    id_machine numeric(18,0)
);


ALTER TABLE dmes.ot_log_production OWNER TO "sipPrueba";

--
-- TOC entry 170 (class 1259 OID 305318)
-- Name: ot_maintenance; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE ot_maintenance (
    id_maintenance numeric(18,0) NOT NULL,
    id_machine_part numeric(18,0) NOT NULL,
    id_priority numeric(18,0) NOT NULL,
    description character varying(400),
    id_maintenance_clasification numeric(18,0) NOT NULL,
    id_maintenance_state numeric(18,0) NOT NULL,
    id_workforce numeric(18,0) NOT NULL,
    duration numeric(18,0) NOT NULL,
    description_damage character varying(400),
    id_maintenance_damage numeric(18,0) NOT NULL,
    creation_date timestamp with time zone NOT NULL,
    response_date timestamp with time zone,
    end_date timestamp with time zone,
    id_maintenance_schedule character varying(400)
);


ALTER TABLE dmes.ot_maintenance OWNER TO "sipPrueba";

--
-- TOC entry 171 (class 1259 OID 305324)
-- Name: ot_maintenance_corrective; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE ot_maintenance_corrective (
    id_maintenance_corrective numeric(18,0) NOT NULL,
    name character varying(200) NOT NULL,
    description character varying(400),
    id_maintenance numeric(18,0) NOT NULL,
    duration character varying(100)
);


ALTER TABLE dmes.ot_maintenance_corrective OWNER TO "sipPrueba";

--
-- TOC entry 172 (class 1259 OID 305330)
-- Name: ot_maintenance_preventive; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE ot_maintenance_preventive (
    id_maintenance_preventive numeric(18,0) NOT NULL,
    name character varying(200) NOT NULL,
    description character varying(400),
    id_maintenance numeric(18,0) NOT NULL,
    type_frequency character varying(50) NOT NULL,
    amount_schedule numeric(18,0),
    duration character varying(100)
);


ALTER TABLE dmes.ot_maintenance_preventive OWNER TO "sipPrueba";

--
-- TOC entry 173 (class 1259 OID 305336)
-- Name: ot_maintenance_schedule; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE ot_maintenance_schedule (
    id_schedule_maintenance numeric(18,0) NOT NULL,
    id_employee numeric(18,0),
    creation_date timestamp(0) with time zone NOT NULL,
    id_maintenance numeric(18,0) NOT NULL,
    end_date timestamp with time zone NOT NULL
);


ALTER TABLE dmes.ot_maintenance_schedule OWNER TO "sipPrueba";

--
-- TOC entry 174 (class 1259 OID 305339)
-- Name: ot_production_order; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE ot_production_order (
    id_production_order numeric(18,0) NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    start_date timestamp without time zone,
    end_date timestamp without time zone,
    id_production_state numeric(18,0) NOT NULL,
    name character varying(50),
    description character varying(200),
    start_date_real timestamp without time zone,
    end_date_real timestamp without time zone
);


ALTER TABLE dmes.ot_production_order OWNER TO "sipPrueba";

--
-- TOC entry 175 (class 1259 OID 305342)
-- Name: sc_competencies; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_competencies (
    id_competencies numeric(18,0) NOT NULL,
    tittle character varying(100) NOT NULL,
    description character varying(2000),
    id_employee numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_competencies OWNER TO "sipPrueba";

--
-- TOC entry 176 (class 1259 OID 305348)
-- Name: sc_constants_load_files; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_constants_load_files (
    id_constants_load_file numeric(18,0) NOT NULL,
    max_size_file numeric(18,0) NOT NULL,
    extension character varying(2000) NOT NULL,
    path character varying(2000)
);


ALTER TABLE dmes.sc_constants_load_files OWNER TO "sipPrueba";

--
-- TOC entry 177 (class 1259 OID 305354)
-- Name: sc_cost_center; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_cost_center (
    id_cost_center numeric(18,0) NOT NULL,
    description character varying(100) NOT NULL,
    cost_center character varying(100) NOT NULL,
    creation_date date NOT NULL,
    modify_date timestamp with time zone
);


ALTER TABLE dmes.sc_cost_center OWNER TO "sipPrueba";

--
-- TOC entry 178 (class 1259 OID 305357)
-- Name: sc_distribution_unit; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_distribution_unit (
    id_distribution_unit numeric(18,0) NOT NULL,
    acronym character varying(20) NOT NULL,
    description character varying(200)
);


ALTER TABLE dmes.sc_distribution_unit OWNER TO "sipPrueba";

--
-- TOC entry 179 (class 1259 OID 305360)
-- Name: sc_documents; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_documents (
    id_document numeric(18,0) NOT NULL,
    document_path character varying(200) NOT NULL,
    document_tittle character varying(2000) NOT NULL,
    creation_date timestamp with time zone NOT NULL,
    document_name character varying(2000) NOT NULL,
    id_person numeric(18,0) NOT NULL,
    upload_by character varying(2000) NOT NULL,
    type_document character varying(200)
);


ALTER TABLE dmes.sc_documents OWNER TO "sipPrueba";

--
-- TOC entry 180 (class 1259 OID 305366)
-- Name: sc_employee; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_employee (
    id_employee numeric(18,0) NOT NULL,
    "position" character varying(100) NOT NULL,
    formation character varying(100) NOT NULL,
    admission_date timestamp with time zone NOT NULL,
    retirement_date timestamp with time zone,
    active character(1) NOT NULL,
    salary numeric(18,2),
    hour_value numeric(18,2),
    creation_date date NOT NULL,
    modify_date timestamp with time zone,
    id_person numeric(18,0) NOT NULL,
    id_turn numeric(18,0)
);


ALTER TABLE dmes.sc_employee OWNER TO "sipPrueba";

--
-- TOC entry 181 (class 1259 OID 305369)
-- Name: sc_factory_location; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_factory_location (
    id_factory_location numeric(18,0) NOT NULL,
    location character varying(200) NOT NULL,
    description character varying(2000)
);


ALTER TABLE dmes.sc_factory_location OWNER TO "sipPrueba";

--
-- TOC entry 182 (class 1259 OID 305375)
-- Name: sc_group; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_group (
    id_group numeric(18,0) NOT NULL,
    name character varying(100) NOT NULL,
    description character varying(200),
    type numeric(1,0) NOT NULL
);


ALTER TABLE dmes.sc_group OWNER TO "sipPrueba";

--
-- TOC entry 183 (class 1259 OID 305378)
-- Name: sc_input; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_input (
    id_input numeric(18,0) NOT NULL,
    type_material character varying(200) NOT NULL,
    expiry_date timestamp with time zone,
    supplier_guarantee numeric(18,0) NOT NULL,
    mark character varying(200) NOT NULL,
    value numeric(18,0) NOT NULL,
    path_picture character varying(2000),
    cost_center numeric(18,0) NOT NULL,
    serie character varying(2000) NOT NULL,
    creation_date timestamp with time zone NOT NULL,
    description character varying(200) NOT NULL,
    id_stock numeric(18,0) NOT NULL,
    id_priority numeric(18,0) NOT NULL,
    id_input_dimension numeric(18,0),
    id_packing numeric(18,0),
    id_money numeric(18,0),
    total_amount_distribution numeric(18,0),
    id_distribution_unit numeric(18,0),
    distribution_amount numeric(18,0),
    distribution_value numeric(18,2),
    id_location numeric(18,0)
);


ALTER TABLE dmes.sc_input OWNER TO "sipPrueba";

--
-- TOC entry 184 (class 1259 OID 305384)
-- Name: sc_input_dimension; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_input_dimension (
    id_input_dimension numeric(18,0) NOT NULL,
    hight character varying(100),
    width character varying(100),
    large character varying(100),
    weight character varying(100),
    volume character varying(100),
    thickness character varying(100),
    radio character varying(100),
    observations character varying(2000)
);


ALTER TABLE dmes.sc_input_dimension OWNER TO "sipPrueba";

--
-- TOC entry 185 (class 1259 OID 305390)
-- Name: sc_input_documents; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_input_documents (
    id_input_documents numeric(18,0) NOT NULL,
    document_path character varying(200) NOT NULL,
    document_tittle character varying(200),
    creation_date timestamp with time zone NOT NULL,
    document_name character varying(200),
    upload_by character varying(50) NOT NULL,
    type_document character varying(200) NOT NULL,
    id_input numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_input_documents OWNER TO "sipPrueba";

--
-- TOC entry 186 (class 1259 OID 305396)
-- Name: sc_input_equivalence; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_input_equivalence (
    id_input_equivalence numeric(18,0) NOT NULL,
    id_input numeric(18,0) NOT NULL,
    id_input_referenced numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_input_equivalence OWNER TO "sipPrueba";

--
-- TOC entry 187 (class 1259 OID 305399)
-- Name: sc_input_feactures; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_input_feactures (
    id_input_feactures numeric(18,0) NOT NULL,
    tittle character varying(200) NOT NULL,
    description character varying(2000) NOT NULL,
    id_input numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_input_feactures OWNER TO "sipPrueba";

--
-- TOC entry 188 (class 1259 OID 305405)
-- Name: sc_input_observations; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_input_observations (
    id_input_observation numeric(18,0) NOT NULL,
    tittle character varying(200) NOT NULL,
    description character varying(2000) NOT NULL,
    id_input numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_input_observations OWNER TO "sipPrueba";

--
-- TOC entry 189 (class 1259 OID 305411)
-- Name: sc_input_specifications; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_input_specifications (
    id_input_specifications numeric(18,0) NOT NULL,
    description character varying(2000) NOT NULL,
    tittle character varying(200) NOT NULL,
    creation_date timestamp with time zone NOT NULL,
    id_input numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_input_specifications OWNER TO "sipPrueba";

--
-- TOC entry 190 (class 1259 OID 305417)
-- Name: sc_location; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_location (
    id_location numeric(18,0) NOT NULL,
    location character varying(200) NOT NULL,
    description character varying(2000),
    id_store numeric(18,0)
);


ALTER TABLE dmes.sc_location OWNER TO "sipPrueba";

--
-- TOC entry 191 (class 1259 OID 305423)
-- Name: sc_machine; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_machine (
    id_machine numeric(18,0) NOT NULL,
    name character varying(200) NOT NULL,
    hour_value numeric(18,2) NOT NULL,
    description character varying(400),
    id_priority numeric(18,0),
    id_time numeric(18,0),
    useful_life numeric(18,0),
    id_partner numeric(18,0),
    id_cost_center numeric(18,0),
    id_money numeric(18,0),
    id_location numeric(18,0),
    mark character varying(200),
    serie character varying(200),
    type character varying(200),
    clasification character varying(200),
    id_dimension numeric(18,0),
    path_picture character varying(2000),
    id_state numeric(1,0) DEFAULT 1
);


ALTER TABLE dmes.sc_machine OWNER TO "sipPrueba";

--
-- TOC entry 3039 (class 0 OID 0)
-- Dependencies: 191
-- Name: COLUMN sc_machine.id_state; Type: COMMENT; Schema: dmes; Owner: sipPrueba
--

COMMENT ON COLUMN sc_machine.id_state IS 'Valor del estado de la máquina 1 en funcionamiento 0 en paro';


--
-- TOC entry 192 (class 1259 OID 305430)
-- Name: sc_machine_attached; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_machine_attached (
    id_attached numeric(18,0) NOT NULL,
    type character varying(50) NOT NULL,
    tittle character varying(200) NOT NULL,
    description character varying(200) NOT NULL,
    id_machine numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_machine_attached OWNER TO "sipPrueba";

--
-- TOC entry 193 (class 1259 OID 305433)
-- Name: sc_machine_conditions; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_machine_conditions (
    id_condition numeric(18,0) NOT NULL,
    type character varying(200) NOT NULL,
    description character varying(400),
    id_machine numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_machine_conditions OWNER TO "sipPrueba";

--
-- TOC entry 194 (class 1259 OID 305439)
-- Name: sc_machine_document; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_machine_document (
    id_machine_document numeric(18,0) NOT NULL,
    document_path character varying(200) NOT NULL,
    document_tittle character varying(200) NOT NULL,
    creation_date timestamp with time zone NOT NULL,
    document_name character varying(200) NOT NULL,
    upload_by character varying(200) NOT NULL,
    id_machine numeric(18,0) NOT NULL,
    document_type character varying(200) NOT NULL
);


ALTER TABLE dmes.sc_machine_document OWNER TO "sipPrueba";

--
-- TOC entry 195 (class 1259 OID 305445)
-- Name: sc_machine_part; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_machine_part (
    id_machine_part numeric(18,0) NOT NULL,
    name character varying(200) NOT NULL,
    description character varying(2000),
    clasification character varying(200) NOT NULL,
    mark character varying(200) NOT NULL,
    serie character varying(200) NOT NULL,
    useful_life numeric(18,0),
    value numeric(18,2) NOT NULL,
    path_picture character varying(200) NOT NULL,
    id_priority numeric(18,0) NOT NULL,
    id_cost_center numeric(18,0) NOT NULL,
    id_time numeric(18,0) NOT NULL,
    id_supplier_guarantee numeric(18,0) NOT NULL,
    id_money numeric(18,0) NOT NULL,
    id_dimension numeric(18,0) NOT NULL,
    creation_date timestamp with time zone NOT NULL,
    id_machine numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_machine_part OWNER TO "sipPrueba";

--
-- TOC entry 196 (class 1259 OID 305451)
-- Name: sc_machine_part_attached; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_machine_part_attached (
    id_machine_part_attached numeric(18,0) NOT NULL,
    type character varying(50) NOT NULL,
    tittle character varying(200) NOT NULL,
    description character varying(200) NOT NULL,
    id_machine_part numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_machine_part_attached OWNER TO "sipPrueba";

--
-- TOC entry 197 (class 1259 OID 305454)
-- Name: sc_machine_part_document; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_machine_part_document (
    id_machine_part_document numeric(18,0) NOT NULL,
    document_path character varying(200) NOT NULL,
    document_tittle character varying(200) NOT NULL,
    creation_date timestamp with time zone NOT NULL,
    document_name character varying(200) NOT NULL,
    upload_by character varying(200) NOT NULL,
    id_machine_part numeric(18,0) NOT NULL,
    document_type character varying(200) NOT NULL
);


ALTER TABLE dmes.sc_machine_part_document OWNER TO "sipPrueba";

--
-- TOC entry 198 (class 1259 OID 305460)
-- Name: sc_mails; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_mails (
    id_mail numeric(18,0) NOT NULL,
    mail character varying(100) NOT NULL,
    description character varying(100),
    id_person numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_mails OWNER TO "sipPrueba";

--
-- TOC entry 199 (class 1259 OID 305463)
-- Name: sc_maintenance_activity; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_maintenance_activity (
    id_maintenance_activity numeric(18,0) NOT NULL,
    name character varying(200) NOT NULL,
    description character varying(400),
    id_maintenance numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_maintenance_activity OWNER TO "sipPrueba";

--
-- TOC entry 200 (class 1259 OID 305469)
-- Name: sc_maintenance_clasification; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_maintenance_clasification (
    id_maintenance_clasification numeric(18,0) NOT NULL,
    clasification character varying(200) NOT NULL
);


ALTER TABLE dmes.sc_maintenance_clasification OWNER TO "sipPrueba";

--
-- TOC entry 201 (class 1259 OID 305472)
-- Name: sc_maintenance_damage; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_maintenance_damage (
    id_maintenance_damage numeric(18,0) NOT NULL,
    damage character varying(200) NOT NULL
);


ALTER TABLE dmes.sc_maintenance_damage OWNER TO "sipPrueba";

--
-- TOC entry 202 (class 1259 OID 305475)
-- Name: sc_maintenance_state; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_maintenance_state (
    id_maintenance_state numeric(18,0) NOT NULL,
    state character varying(200) NOT NULL
);


ALTER TABLE dmes.sc_maintenance_state OWNER TO "sipPrueba";

--
-- TOC entry 203 (class 1259 OID 305478)
-- Name: sc_measure_unit; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_measure_unit (
    id_measure numeric(18,0) NOT NULL,
    acronym character varying(20),
    type character varying(200) NOT NULL
);


ALTER TABLE dmes.sc_measure_unit OWNER TO "sipPrueba";

--
-- TOC entry 204 (class 1259 OID 305481)
-- Name: sc_module_permission; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_module_permission (
    id_module_permission numeric(18,0) NOT NULL,
    name character varying(100) NOT NULL,
    description character varying(2000),
    icone character varying(2000),
    type character varying(50),
    id_father numeric(18,0),
    page character varying(50)
);


ALTER TABLE dmes.sc_module_permission OWNER TO "sipPrueba";

--
-- TOC entry 205 (class 1259 OID 305487)
-- Name: sc_module_permission_by_role; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_module_permission_by_role (
    id_module_permission_by_role numeric(18,0) NOT NULL,
    id_role numeric(18,0) NOT NULL,
    id_type character varying(5),
    id_module_permission numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_module_permission_by_role OWNER TO "sipPrueba";

--
-- TOC entry 206 (class 1259 OID 305490)
-- Name: sc_money; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_money (
    id_money numeric(18,0) NOT NULL,
    description character varying(50),
    acronym character varying(2),
    trm numeric(18,2)
);


ALTER TABLE dmes.sc_money OWNER TO "sipPrueba";

--
-- TOC entry 207 (class 1259 OID 305493)
-- Name: sc_notification; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_notification (
    id_notification numeric(18,0) NOT NULL,
    id_machine numeric(18,0) NOT NULL,
    password character varying(200) NOT NULL,
    id_group numeric(18,0) NOT NULL,
    id_stop_machine numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_notification OWNER TO "sipPrueba";

--
-- TOC entry 208 (class 1259 OID 305496)
-- Name: sc_operating_conditions; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_operating_conditions (
    id_operating_condition numeric(18,0) NOT NULL,
    internal character varying(2000) NOT NULL,
    external character varying(2000) NOT NULL,
    observations character varying(2000) NOT NULL,
    creation_date date NOT NULL,
    modify_date date,
    id_machine numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_operating_conditions OWNER TO "sipPrueba";

--
-- TOC entry 209 (class 1259 OID 305502)
-- Name: sc_packing_unit; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_packing_unit (
    id_packing numeric(18,0) NOT NULL,
    description character varying(200),
    acronym character varying(20) NOT NULL
);


ALTER TABLE dmes.sc_packing_unit OWNER TO "sipPrueba";

--
-- TOC entry 210 (class 1259 OID 305505)
-- Name: sc_partner; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_partner (
    id_partner numeric(18,0) NOT NULL,
    active character varying(1) NOT NULL,
    "position" character varying(100) NOT NULL,
    web_page character varying(100),
    creation_date date NOT NULL,
    modify_date timestamp with time zone,
    id_person numeric(18,0) NOT NULL,
    company_name character varying(100) NOT NULL
);


ALTER TABLE dmes.sc_partner OWNER TO "sipPrueba";

--
-- TOC entry 211 (class 1259 OID 305508)
-- Name: sc_person; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_person (
    id_person numeric(18,0) NOT NULL,
    first_name character varying(100) NOT NULL,
    last_name character varying(100) NOT NULL,
    age numeric(3,0) NOT NULL,
    country character varying(100) NOT NULL,
    city character varying(100) NOT NULL,
    personal_information character varying(2000),
    domicilie character varying(100) NOT NULL,
    studies character varying(2000),
    description character varying(2000),
    path_photo character varying NOT NULL,
    creation_date timestamp with time zone NOT NULL,
    modify_date timestamp with time zone,
    identification numeric(18,0) NOT NULL,
    "pathFile" character varying(100)
);


ALTER TABLE dmes.sc_person OWNER TO "sipPrueba";

--
-- TOC entry 212 (class 1259 OID 305514)
-- Name: sc_person_by_group; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_person_by_group (
    id_person_by_group numeric(18,0) NOT NULL,
    id_person numeric(18,0) NOT NULL,
    id_group numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_person_by_group OWNER TO "sipPrueba";

--
-- TOC entry 213 (class 1259 OID 305517)
-- Name: sc_person_observations; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_person_observations (
    id_person_observations numeric(18,0) NOT NULL,
    tittle character varying(200) NOT NULL,
    observation character varying(2000) NOT NULL,
    id_person numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_person_observations OWNER TO "sipPrueba";

--
-- TOC entry 214 (class 1259 OID 305523)
-- Name: sc_person_specifications; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_person_specifications (
    id_person_specifications numeric(18,0) NOT NULL,
    tittle character varying(200) NOT NULL,
    specification character varying(2000) NOT NULL,
    id_person numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_person_specifications OWNER TO "sipPrueba";

--
-- TOC entry 215 (class 1259 OID 305529)
-- Name: sc_phones; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_phones (
    id_phone numeric(18,0) NOT NULL,
    number_phone numeric(18,0) NOT NULL,
    description character varying(100),
    id_person numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_phones OWNER TO "sipPrueba";

--
-- TOC entry 216 (class 1259 OID 305532)
-- Name: sc_priority; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_priority (
    id_priority numeric(18,0) NOT NULL,
    name character varying(200) NOT NULL,
    description character varying(200)
);


ALTER TABLE dmes.sc_priority OWNER TO "sipPrueba";

--
-- TOC entry 217 (class 1259 OID 305535)
-- Name: sc_procces_product; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_procces_product (
    id_process_product numeric(18,0) NOT NULL,
    id_process_type numeric(18,0) NOT NULL,
    name character varying(200) NOT NULL,
    description character varying(2000),
    total_time_machine numeric(18,0),
    total_value_machine numeric(18,2),
    total_value_input numeric(18,2),
    total_time_process numeric(18,0),
    total_value_process numeric(18,2),
    total_time_employee numeric(18,0),
    total_value_employee numeric(18,2),
    id_product_formulation numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_procces_product OWNER TO "sipPrueba";

--
-- TOC entry 218 (class 1259 OID 305541)
-- Name: sc_procces_product_order; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_procces_product_order (
    id_process_product_order numeric(18,0) NOT NULL,
    id_process_type numeric(18,0) NOT NULL,
    name character varying(200) NOT NULL,
    description character varying(2000),
    total_time_machine numeric(18,0),
    total_value_machine numeric(18,2),
    total_value_input numeric(18,2),
    total_time_process numeric(18,0),
    total_value_process numeric(18,2),
    total_time_employee numeric(18,0),
    total_value_employee numeric(18,2),
    id_process_state numeric(18,0) NOT NULL,
    amount_produced numeric(18,0) NOT NULL,
    id_product_order numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_procces_product_order OWNER TO "sipPrueba";

--
-- TOC entry 219 (class 1259 OID 305547)
-- Name: sc_process_attached; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_process_attached (
    id_process_attached numeric(18,0) NOT NULL,
    tittle character varying(200) NOT NULL,
    description character varying(200),
    type character varying(20) NOT NULL
);


ALTER TABLE dmes.sc_process_attached OWNER TO "sipPrueba";

--
-- TOC entry 220 (class 1259 OID 305550)
-- Name: sc_process_employee; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_process_employee (
    id_process_employee numeric(18,0) NOT NULL,
    labor_description character varying(200),
    time_use numeric(18,0) NOT NULL,
    other_expenses numeric(18,2),
    total_value_employee numeric(18,2),
    id_employee numeric(18,0) NOT NULL,
    id_process numeric(18,0) NOT NULL,
    description_other_expenses character varying(2000)
);


ALTER TABLE dmes.sc_process_employee OWNER TO "sipPrueba";

--
-- TOC entry 221 (class 1259 OID 305556)
-- Name: sc_process_employee_order; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_process_employee_order (
    id_process_employee_order numeric(18,0) NOT NULL,
    labor_description character varying(200),
    time_use numeric(18,0) NOT NULL,
    other_expenses numeric(18,2),
    total_value_employee numeric(18,2),
    id_employee numeric(18,0) NOT NULL,
    id_process_order numeric(18,0) NOT NULL,
    description_other_expenses character varying(2000)
);


ALTER TABLE dmes.sc_process_employee_order OWNER TO "sipPrueba";

--
-- TOC entry 222 (class 1259 OID 305562)
-- Name: sc_process_input; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_process_input (
    id_process_input numeric(18,0) NOT NULL,
    id_process numeric(18,0) NOT NULL,
    id_input numeric(18,0) NOT NULL,
    amount_distribution numeric(18,0) NOT NULL,
    percentage_residue numeric(18,2) NOT NULL,
    total_value_input numeric(18,2) NOT NULL
);


ALTER TABLE dmes.sc_process_input OWNER TO "sipPrueba";

--
-- TOC entry 223 (class 1259 OID 305565)
-- Name: sc_process_input_order; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_process_input_order (
    id_process_input_order numeric(18,0) NOT NULL,
    id_process_order numeric(18,0) NOT NULL,
    id_input numeric(18,0) NOT NULL,
    amount_distribution numeric(18,0) NOT NULL,
    percentage_residue numeric(18,2) NOT NULL,
    total_value_input numeric(18,2) NOT NULL
);


ALTER TABLE dmes.sc_process_input_order OWNER TO "sipPrueba";

--
-- TOC entry 224 (class 1259 OID 305568)
-- Name: sc_process_machine; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_process_machine (
    id_process_machine numeric(18,0) NOT NULL,
    id_machine numeric(18,0) NOT NULL,
    time_use numeric(18,0) NOT NULL,
    other_expenses numeric(18,2),
    total_value_machine numeric(18,2),
    id_process numeric(18,0) NOT NULL,
    description_other_expenses character varying(2000)
);


ALTER TABLE dmes.sc_process_machine OWNER TO "sipPrueba";

--
-- TOC entry 225 (class 1259 OID 305574)
-- Name: sc_process_machine_order; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_process_machine_order (
    id_process_machine_order numeric(18,0) NOT NULL,
    id_machine numeric(18,0) NOT NULL,
    time_use numeric(18,0) NOT NULL,
    other_expenses numeric(18,2),
    total_value_machine numeric(18,2),
    id_process_order numeric(18,0) NOT NULL,
    description_other_expenses character varying(2000)
);


ALTER TABLE dmes.sc_process_machine_order OWNER TO "sipPrueba";

--
-- TOC entry 226 (class 1259 OID 305580)
-- Name: sc_process_type; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_process_type (
    id_process_type numeric(18,0) NOT NULL,
    description character varying(200),
    type character varying(200) NOT NULL
);


ALTER TABLE dmes.sc_process_type OWNER TO "sipPrueba";

--
-- TOC entry 227 (class 1259 OID 305583)
-- Name: sc_product_attached; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_product_attached (
    id_product_attached numeric(18,0) NOT NULL,
    type character varying(200) NOT NULL,
    tittle character varying(200) NOT NULL,
    description character varying(2000) NOT NULL,
    id_product_formulation numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_product_attached OWNER TO "sipPrueba";

--
-- TOC entry 228 (class 1259 OID 305589)
-- Name: sc_product_documents; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_product_documents (
    id_product_documents numeric(18,0) NOT NULL,
    document_path character varying(200) NOT NULL,
    document_tittle character varying(200),
    creation_date timestamp with time zone NOT NULL,
    document_name character varying(200),
    upload_by character varying(50) NOT NULL,
    type_document character varying(200) NOT NULL,
    id_product_formulation numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_product_documents OWNER TO "sipPrueba";

--
-- TOC entry 229 (class 1259 OID 305595)
-- Name: sc_product_formulation; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_product_formulation (
    id_product_formulation numeric(18,0) NOT NULL,
    path_picture character varying(200),
    type_material character varying(200) NOT NULL,
    mark character varying(200) NOT NULL,
    serie character varying(200) NOT NULL,
    id_packing numeric(18,0) NOT NULL,
    expiry_date timestamp with time zone,
    creation_date timestamp with time zone NOT NULL,
    id_priority numeric(18,0) NOT NULL,
    id_cost_center numeric(18,0) NOT NULL,
    value numeric(18,0) NOT NULL,
    id_money numeric(18,0) NOT NULL,
    id_location numeric(18,0) NOT NULL,
    manufacturing_time numeric(18,0) NOT NULL,
    description character varying(200) NOT NULL,
    id_product_dimension numeric(18,0) NOT NULL,
    id_stock numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_product_formulation OWNER TO "sipPrueba";

--
-- TOC entry 3040 (class 0 OID 0)
-- Dependencies: 229
-- Name: COLUMN sc_product_formulation.id_priority; Type: COMMENT; Schema: dmes; Owner: sipPrueba
--

COMMENT ON COLUMN sc_product_formulation.id_priority IS '
';


--
-- TOC entry 230 (class 1259 OID 305601)
-- Name: sc_product_order; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_product_order (
    id_product_order numeric(18,0) NOT NULL,
    path_picture character varying(200),
    type_material character varying(200) NOT NULL,
    mark character varying(200) NOT NULL,
    serie character varying(200) NOT NULL,
    id_packing numeric(18,0) NOT NULL,
    expiry_date timestamp with time zone,
    creation_date timestamp with time zone NOT NULL,
    id_priority numeric(18,0) NOT NULL,
    id_cost_center numeric(18,0) NOT NULL,
    value numeric(18,0) NOT NULL,
    id_money numeric(18,0) NOT NULL,
    id_location numeric(18,0) NOT NULL,
    manufacturing_time numeric(18,0) NOT NULL,
    description character varying(200) NOT NULL,
    id_product_dimension numeric(18,0) NOT NULL,
    id_order numeric(18,0) NOT NULL,
    amount_required numeric(18,0),
    amount_produced numeric(18,0),
    id_product_formulation numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_product_order OWNER TO "sipPrueba";

--
-- TOC entry 231 (class 1259 OID 305607)
-- Name: sc_production_state; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_production_state (
    id_production_state numeric(18,0) NOT NULL,
    description character varying(50) NOT NULL
);


ALTER TABLE dmes.sc_production_state OWNER TO "sipPrueba";

--
-- TOC entry 232 (class 1259 OID 305610)
-- Name: sc_replacement; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_replacement (
    id_replacement numeric(18,0) NOT NULL,
    type_replacement character varying(200) NOT NULL,
    useful_life numeric(18,0) NOT NULL,
    supplier_guarantee numeric(18,0) NOT NULL,
    mark character varying(200) NOT NULL,
    value numeric(18,2) NOT NULL,
    path_picture character varying(2000),
    cost_center numeric(18,0) NOT NULL,
    serie character varying(2000) NOT NULL,
    creation_date timestamp with time zone NOT NULL,
    description character varying(200) NOT NULL,
    id_stock numeric(18,0) NOT NULL,
    id_priority numeric(18,0) NOT NULL,
    id_replacement_dimension numeric(18,0),
    id_money numeric(18,0),
    id_location numeric(18,0),
    name character varying(200) NOT NULL,
    "time" numeric(18,0),
    value_minutes numeric(18,0)
);


ALTER TABLE dmes.sc_replacement OWNER TO "sipPrueba";

--
-- TOC entry 233 (class 1259 OID 305616)
-- Name: sc_replacement_attached; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_replacement_attached (
    id_replacement_attached numeric(18,0) NOT NULL,
    type character varying(200) NOT NULL,
    tittle character varying(200) NOT NULL,
    description character varying(2000) NOT NULL,
    id_replacement numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_replacement_attached OWNER TO "sipPrueba";

--
-- TOC entry 234 (class 1259 OID 305622)
-- Name: sc_replacement_documents; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_replacement_documents (
    id_replacement_documents numeric(18,0) NOT NULL,
    document_path character varying(200) NOT NULL,
    document_tittle character varying(200),
    creation_date timestamp with time zone NOT NULL,
    document_name character varying(200),
    upload_by character varying(50) NOT NULL,
    type_document character varying(200) NOT NULL,
    id_replacement numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_replacement_documents OWNER TO "sipPrueba";

--
-- TOC entry 235 (class 1259 OID 305628)
-- Name: sc_roles; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_roles (
    id_role numeric(18,0) NOT NULL,
    name character varying(100) NOT NULL,
    description character varying(2000),
    creation_date timestamp with time zone NOT NULL,
    modify_date timestamp with time zone
);


ALTER TABLE dmes.sc_roles OWNER TO "sipPrueba";

--
-- TOC entry 236 (class 1259 OID 305634)
-- Name: sc_services_or_products; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_services_or_products (
    id_service_or_products numeric(18,0) NOT NULL,
    name_service_or_product character varying(100) NOT NULL,
    cost numeric(18,2) NOT NULL,
    guarantee character varying(2000),
    description character varying(2000),
    amount numeric(18,0) NOT NULL,
    id_partner numeric(18,0) NOT NULL,
    type character varying(100) NOT NULL
);


ALTER TABLE dmes.sc_services_or_products OWNER TO "sipPrueba";

--
-- TOC entry 237 (class 1259 OID 305640)
-- Name: sc_stock; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_stock (
    id_stock numeric(18,0) NOT NULL,
    maxime_stock numeric(18,0) NOT NULL,
    minime_stock numeric(18,0) NOT NULL,
    current_stock numeric(18,0) NOT NULL,
    price_unit numeric(18,0) NOT NULL,
    total_value numeric(18,0) NOT NULL,
    id_store numeric(18,0) NOT NULL,
    optime_stock numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_stock OWNER TO "sipPrueba";

--
-- TOC entry 238 (class 1259 OID 305643)
-- Name: sc_stop_machine; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_stop_machine (
    id_stop_machine numeric(18,0) NOT NULL,
    id_maintenance numeric(18,0),
    creation_date timestamp with time zone NOT NULL,
    reason character varying(400),
    duration_estimate numeric(18,0),
    end_date timestamp with time zone,
    duration_real numeric(18,0),
    state numeric(1,0) DEFAULT 1 NOT NULL,
    password character varying(100),
    response_date timestamp with time zone
);


ALTER TABLE dmes.sc_stop_machine OWNER TO "sipPrueba";

--
-- TOC entry 239 (class 1259 OID 305650)
-- Name: sc_store; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_store (
    id_store numeric(18,0) NOT NULL,
    name character varying(2000) NOT NULL
);


ALTER TABLE dmes.sc_store OWNER TO "sipPrueba";

--
-- TOC entry 240 (class 1259 OID 305656)
-- Name: sc_store_order; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_store_order (
    id_store_order numeric(18,0) NOT NULL,
    order_type character varying(200) NOT NULL,
    order_class character varying(200) NOT NULL,
    id_state numeric(18,0) NOT NULL,
    creation_date timestamp with time zone NOT NULL,
    reason_cancellation character varying(200),
    required_by character varying(200) NOT NULL,
    amount_items numeric(18,0) NOT NULL,
    id_employee_create numeric(18,0),
    id_employee_store numeric(18,0),
    id_order_request character varying(50)
);


ALTER TABLE dmes.sc_store_order OWNER TO "sipPrueba";

--
-- TOC entry 241 (class 1259 OID 305662)
-- Name: sc_store_order_item; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_store_order_item (
    id_item numeric(18,0) NOT NULL,
    class_item character varying(200) NOT NULL,
    amount_required numeric(18,0) NOT NULL,
    amount_delivery numeric(18,0) NOT NULL,
    amount_store numeric(18,0) NOT NULL,
    amount_pending numeric(18,0) NOT NULL,
    item_description character varying(200) NOT NULL,
    id_store_order numeric(18,0) NOT NULL,
    id_item_class numeric(18,0),
    amount_pending_hidden numeric(18,0)
);


ALTER TABLE dmes.sc_store_order_item OWNER TO "sipPrueba";

--
-- TOC entry 242 (class 1259 OID 305665)
-- Name: sc_store_order_state; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_store_order_state (
    id_state numeric(18,0) NOT NULL,
    description character varying(200) NOT NULL
);


ALTER TABLE dmes.sc_store_order_state OWNER TO "sipPrueba";

--
-- TOC entry 243 (class 1259 OID 305668)
-- Name: sc_store_requisition; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_store_requisition (
    id_store_requisition numeric(18,0) NOT NULL,
    requisition_type character varying(200) NOT NULL,
    order_class character varying(200) NOT NULL,
    id_state numeric(18,0) NOT NULL,
    creation_date timestamp with time zone NOT NULL,
    reason_cancellation character varying(200),
    amount_items numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_store_requisition OWNER TO "sipPrueba";

--
-- TOC entry 244 (class 1259 OID 305674)
-- Name: sc_store_requisition_item; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_store_requisition_item (
    id_item numeric(18,0) NOT NULL,
    class_item character varying(200) NOT NULL,
    amount_required numeric(18,0) NOT NULL,
    amount_delivery numeric(18,0) NOT NULL,
    amount_store numeric(18,0) NOT NULL,
    amount_pending numeric(18,0) NOT NULL,
    item_description character varying(200) NOT NULL,
    id_store_requisition numeric(18,0) NOT NULL,
    id_item_class numeric(18,0),
    amount_pending_hidden numeric(18,0)
);


ALTER TABLE dmes.sc_store_requisition_item OWNER TO "sipPrueba";

--
-- TOC entry 245 (class 1259 OID 305677)
-- Name: sc_store_requisition_state; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_store_requisition_state (
    id_state numeric(18,0) NOT NULL,
    description character varying(200) NOT NULL
);


ALTER TABLE dmes.sc_store_requisition_state OWNER TO "sipPrueba";

--
-- TOC entry 246 (class 1259 OID 305680)
-- Name: sc_time; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_time (
    id_time numeric(18,0) NOT NULL,
    acronym character varying(200) NOT NULL,
    minutes numeric(18,0)
);


ALTER TABLE dmes.sc_time OWNER TO "sipPrueba";

--
-- TOC entry 247 (class 1259 OID 305683)
-- Name: sc_tool; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_tool (
    id_tool numeric(18,0) NOT NULL,
    type_tool character varying(200) NOT NULL,
    useful_life numeric(18,0) NOT NULL,
    supplier_guarantee numeric(18,0) NOT NULL,
    mark character varying(200) NOT NULL,
    value numeric(18,2) NOT NULL,
    path_picture character varying(2000),
    cost_center numeric(18,0) NOT NULL,
    serie character varying(2000) NOT NULL,
    creation_date timestamp with time zone NOT NULL,
    description character varying(200) NOT NULL,
    id_stock numeric(18,0) NOT NULL,
    id_priority numeric(18,0) NOT NULL,
    id_tool_dimension numeric(18,0),
    id_money numeric(18,0),
    id_location numeric(18,0),
    name character varying(200) NOT NULL,
    "time" numeric(18,0),
    value_minutes numeric(18,0)
);


ALTER TABLE dmes.sc_tool OWNER TO "sipPrueba";

--
-- TOC entry 248 (class 1259 OID 305689)
-- Name: sc_tool_attached; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_tool_attached (
    id_tool_attached numeric(18,0) NOT NULL,
    type character varying(200) NOT NULL,
    tittle character varying(200) NOT NULL,
    description character varying(2000) NOT NULL,
    id_tool numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_tool_attached OWNER TO "sipPrueba";

--
-- TOC entry 249 (class 1259 OID 305695)
-- Name: sc_tool_documents; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_tool_documents (
    id_tool_documents numeric(18,0) NOT NULL,
    document_path character varying(200) NOT NULL,
    document_tittle character varying(200),
    creation_date timestamp with time zone NOT NULL,
    document_name character varying(200),
    upload_by character varying(50) NOT NULL,
    type_document character varying(200) NOT NULL,
    id_tool numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_tool_documents OWNER TO "sipPrueba";

--
-- TOC entry 250 (class 1259 OID 305701)
-- Name: sc_turn; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_turn (
    id_turn numeric(18,0) NOT NULL,
    description character varying(100) NOT NULL,
    hour_amount numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_turn OWNER TO "sipPrueba";

--
-- TOC entry 251 (class 1259 OID 305704)
-- Name: sc_type; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_type (
    id_type numeric(18,0) NOT NULL,
    type character varying(100) NOT NULL,
    id_class_type numeric(18,0) NOT NULL,
    creation_date date NOT NULL,
    modify_date date NOT NULL
);


ALTER TABLE dmes.sc_type OWNER TO "sipPrueba";

--
-- TOC entry 252 (class 1259 OID 305707)
-- Name: sc_users; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_users (
    id_user numeric(18,0) NOT NULL,
    id_person numeric(18,0) NOT NULL,
    id_role numeric(18,0) NOT NULL,
    login character varying(100) NOT NULL,
    password character varying(100) NOT NULL,
    creation_date timestamp with time zone NOT NULL,
    modify_date timestamp with time zone
);


ALTER TABLE dmes.sc_users OWNER TO "sipPrueba";

--
-- TOC entry 253 (class 1259 OID 305710)
-- Name: sc_work_experience; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_work_experience (
    id_work_experience numeric(18,0) NOT NULL,
    init_date timestamp with time zone NOT NULL,
    end_date timestamp with time zone NOT NULL,
    id_employee numeric(18,0) NOT NULL,
    company_name character varying(100) NOT NULL
);


ALTER TABLE dmes.sc_work_experience OWNER TO "sipPrueba";

--
-- TOC entry 254 (class 1259 OID 305713)
-- Name: sc_workforce; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_workforce (
    id_workforce numeric(18,0) NOT NULL,
    workforce character varying(200) NOT NULL,
    id_employee numeric(18,0),
    type_workforce character varying(100) NOT NULL
);


ALTER TABLE dmes.sc_workforce OWNER TO "sipPrueba";

--
-- TOC entry 255 (class 1259 OID 305716)
-- Name: sqclasstype; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqclasstype
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqclasstype OWNER TO "sipPrueba";

--
-- TOC entry 256 (class 1259 OID 305718)
-- Name: sqmaintenanceschedule; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqmaintenanceschedule
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 1111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqmaintenanceschedule OWNER TO "sipPrueba";

--
-- TOC entry 337 (class 1259 OID 313513)
-- Name: sqotlogproduction; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqotlogproduction
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqotlogproduction OWNER TO "sipPrueba";

--
-- TOC entry 257 (class 1259 OID 305720)
-- Name: sqotmaintenance; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqotmaintenance
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqotmaintenance OWNER TO "sipPrueba";

--
-- TOC entry 258 (class 1259 OID 305722)
-- Name: sqotmaintenancecorrective; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqotmaintenancecorrective
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqotmaintenancecorrective OWNER TO "sipPrueba";

--
-- TOC entry 259 (class 1259 OID 305724)
-- Name: sqotmaintenancepreventive; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqotmaintenancepreventive
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 1111111111111111
    CACHE 1;


ALTER TABLE dmes.sqotmaintenancepreventive OWNER TO "sipPrueba";

--
-- TOC entry 260 (class 1259 OID 305726)
-- Name: sqotproductionorder; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqotproductionorder
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqotproductionorder OWNER TO "sipPrueba";

--
-- TOC entry 261 (class 1259 OID 305728)
-- Name: sqotproductionproduct; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqotproductionproduct
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqotproductionproduct OWNER TO "sipPrueba";

--
-- TOC entry 262 (class 1259 OID 305730)
-- Name: sqsccompetencies; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqsccompetencies
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 11111111111111111
    CACHE 1;


ALTER TABLE dmes.sqsccompetencies OWNER TO "sipPrueba";

--
-- TOC entry 263 (class 1259 OID 305732)
-- Name: sqsccostcenter; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqsccostcenter
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqsccostcenter OWNER TO "sipPrueba";

--
-- TOC entry 264 (class 1259 OID 305734)
-- Name: sqscdistributionunit; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscdistributionunit
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscdistributionunit OWNER TO "sipPrueba";

--
-- TOC entry 265 (class 1259 OID 305736)
-- Name: sqscdocuments; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscdocuments
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscdocuments OWNER TO "sipPrueba";

--
-- TOC entry 266 (class 1259 OID 305738)
-- Name: sqscemployee; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscemployee
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 11111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscemployee OWNER TO "sipPrueba";

--
-- TOC entry 267 (class 1259 OID 305740)
-- Name: sqscfactorylocation; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscfactorylocation
    START WITH 7
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscfactorylocation OWNER TO "sipPrueba";

--
-- TOC entry 268 (class 1259 OID 305742)
-- Name: sqscgroup; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscgroup
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscgroup OWNER TO "sipPrueba";

--
-- TOC entry 269 (class 1259 OID 305744)
-- Name: sqscinput; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscinput
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscinput OWNER TO "sipPrueba";

--
-- TOC entry 270 (class 1259 OID 305746)
-- Name: sqscinputdimension; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscinputdimension
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscinputdimension OWNER TO "sipPrueba";

--
-- TOC entry 271 (class 1259 OID 305748)
-- Name: sqscinputdocuments; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscinputdocuments
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscinputdocuments OWNER TO "sipPrueba";

--
-- TOC entry 272 (class 1259 OID 305750)
-- Name: sqscinputequivalence; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscinputequivalence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscinputequivalence OWNER TO "sipPrueba";

--
-- TOC entry 273 (class 1259 OID 305752)
-- Name: sqscinputfeature; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscinputfeature
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscinputfeature OWNER TO "sipPrueba";

--
-- TOC entry 274 (class 1259 OID 305754)
-- Name: sqscinputobservation; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscinputobservation
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscinputobservation OWNER TO "sipPrueba";

--
-- TOC entry 275 (class 1259 OID 305756)
-- Name: sqscinputspecification; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscinputspecification
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscinputspecification OWNER TO "sipPrueba";

--
-- TOC entry 276 (class 1259 OID 305758)
-- Name: sqsclocation; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqsclocation
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqsclocation OWNER TO "sipPrueba";

--
-- TOC entry 277 (class 1259 OID 305760)
-- Name: sqscmachine; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscmachine
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscmachine OWNER TO "sipPrueba";

--
-- TOC entry 278 (class 1259 OID 305762)
-- Name: sqscmachineattached; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscmachineattached
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscmachineattached OWNER TO "sipPrueba";

--
-- TOC entry 279 (class 1259 OID 305764)
-- Name: sqscmachineconditions; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscmachineconditions
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscmachineconditions OWNER TO "sipPrueba";

--
-- TOC entry 280 (class 1259 OID 305766)
-- Name: sqscmachinedocument; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscmachinedocument
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscmachinedocument OWNER TO "sipPrueba";

--
-- TOC entry 281 (class 1259 OID 305768)
-- Name: sqscmachinepart; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscmachinepart
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscmachinepart OWNER TO "sipPrueba";

--
-- TOC entry 282 (class 1259 OID 305770)
-- Name: sqscmachinepartattached; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscmachinepartattached
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscmachinepartattached OWNER TO "sipPrueba";

--
-- TOC entry 283 (class 1259 OID 305772)
-- Name: sqscmachinepartdocument; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscmachinepartdocument
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscmachinepartdocument OWNER TO "sipPrueba";

--
-- TOC entry 284 (class 1259 OID 305774)
-- Name: sqscmails; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscmails
    START WITH 6
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscmails OWNER TO "sipPrueba";

--
-- TOC entry 285 (class 1259 OID 305776)
-- Name: sqscmaintenanceactivity; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscmaintenanceactivity
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscmaintenanceactivity OWNER TO "sipPrueba";

--
-- TOC entry 286 (class 1259 OID 305778)
-- Name: sqscmaintenanceplan; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscmaintenanceplan
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 11111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscmaintenanceplan OWNER TO "sipPrueba";

--
-- TOC entry 287 (class 1259 OID 305780)
-- Name: sqscmaintenancereplacement; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscmaintenancereplacement
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscmaintenancereplacement OWNER TO "sipPrueba";

--
-- TOC entry 288 (class 1259 OID 305782)
-- Name: sqscmaintenancetool; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscmaintenancetool
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscmaintenancetool OWNER TO "sipPrueba";

--
-- TOC entry 289 (class 1259 OID 305784)
-- Name: sqscmeasure; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscmeasure
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscmeasure OWNER TO "sipPrueba";

--
-- TOC entry 290 (class 1259 OID 305786)
-- Name: sqscmodulespermissionbyrole; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscmodulespermissionbyrole
    START WITH 17
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscmodulespermissionbyrole OWNER TO "sipPrueba";

--
-- TOC entry 291 (class 1259 OID 305788)
-- Name: sqscmoney; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscmoney
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscmoney OWNER TO "sipPrueba";

--
-- TOC entry 292 (class 1259 OID 305790)
-- Name: sqscnotification; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscnotification
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscnotification OWNER TO "sipPrueba";

--
-- TOC entry 293 (class 1259 OID 305792)
-- Name: sqscoperatingconditions; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscoperatingconditions
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscoperatingconditions OWNER TO "sipPrueba";

--
-- TOC entry 294 (class 1259 OID 305794)
-- Name: sqscpackingunit; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscpackingunit
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscpackingunit OWNER TO "sipPrueba";

--
-- TOC entry 295 (class 1259 OID 305796)
-- Name: sqscpartners; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscpartners
    START WITH 15
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 11111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscpartners OWNER TO "sipPrueba";

--
-- TOC entry 296 (class 1259 OID 305798)
-- Name: sqscpartsandconsumables; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscpartsandconsumables
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscpartsandconsumables OWNER TO "sipPrueba";

--
-- TOC entry 297 (class 1259 OID 305800)
-- Name: sqscpersonbygroup; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscpersonbygroup
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscpersonbygroup OWNER TO "sipPrueba";

--
-- TOC entry 298 (class 1259 OID 305802)
-- Name: sqscpersondocumentationattached; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscpersondocumentationattached
    START WITH 6
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscpersondocumentationattached OWNER TO "sipPrueba";

--
-- TOC entry 299 (class 1259 OID 305804)
-- Name: sqscpersonobservations; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscpersonobservations
    START WITH 6
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscpersonobservations OWNER TO "sipPrueba";

--
-- TOC entry 300 (class 1259 OID 305806)
-- Name: sqscpersons; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscpersons
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscpersons OWNER TO "sipPrueba";

--
-- TOC entry 301 (class 1259 OID 305808)
-- Name: sqscpersonspecifications; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscpersonspecifications
    START WITH 6
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscpersonspecifications OWNER TO "sipPrueba";

--
-- TOC entry 302 (class 1259 OID 305810)
-- Name: sqscphones; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscphones
    START WITH 6
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscphones OWNER TO "sipPrueba";

--
-- TOC entry 303 (class 1259 OID 305812)
-- Name: sqscphoto; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscphoto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 11111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscphoto OWNER TO "sipPrueba";

--
-- TOC entry 304 (class 1259 OID 305814)
-- Name: sqscprocessattached; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscprocessattached
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscprocessattached OWNER TO "sipPrueba";

--
-- TOC entry 305 (class 1259 OID 305816)
-- Name: sqscprocessemployee; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscprocessemployee
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscprocessemployee OWNER TO "sipPrueba";

--
-- TOC entry 306 (class 1259 OID 305818)
-- Name: sqscprocessemployeeorder; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscprocessemployeeorder
    START WITH 33
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscprocessemployeeorder OWNER TO "sipPrueba";

--
-- TOC entry 307 (class 1259 OID 305820)
-- Name: sqscprocessinput; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscprocessinput
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscprocessinput OWNER TO "sipPrueba";

--
-- TOC entry 308 (class 1259 OID 305822)
-- Name: sqscprocessinputorder; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscprocessinputorder
    START WITH 23
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscprocessinputorder OWNER TO "sipPrueba";

--
-- TOC entry 309 (class 1259 OID 305824)
-- Name: sqscprocessmachine; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscprocessmachine
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscprocessmachine OWNER TO "sipPrueba";

--
-- TOC entry 310 (class 1259 OID 305826)
-- Name: sqscprocessmachineorder; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscprocessmachineorder
    START WITH 32
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscprocessmachineorder OWNER TO "sipPrueba";

--
-- TOC entry 311 (class 1259 OID 305828)
-- Name: sqscprocessproduct; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscprocessproduct
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscprocessproduct OWNER TO "sipPrueba";

--
-- TOC entry 312 (class 1259 OID 305830)
-- Name: sqscprocessproductorder; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscprocessproductorder
    START WITH 25
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscprocessproductorder OWNER TO "sipPrueba";

--
-- TOC entry 313 (class 1259 OID 305832)
-- Name: sqscprocesstype; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscprocesstype
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscprocesstype OWNER TO "sipPrueba";

--
-- TOC entry 314 (class 1259 OID 305834)
-- Name: sqscproductattached; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscproductattached
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscproductattached OWNER TO "sipPrueba";

--
-- TOC entry 315 (class 1259 OID 305836)
-- Name: sqscproductdocuments; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscproductdocuments
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscproductdocuments OWNER TO "sipPrueba";

--
-- TOC entry 316 (class 1259 OID 305838)
-- Name: sqscproductformulation; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscproductformulation
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscproductformulation OWNER TO "sipPrueba";

--
-- TOC entry 317 (class 1259 OID 305840)
-- Name: sqscproductorder; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscproductorder
    START WITH 12
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscproductorder OWNER TO "sipPrueba";

--
-- TOC entry 318 (class 1259 OID 305842)
-- Name: sqscreplacement; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscreplacement
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscreplacement OWNER TO "sipPrueba";

--
-- TOC entry 319 (class 1259 OID 305844)
-- Name: sqscreplacementattached; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscreplacementattached
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscreplacementattached OWNER TO "sipPrueba";

--
-- TOC entry 320 (class 1259 OID 305846)
-- Name: sqscreplacementdocuments; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscreplacementdocuments
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscreplacementdocuments OWNER TO "sipPrueba";

--
-- TOC entry 321 (class 1259 OID 305848)
-- Name: sqscroles; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscroles
    START WITH 2
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscroles OWNER TO "sipPrueba";

--
-- TOC entry 322 (class 1259 OID 305850)
-- Name: sqscservicesorproducts; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscservicesorproducts
    START WITH 15
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 11111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscservicesorproducts OWNER TO "sipPrueba";

--
-- TOC entry 323 (class 1259 OID 305852)
-- Name: sqscstock; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscstock
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscstock OWNER TO "sipPrueba";

--
-- TOC entry 324 (class 1259 OID 305854)
-- Name: sqscstopmachine; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscstopmachine
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscstopmachine OWNER TO "sipPrueba";

--
-- TOC entry 325 (class 1259 OID 305856)
-- Name: sqscstore; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscstore
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 11111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscstore OWNER TO "sipPrueba";

--
-- TOC entry 326 (class 1259 OID 305858)
-- Name: sqscstoreorder; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscstoreorder
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscstoreorder OWNER TO "sipPrueba";

--
-- TOC entry 327 (class 1259 OID 305860)
-- Name: sqscstoreorderitem; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscstoreorderitem
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscstoreorderitem OWNER TO "sipPrueba";

--
-- TOC entry 328 (class 1259 OID 305862)
-- Name: sqscstoreorderstate; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscstoreorderstate
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscstoreorderstate OWNER TO "sipPrueba";

--
-- TOC entry 329 (class 1259 OID 305864)
-- Name: sqsctool; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqsctool
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqsctool OWNER TO "sipPrueba";

--
-- TOC entry 330 (class 1259 OID 305866)
-- Name: sqsctoolattached; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqsctoolattached
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqsctoolattached OWNER TO "sipPrueba";

--
-- TOC entry 331 (class 1259 OID 305868)
-- Name: sqsctooldocuments; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqsctooldocuments
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqsctooldocuments OWNER TO "sipPrueba";

--
-- TOC entry 332 (class 1259 OID 305870)
-- Name: sqscusers; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscusers
    START WITH 2
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 11111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscusers OWNER TO "sipPrueba";

--
-- TOC entry 333 (class 1259 OID 305872)
-- Name: sqscworkexperience; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscworkexperience
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 11111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscworkexperience OWNER TO "sipPrueba";

--
-- TOC entry 334 (class 1259 OID 305874)
-- Name: sqscworkforce; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqscworkforce
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 111111111111111111
    CACHE 1;


ALTER TABLE dmes.sqscworkforce OWNER TO "sipPrueba";

--
-- TOC entry 335 (class 1259 OID 305876)
-- Name: sqtype; Type: SEQUENCE; Schema: dmes; Owner: sipPrueba
--

CREATE SEQUENCE sqtype
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 11111111
    CACHE 1;


ALTER TABLE dmes.sqtype OWNER TO "sipPrueba";

--
-- TOC entry 3031 (class 0 OID 313508)
-- Dependencies: 336
-- Data for Name: ot_log_production; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO ot_log_production (id_log, id_process_product, id_order, creation_date, id_machine) VALUES (100000, 1, 1, '2012-12-12 00:00:00', 1);
INSERT INTO ot_log_production (id_log, id_process_product, id_order, creation_date, id_machine) VALUES (100001, 1, 1, '2012-12-12 00:00:00', 1);
INSERT INTO ot_log_production (id_log, id_process_product, id_order, creation_date, id_machine) VALUES (2, 12, 1, '2012-12-12 12:12:12', 1);
INSERT INTO ot_log_production (id_log, id_process_product, id_order, creation_date, id_machine) VALUES (3, 12, 1, '2012-12-12 12:12:12', 1);
INSERT INTO ot_log_production (id_log, id_process_product, id_order, creation_date, id_machine) VALUES (4, 12, 1, '2012-12-12 12:12:12', 1);
INSERT INTO ot_log_production (id_log, id_process_product, id_order, creation_date, id_machine) VALUES (5, 12, 1, '2012-12-12 12:12:12', 1);


--
-- TOC entry 2865 (class 0 OID 305318)
-- Dependencies: 170
-- Data for Name: ot_maintenance; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO ot_maintenance (id_maintenance, id_machine_part, id_priority, description, id_maintenance_clasification, id_maintenance_state, id_workforce, duration, description_damage, id_maintenance_damage, creation_date, response_date, end_date, id_maintenance_schedule) VALUES (29, 11, 2, '2 Minutos ', 1, 1, 21, 0, '', 2, '2015-11-01 01:00:00.141-05', NULL, '2015-11-01 01:02:00.141-05', '26');
INSERT INTO ot_maintenance (id_maintenance, id_machine_part, id_priority, description, id_maintenance_clasification, id_maintenance_state, id_workforce, duration, description_damage, id_maintenance_damage, creation_date, response_date, end_date, id_maintenance_schedule) VALUES (28, 10, 2, '3 Meses - 2 Días - 10 Horas - 17 Minutos ', 1, 1, 20, 0, '', 1, '2015-10-31 18:00:00-05', NULL, '2016-02-03 06:19:00-05', '37,38,39');
INSERT INTO ot_maintenance (id_maintenance, id_machine_part, id_priority, description, id_maintenance_clasification, id_maintenance_state, id_workforce, duration, description_damage, id_maintenance_damage, creation_date, response_date, end_date, id_maintenance_schedule) VALUES (30, 10, 2, '1 Días - 5 Minutos ', 2, 1, 22, 0, '', 1, '2015-11-04 21:00:00.572-05', NULL, '2015-11-05 21:05:00.572-05', '40');
INSERT INTO ot_maintenance (id_maintenance, id_machine_part, id_priority, description, id_maintenance_clasification, id_maintenance_state, id_workforce, duration, description_damage, id_maintenance_damage, creation_date, response_date, end_date, id_maintenance_schedule) VALUES (32, 10, 2, '6 Horas - 5 Minutos ', 1, 1, 24, 0, '', 1, '2015-11-05 22:04:00.953-05', NULL, '2015-11-06 04:09:00.953-05', '42');
INSERT INTO ot_maintenance (id_maintenance, id_machine_part, id_priority, description, id_maintenance_clasification, id_maintenance_state, id_workforce, duration, description_damage, id_maintenance_damage, creation_date, response_date, end_date, id_maintenance_schedule) VALUES (34, 8, 3, '2 Horas - ', 3, 1, 26, 0, '', 3, '2015-11-06 18:00:00-05', NULL, '2015-11-07 00:04:00-05', '44,45,46,47,48');
INSERT INTO ot_maintenance (id_maintenance, id_machine_part, id_priority, description, id_maintenance_clasification, id_maintenance_state, id_workforce, duration, description_damage, id_maintenance_damage, creation_date, response_date, end_date, id_maintenance_schedule) VALUES (33, 15, 1, '7 Horas - 5 Minutos ', 2, 1, 25, 0, '', 2, '2015-11-06 02:06:00.274-05', '2016-02-09 19:01:34-05', '2016-07-09 19:00:43-05', '43');
INSERT INTO ot_maintenance (id_maintenance, id_machine_part, id_priority, description, id_maintenance_clasification, id_maintenance_state, id_workforce, duration, description_damage, id_maintenance_damage, creation_date, response_date, end_date, id_maintenance_schedule) VALUES (35, 8, 1, '1 Minutos ', 2, 1, 27, 0, '', 1, '2016-02-09 20:01:00.892-05', NULL, '2016-02-09 20:02:00.892-05', '49');
INSERT INTO ot_maintenance (id_maintenance, id_machine_part, id_priority, description, id_maintenance_clasification, id_maintenance_state, id_workforce, duration, description_damage, id_maintenance_damage, creation_date, response_date, end_date, id_maintenance_schedule) VALUES (31, 1, 3, '3 Días - 4 Horas - 3 Minutos ', 2, 1, 23, 0, '', 2, '2015-11-05 00:10:00.999-05', '2016-02-13 11:01:12-05', '2016-07-13 11:00:13-05', '41');
INSERT INTO ot_maintenance (id_maintenance, id_machine_part, id_priority, description, id_maintenance_clasification, id_maintenance_state, id_workforce, duration, description_damage, id_maintenance_damage, creation_date, response_date, end_date, id_maintenance_schedule) VALUES (37, 14, 1, '1 Minutos ', 2, 1, 29, 0, '', 2, '2016-02-12 20:00:00.533-05', '2016-02-13 18:54:35-05', '2016-08-13 23:54:42-05', '51');
INSERT INTO ot_maintenance (id_maintenance, id_machine_part, id_priority, description, id_maintenance_clasification, id_maintenance_state, id_workforce, duration, description_damage, id_maintenance_damage, creation_date, response_date, end_date, id_maintenance_schedule) VALUES (38, 9, 3, '1 Meses - 1 Días - 1 Horas - 1 Minutos ', 2, 1, 30, 0, '', 2, '2016-02-13 20:01:00.964-05', NULL, '2016-03-14 21:02:00.964-05', '52');
INSERT INTO ot_maintenance (id_maintenance, id_machine_part, id_priority, description, id_maintenance_clasification, id_maintenance_state, id_workforce, duration, description_damage, id_maintenance_damage, creation_date, response_date, end_date, id_maintenance_schedule) VALUES (36, 15, 1, '1 Días - 1 Horas - 1 Minutos ', 1, 1, 28, 0, '', 2, '2016-02-12 20:01:00.715-05', '2016-02-14 07:04:55-05', '2016-08-14 12:05:09-05', '50');
INSERT INTO ot_maintenance (id_maintenance, id_machine_part, id_priority, description, id_maintenance_clasification, id_maintenance_state, id_workforce, duration, description_damage, id_maintenance_damage, creation_date, response_date, end_date, id_maintenance_schedule) VALUES (39, 15, 1, '1 Horas - ', 1, 1, 31, 0, '', 1, '2016-02-26 00:43:17.356-05', NULL, '2016-02-26 00:43:17.356-05', '53');
INSERT INTO ot_maintenance (id_maintenance, id_machine_part, id_priority, description, id_maintenance_clasification, id_maintenance_state, id_workforce, duration, description_damage, id_maintenance_damage, creation_date, response_date, end_date, id_maintenance_schedule) VALUES (40, 8, 2, '3 Horas - 3 Minutos ', 1, 1, 32, 0, '', 1, '2016-04-18 06:09:00.317-05', NULL, '2016-04-18 09:12:00.317-05', '54');
INSERT INTO ot_maintenance (id_maintenance, id_machine_part, id_priority, description, id_maintenance_clasification, id_maintenance_state, id_workforce, duration, description_damage, id_maintenance_damage, creation_date, response_date, end_date, id_maintenance_schedule) VALUES (41, 15, 3, '3 Horas - 3 Minutos ', 1, 1, 33, 0, 'test', 1, '2016-04-19 00:00:00-05', NULL, '2016-04-19 07:05:00-05', '55,56,57');
INSERT INTO ot_maintenance (id_maintenance, id_machine_part, id_priority, description, id_maintenance_clasification, id_maintenance_state, id_workforce, duration, description_damage, id_maintenance_damage, creation_date, response_date, end_date, id_maintenance_schedule) VALUES (42, 1, 1, '4 Horas - 5 Minutos ', 2, 1, 34, 0, '', 2, '2016-04-23 21:10:00.33-05', NULL, '2016-04-24 01:15:00.33-05', '58');
INSERT INTO ot_maintenance (id_maintenance, id_machine_part, id_priority, description, id_maintenance_clasification, id_maintenance_state, id_workforce, duration, description_damage, id_maintenance_damage, creation_date, response_date, end_date, id_maintenance_schedule) VALUES (43, 8, 2, '3 Minutos ', 2, 1, 35, 0, '', 1, '2016-04-24 00:00:00-05', NULL, '2016-04-24 02:03:00-05', '59,60,61');


--
-- TOC entry 2866 (class 0 OID 305324)
-- Dependencies: 171
-- Data for Name: ot_maintenance_corrective; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO ot_maintenance_corrective (id_maintenance_corrective, name, description, id_maintenance, duration) VALUES (17, 'Correctivo_Máquina 2_motor20151105153730', '1 Días - 5 Minutos ', 30, NULL);
INSERT INTO ot_maintenance_corrective (id_maintenance_corrective, name, description, id_maintenance, duration) VALUES (18, 'Correctivo_Máquina 1_Máquina 1 Test20151105155545', '3 Días - 4 Horas - 3 Minutos ', 31, NULL);
INSERT INTO ot_maintenance_corrective (id_maintenance_corrective, name, description, id_maintenance, duration) VALUES (19, 'Correctivo_Máquina 2_motor20151106152337', '6 Horas - 5 Minutos ', 32, NULL);
INSERT INTO ot_maintenance_corrective (id_maintenance_corrective, name, description, id_maintenance, duration) VALUES (20, 'Correctivo_Máquina 1_prueba 120151106153023', '7 Horas - 5 Minutos ', 33, NULL);
INSERT INTO ot_maintenance_corrective (id_maintenance_corrective, name, description, id_maintenance, duration) VALUES (21, 'Correctivo_Máquina 1_Gas para moto20160210132619', '1 Minutos ', 35, NULL);
INSERT INTO ot_maintenance_corrective (id_maintenance_corrective, name, description, id_maintenance, duration) VALUES (22, 'Correctivo_Máquina 1_prueba 120160213131452', '1 Días - 1 Horas - 1 Minutos ', 36, NULL);
INSERT INTO ot_maintenance_corrective (id_maintenance_corrective, name, description, id_maintenance, duration) VALUES (23, 'Correctivo_Máquina 4_bandas20160213225519', '1 Minutos ', 37, NULL);
INSERT INTO ot_maintenance_corrective (id_maintenance_corrective, name, description, id_maintenance, duration) VALUES (24, 'Correctivo_Máquina 4_test20160214144558', '1 Meses - 1 Días - 1 Horas - 1 Minutos ', 38, NULL);
INSERT INTO ot_maintenance_corrective (id_maintenance_corrective, name, description, id_maintenance, duration) VALUES (25, 'Correctivo_Máquina 1_prueba 120160226004333', '1 Horas - ', 39, NULL);
INSERT INTO ot_maintenance_corrective (id_maintenance_corrective, name, description, id_maintenance, duration) VALUES (26, 'Correctivo_Máquina 1_Gas para moto20160418000900', '3 Horas - 3 Minutos ', 40, NULL);
INSERT INTO ot_maintenance_corrective (id_maintenance_corrective, name, description, id_maintenance, duration) VALUES (27, 'Correctivo_Máquina 1_Máquina 1 Test20160423211020', '4 Horas - 5 Minutos ', 42, NULL);


--
-- TOC entry 2867 (class 0 OID 305330)
-- Dependencies: 172
-- Data for Name: ot_maintenance_preventive; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO ot_maintenance_preventive (id_maintenance_preventive, name, description, id_maintenance, type_frequency, amount_schedule, duration) VALUES (6, 'Preventivo_Máquina 1_Gas para moto20151106154747', '2 Horas - ', 34, 'DAILY', 5, NULL);
INSERT INTO ot_maintenance_preventive (id_maintenance_preventive, name, description, id_maintenance, type_frequency, amount_schedule, duration) VALUES (7, 'Preventivo_Máquina 1_prueba 120160418001021', '3 Horas - 3 Minutos ', 41, 'DAILY', 3, NULL);
INSERT INTO ot_maintenance_preventive (id_maintenance_preventive, name, description, id_maintenance, type_frequency, amount_schedule, duration) VALUES (8, 'Preventivo_Máquina 1_Gas para moto20160423212132', '3 Minutos ', 43, 'DAILY', 3, NULL);


--
-- TOC entry 2868 (class 0 OID 305336)
-- Dependencies: 173
-- Data for Name: ot_maintenance_schedule; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (40, 8, '2015-11-04 18:00:00-05', 30, '2015-11-05 18:00:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (41, 8, '2015-11-04 18:00:00-05', 31, '2015-11-07 18:00:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (42, 12, '2015-11-05 18:00:00-05', 32, '2015-11-05 18:00:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (43, 12, '2015-11-06 02:06:00-05', 33, '2015-11-06 09:11:00.274-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (44, 15, '2015-11-06 22:04:00-05', 34, '2015-11-07 00:04:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (45, 15, '2015-11-07 22:04:00-05', 34, '2015-11-08 00:04:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (46, 15, '2015-11-08 22:04:00-05', 34, '2015-11-09 00:04:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (47, 15, '2015-11-09 22:04:00-05', 34, '2015-11-10 00:04:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (48, 15, '2015-11-10 22:04:00-05', 34, '2015-11-11 00:04:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (49, 8, '2016-02-09 20:01:01-05', 35, '2016-02-09 20:02:00.892-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (50, 12, '2016-02-12 20:01:01-05', 36, '2016-02-13 21:02:00.715-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (51, 12, '2016-02-12 20:00:01-05', 37, '2016-02-12 20:01:00.533-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (52, 13, '2016-02-13 20:01:01-05', 38, '2016-03-14 21:02:00.964-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (53, 9, '2016-02-26 00:43:17-05', 39, '2016-02-26 00:43:17.356-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (54, 8, '2016-04-18 06:09:00-05', 40, '2016-04-18 09:12:00.317-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (55, 13, '2016-04-19 04:02:00-05', 41, '2016-04-19 07:05:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (56, 13, '2016-04-20 04:02:00-05', 41, '2016-04-20 07:05:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (57, 13, '2016-04-21 04:02:00-05', 41, '2016-04-21 07:05:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (58, 15, '2016-04-23 21:10:00-05', 42, '2016-04-24 01:15:00.33-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (59, 15, '2016-04-24 02:00:00-05', 43, '2016-04-24 02:03:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (60, 15, '2016-04-25 02:00:00-05', 43, '2016-04-25 02:03:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (61, 15, '2016-04-26 02:00:00-05', 43, '2016-04-26 02:03:00-05');


--
-- TOC entry 2869 (class 0 OID 305339)
-- Dependencies: 174
-- Data for Name: ot_production_order; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO ot_production_order (id_production_order, creation_date, start_date, end_date, id_production_state, name, description, start_date_real, end_date_real) VALUES (20, '2016-03-08 00:58:58.519', '2016-03-13 00:12:24.039466', '2016-03-08 05:08:00', 1, 'tesla', '', NULL, NULL);
INSERT INTO ot_production_order (id_production_order, creation_date, start_date, end_date, id_production_state, name, description, start_date_real, end_date_real) VALUES (22, '2016-03-08 14:07:02.156', '2016-03-13 00:12:24.039466', '2016-03-09 02:37:00', 1, 'Caramelo 1X12 Marzo', '', NULL, NULL);
INSERT INTO ot_production_order (id_production_order, creation_date, start_date, end_date, id_production_state, name, description, start_date_real, end_date_real) VALUES (23, '2016-03-09 14:38:49.088', '2016-03-13 00:12:24.039466', '2016-03-09 18:48:00', 1, 'test', '', NULL, NULL);
INSERT INTO ot_production_order (id_production_order, creation_date, start_date, end_date, id_production_state, name, description, start_date_real, end_date_real) VALUES (21, '2016-03-08 14:00:59.281', '2016-03-13 00:12:24.039466', '2016-03-09 04:00:00', 1, 'Snacky 8X3', 'Snacky para paquete mediano de 8 unidades', NULL, NULL);
INSERT INTO ot_production_order (id_production_order, creation_date, start_date, end_date, id_production_state, name, description, start_date_real, end_date_real) VALUES (24, '2016-03-28 01:17:10.906', '2016-03-28 01:17:00', '2016-03-28 09:37:00', 2, 'Test de Producción', '', '2016-03-28 02:03:01.125598', NULL);


--
-- TOC entry 2870 (class 0 OID 305342)
-- Dependencies: 175
-- Data for Name: sc_competencies; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_competencies (id_competencies, tittle, description, id_employee) VALUES (6, 'Liderazgo', 'Liderazgo', 8);
INSERT INTO sc_competencies (id_competencies, tittle, description, id_employee) VALUES (7, 'Agresividad de mercadeo', 'Agresividad de mercadeo', 9);
INSERT INTO sc_competencies (id_competencies, tittle, description, id_employee) VALUES (8, 'toleracia al estres', 'trabajo bajo presion., con exelentes resultados', 15);
INSERT INTO sc_competencies (id_competencies, tittle, description, id_employee) VALUES (9, 'comunicacion', 'tiene excelente comunicacion en trabajo en grupo ', 16);


--
-- TOC entry 2871 (class 0 OID 305348)
-- Dependencies: 176
-- Data for Name: sc_constants_load_files; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_constants_load_files (id_constants_load_file, max_size_file, extension, path) VALUES (1, 10, '/(\.|\/)(pdf|xsl|doc|xlsx|docx|txt|pps|ppt|pptx|ppsx)$/', NULL);


--
-- TOC entry 2872 (class 0 OID 305354)
-- Dependencies: 177
-- Data for Name: sc_cost_center; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (4, 'Contabilidad', '2121331', '2015-02-12', NULL);
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (2, 'Mercadeo', '11212545', '2014-12-14', NULL);
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (3, 'Facturación', '52545582', '2015-01-14', NULL);
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (9, 'Sistemas', '32334323', '2015-02-25', NULL);
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (10, 'Servicios Generales', '32334323', '2015-02-27', NULL);
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (11, 'Recusos Humanos', '3234342', '2015-02-28', NULL);
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (12, 'sala', '0980', '2015-05-14', NULL);
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (13, 'calidad', '5656', '2015-08-01', NULL);
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (16, 'Sistemas de Producción', '1209494', '2015-08-08', NULL);
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (17, 'bodega', '1210', '2015-08-11', NULL);
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (18, 'logisticax1', '3409', '2015-08-11', NULL);
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (19, 'office', '9809', '2015-08-12', NULL);
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (1, 'Compras', '14454545', '2014-12-14', '2014-12-13 18:00:00-05');


--
-- TOC entry 2873 (class 0 OID 305357)
-- Dependencies: 178
-- Data for Name: sc_distribution_unit; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_distribution_unit (id_distribution_unit, acronym, description) VALUES (1, 'botella', 'una botella de cajas');
INSERT INTO sc_distribution_unit (id_distribution_unit, acronym, description) VALUES (2, 'Libras', '25 libras por cajas');
INSERT INTO sc_distribution_unit (id_distribution_unit, acronym, description) VALUES (3, 'Litros Cilindricos', 'Cantidad de litros en un cilindro');
INSERT INTO sc_distribution_unit (id_distribution_unit, acronym, description) VALUES (4, 'unid', 'unidad');


--
-- TOC entry 2874 (class 0 OID 305360)
-- Dependencies: 179
-- Data for Name: sc_documents; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2875 (class 0 OID 305366)
-- Dependencies: 180
-- Data for Name: sc_employee; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, creation_date, modify_date, id_person, id_turn) VALUES (12, 'ventas', 'ingeiera electronica', '2015-08-09 17:00:00-05', NULL, 'Y', 2.00, 20.00, '2015-08-10', '2015-08-28 17:00:00-05', 3, 2);
INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, creation_date, modify_date, id_person, id_turn) VALUES (8, 'Administrador del Mundo', 'Ingeniero de Sistemas', '2014-11-09 18:00:00-05', NULL, 'Y', 30.00, 3233.00, '2014-11-19', '2015-08-28 17:00:00-05', 1, 1);
INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, creation_date, modify_date, id_person, id_turn) VALUES (16, 'empleado', 'g', '2015-08-13 17:00:00-05', NULL, 'Y', 123450.00, 34000.00, '2015-08-15', '2015-08-14 17:00:00-05', 27, 1);
INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, creation_date, modify_date, id_person, id_turn) VALUES (15, 'navegador', 'navegador', '2015-03-08 18:00:00-05', NULL, 'Y', 1230000.00, 45909.00, '2015-08-11', '2015-08-14 17:00:00-05', 17, 2);
INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, creation_date, modify_date, id_person, id_turn) VALUES (14, 'operarios', '', '2015-08-10 17:00:00-05', NULL, 'Y', 8.00, 1000.00, '2015-08-11', '2015-08-14 17:00:00-05', 19, 2);
INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, creation_date, modify_date, id_person, id_turn) VALUES (13, 'Super administrador', '', '2015-08-10 17:00:00-05', NULL, 'Y', 3000000.00, 10000.00, '2015-08-11', '2015-08-14 17:00:00-05', 22, 2);
INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, creation_date, modify_date, id_person, id_turn) VALUES (9, 'Administradora General', 'Ingeniería Electrónica', '2015-03-31 17:00:00-05', NULL, 'Y', 500000.00, 1200.00, '2015-04-19', '2015-08-14 17:00:00-05', 14, 2);
INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, creation_date, modify_date, id_person, id_turn) VALUES (10, 'Personal de Pruebas', 'NA', '2015-03-31 17:00:00-05', NULL, 'Y', 1.00, 1.00, '2015-04-19', '2015-08-14 17:00:00-05', 11, 2);


--
-- TOC entry 2876 (class 0 OID 305369)
-- Dependencies: 181
-- Data for Name: sc_factory_location; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_factory_location (id_factory_location, location, description) VALUES (1, 'Cortadoras', 'Sección de maquinas cortadoras');
INSERT INTO sc_factory_location (id_factory_location, location, description) VALUES (2, 'Empaquetadoras', 'Sección de máquinas empaquetadoras');


--
-- TOC entry 2877 (class 0 OID 305375)
-- Dependencies: 182
-- Data for Name: sc_group; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_group (id_group, name, description, type) VALUES (1, 'Eléctrico', 'Grupo conformado por el personal eléctrico de la empresa', 1);
INSERT INTO sc_group (id_group, name, description, type) VALUES (2, 'Mecánico', 'Grupo conformado por los mecánicos de la empresa', 1);
INSERT INTO sc_group (id_group, name, description, type) VALUES (3, 'Hidráulico', 'Grupo conformado por el personal hidráulico de la empresa ', 1);
INSERT INTO sc_group (id_group, name, description, type) VALUES (4, 'Jefe de Operación', 'Grupo conformado por los jefes de operación de cada pabellón', 2);
INSERT INTO sc_group (id_group, name, description, type) VALUES (5, 'Supervisor de Área', 'Grupo conformado por los supervisores de la planta', 2);
INSERT INTO sc_group (id_group, name, description, type) VALUES (6, 'Operario SuperNumerario', 'Grupo conformado por todos los operarios de la planta', 2);


--
-- TOC entry 2878 (class 0 OID 305378)
-- Dependencies: 183
-- Data for Name: sc_input; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_input (id_input, type_material, expiry_date, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_input_dimension, id_packing, id_money, total_amount_distribution, id_distribution_unit, distribution_amount, distribution_value, id_location) VALUES (15, 'ninguno', '2015-04-29 17:00:00-05', 1, 'ninguno', 1000, '/home/gchavarro88/inputs_filePath/img/producto1.png', 4, '655555', '2015-04-24 17:00:00-05', 'producto pepe', 14, 2, 25, 4, 1, 51100, 2, 100, 10.00, 2);
INSERT INTO sc_input (id_input, type_material, expiry_date, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_input_dimension, id_packing, id_money, total_amount_distribution, id_distribution_unit, distribution_amount, distribution_value, id_location) VALUES (14, '23', NULL, 1, '33', 33, '/home/gchavarro88/inputs_filePath/img/corre papeles.jpg', 3, '33', '2015-04-24 17:00:00-05', 'Prueba Jhon', 13, 2, 21, 4, 1, 23, 1, 23, 1.00, 6);
INSERT INTO sc_input (id_input, type_material, expiry_date, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_input_dimension, id_packing, id_money, total_amount_distribution, id_distribution_unit, distribution_amount, distribution_value, id_location) VALUES (16, 'plomo', NULL, 15, 'complex', 2000, '/home/gchavarro88/inputs_filePath/img/desorientado.jpg', 1, 'plom098', '2015-04-24 17:00:00-05', 'cercha', 15, 1, 26, 3, 2, 500, 1, 10, 200.00, 12);
INSERT INTO sc_input (id_input, type_material, expiry_date, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_input_dimension, id_packing, id_money, total_amount_distribution, id_distribution_unit, distribution_amount, distribution_value, id_location) VALUES (8, 'Tipo de Prueba de Material', '2015-08-25 17:00:00-05', 1, 'Nike', 1200, '/home/gchavarro88/inputs_filePath/img/producto1.jpg', 4, '1212', '2015-03-18 18:00:00-05', 'Prueba de Insumo', 7, 1, 7, 4, 3, 123, 1, 3, 400.00, 5);
INSERT INTO sc_input (id_input, type_material, expiry_date, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_input_dimension, id_packing, id_money, total_amount_distribution, id_distribution_unit, distribution_amount, distribution_value, id_location) VALUES (17, 'verdura', '2016-02-25 18:00:00-05', 19, 'planton', 800000, ' ', 13, 'pl09oi', '2015-08-16 17:00:00-05', 'platano', 36, 1, 57, 4, 3, 1000, 1, 10, 80000.00, 3);
INSERT INTO sc_input (id_input, type_material, expiry_date, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_input_dimension, id_packing, id_money, total_amount_distribution, id_distribution_unit, distribution_amount, distribution_value, id_location) VALUES (18, 'liquido ', '2016-01-06 18:00:00-05', 16, 'brisa', 400000, ' ', 4, '5ty65', '2015-08-16 17:00:00-05', 'water', 37, 2, 58, 5, 1, 1440, 1, 10, 40000.00, 6);


--
-- TOC entry 2879 (class 0 OID 305384)
-- Dependencies: 184
-- Data for Name: sc_input_dimension; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (2, '23-Mms', '23-Mms', '3-Mms', '23-Mts', '12-Mms', '23-Mms', '2-Mms', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (21, '1-Mms', '1-Mms', '1-KMS', '1-Mts', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (25, '12-Mts', '12-Mts', '12-Mms', '12-Mms', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (26, '54-Mts', '78-cmt', '8-Mms', '23-Mms', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (27, '2-Mts', '2.20-Mts', '3-Mts', '12000-Hershas', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (33, '5-Mts', '6-Mms', '5-Mts', '87-Mts', '7-Mts', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (34, '4-KMS', '4-Mms', '4-KMS', '4-Mts', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (35, '13-KMS', '34-Mms', '5-Mts', '9-KMS', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (36, '12-KMS', '8-Mms', '8-Mms', '9-Mms', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (37, '10-Pulgadas', '78-Hershas', '56-baso', '98-Pulgadas', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (38, '2-KMS', '2-Mts', '2-Mts', '2-KMS', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (39, '8-Mms', '9-Mms', '9-Mts', '5-Mms', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (40, '10-Mts', '10-Mts', '10-Mts', '22-Mts', '10-Mts', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (41, '23-Cts', '54-KMS', '34-Mts', '5-Pulgadas', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (42, '9-Pulgadas', '7-Mms', '9-Pulgadas', '9-baso', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (43, '54-Mms', '55-Mms', '54-Mts', '5-Mms', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (44, '5-Mms', '5-Mms', '5-Mts', '5-Mms', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (45, '65-Mms', '4-Mms', '55-Mms', '55-Mms', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (46, '1-Mts', '1-Mts', '1-Mts', '1-Mts', '1-Mms', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (48, '1-Mms', '1-Mms', '1-Mts', '1-Mts', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (50, '4-Mms', '5-KMS', '7-KMS', '2-Mts', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (51, '1-Mms', '2-KMS', '2-Mts', '2-Mts', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (53, '99-Mms', '45-baso', '87-Cts', '12-Pulgadas', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (54, '12-Mms', '98-Hershas', '54-PruebaYoleidy', '67-Pulgadas', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (55, '43-Mms', '98-baso', '56-PruebaYoleidy', '87-Pulgadas', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (56, '5-Mts', '5-Mts', '5-Mts', '4-Mms', '4-Mts', '', '4-Mms', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (49, '1-KMS', '1-Mts', '1-KMS', '1-Mms', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (7, '12-KMS', '2-Yoleidy Measure', '2-Mms', '5-pies3', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (57, '98-Mms', '89-Mms', '98-KMS', '677-Mms', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (58, '6-Mms', '8-test measure', '8-KMS', '9-PruebaYoleidy', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (60, '32-Mms', '43-Mms', '33-Mts', '65-KMS', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (61, '1-Mms', '1-Mms', '1-KMS', '1-Mts', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (62, '2-KMS', '2-Mms', '2-Mms', '2-Mts', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (65, '65-KMS', '7-Mms', '78-Mms', '57-Mms', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (66, '76-Mms', '8-PruebaYoleidy', '87-Mms', '8-Mts', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (69, '1-Mts', '1-Mts', '1-Mts', '1-Mts', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (70, '56-Mms', '9-Yoleidy Measure', '4-KMS', '8-galon', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (71, '9-KMS', '9-Mms', '8-KMS', '6-galon', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (74, '12-Cts', '300-Mms', '10-Cts', '500-Gramos', '', '', '', '');


--
-- TOC entry 2880 (class 0 OID 305390)
-- Dependencies: 185
-- Data for Name: sc_input_documents; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_input_documents (id_input_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_input) VALUES (10, '/home/gchavarro88/inputs_filePath/docs', '222', '2015-04-24 17:00:00-05', 'GSI-R-29 Formulario PQR V2.pdf', 'jguerrero', 'application/pdf', 14);
INSERT INTO sc_input_documents (id_input_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_input) VALUES (11, '/home/gchavarro88/inputs_filePath/docs', 'tiii', '2015-04-24 17:00:00-05', 'MANUAL DE CALIDAD FELIX DE BEDOUT.doc', 'jguerrero', 'application/octet-stream', 15);
INSERT INTO sc_input_documents (id_input_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_input) VALUES (12, '/home/gchavarro88/inputs_filePath/docs', 'plande trabajo', '2015-04-24 17:00:00-05', 'Plan de Trabajo Modalides version 1-2012 (1).docx', 'yaconcha', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 16);


--
-- TOC entry 2881 (class 0 OID 305396)
-- Dependencies: 186
-- Data for Name: sc_input_equivalence; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2882 (class 0 OID 305399)
-- Dependencies: 187
-- Data for Name: sc_input_feactures; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_input_feactures (id_input_feactures, tittle, description, id_input) VALUES (14, 'rojo', 'prueba', 16);
INSERT INTO sc_input_feactures (id_input_feactures, tittle, description, id_input) VALUES (15, 'rojo', 'prueba', 16);
INSERT INTO sc_input_feactures (id_input_feactures, tittle, description, id_input) VALUES (16, 'hoka5', 'nadasergg', 17);
INSERT INTO sc_input_feactures (id_input_feactures, tittle, description, id_input) VALUES (17, 'hola5', 'prueba3', 18);


--
-- TOC entry 2883 (class 0 OID 305405)
-- Dependencies: 188
-- Data for Name: sc_input_observations; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_input_observations (id_input_observation, tittle, description, id_input) VALUES (9, 'hola6', 'nadasd', 17);
INSERT INTO sc_input_observations (id_input_observation, tittle, description, id_input) VALUES (10, 'hola7', 'buenas', 17);
INSERT INTO sc_input_observations (id_input_observation, tittle, description, id_input) VALUES (11, 'hola', 'prueba', 18);


--
-- TOC entry 2884 (class 0 OID 305411)
-- Dependencies: 189
-- Data for Name: sc_input_specifications; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_input_specifications (id_input_specifications, description, tittle, creation_date, id_input) VALUES (16, 'v', 'm', '2015-04-24 17:00:00-05', 16);
INSERT INTO sc_input_specifications (id_input_specifications, description, tittle, creation_date, id_input) VALUES (17, 'prueba', 'hola', '2015-08-16 17:00:00-05', 17);
INSERT INTO sc_input_specifications (id_input_specifications, description, tittle, creation_date, id_input) VALUES (18, 'preuba2', 'hola2', '2015-08-16 17:00:00-05', 17);
INSERT INTO sc_input_specifications (id_input_specifications, description, tittle, creation_date, id_input) VALUES (19, 'prueba', 'hola', '2015-08-16 17:00:00-05', 18);
INSERT INTO sc_input_specifications (id_input_specifications, description, tittle, creation_date, id_input) VALUES (20, 'prueb1', 'hola2', '2015-08-16 17:00:00-05', 18);


--
-- TOC entry 2885 (class 0 OID 305417)
-- Dependencies: 190
-- Data for Name: sc_location; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_location (id_location, location, description, id_store) VALUES (1, 'Superir', 'Lado superiro del insumo', 1);
INSERT INTO sc_location (id_location, location, description, id_store) VALUES (2, 'Inferior', 'Lado inferior en la posicion del almacen', 2);
INSERT INTO sc_location (id_location, location, description, id_store) VALUES (3, 'Medio', 'Parte media del almacen', 3);
INSERT INTO sc_location (id_location, location, description, id_store) VALUES (5, 'sfsd', 'sfsd', 1);
INSERT INTO sc_location (id_location, location, description, id_store) VALUES (6, 'Superior Derecha', 'dfsdfds', 2);
INSERT INTO sc_location (id_location, location, description, id_store) VALUES (7, 'Bloque 1 Seccion 3', '', 3);
INSERT INTO sc_location (id_location, location, description, id_store) VALUES (8, 'Bloque 3 Sección 2', '', 4);
INSERT INTO sc_location (id_location, location, description, id_store) VALUES (9, 'Bloque 3 Sección 2e', '', 5);
INSERT INTO sc_location (id_location, location, description, id_store) VALUES (10, 'Norte suro', 'Ubicación del norte a sur', 1);
INSERT INTO sc_location (id_location, location, description, id_store) VALUES (11, 'Hershita posición', 'Es una posición de prueba', 1);
INSERT INTO sc_location (id_location, location, description, id_store) VALUES (12, 'Sabado', 'almacen2', 5);
INSERT INTO sc_location (id_location, location, description, id_store) VALUES (13, 'exito', 'exito', 3);
INSERT INTO sc_location (id_location, location, description, id_store) VALUES (14, 'exito la flora', '', 3);
INSERT INTO sc_location (id_location, location, description, id_store) VALUES (15, 'exito-melendez', 'exito-melendez', 3);
INSERT INTO sc_location (id_location, location, description, id_store) VALUES (16, 'edifio1,piso5, apt501', 'direccion', 3);
INSERT INTO sc_location (id_location, location, description, id_store) VALUES (17, 'almacen1-piso2-edificio4', 'almacen1', 2);
INSERT INTO sc_location (id_location, location, description, id_store) VALUES (18, 'Serena', 'Serena', 1);


--
-- TOC entry 2886 (class 0 OID 305423)
-- Dependencies: 191
-- Data for Name: sc_machine; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_machine (id_machine, name, hour_value, description, id_priority, id_time, useful_life, id_partner, id_cost_center, id_money, id_location, mark, serie, type, clasification, id_dimension, path_picture, id_state) VALUES (7, 'Diseñadora de Software', 12000.00, 'Máquina especializada en el diseño de software', 2, 3, 2, 19, 13, 1, 2, 'Samsung', 'S4AC23', 'Troqueleadoras', 'Eléctrica', 62, '/home/ubuntu/machine_filePath/img/Selección_652.png', 1);
INSERT INTO sc_machine (id_machine, name, hour_value, description, id_priority, id_time, useful_life, id_partner, id_cost_center, id_money, id_location, mark, serie, type, clasification, id_dimension, path_picture, id_state) VALUES (10, 'tapadora RD3', 60000.00, 'fsf', 1, 4, 5, 18, 13, 2, 1, 'cuerperty', 'dfw4', 'Troqueleadoras', 'Eléctrica', 65, '/home/ubuntu/machine_filePath/img/10428615_764352153627787_7203881861240549227_n.jpg', 1);
INSERT INTO sc_machine (id_machine, name, hour_value, description, id_priority, id_time, useful_life, id_partner, id_cost_center, id_money, id_location, mark, serie, type, clasification, id_dimension, path_picture, id_state) VALUES (11, 'rotador', 70000.00, 'adf', 1, 3, 56, 17, 13, 3, 2, 'sentes', 't7y7', 'Soldadoras', 'Eléctrica', 66, '/home/ubuntu/machine_filePath/img/1559846_725206147504724_1463329936_n.jpg', 1);
INSERT INTO sc_machine (id_machine, name, hour_value, description, id_priority, id_time, useful_life, id_partner, id_cost_center, id_money, id_location, mark, serie, type, clasification, id_dimension, path_picture, id_state) VALUES (2, 'Máquina 2', 2000.00, 'Maquina para cortar tela', 2, 1, 23, 1, 2, 2, 2, 'Chromium', 'CE233', 'Cortadora', 'Electricas', 7, NULL, 0);
INSERT INTO sc_machine (id_machine, name, hour_value, description, id_priority, id_time, useful_life, id_partner, id_cost_center, id_money, id_location, mark, serie, type, clasification, id_dimension, path_picture, id_state) VALUES (3, 'Máquina 4', 4000.00, 'Maquina para empacar', 3, 2, 23, 1, 3, 3, 1, 'Titanium', 'AT3334', 'Cortadoras', 'Mecánica', 7, '/home/ubuntu/machine_filePath/img/producto1.png', 1);
INSERT INTO sc_machine (id_machine, name, hour_value, description, id_priority, id_time, useful_life, id_partner, id_cost_center, id_money, id_location, mark, serie, type, clasification, id_dimension, path_picture, id_state) VALUES (14, 'Enrolladora', 120000.00, 'Prueba de Sip', 2, 4, 3, 20, 1, 2, 2, 'Shimano', 'AC23445', 'Troqueleadoras', 'Eléctrica', 69, '/home/ubuntu/machine_filePath/img/yoe.jpg', 0);
INSERT INTO sc_machine (id_machine, name, hour_value, description, id_priority, id_time, useful_life, id_partner, id_cost_center, id_money, id_location, mark, serie, type, clasification, id_dimension, path_picture, id_state) VALUES (1, 'Máquina 1', 1000.00, 'Maquina para Comidas', 1, 1, 12, 1, 1, 1, 1, 'Shines', 'SA233', 'Cortadoras', 'Eléctrica', 7, '/home/ubuntu/machine_filePath/img/producto2.jpg', 1);


--
-- TOC entry 2887 (class 0 OID 305430)
-- Dependencies: 192
-- Data for Name: sc_machine_attached; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_machine_attached (id_attached, type, tittle, description, id_machine) VALUES (1, 'SPECIFICATION', 'Diseño UML', 'Diseño en el lenguaje de modelamiento universal', 7);
INSERT INTO sc_machine_attached (id_attached, type, tittle, description, id_machine) VALUES (2, 'FEACTURE', 'Software no gratuito', 'software cobrado por la empresa staruml', 7);
INSERT INTO sc_machine_attached (id_attached, type, tittle, description, id_machine) VALUES (9, 'SPECIFICATION', 'tuti', 'tkg', 10);
INSERT INTO sc_machine_attached (id_attached, type, tittle, description, id_machine) VALUES (10, 'SPECIFICATION', 'iyo', 'zhfhfj', 10);
INSERT INTO sc_machine_attached (id_attached, type, tittle, description, id_machine) VALUES (11, 'FEACTURE', 'iykut', 'm,', 10);
INSERT INTO sc_machine_attached (id_attached, type, tittle, description, id_machine) VALUES (12, 'FEACTURE', 'add', 'fyuj', 10);
INSERT INTO sc_machine_attached (id_attached, type, tittle, description, id_machine) VALUES (13, 'OBSERVATION', 'kf', '647yfjf', 10);
INSERT INTO sc_machine_attached (id_attached, type, tittle, description, id_machine) VALUES (20, 'SPECIFICATION', 'Prueba', 'Prueba', 14);
INSERT INTO sc_machine_attached (id_attached, type, tittle, description, id_machine) VALUES (21, 'FEACTURE', 'Prueba', 'Prueba', 14);
INSERT INTO sc_machine_attached (id_attached, type, tittle, description, id_machine) VALUES (22, 'OBSERVATION', 'Prueba', 'Prueba3', 14);


--
-- TOC entry 2888 (class 0 OID 305433)
-- Dependencies: 193
-- Data for Name: sc_machine_conditions; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_machine_conditions (id_condition, type, description, id_machine) VALUES (1, 'Interna', 'jhgjgjh', 1);
INSERT INTO sc_machine_conditions (id_condition, type, description, id_machine) VALUES (2, 'Interna', 'jkhjkl', 3);


--
-- TOC entry 2889 (class 0 OID 305439)
-- Dependencies: 194
-- Data for Name: sc_machine_document; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_machine_document (id_machine_document, document_path, document_tittle, creation_date, document_name, upload_by, id_machine, document_type) VALUES (1, '/home/ubuntu/machine_filePath/docs', 'Documento de prueba', '2015-09-11 17:00:00-05', 'Certificado HTML 5.pdf', 'guschaor', 7, 'application/stream');
INSERT INTO sc_machine_document (id_machine_document, document_path, document_tittle, creation_date, document_name, upload_by, id_machine, document_type) VALUES (4, '/home/ubuntu/machine_filePath/docs', 'tuyo', '2015-09-13 17:00:00-05', '11-08-09 revision bd.docx', 'yaconcha', 10, 'application/vnd.openxmlformats-officedocument.wordprocessingml.document');
INSERT INTO sc_machine_document (id_machine_document, document_path, document_tittle, creation_date, document_name, upload_by, id_machine, document_type) VALUES (5, '/home/ubuntu/machine_filePath/docs', 'fjhf', '2015-09-16 17:00:00-05', 'MANUAL DE CALIDAD FELIX DE BEDOUT.doc', 'jguerrero', 1, 'application/msword');


--
-- TOC entry 2890 (class 0 OID 305445)
-- Dependencies: 195
-- Data for Name: sc_machine_part; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_machine_part (id_machine_part, name, description, clasification, mark, serie, useful_life, value, path_picture, id_priority, id_cost_center, id_time, id_supplier_guarantee, id_money, id_dimension, creation_date, id_machine) VALUES (10, 'motor', '', 'tr', 'ew', '76', 76, 65.00, '/home/gchavarro88/partofmachine_filePath/img/1012809_10154624470920386_2584141155262184458_n.jpg', 1, 4, 2, 1, 4, 50, '2015-08-07 17:00:00-05', 2);
INSERT INTO sc_machine_part (id_machine_part, name, description, clasification, mark, serie, useful_life, value, path_picture, id_priority, id_cost_center, id_time, id_supplier_guarantee, id_money, id_dimension, creation_date, id_machine) VALUES (14, 'bandas', '', 'semiautomatica', 'lastrasport', 'rt45th', 56, 1200000.00, '/root/partofmachine_filePath/img/banda.jpg', 2, 1, 3, 16, 3, 54, '2015-08-10 17:00:00-05', 3);
INSERT INTO sc_machine_part (id_machine_part, name, description, clasification, mark, serie, useful_life, value, path_picture, id_priority, id_cost_center, id_time, id_supplier_guarantee, id_money, id_dimension, creation_date, id_machine) VALUES (8, 'Gas para moto', '', 'Manual', '11', '11', 1, 10.00, '/home/gchavarro88/partofmachine_filePath/img/SipIngenieria.png', 1, 13, 1, 1, 4, 48, '2015-08-06 17:00:00-05', 1);
INSERT INTO sc_machine_part (id_machine_part, name, description, clasification, mark, serie, useful_life, value, path_picture, id_priority, id_cost_center, id_time, id_supplier_guarantee, id_money, id_dimension, creation_date, id_machine) VALUES (15, 'prueba 1', '', 'Eléctrica', 'cccc', 'gr3242rr', 33, 55343.00, ' ', 1, 17, 2, 1, 1, 56, '2015-08-16 17:00:00-05', 1);
INSERT INTO sc_machine_part (id_machine_part, name, description, clasification, mark, serie, useful_life, value, path_picture, id_priority, id_cost_center, id_time, id_supplier_guarantee, id_money, id_dimension, creation_date, id_machine) VALUES (11, '33', 'teste', 'Electromecánica', 'sdfsd', 'sdfsd', 4444, 44.00, '/home/gchavarro88/partofmachine_filePath/img/1010149_402668793217506_3141996706551348117_n.jpg', 1, 13, 2, 1, 3, 51, '2015-08-07 17:00:00-05', 2);
INSERT INTO sc_machine_part (id_machine_part, name, description, clasification, mark, serie, useful_life, value, path_picture, id_priority, id_cost_center, id_time, id_supplier_guarantee, id_money, id_dimension, creation_date, id_machine) VALUES (1, 'Máquina 1 Test', 'Máquina de Pruebas', 'Eléctrica', 'ACERO', 'AC12345555', 10, 20302.23, '/home/gchavarro88/partofmachine_filePath/img/lindos.jpg', 1, 1, 2, 1, 2, 7, '2012-12-11 18:00:00-05', 1);
INSERT INTO sc_machine_part (id_machine_part, name, description, clasification, mark, serie, useful_life, value, path_picture, id_priority, id_cost_center, id_time, id_supplier_guarantee, id_money, id_dimension, creation_date, id_machine) VALUES (9, 'test', '', 'Automática', 'test', 'test', 3333, 123.00, '/home/gchavarro88/partofmachine_filePath/img/minionpoema.jpg', 1, 1, 1, 1, 4, 49, '2015-08-07 17:00:00-05', 3);
INSERT INTO sc_machine_part (id_machine_part, name, description, clasification, mark, serie, useful_life, value, path_picture, id_priority, id_cost_center, id_time, id_supplier_guarantee, id_money, id_dimension, creation_date, id_machine) VALUES (17, 'panel control', '', 'Manual', 'suermi', 'dsxh567', 22, 123000.00, ' ', 1, 13, 2, 17, 1, 60, '2015-08-16 17:00:00-05', 2);
INSERT INTO sc_machine_part (id_machine_part, name, description, clasification, mark, serie, useful_life, value, path_picture, id_priority, id_cost_center, id_time, id_supplier_guarantee, id_money, id_dimension, creation_date, id_machine) VALUES (13, 'torniquete ', '', 'Eléctrica', 'karmez', 'dy78hj', 23, 500000.00, '/root/partofmachine_filePath/img/10171780_10152809207484931_6144923938692578050_n.jpg', 2, 17, 3, 15, 1, 53, '2015-08-10 17:00:00-05', 1);
INSERT INTO sc_machine_part (id_machine_part, name, description, clasification, mark, serie, useful_life, value, path_picture, id_priority, id_cost_center, id_time, id_supplier_guarantee, id_money, id_dimension, creation_date, id_machine) VALUES (18, '1', '1', 'Eléctrica', '1', '1', 1, 1.00, ' ', 2, 13, 3, 16, 1, 61, '2015-08-16 17:00:00-05', 2);


--
-- TOC entry 2891 (class 0 OID 305451)
-- Dependencies: 196
-- Data for Name: sc_machine_part_attached; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (7, 'OBSERVATION', '1123', '33', 8);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (8, 'SPECIFICATION', 'ty', 'sg', 10);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (9, 'SPECIFICATION', 'oui', '45', 10);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (10, 'OBSERVATION', '656', 'srfg', 10);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (11, 'SPECIFICATION', '2', '2', 11);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (12, 'FEACTURE', '22', '222', 11);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (13, 'OBSERVATION', '222', '222', 11);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (17, 'SPECIFICATION', 'motor', 'doble movilidad ', 14);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (18, 'FEACTURE', 'inyección ', 'inyección por aire refinado ', 14);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (19, 'OBSERVATION', 'condiciones internas ', 'punto de aire y agua', 14);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (20, 'OBSERVATION', 'condiciones externas ', 'no cercanía a zonas húmedas ', 14);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (21, 'SPECIFICATION', 'titulo 1', 'fff', 15);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (22, 'FEACTURE', 'carateris', 'dfddd', 15);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (23, 'OBSERVATION', 'eeee', 'eeee', 15);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (24, 'OBSERVATION', 'TEST', 'TEST', 8);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (25, 'SPECIFICATION', 'TEST', 'TEST', 8);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (26, 'OBSERVATION', 'TEST', 'TEST', 8);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (27, 'SPECIFICATION', 'TEST', 'TEST', 8);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (28, 'SPECIFICATION', 'nota', 'prueba', 17);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (29, 'FEACTURE', 'prueba2', 'nota', 17);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (30, 'OBSERVATION', 'prueba3', 'notas', 17);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (31, 'SPECIFICATION', 'jotw', 'sgdg', 17);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (32, 'SPECIFICATION', 'yte', 'dsghds', 17);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (33, 'SPECIFICATION', '1', '1', 18);
INSERT INTO sc_machine_part_attached (id_machine_part_attached, type, tittle, description, id_machine_part) VALUES (34, 'OBSERVATION', '1', '1', 18);


--
-- TOC entry 2892 (class 0 OID 305454)
-- Dependencies: 197
-- Data for Name: sc_machine_part_document; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_machine_part_document (id_machine_part_document, document_path, document_tittle, creation_date, document_name, upload_by, id_machine_part, document_type) VALUES (3, '/home/gchavarro88/partofmachine_filePath/docs', 'test', '2015-08-07 17:00:00-05', 'GSI-R-29 Formulario PQR V2.pdf', 'guschaor', 8, 'application/stream');
INSERT INTO sc_machine_part_document (id_machine_part_document, document_path, document_tittle, creation_date, document_name, upload_by, id_machine_part, document_type) VALUES (4, '/home/gchavarro88/partofmachine_filePath/docs', 'test', '2015-08-07 17:00:00-05', 'GSI-R-29 Formulario PQR V2.pdf', 'guschaor', 8, 'application/stream');
INSERT INTO sc_machine_part_document (id_machine_part_document, document_path, document_tittle, creation_date, document_name, upload_by, id_machine_part, document_type) VALUES (5, '/home/gchavarro88/partofmachine_filePath/docs', 'tjghn', '2015-08-07 17:00:00-05', '11-08-09 revision bd.docx', 'yaconcha', 10, 'application/vnd.openxmlformats-officedocument.wordprocessingml.document');
INSERT INTO sc_machine_part_document (id_machine_part_document, document_path, document_tittle, creation_date, document_name, upload_by, id_machine_part, document_type) VALUES (6, '/home/gchavarro88/partofmachine_filePath/docs', '222', '2015-08-07 17:00:00-05', '11-08-09 revision bd.docx', 'guschaor', 11, 'application/vnd.openxmlformats-officedocument.wordprocessingml.document');
INSERT INTO sc_machine_part_document (id_machine_part_document, document_path, document_tittle, creation_date, document_name, upload_by, id_machine_part, document_type) VALUES (8, '/home/gchavarro88/partofmachine_filePath/docs', 'tuh', '2015-08-11 17:00:00-05', '239-451-1-SM.pdf', 'yaconcha', 1, 'application/pdf');
INSERT INTO sc_machine_part_document (id_machine_part_document, document_path, document_tittle, creation_date, document_name, upload_by, id_machine_part, document_type) VALUES (9, '/home/gchavarro88/partofmachine_filePath/docs', 'tuh', '2015-08-11 17:00:00-05', '239-451-1-SM.pdf', 'yaconcha', 1, 'application/pdf');
INSERT INTO sc_machine_part_document (id_machine_part_document, document_path, document_tittle, creation_date, document_name, upload_by, id_machine_part, document_type) VALUES (10, '/home/gchavarro88/partofmachine_filePath/docs', 'ddd', '2015-08-16 17:00:00-05', 'problemasDriver2.txt', 'jguerrero', 15, 'text/plain');
INSERT INTO sc_machine_part_document (id_machine_part_document, document_path, document_tittle, creation_date, document_name, upload_by, id_machine_part, document_type) VALUES (11, '/home/gchavarro88/partofmachine_filePath/docs', 'test', '2015-08-16 17:00:00-05', 'Certificado HTML 5.pdf', 'guschaor', 11, 'application/stream');
INSERT INTO sc_machine_part_document (id_machine_part_document, document_path, document_tittle, creation_date, document_name, upload_by, id_machine_part, document_type) VALUES (12, '/home/gchavarro88/partofmachine_filePath/docs', 'test', '2015-08-16 17:00:00-05', 'Certificado HTML 5.pdf', 'guschaor', 11, 'application/stream');
INSERT INTO sc_machine_part_document (id_machine_part_document, document_path, document_tittle, creation_date, document_name, upload_by, id_machine_part, document_type) VALUES (13, '/home/gchavarro88/partofmachine_filePath/docs', 'test', '2015-08-16 17:00:00-05', 'Hoja de Vida Estandar Gustavo Chavarro Ortiz_D.docx', 'guschaor', 9, 'application/vnd.openxmlformats-officedocument.wordprocessingml.document');
INSERT INTO sc_machine_part_document (id_machine_part_document, document_path, document_tittle, creation_date, document_name, upload_by, id_machine_part, document_type) VALUES (14, '/home/gchavarro88/partofmachine_filePath/docs', 'test', '2015-08-16 17:00:00-05', 'Hoja de Vida Estandar Gustavo Chavarro Ortiz_D.docx', 'guschaor', 9, 'application/vnd.openxmlformats-officedocument.wordprocessingml.document');
INSERT INTO sc_machine_part_document (id_machine_part_document, document_path, document_tittle, creation_date, document_name, upload_by, id_machine_part, document_type) VALUES (15, '/home/gchavarro88/partofmachine_filePath/docs', 'yoleidys', '2015-08-16 17:00:00-05', 'Extracto_03012013_00078194.pdf', 'guschaor', 9, 'application/stream');
INSERT INTO sc_machine_part_document (id_machine_part_document, document_path, document_tittle, creation_date, document_name, upload_by, id_machine_part, document_type) VALUES (16, '/home/gchavarro88/partofmachine_filePath/docs', 'yoleidys', '2015-08-16 17:00:00-05', 'Extracto_03012013_00078194.pdf', 'guschaor', 9, 'application/stream');


--
-- TOC entry 2893 (class 0 OID 305460)
-- Dependencies: 198
-- Data for Name: sc_mails; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (32, 'gustavo.chavarro.or@gmail.com', 'Correo Principal', 1);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (33, 'gustavo.chavarro@hotmail.es', 'Correo Alternativo', 1);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (34, 'gustavo.chavarro@carvajal.com', 'Correo Empresarial', 1);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (35, 'aconcha.y@gmail.com', 'Correo Principal', 3);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (36, 'yoleidys.sipingenieria@gmail.com', 'Correo Empresarial', 3);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (37, 'jhon.guerrero.o@gmail.com', 'Correo Principal', 22);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (38, 'jhonguerrero.sipingenieria@gmail.com', 'Correo Empresarial Sip Ingenieria', 22);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (39, 'jhonguerrero.minibartech@gmail.com', 'Correo Emprearial Mini Bar', 22);


--
-- TOC entry 2894 (class 0 OID 305463)
-- Dependencies: 199
-- Data for Name: sc_maintenance_activity; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_maintenance_activity (id_maintenance_activity, name, description, id_maintenance) VALUES (28, 'Limpiar Piñon', '', 28);
INSERT INTO sc_maintenance_activity (id_maintenance_activity, name, description, id_maintenance) VALUES (29, 'yrrtr', '', 29);
INSERT INTO sc_maintenance_activity (id_maintenance_activity, name, description, id_maintenance) VALUES (30, 'Prueba para Jhon', '', 30);
INSERT INTO sc_maintenance_activity (id_maintenance_activity, name, description, id_maintenance) VALUES (31, 'Pruba del primer ingreso', 'test', 31);
INSERT INTO sc_maintenance_activity (id_maintenance_activity, name, description, id_maintenance) VALUES (32, 'Pruba del primer ingreso', '', 32);
INSERT INTO sc_maintenance_activity (id_maintenance_activity, name, description, id_maintenance) VALUES (33, 'Pruba del primer ingreso', '', 33);
INSERT INTO sc_maintenance_activity (id_maintenance_activity, name, description, id_maintenance) VALUES (34, 'Pruba del primer ingreso', '', 34);
INSERT INTO sc_maintenance_activity (id_maintenance_activity, name, description, id_maintenance) VALUES (35, 'Prueba para Jhon', '', 34);
INSERT INTO sc_maintenance_activity (id_maintenance_activity, name, description, id_maintenance) VALUES (36, 'reabastecimeinto', 'y busqueda de fuga', 35);
INSERT INTO sc_maintenance_activity (id_maintenance_activity, name, description, id_maintenance) VALUES (37, 'gdh', 'dhd', 36);
INSERT INTO sc_maintenance_activity (id_maintenance_activity, name, description, id_maintenance) VALUES (38, 'test', 'test', 37);
INSERT INTO sc_maintenance_activity (id_maintenance_activity, name, description, id_maintenance) VALUES (39, 'jhon prueba', 'bloqueo equipo', 38);
INSERT INTO sc_maintenance_activity (id_maintenance_activity, name, description, id_maintenance) VALUES (40, 'test', 'test', 39);
INSERT INTO sc_maintenance_activity (id_maintenance_activity, name, description, id_maintenance) VALUES (41, 'Limpiar Piñon', 'test', 40);
INSERT INTO sc_maintenance_activity (id_maintenance_activity, name, description, id_maintenance) VALUES (42, 'Limpiar Piñon', 'teste', 41);
INSERT INTO sc_maintenance_activity (id_maintenance_activity, name, description, id_maintenance) VALUES (43, 'test', 'test', 42);
INSERT INTO sc_maintenance_activity (id_maintenance_activity, name, description, id_maintenance) VALUES (44, 'test', 'teste', 43);


--
-- TOC entry 2895 (class 0 OID 305469)
-- Dependencies: 200
-- Data for Name: sc_maintenance_clasification; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_maintenance_clasification (id_maintenance_clasification, clasification) VALUES (1, 'Eléctrica');
INSERT INTO sc_maintenance_clasification (id_maintenance_clasification, clasification) VALUES (2, 'Mecánica');
INSERT INTO sc_maintenance_clasification (id_maintenance_clasification, clasification) VALUES (3, 'Hidráulica');
INSERT INTO sc_maintenance_clasification (id_maintenance_clasification, clasification) VALUES (4, 'Electrónica');
INSERT INTO sc_maintenance_clasification (id_maintenance_clasification, clasification) VALUES (5, 'Automática');
INSERT INTO sc_maintenance_clasification (id_maintenance_clasification, clasification) VALUES (6, 'Semiautomática');


--
-- TOC entry 2896 (class 0 OID 305472)
-- Dependencies: 201
-- Data for Name: sc_maintenance_damage; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_maintenance_damage (id_maintenance_damage, damage) VALUES (1, 'Mecánico');
INSERT INTO sc_maintenance_damage (id_maintenance_damage, damage) VALUES (2, 'Eléctrico');
INSERT INTO sc_maintenance_damage (id_maintenance_damage, damage) VALUES (3, 'Fallo de Equipo');
INSERT INTO sc_maintenance_damage (id_maintenance_damage, damage) VALUES (4, 'Fallo de Servidor');
INSERT INTO sc_maintenance_damage (id_maintenance_damage, damage) VALUES (5, 'Fallo de Digitación');


--
-- TOC entry 2897 (class 0 OID 305475)
-- Dependencies: 202
-- Data for Name: sc_maintenance_state; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_maintenance_state (id_maintenance_state, state) VALUES (1, 'PENDIENTE');
INSERT INTO sc_maintenance_state (id_maintenance_state, state) VALUES (2, 'FINALIZADO');


--
-- TOC entry 2898 (class 0 OID 305478)
-- Dependencies: 203
-- Data for Name: sc_measure_unit; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (1, 'Mts', 'Metros');
INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (2, 'Mms', 'Milimetros');
INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (4, 'KMS', 'Kilometros');
INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (17, 'PruebaYoleidy', 'PruebaYoleidy');
INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (19, 'Cts', 'Centímetros');
INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (5, 'Pulgadas', 'Pulgadas');
INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (20, 'Hershas', 'Hershas');
INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (22, 'baso', 'logitud');
INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (23, 'pies3', 'longitud ');
INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (24, 'Yoleidy Measure', 'Personal');
INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (25, 'test measure', 'test measure');
INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (26, 'wallet', 'dinero');
INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (27, 'galon', 'liquido');
INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (28, 'milla1', 'distancia');
INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (29, 'Gramos', 'Peso');


--
-- TOC entry 2899 (class 0 OID 305481)
-- Dependencies: 204
-- Data for Name: sc_module_permission; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (2, 'Visibilidad de Planta', NULL, 'oee.png', 'Folder', 1, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (3, 'Programación de Orden de Fabricación', NULL, 'ord.png', 'Folder', 1, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (5, 'Gestión del Mantenimiento', NULL, 'man.png', 'Folder', 1, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (6, 'Gestión de los Recursos', NULL, 'rec.png', 'Folder', 1, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (7, 'Configuraciones', NULL, 'confi.png', 'Folder', 1, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (8, 'Cerrar Sesión', NULL, 'salir.png', 'Folder', 1, 'exit');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (4, 'Gestión de la Calidad y la Trazabilidad', NULL, 'cal.png', 'Folder', 1, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (10, 'Recursos Materiales', NULL, 'Recursos_materiales.png', 'Folder', 7, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (9, 'Recursos Humanos', '', 'Recursos_humanos.png', 'Folder', 7, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (13, 'Usuarios y Permisos', '', 'Usuarios_y_permisos.png', 'Folder', 7, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (15, 'Grupos y Roles', NULL, 'grupos.png', 'Item', 13, 'security/Scroles.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (16, 'Terceros', NULL, 'grupos.png', 'Item', 13, 'security/Scperson.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (14, 'Usuarios', '', 'grupos.png', 'Item', 13, 'security/Scusers.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (12, 'Proveedores', NULL, 'grupos.png', 'Item', 9, 'resources/humans/ScPartners.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (11, 'Empleados', NULL, 'grupos.png', 'Item', 9, 'resources/humans/ScEmployees.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (17, 'Máquina', NULL, 'maquinas.png', 'Item', 10, 'resources/materials/ScMachines.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (18, 'Cargue de Documentos', NULL, 'cargue_documentos.png', 'Folder', 7, ' Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (19, 'Documentos por Usuario', NULL, 'cargue_documentos.png', 'Item', 18, 'LoadDocuments/FsdocumentsByUser.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (20, 'Documentos a Usuario', NULL, 'cargue_documentos.png', 'Item', 18, 'LoadDocuments/FsdocumentsToUser.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (21, 'Cargue de Parámetros', NULL, 'cargue_parametros.png', 'Folder', 7, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (22, 'Parámetros Básicos', NULL, 'cargue_parametros.png', 'Item', 21, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (25, 'Formulación de Productos', NULL, 'productos.png', 'Item', 10, 'resources/materials/ScProductFormulation.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (24, 'Insumos', NULL, 'productos.png', 'Item', 10, 'resources/materials/ScInput.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (26, 'Repuestos y Consumibles', NULL, 'repuestos_y_consumibles.png', 'Item', 10, 'resources/materials/ScReplacement.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (27, 'Herramientas', NULL, 'repuestos_y_consumibles.png', 'Item', 10, 'resources/materials/ScTool.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (28, 'Gestión de Ordenes', NULL, 'repuestos_y_consumibles.png', 'Item', 23, 'resources/materials/store/ScStoreOrders.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (23, 'Almacén', NULL, 'maquinas.png', 'Folder', 7, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (29, 'Gestión de Requisiciones', NULL, 'repuestos_y_consumibles.png', 'Item', 23, 'resources/materials/store/ScRequisitions.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (30, 'Partes de una Máquina', NULL, 'maquinas.png', 'Item', 10, 'resources/materials/ScMachinePart.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (31, 'Visibilidad del Almacén', NULL, 'maquinas.png', 'Item', 23, 'resources/materials/store/ScViewStore.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (33, 'Correctivo', NULL, 'cargue_documentos.png', 'Item', 32, 'orders/maintenance/OtMaintenanceCorrective.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (34, 'Preventivo', NULL, 'cargue_documentos.png', 'Item', 32, 'orders/maintenance/OtMaintenancePreventive.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (35, 'Programación de Mantenimientos', NULL, 'maquinas.png', 'Item', 32, 'orders/maintenance/OtMaintenanceSchedule.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (38, 'Programación de Producción', NULL, 'maquinas.png', 'Item', 36, 'orders/production/OtProductionSchedule.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (37, 'Ordenes de Producción', NULL, 'productos.png', 'Item', 36, 'orders/production/OtProduction.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (39, 'Log de Producción', NULL, 'repuestos_y_consumibles.png', 'Item', 36, 'orders/production/OtLogProduction.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (1, 'Gestión de Planta', NULL, 'oee.png', 'Home', -1, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (32, 'Mantenimiento', NULL, 'maquinas.png', 'Folder', 5, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (36, 'Producción', NULL, 'maquinas.png', 'Folder', 3, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (40, 'Fábrica y Máquinas', NULL, 'maquinas.png', 'Folder', 2, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (41, 'Indicadores', NULL, 'Recursos_materiales.png', 'Item', 40, 'factoryVisibility/FactoryVisibility.jsf');


--
-- TOC entry 2900 (class 0 OID 305487)
-- Dependencies: 205
-- Data for Name: sc_module_permission_by_role; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (202, 6, 'CRUD', 1);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (203, 6, 'CRUD', 7);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (204, 6, 'CRUD', 13);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (205, 6, 'CRUD', 15);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (209, 6, 'CRUD', 14);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (213, 6, 'CRUD', 16);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (438, 7, 'CRUD', 1);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (439, 7, 'CRUD', 7);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (440, 7, 'CRUD', 10);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (441, 7, 'CRUD', 25);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (445, 7, 'CRUD', 24);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (448, 7, 'CRUD', 18);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (449, 7, 'CRUD', 20);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (453, 7, 'CRUD', 19);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (456, 7, 'CRUD', 21);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (457, 7, 'CRUD', 22);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (460, 7, 'CRUD', 9);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (461, 7, 'CRUD', 12);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (465, 7, 'CRUD', 11);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (468, 7, 'CRUD', 13);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (469, 7, 'CRUD', 16);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (473, 7, 'CRUD', 15);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (477, 7, 'CRUD', 14);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1208, 10, 'CRUD', 1);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1210, 10, 'CRUD', 21);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1212, 2, 'CRUD', 1);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1214, 2, 'CRUD', 9);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1222, 2, 'CRUD', 13);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (876, 9, 'CRUD', 1);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (877, 9, 'CRUD', 7);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (878, 9, 'CRUD', 9);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (879, 9, 'CRUD', 11);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (883, 9, 'CRUD', 12);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (886, 9, 'CRUD', 13);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (887, 9, 'CRUD', 14);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (891, 9, 'CRUD', 16);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (895, 9, 'CRUD', 15);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (898, 9, 'CRUD', 10);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (899, 9, 'CRUD', 17);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (902, 9, 'CRUD', 18);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (910, 9, 'CRUD', 21);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (930, 9, 'CRUD', 23);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (903, 9, 'CRUD', 19);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (907, 9, 'CRUD', 20);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (911, 9, 'CRUD', 22);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (915, 9, 'CRUD', 25);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (919, 9, 'CRUD', 24);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (923, 9, 'CRUD', 26);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (927, 9, 'CRUD', 27);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (931, 9, 'CRUD', 28);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (935, 9, 'CRUD', 29);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (939, 9, 'CRUD', 30);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1209, 10, 'CRUD', 7);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1211, 10, 'CRUD', 22);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1213, 2, 'CRUD', 7);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1215, 2, 'CRUD', 11);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1219, 2, 'CRUD', 12);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1223, 2, 'CRUD', 14);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1227, 2, 'CRUD', 16);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1228, 11, 'CRUD', 1);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1229, 11, 'CRUD', 7);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1230, 11, 'CRUD', 13);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1231, 11, 'CRUD', 15);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1235, 11, 'CRUD', 16);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1239, 11, 'CRUD', 14);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1242, 11, 'CRUD', 9);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1243, 11, 'CRUD', 12);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1247, 11, 'CRUD', 11);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1250, 11, 'CRUD', 10);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1251, 11, 'CRUD', 17);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1254, 11, 'CRUD', 18);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1255, 11, 'CRUD', 19);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1259, 11, 'CRUD', 20);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1262, 11, 'CRUD', 21);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1263, 11, 'CRUD', 22);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1267, 11, 'CRUD', 25);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1271, 11, 'CRUD', 24);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1275, 11, 'CRUD', 26);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1279, 11, 'CRUD', 27);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1282, 11, 'CRUD', 23);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1283, 11, 'CRUD', 28);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1287, 11, 'CRUD', 29);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1291, 11, 'CRUD', 30);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1295, 11, 'CRUD', 31);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1932, 1, 'CRUD', 1);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1934, 1, 'CRUD', 13);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1946, 1, 'CRUD', 9);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1954, 1, 'CRUD', 10);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1958, 1, 'CRUD', 18);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1962, 1, 'CRUD', 21);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1986, 1, 'CRUD', 23);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2112, 8, 'CRUD', 1);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2113, 8, 'CRUD', 7);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2114, 8, 'CRUD', 13);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2115, 8, 'CRUD', 15);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2002, 1, 'CRUD', 32);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2119, 8, 'CRUD', 16);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1933, 1, 'CRUD', 7);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1935, 1, 'CRUD', 15);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1939, 1, 'CRUD', 14);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1943, 1, 'CRUD', 16);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2123, 8, 'CRUD', 14);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1947, 1, 'CRUD', 11);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1951, 1, 'CRUD', 12);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1955, 1, 'CRUD', 17);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2126, 8, 'CRUD', 9);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2127, 8, 'CRUD', 12);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1959, 1, 'CRUD', 20);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1963, 1, 'CRUD', 22);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1967, 1, 'CRUD', 19);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1971, 1, 'CRUD', 24);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1975, 1, 'CRUD', 25);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1979, 1, 'CRUD', 26);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2131, 8, 'CRUD', 11);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1983, 1, 'CRUD', 27);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1987, 1, 'CRUD', 28);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2134, 8, 'CRUD', 10);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1991, 1, 'CRUD', 29);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1995, 1, 'CRUD', 30);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2135, 8, 'CRUD', 17);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1999, 1, 'CRUD', 31);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2003, 1, 'CRUD', 34);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2138, 8, 'CRUD', 18);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2007, 1, 'CRUD', 33);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2139, 8, 'CRUD', 19);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2011, 1, 'CRUD', 35);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2014, 1, 'CRUD', 36);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2015, 1, 'CRUD', 38);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2143, 8, 'CRUD', 20);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2019, 1, 'CRUD', 37);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2146, 8, 'CRUD', 21);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2147, 8, 'CRUD', 22);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2151, 8, 'CRUD', 25);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2155, 8, 'CRUD', 24);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2159, 8, 'CRUD', 26);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2163, 8, 'CRUD', 27);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2166, 8, 'CRUD', 23);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2167, 8, 'CRUD', 28);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2171, 8, 'CRUD', 29);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2175, 8, 'CRUD', 30);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2179, 8, 'CRUD', 31);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2181, 8, 'CRUD', 5);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2182, 8, 'CRUD', 32);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2183, 8, 'CRUD', 33);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2187, 8, 'CRUD', 34);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2191, 8, 'CRUD', 35);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2193, 8, 'CRUD', 3);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2194, 8, 'CRUD', 36);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2195, 8, 'CRUD', 37);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2199, 8, 'CRUD', 38);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2203, 8, 'CRUD', 39);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2205, 8, 'CRUD', 2);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2206, 8, 'CRUD', 40);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2207, 8, 'CRUD', 41);


--
-- TOC entry 2901 (class 0 OID 305490)
-- Dependencies: 206
-- Data for Name: sc_money; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_money (id_money, description, acronym, trm) VALUES (1, 'Dolar', '$', 2300.00);
INSERT INTO sc_money (id_money, description, acronym, trm) VALUES (2, 'Pesos', '$', 1.00);
INSERT INTO sc_money (id_money, description, acronym, trm) VALUES (3, 'Libra', '£', 1500.00);
INSERT INTO sc_money (id_money, description, acronym, trm) VALUES (4, 'Centavo', '¢', 0.01);
INSERT INTO sc_money (id_money, description, acronym, trm) VALUES (5, 'Yen', '¥', 400.00);


--
-- TOC entry 2902 (class 0 OID 305493)
-- Dependencies: 207
-- Data for Name: sc_notification; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (40, 1, '1ns7af1gc1', 1, 52);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (41, 2, '0af4mj7yt0', 4, 53);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (42, 3, '9ex8ad1pl6', 1, 54);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (43, 3, '1ts2ri4hc5', 1, 55);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (44, 3, '8pm6ck8tl0', 1, 56);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (45, 3, '7ia4fh8pt4', 1, 57);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (46, 3, '0ch9br2yo3', 1, 58);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (47, 3, '3yv3og3rh8', 1, 59);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (48, 3, '6vo8go7bg6', 1, 60);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (49, 3, '9pu3lm6if0', 1, 61);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (50, 3, '0lv8is1ka8', 1, 62);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (51, 3, '3qy9pq8vg9', 1, 63);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (52, 3, '7ia3um4sj3', 1, 64);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (53, 3, '3nh6yr0dt3', 1, 65);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (54, 3, '6eq5fu1jo1', 1, 66);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (55, 3, '0hb0hs4ih1', 1, 67);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (56, 14, '4cb8cf0nb2', 1, 68);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (57, 1, '8hn0ry5ds9', 1, 69);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (58, 1, '9xv7ah3gs1', 1, 70);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (59, 1, '9cc4bq8dk7', 1, 71);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (60, 1, '3ne6ls3vs7', 1, 72);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (61, 1, '2tm1xe5pr1', 1, 73);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (62, 1, '5pl8pt7uq2', 1, 74);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (63, 1, '9jo2qq3ia3', 1, 75);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (64, 1, '3my9ta2sb9', 1, 76);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (65, 1, '6od3qy9mb6', 1, 77);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (66, 1, '6qn8qt5iv5', 1, 78);
INSERT INTO sc_notification (id_notification, id_machine, password, id_group, id_stop_machine) VALUES (67, 1, '8tu5gf9td2', 5, 79);


--
-- TOC entry 2903 (class 0 OID 305496)
-- Dependencies: 208
-- Data for Name: sc_operating_conditions; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2904 (class 0 OID 305502)
-- Dependencies: 209
-- Data for Name: sc_packing_unit; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_packing_unit (id_packing, description, acronym) VALUES (3, 'unidad de empaque', 'Caja');
INSERT INTO sc_packing_unit (id_packing, description, acronym) VALUES (5, 'Cilindro para gases', 'Cilindro');
INSERT INTO sc_packing_unit (id_packing, description, acronym) VALUES (4, 'arrobas', 'arroba');
INSERT INTO sc_packing_unit (id_packing, description, acronym) VALUES (6, 'Estibas', 'Estiba');
INSERT INTO sc_packing_unit (id_packing, description, acronym) VALUES (7, 'Cantidad medido en vasoso milimetricos', 'Vaso');


--
-- TOC entry 2905 (class 0 OID 305505)
-- Dependencies: 210
-- Data for Name: sc_partner; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_partner (id_partner, active, "position", web_page, creation_date, modify_date, id_person, company_name) VALUES (1, 'Y', 'Asesor', 'http://www.google.com', '2014-11-19', NULL, 13, 'Carvajal');
INSERT INTO sc_partner (id_partner, active, "position", web_page, creation_date, modify_date, id_person, company_name) VALUES (15, 'Y', 'Ingeniero Analista', '', '2014-11-21', NULL, 2, 'Tecnoquímicas');
INSERT INTO sc_partner (id_partner, active, "position", web_page, creation_date, modify_date, id_person, company_name) VALUES (16, 'Y', 'jefe venta', '', '2015-05-14', NULL, 16, 'complex');
INSERT INTO sc_partner (id_partner, active, "position", web_page, creation_date, modify_date, id_person, company_name) VALUES (17, 'Y', 'vendedor', '', '2015-08-15', NULL, 18, 'durman');
INSERT INTO sc_partner (id_partner, active, "position", web_page, creation_date, modify_date, id_person, company_name) VALUES (18, 'Y', 'prueba', '', '2015-08-15', NULL, 29, 'prueba');
INSERT INTO sc_partner (id_partner, active, "position", web_page, creation_date, modify_date, id_person, company_name) VALUES (19, 'Y', 'De ventas', '', '2015-08-15', NULL, 20, 'La 14');
INSERT INTO sc_partner (id_partner, active, "position", web_page, creation_date, modify_date, id_person, company_name) VALUES (20, 'Y', 'Asesor Comercial', 'www.jgb.com', '2015-08-15', NULL, 30, 'JGB');
INSERT INTO sc_partner (id_partner, active, "position", web_page, creation_date, modify_date, id_person, company_name) VALUES (21, 'Y', 'ventas', '', '2015-12-14', NULL, 38, 'complex');


--
-- TOC entry 2906 (class 0 OID 305508)
-- Dependencies: 211
-- Data for Name: sc_person; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (2, 'Cristian Camilo', 'Chaparro Cuadros', 23, 'Colombia ', 'Cali', NULL, 'Oeste de Cali', NULL, NULL, '/', '2014-09-22 17:00:00-05', NULL, 111111111111111111, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (11, 'Valentina', 'Trujillo Ocampo', 33, 'Colombia', 'Cali', 'Barrio Champañat', 'Carrera 28 # 9-52', 'Colegio 3 de primaria básica', 'Niña de Javier', '/', '2014-11-03 18:00:00-05', '2014-11-08 18:00:00-05', 11133333333, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (14, 'carlos', 'uzman', 18, 'colombia', 'cali', 'ksksks', 'calle 100', 'ooo', 'sssss', '/', '2014-11-29 18:00:00-05', NULL, 222222222, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (17, 'Camilo', 'Topo', 22, 'Colombia', 'Cali', '', 'Crr 92 # 3 a 30', '', '', '/', '2015-07-28 17:00:00-05', NULL, 8675545, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (18, 'leonardo jose', 'ordoñes ', 29, 'colombia', 'cali', 'tiene habilidades en la creacion de diseño arquitectonico ', ' calle 49·43 - 76', 'ing. industrial', '', '/', '2015-07-28 17:00:00-05', NULL, 166905680, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (19, 'Jesus', 'Lopez', 30, 'Colombia', 'Cali', '', 'Cr 87#4-8', '', '', '/', '2015-07-28 17:00:00-05', NULL, 6456456, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (20, 'Jose Raúl', 'Lover Daza', 28, 'Colombia', 'Cali', '', 'Calle 1 # 25 - 32 Palmira', 'Ingeniería de Sistemas', '', '/', '2015-07-28 17:00:00-05', NULL, 11302322459, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (27, 'Camilo', 'Topo', 22, 'Colombia', 'Cali', '', 'Calle 2 #3-5', 'Tecnico', '', '/', '2015-08-10 17:00:00-05', NULL, 6456454, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (29, 'jorge luis', 'PEÑA', 24, 'Colombia', 'Cali', '', 'Calle 2 #3-4', 'Tecnico', '', '/', '2015-08-10 17:00:00-05', NULL, 6456458, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (30, 'fernando', 'SAUCEDO', 25, 'Colombia', 'Cali', '', 'Calle 2 #3-3', 'Tecnico', '', '/', '2015-08-10 17:00:00-05', NULL, 6456459, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (13, 'Lizeth Nathalia', 'Girón López ', 18, 'Colombia', 'Cali', 'test', 'Calle 23 # Alfonso Bonilla Aragón', 'Ingeniería Agrícola', 'Persona interesada en conocer la empresa', '/', '2014-11-07 18:00:00-05', '2015-08-14 17:00:00-05', 1149493828, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (34, 'Montenegro', 'Solis', 34, 'Colombia', 'Bogotá', '', 'Calle 34 # 23 -34 ', 'Licenciatura en Portugués', '', '/', '2015-08-23 17:00:00-05', NULL, 111110303, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (36, 'Felipe Alexander', 'Escobar Giraldo', 23, 'Venezuela', 'Caracas', '', 'Calle 44 av Cali', 'Gerente en ventas', '', '/home/gchavarro88/inputs_filePath/img/superman.png', '2015-08-23 17:00:00-05', NULL, 112040405, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (16, 'jaime', 'acosta', 23, 'colombia', 'cali', 'szvsfzv', 'calla 1 34#45', 'fdbdf', 'sdvsv', '/home/ubuntu/inputs_filePath/img/pruebayole.png', '2015-05-13 17:00:00-05', '2015-12-14 07:16:52.486-05', 1609873, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (37, 'miguel andres', 'cardona', 28, 'colombia', 'cali', 'sg', 'calle h55', 'sg', 'sg', '/home/ubuntu/inputs_filePath/img/Screenshot - 101215 - 14:09:49.png', '2015-12-14 07:24:01.388-05', NULL, 1987893, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (38, 'jose carlos', 'san miguel', 28, 'colombia', 'cali', '', 'sfsgggg', '', '', '', '2015-12-14 08:56:49.115-05', NULL, 66667677, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (1, 'Gustavo Adolfo', 'Chavarro Ortiz', 26, 'Colombia', 'Cali', '', 'Carrera 21 # 13-16', 'Ingeniero de Sistemas', '', '/', '2014-09-25 17:00:00-05', '2016-02-07 02:50:52.793-05', 1107046850, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (3, 'Yoleidy', 'Aconcha', 26, 'Colombia', 'Cali', '', 'Carrera 103 Calle 49', 'Ingeniera Electrónica', '', '/home/ubuntu/inputs_filePath/img/Screenshot - 101215 - 14:09:49.png', '2014-10-20 17:00:00-05', '2016-02-07 02:53:28.913-05', 11111111111111, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (22, 'Jhon', 'Guerrero', 30, 'Colombia', 'Cali', '', 'Calle 100', 'Ingeniero Electrónico', '', '/', '2015-08-07 17:00:00-05', '2016-02-07 02:55:46.56-05', 945409696, NULL);


--
-- TOC entry 2907 (class 0 OID 305514)
-- Dependencies: 212
-- Data for Name: sc_person_by_group; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_person_by_group (id_person_by_group, id_person, id_group) VALUES (1, 1, 1);
INSERT INTO sc_person_by_group (id_person_by_group, id_person, id_group) VALUES (3, 22, 1);
INSERT INTO sc_person_by_group (id_person_by_group, id_person, id_group) VALUES (4, 1, 2);
INSERT INTO sc_person_by_group (id_person_by_group, id_person, id_group) VALUES (7, 11, 3);
INSERT INTO sc_person_by_group (id_person_by_group, id_person, id_group) VALUES (8, 14, 4);
INSERT INTO sc_person_by_group (id_person_by_group, id_person, id_group) VALUES (9, 13, 5);
INSERT INTO sc_person_by_group (id_person_by_group, id_person, id_group) VALUES (10, 16, 6);
INSERT INTO sc_person_by_group (id_person_by_group, id_person, id_group) VALUES (2, 3, 1);
INSERT INTO sc_person_by_group (id_person_by_group, id_person, id_group) VALUES (5, 3, 2);
INSERT INTO sc_person_by_group (id_person_by_group, id_person, id_group) VALUES (6, 22, 2);


--
-- TOC entry 2908 (class 0 OID 305517)
-- Dependencies: 213
-- Data for Name: sc_person_observations; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_person_observations (id_person_observations, tittle, observation, id_person) VALUES (10, 'Almuerza', 'almuerza', 11);
INSERT INTO sc_person_observations (id_person_observations, tittle, observation, id_person) VALUES (11, 'Mi novia', 'es la persona que me alegra las mañanas', 13);
INSERT INTO sc_person_observations (id_person_observations, tittle, observation, id_person) VALUES (12, 'Mi novia', 'es la persona que me alegra las mañanas', 13);
INSERT INTO sc_person_observations (id_person_observations, tittle, observation, id_person) VALUES (14, 'compañerismo', 'personal con caracteristicas de trabajo en grupo', 18);
INSERT INTO sc_person_observations (id_person_observations, tittle, observation, id_person) VALUES (17, 'observacion 2', 'observacion 2', 27);
INSERT INTO sc_person_observations (id_person_observations, tittle, observation, id_person) VALUES (18, 'observacion 5', 'observacion 5', 30);


--
-- TOC entry 2909 (class 0 OID 305523)
-- Dependencies: 214
-- Data for Name: sc_person_specifications; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_person_specifications (id_person_specifications, tittle, specification, id_person) VALUES (11, 'Dedicación', 'Dedicación', 11);
INSERT INTO sc_person_specifications (id_person_specifications, tittle, specification, id_person) VALUES (12, 'especializacion ', 'con especializacion en procesos lineales y compuestos', 18);
INSERT INTO sc_person_specifications (id_person_specifications, tittle, specification, id_person) VALUES (13, 'ingeniero', 'ngeniero indstrail', 18);
INSERT INTO sc_person_specifications (id_person_specifications, tittle, specification, id_person) VALUES (16, 'Espesificacion 2', 'Espesificacion 2', 27);
INSERT INTO sc_person_specifications (id_person_specifications, tittle, specification, id_person) VALUES (17, 'Espesificacion 5', 'Espesificacion 5', 30);


--
-- TOC entry 2910 (class 0 OID 305529)
-- Dependencies: 215
-- Data for Name: sc_phones; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (10, 3176600681, 'Telefono de Mama', 11);
INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (14, 3117036163, 'Personal', 13);
INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (15, 3182433265, 'Abuela', 11);
INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (17, 983245678, 'fijo', 16);
INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (18, 5568568, '', 17);
INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (19, 32879098, 'casa', 18);
INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (20, 31890976673, 'celular ', 18);
INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (21, 4568978, '', 19);
INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (23, 3163237086, 'Personal', 22);
INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (27, 1234568, 'Casa', 27);
INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (29, 1234570, 'casa', 29);
INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (30, 1234571, 'Casa', 30);
INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (33, 3341934, 'personal', 34);
INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (34, 45769865, 'sgrdh', 37);


--
-- TOC entry 2911 (class 0 OID 305532)
-- Dependencies: 216
-- Data for Name: sc_priority; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_priority (id_priority, name, description) VALUES (1, 'Alta', 'Alta');
INSERT INTO sc_priority (id_priority, name, description) VALUES (2, 'Media', 'Media');
INSERT INTO sc_priority (id_priority, name, description) VALUES (3, 'Baja', 'Baja');


--
-- TOC entry 2912 (class 0 OID 305535)
-- Dependencies: 217
-- Data for Name: sc_procces_product; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_procces_product (id_process_product, id_process_type, name, description, total_time_machine, total_value_machine, total_value_input, total_time_process, total_value_process, total_time_employee, total_value_employee, id_product_formulation) VALUES (35, 1, 'Empaquetado', 'Proceso donde se guardan los el producto en sus empaques', 1, 16.67, 4000.00, 2, 4033.34, 1, 16.67, 15);


--
-- TOC entry 2913 (class 0 OID 305541)
-- Dependencies: 218
-- Data for Name: sc_procces_product_order; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_procces_product_order (id_process_product_order, id_process_type, name, description, total_time_machine, total_value_machine, total_value_input, total_time_process, total_value_process, total_time_employee, total_value_employee, id_process_state, amount_produced, id_product_order) VALUES (27, 2, 'Fundido de Lamina', 'Moldeado de la lamina a 30° Celsius', 130, 2333.33, 4800.00, 250, 7135.33, 120, 2.00, 1, 1, 14);
INSERT INTO sc_procces_product_order (id_process_product_order, id_process_type, name, description, total_time_machine, total_value_machine, total_value_input, total_time_process, total_value_process, total_time_employee, total_value_employee, id_process_state, amount_produced, id_product_order) VALUES (29, 2, 'Fundido de Lamina', 'Moldeado de la lamina a 30° Celsius', 130, 2333.33, 4800.00, 250, 7135.33, 120, 2.00, 1, 3, 16);
INSERT INTO sc_procces_product_order (id_process_product_order, id_process_type, name, description, total_time_machine, total_value_machine, total_value_input, total_time_process, total_value_process, total_time_employee, total_value_employee, id_process_state, amount_produced, id_product_order) VALUES (30, 2, 'Fundido de Lamina', 'Moldeado de la lamina a 30° Celsius', 130, 2333.33, 4800.00, 250, 7135.33, 120, 2.00, 1, 1, 17);
INSERT INTO sc_procces_product_order (id_process_product_order, id_process_type, name, description, total_time_machine, total_value_machine, total_value_input, total_time_process, total_value_process, total_time_employee, total_value_employee, id_process_state, amount_produced, id_product_order) VALUES (28, 2, 'Fundido de Lamina', 'Moldeado de la lamina a 30° Celsius', 130, 2333.33, 4800.00, 250, 7135.33, 120, 2.00, 2, 3, 15);
INSERT INTO sc_procces_product_order (id_process_product_order, id_process_type, name, description, total_time_machine, total_value_machine, total_value_input, total_time_process, total_value_process, total_time_employee, total_value_employee, id_process_state, amount_produced, id_product_order) VALUES (31, 2, 'Fundido de Lamina', 'Moldeado de la lamina a 30° Celsius', 130, 2333.33, 4800.00, 250, 7135.33, 120, 2.00, 2, 2, 18);


--
-- TOC entry 2914 (class 0 OID 305547)
-- Dependencies: 219
-- Data for Name: sc_process_attached; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2915 (class 0 OID 305550)
-- Dependencies: 220
-- Data for Name: sc_process_employee; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_process_employee (id_process_employee, labor_description, time_use, other_expenses, total_value_employee, id_employee, id_process, description_other_expenses) VALUES (42, 'Operador que maneja la máquina', 1, 0.00, 16.67, 14, 35, '');


--
-- TOC entry 2916 (class 0 OID 305556)
-- Dependencies: 221
-- Data for Name: sc_process_employee_order; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_process_employee_order (id_process_employee_order, labor_description, time_use, other_expenses, total_value_employee, id_employee, id_process_order, description_other_expenses) VALUES (35, 'Fundido de la lámina', 120, 0.00, 2.00, 10, 27, '');
INSERT INTO sc_process_employee_order (id_process_employee_order, labor_description, time_use, other_expenses, total_value_employee, id_employee, id_process_order, description_other_expenses) VALUES (36, 'Fundido de la lámina', 120, 0.00, 2.00, 10, 28, '');
INSERT INTO sc_process_employee_order (id_process_employee_order, labor_description, time_use, other_expenses, total_value_employee, id_employee, id_process_order, description_other_expenses) VALUES (37, 'Fundido de la lámina', 120, 0.00, 2.00, 10, 29, '');
INSERT INTO sc_process_employee_order (id_process_employee_order, labor_description, time_use, other_expenses, total_value_employee, id_employee, id_process_order, description_other_expenses) VALUES (38, 'Fundido de la lámina', 120, 0.00, 2.00, 10, 30, '');
INSERT INTO sc_process_employee_order (id_process_employee_order, labor_description, time_use, other_expenses, total_value_employee, id_employee, id_process_order, description_other_expenses) VALUES (39, 'Fundido de la lámina', 120, 0.00, 2.00, 10, 31, '');


--
-- TOC entry 2917 (class 0 OID 305562)
-- Dependencies: 222
-- Data for Name: sc_process_input; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_process_input (id_process_input, id_process, id_input, amount_distribution, percentage_residue, total_value_input) VALUES (39, 35, 16, 20, 12.00, 4000.00);


--
-- TOC entry 2918 (class 0 OID 305565)
-- Dependencies: 223
-- Data for Name: sc_process_input_order; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_process_input_order (id_process_input_order, id_process_order, id_input, amount_distribution, percentage_residue, total_value_input) VALUES (27, 27, 16, 12, 0.00, 2400.00);
INSERT INTO sc_process_input_order (id_process_input_order, id_process_order, id_input, amount_distribution, percentage_residue, total_value_input) VALUES (28, 27, 16, 12, 0.00, 2400.00);
INSERT INTO sc_process_input_order (id_process_input_order, id_process_order, id_input, amount_distribution, percentage_residue, total_value_input) VALUES (29, 28, 16, 12, 0.00, 2400.00);
INSERT INTO sc_process_input_order (id_process_input_order, id_process_order, id_input, amount_distribution, percentage_residue, total_value_input) VALUES (30, 28, 16, 12, 0.00, 2400.00);
INSERT INTO sc_process_input_order (id_process_input_order, id_process_order, id_input, amount_distribution, percentage_residue, total_value_input) VALUES (31, 29, 16, 12, 0.00, 2400.00);
INSERT INTO sc_process_input_order (id_process_input_order, id_process_order, id_input, amount_distribution, percentage_residue, total_value_input) VALUES (32, 29, 16, 12, 0.00, 2400.00);
INSERT INTO sc_process_input_order (id_process_input_order, id_process_order, id_input, amount_distribution, percentage_residue, total_value_input) VALUES (33, 30, 16, 12, 0.00, 2400.00);
INSERT INTO sc_process_input_order (id_process_input_order, id_process_order, id_input, amount_distribution, percentage_residue, total_value_input) VALUES (34, 30, 16, 12, 0.00, 2400.00);
INSERT INTO sc_process_input_order (id_process_input_order, id_process_order, id_input, amount_distribution, percentage_residue, total_value_input) VALUES (35, 31, 16, 12, 0.00, 2400.00);
INSERT INTO sc_process_input_order (id_process_input_order, id_process_order, id_input, amount_distribution, percentage_residue, total_value_input) VALUES (36, 31, 16, 12, 0.00, 2400.00);


--
-- TOC entry 2919 (class 0 OID 305568)
-- Dependencies: 224
-- Data for Name: sc_process_machine; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_process_machine (id_process_machine, id_machine, time_use, other_expenses, total_value_machine, id_process, description_other_expenses) VALUES (43, 1, 1, 0.00, 16.67, 35, '');


--
-- TOC entry 2920 (class 0 OID 305574)
-- Dependencies: 225
-- Data for Name: sc_process_machine_order; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_process_machine_order (id_process_machine_order, id_machine, time_use, other_expenses, total_value_machine, id_process_order, description_other_expenses) VALUES (36, 1, 120, 0.00, 2000.00, 27, '');
INSERT INTO sc_process_machine_order (id_process_machine_order, id_machine, time_use, other_expenses, total_value_machine, id_process_order, description_other_expenses) VALUES (37, 2, 10, 0.00, 333.33, 27, '');
INSERT INTO sc_process_machine_order (id_process_machine_order, id_machine, time_use, other_expenses, total_value_machine, id_process_order, description_other_expenses) VALUES (38, 1, 120, 0.00, 2000.00, 28, '');
INSERT INTO sc_process_machine_order (id_process_machine_order, id_machine, time_use, other_expenses, total_value_machine, id_process_order, description_other_expenses) VALUES (39, 2, 10, 0.00, 333.33, 28, '');
INSERT INTO sc_process_machine_order (id_process_machine_order, id_machine, time_use, other_expenses, total_value_machine, id_process_order, description_other_expenses) VALUES (40, 1, 120, 0.00, 2000.00, 29, '');
INSERT INTO sc_process_machine_order (id_process_machine_order, id_machine, time_use, other_expenses, total_value_machine, id_process_order, description_other_expenses) VALUES (41, 2, 10, 0.00, 333.33, 29, '');
INSERT INTO sc_process_machine_order (id_process_machine_order, id_machine, time_use, other_expenses, total_value_machine, id_process_order, description_other_expenses) VALUES (42, 1, 120, 0.00, 2000.00, 30, '');
INSERT INTO sc_process_machine_order (id_process_machine_order, id_machine, time_use, other_expenses, total_value_machine, id_process_order, description_other_expenses) VALUES (43, 2, 10, 0.00, 333.33, 30, '');
INSERT INTO sc_process_machine_order (id_process_machine_order, id_machine, time_use, other_expenses, total_value_machine, id_process_order, description_other_expenses) VALUES (44, 1, 120, 0.00, 2000.00, 31, '');
INSERT INTO sc_process_machine_order (id_process_machine_order, id_machine, time_use, other_expenses, total_value_machine, id_process_order, description_other_expenses) VALUES (45, 2, 10, 0.00, 333.33, 31, '');


--
-- TOC entry 2921 (class 0 OID 305580)
-- Dependencies: 226
-- Data for Name: sc_process_type; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_process_type (id_process_type, description, type) VALUES (1, 'Proceso realizado por la misma empresa', 'Interno');
INSERT INTO sc_process_type (id_process_type, description, type) VALUES (2, 'Proceso realizado po r un proveedor o fabricante', 'Externo');


--
-- TOC entry 2922 (class 0 OID 305583)
-- Dependencies: 227
-- Data for Name: sc_product_attached; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2923 (class 0 OID 305589)
-- Dependencies: 228
-- Data for Name: sc_product_documents; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2924 (class 0 OID 305595)
-- Dependencies: 229
-- Data for Name: sc_product_formulation; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_product_formulation (id_product_formulation, path_picture, type_material, mark, serie, id_packing, expiry_date, creation_date, id_priority, id_cost_center, value, id_money, id_location, manufacturing_time, description, id_product_dimension, id_stock) VALUES (15, '/home/gchavarro88/product_filePath/img/yupi.jpg', 'Semilla de Girasol', 'Frito Light', '123AC', 3, NULL, '2016-04-23 20:49:07.63-05', 3, 16, 500, 2, 17, 2, 'Yupis 500Grs', 74, 42);


--
-- TOC entry 2925 (class 0 OID 305601)
-- Dependencies: 230
-- Data for Name: sc_product_order; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_product_order (id_product_order, path_picture, type_material, mark, serie, id_packing, expiry_date, creation_date, id_priority, id_cost_center, value, id_money, id_location, manufacturing_time, description, id_product_dimension, id_order, amount_required, amount_produced, id_product_formulation) VALUES (14, '/home/gchavarro88/products_filePath/img/celos.jpg', 'Metal', 'Spark GT', 'ACGD12021988', 6, '2020-02-11 18:00:00-05', '2015-04-30 17:00:00-05', 1, 10, 15000, 1, 1, 250, 'Carros Chevrolet', 27, 20, 1, NULL, 12);
INSERT INTO sc_product_order (id_product_order, path_picture, type_material, mark, serie, id_packing, expiry_date, creation_date, id_priority, id_cost_center, value, id_money, id_location, manufacturing_time, description, id_product_dimension, id_order, amount_required, amount_produced, id_product_formulation) VALUES (15, '/home/gchavarro88/products_filePath/img/celos.jpg', 'Metal', 'Spark GT', 'ACGD12021988', 6, '2020-02-11 18:00:00-05', '2015-04-30 17:00:00-05', 1, 10, 15000, 1, 1, 250, 'Carros Chevrolet', 27, 21, 3, NULL, 12);
INSERT INTO sc_product_order (id_product_order, path_picture, type_material, mark, serie, id_packing, expiry_date, creation_date, id_priority, id_cost_center, value, id_money, id_location, manufacturing_time, description, id_product_dimension, id_order, amount_required, amount_produced, id_product_formulation) VALUES (16, '/home/gchavarro88/products_filePath/img/celos.jpg', 'Metal', 'Spark GT', 'ACGD12021988', 6, '2020-02-11 18:00:00-05', '2015-04-30 17:00:00-05', 1, 10, 15000, 1, 1, 250, 'Carros Chevrolet', 27, 22, 3, NULL, 12);
INSERT INTO sc_product_order (id_product_order, path_picture, type_material, mark, serie, id_packing, expiry_date, creation_date, id_priority, id_cost_center, value, id_money, id_location, manufacturing_time, description, id_product_dimension, id_order, amount_required, amount_produced, id_product_formulation) VALUES (17, '/home/gchavarro88/products_filePath/img/celos.jpg', 'Metal', 'Spark GT', 'ACGD12021988', 6, '2020-02-11 18:00:00-05', '2015-04-30 17:00:00-05', 1, 10, 15000, 1, 1, 250, 'Carros Chevrolet', 27, 23, 1, NULL, 12);
INSERT INTO sc_product_order (id_product_order, path_picture, type_material, mark, serie, id_packing, expiry_date, creation_date, id_priority, id_cost_center, value, id_money, id_location, manufacturing_time, description, id_product_dimension, id_order, amount_required, amount_produced, id_product_formulation) VALUES (18, '/home/gchavarro88/products_filePath/img/celos.jpg', 'Metal', 'Spark GT', 'ACGD12021988', 6, '2020-02-11 18:00:00-05', '2015-04-30 17:00:00-05', 1, 10, 15000, 1, 1, 250, 'Carros Chevrolet', 27, 24, 2, NULL, 12);


--
-- TOC entry 2926 (class 0 OID 305607)
-- Dependencies: 231
-- Data for Name: sc_production_state; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_production_state (id_production_state, description) VALUES (1, 'PENDIENTE');
INSERT INTO sc_production_state (id_production_state, description) VALUES (2, 'EN PROCESO');
INSERT INTO sc_production_state (id_production_state, description) VALUES (3, 'FINALIZADA');


--
-- TOC entry 2927 (class 0 OID 305610)
-- Dependencies: 232
-- Data for Name: sc_replacement; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_replacement (id_replacement, type_replacement, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_replacement_dimension, id_money, id_location, name, "time", value_minutes) VALUES (200, 'Repuesto', 2, 1, 'Pioner', 12500.00, '/home/guschaor/inputs_filePath/img/yo.jpg', 1, 'AC1211212', '2015-05-01 17:00:00-05', 'Ninguna', 2, 1, 2, 1, 2, 'Palanca 3X2', 2, 20160);
INSERT INTO sc_replacement (id_replacement, type_replacement, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_replacement_dimension, id_money, id_location, name, "time", value_minutes) VALUES (7, 'Consumible', 6565, 16, 'familia', 5656.00, ' ', 4, '5656', '2015-06-14 17:00:00-05', '', 34, 2, 46, 1, 10, 'papel lija', 2, 66175200);
INSERT INTO sc_replacement (id_replacement, type_replacement, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_replacement_dimension, id_money, id_location, name, "time", value_minutes) VALUES (8, 'Consumible', 4, 17, 'silicom', 20000.00, '/home/ubuntu/replacement_filePath/img/consumible1.jpg', 13, 'rt56', '2015-09-21 17:00:00-05', 'geg', 38, 1, 70, 3, 7, 'silicona', 3, 172800);
INSERT INTO sc_replacement (id_replacement, type_replacement, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_replacement_dimension, id_money, id_location, name, "time", value_minutes) VALUES (9, 'Consumible', 6, 20, 'induplast', 40000.00, '/home/ubuntu/replacement_filePath/img/insumo.jpg', 1, 'fgfg5', '2015-09-21 17:00:00-05', 'retety4', 39, 3, 71, 3, 12, 'cinta', 3, 259200);
INSERT INTO sc_replacement (id_replacement, type_replacement, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_replacement_dimension, id_money, id_location, name, "time", value_minutes) VALUES (6, 'Consumible', 67, 15, 'hjkjhk', 676.00, '/home/ubuntu/replacement_filePath/img/1451371_761042713962949_8212055255207949329_n.jpg', 4, '678878', '2015-05-08 17:00:00-05', '', 21, 2, 33, 4, 15, 'jhgjh', 2, 675360);


--
-- TOC entry 2928 (class 0 OID 305616)
-- Dependencies: 233
-- Data for Name: sc_replacement_attached; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_replacement_attached (id_replacement_attached, type, tittle, description, id_replacement) VALUES (1, 'd', 'dd', 'sds', 200);
INSERT INTO sc_replacement_attached (id_replacement_attached, type, tittle, description, id_replacement) VALUES (18, 'SPECIFICATION', 'tete', 'tete', 200);
INSERT INTO sc_replacement_attached (id_replacement_attached, type, tittle, description, id_replacement) VALUES (19, 'SPECIFICATION', 'ete', 'tetett', 200);
INSERT INTO sc_replacement_attached (id_replacement_attached, type, tittle, description, id_replacement) VALUES (20, 'FEACTURE', 'frf', 'frdfdf', 200);
INSERT INTO sc_replacement_attached (id_replacement_attached, type, tittle, description, id_replacement) VALUES (21, 'FEACTURE', 'gtres', 'gresd', 200);
INSERT INTO sc_replacement_attached (id_replacement_attached, type, tittle, description, id_replacement) VALUES (22, 'OBSERVATION', 'desde', 'desde', 200);
INSERT INTO sc_replacement_attached (id_replacement_attached, type, tittle, description, id_replacement) VALUES (23, 'OBSERVATION', 'desde', 'desde', 200);
INSERT INTO sc_replacement_attached (id_replacement_attached, type, tittle, description, id_replacement) VALUES (24, 'SPECIFICATION', 'tet', 'ete', 8);


--
-- TOC entry 2929 (class 0 OID 305622)
-- Dependencies: 234
-- Data for Name: sc_replacement_documents; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_replacement_documents (id_replacement_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_replacement) VALUES (5, '/home/guschaor/inputs_filePath/docs', 'desd', '2015-05-04 17:00:00-05', '1107046850.pdf', 'guschaor', 'application/pdf', 200);
INSERT INTO sc_replacement_documents (id_replacement_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_replacement) VALUES (6, '/home/guschaor/inputs_filePath/docs', 'grresess', '2015-05-04 17:00:00-05', '1107046850.pdf', 'guschaor', 'application/pdf', 200);
INSERT INTO sc_replacement_documents (id_replacement_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_replacement) VALUES (7, '/home/gchavarro88/inputs_filePath/docs', 'test', '2015-05-08 17:00:00-05', 'jsgaviota.pdf', 'jguerrero', 'application/pdf', 6);
INSERT INTO sc_replacement_documents (id_replacement_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_replacement) VALUES (8, '/home/gchavarro88/inputs_filePath/docs', 'titulo 2', '2015-05-08 17:00:00-05', 'jsgaviota - copia.pdf', 'jguerrero', 'application/pdf', 6);
INSERT INTO sc_replacement_documents (id_replacement_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_replacement) VALUES (9, '/home/gchavarro88/replacement_filePath/docs', 'cal2', '2015-06-14 17:00:00-05', 'GuiaCalidad.pdf', 'jguerrero', 'application/pdf', 6);
INSERT INTO sc_replacement_documents (id_replacement_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_replacement) VALUES (10, '/home/gchavarro88/replacement_filePath/docs', 'test', '2015-06-14 17:00:00-05', 'MANUAL DE CALIDAD FELIX DE BEDOUT.doc', 'jguerrero', 'application/octet-stream', 6);
INSERT INTO sc_replacement_documents (id_replacement_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_replacement) VALUES (11, '/home/gchavarro88/replacement_filePath/docs', 'calidad 3', '2015-06-14 17:00:00-05', 'GuiaCalidad.pdf', 'jguerrero', 'application/pdf', 6);
INSERT INTO sc_replacement_documents (id_replacement_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_replacement) VALUES (12, '/home/gchavarro88/replacement_filePath/docs', 'hhh', '2015-06-14 17:00:00-05', 'MANUAL DE CALIDAD FELIX DE BEDOUT.doc', 'jguerrero', 'application/octet-stream', 6);
INSERT INTO sc_replacement_documents (id_replacement_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_replacement) VALUES (13, '/home/gchavarro88/replacement_filePath/docs', 'uchita', '2015-06-14 17:00:00-05', 'Extracto_03012013_00078194.pdf', 'guschaor', 'application/pdf', 200);
INSERT INTO sc_replacement_documents (id_replacement_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_replacement) VALUES (14, '/home/ubuntu/replacement_filePath/docs', 'yuyu', '2015-09-21 17:00:00-05', '239-451-1-SM.pdf', 'yaconcha', 'application/pdf', 8);
INSERT INTO sc_replacement_documents (id_replacement_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_replacement) VALUES (15, '/home/ubuntu/replacement_filePath/docs', 'sf', '2015-09-21 17:00:00-05', '11-08-09 revision bd.docx', 'yaconcha', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 9);


--
-- TOC entry 2930 (class 0 OID 305628)
-- Dependencies: 235
-- Data for Name: sc_roles; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (3, 'INGENIERO DE PRODUCCIÓN', 'Encargado de la revisión y gestión de ordenes para su aprobación', '2014-10-12 17:00:00-05', '2014-10-12 17:00:00-05');
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (6, 'ARQUITECTO DE SOFTWARE', 'Encargado del diseño de componentes de la aplicación', '2014-10-12 17:00:00-05', '2014-10-12 17:00:00-05');
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (7, 'PRUEBAS', '', '2015-04-24 17:00:00-05', NULL);
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (9, 'CALIDAD', 'calidad', '2015-07-31 17:00:00-05', '2015-07-31 17:00:00-05');
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (10, 'OPERARIO', 'planta', '2015-08-10 17:00:00-05', NULL);
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (2, 'GESTIÓN HUMANA', 'Rol asignado al personal de selección y pruebas', '2014-10-12 17:00:00-05', '2015-08-14 17:00:00-05');
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (11, 'TESTING', 'pruebas', '2015-08-14 17:00:00-05', NULL);
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (1, 'ADMINISTRATOR', 'Grupo de permisos infinitos', '2014-09-25 17:00:00-05', '2015-12-12 14:23:34.593-05');
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (8, 'DESARROLLO', 'Perfil asociado a los desarrolladores de la aplicación ', '2015-07-28 17:00:00-05', '2016-04-27 23:57:16.772-05');


--
-- TOC entry 2931 (class 0 OID 305634)
-- Dependencies: 236
-- Data for Name: sc_services_or_products; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_services_or_products (id_service_or_products, name_service_or_product, cost, guarantee, description, amount, id_partner, type) VALUES (15, 'servicios de toma de dato', 20000000.00, '', 'nada', 2, 16, 'Servicio');
INSERT INTO sc_services_or_products (id_service_or_products, name_service_or_product, cost, guarantee, description, amount, id_partner, type) VALUES (16, 'caucho', 20000.00, 'toda', 'ninguna', 30, 15, 'Servicio');
INSERT INTO sc_services_or_products (id_service_or_products, name_service_or_product, cost, guarantee, description, amount, id_partner, type) VALUES (17, 'maquinaria pesada', 600000.00, '60 días hábiles ', 'pulidora durman de soldadura pit', 10, 17, 'Producto');
INSERT INTO sc_services_or_products (id_service_or_products, name_service_or_product, cost, guarantee, description, amount, id_partner, type) VALUES (18, 'pulidora', 200000.00, '2 años ', 'pulidora durman', 1, 18, 'Producto');
INSERT INTO sc_services_or_products (id_service_or_products, name_service_or_product, cost, guarantee, description, amount, id_partner, type) VALUES (19, 'cortadora manual', 50000.00, '8 meses', 'cortadora manual para metales de 4 cm de espesor', 3, 18, 'Producto');
INSERT INTO sc_services_or_products (id_service_or_products, name_service_or_product, cost, guarantee, description, amount, id_partner, type) VALUES (20, 'dfsdfsdf', 32423.00, '', '', 43434, 15, 'Producto');
INSERT INTO sc_services_or_products (id_service_or_products, name_service_or_product, cost, guarantee, description, amount, id_partner, type) VALUES (21, 'Prue 1', 50000.00, 'cccc', 'dddd', 4, 19, 'Producto');
INSERT INTO sc_services_or_products (id_service_or_products, name_service_or_product, cost, guarantee, description, amount, id_partner, type) VALUES (22, 'test', 1222.00, 'test', 'test', 12, 20, 'Producto');
INSERT INTO sc_services_or_products (id_service_or_products, name_service_or_product, cost, guarantee, description, amount, id_partner, type) VALUES (23, 'mouse', 200000.00, '', '', 20, 21, 'Servicio');


--
-- TOC entry 2932 (class 0 OID 305640)
-- Dependencies: 237
-- Data for Name: sc_stock; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (3, 12, 32, 23, 232, 2332, 2, 2332);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (20, 5555, 4, 44, 4, 176, 2, 44);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (21, 1000, 100, 500, 676, 338000, 3, 700);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (22, 1244444444, 4, 4, 222, 888, 2, 122);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (24, 20, 10, 40, 30000000, 1200000000, 2, 15);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (26, 3, 2, 2, 333, 666, 3, 2);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (32, 100, 10, 20, 565656, 11313120, 1, 20);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (6, 12, 11, 2, 4, 48, 1, 11);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (31, 500, 100, 206, 5645645, 1129129000, 2, 300);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (14, 515151, 12, 518, 1000, 511000, 2, 111);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (23, 15, 10, 38, 12000, 264000, 3, 12);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (30, 20, 10, 18, 12340, 123400, 5, 15);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (12, 30, 2, 12, 1223, 7338, 3, 3);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (7, 12, 2, 13, 1200, 3600, 1, 12);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (15, 100, 40, 63, 2000, 100000, 5, 60);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (33, 50, 5, 30, 455, 9100, 2, 10);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (34, 100, 10, 21, 5656, 113120, 1, 20);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (25, 167, 87, 110, 30000, 2670000, 1, 100);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (27, 20, 10, 42, 400000, 6000000, 2, 16);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (29, 6, 5, 38, 7000000, 35000000, 2, 5);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (35, 100, 30, 80, 2000000, 160000000, 3, 60);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (36, 200, 70, 100, 800000, 80000000, 3, 170);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (37, 200, 50, 144, 400000, 57600000, 2, 180);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (4, 10, 7, 6, 12323, 73938, 1, 8);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (38, 34, 10, 1, 20000, 20000, 3, 20);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (39, 10, 2, 1, 40000, 40000, 5, 6);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (13, 2, 1, 11, 33, 33, 2, 1);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (28, 100, 20, 658, 6000, 3600000, 2, 50);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (2, 4444, 1, 28, 15000, 275000, 1, 22);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (40, 5, 4, 4, 3, 12, 1, 4);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (41, 4, 3, 3, 3, 9, 2, 3);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (42, 600, 200, 1, 500, 500, 2, 350);


--
-- TOC entry 2933 (class 0 OID 305643)
-- Dependencies: 238
-- Data for Name: sc_stop_machine; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (52, 31, '2016-02-13 10:59:43.51-05', 'TESTESEST', 0, '2016-07-13 11:00:13-05', 0, 3, '1ns7af1gc1', '2016-02-13 11:01:12-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (53, NULL, '2016-02-13 11:00:59.485-05', 'pruebas', 0, NULL, NULL, 1, '0af4mj7yt0', NULL);
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (54, 37, '2016-02-13 17:30:38.449-05', 'pruebas', 0, '2016-07-13 17:59:26-05', 0, 3, '9ex8ad1pl6', '2016-02-13 12:57:18-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (55, 37, '2016-02-13 18:00:10.218-05', 'pruebs', 0, '2016-07-13 18:01:08-05', 0, 3, '1ts2ri4hc5', '2016-02-13 13:00:55-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (56, 37, '2016-02-13 18:06:45.984-05', 'preuba en conjunto', 0, '2016-07-13 18:19:09-05', 0, 3, '8pm6ck8tl0', '2016-02-13 13:12:22-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (57, 37, '2016-02-13 18:19:37.54-05', 'pruebas', 0, '2016-07-13 18:21:06-05', 0, 3, '7ia4fh8pt4', '2016-02-13 13:20:54-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (58, 37, '2016-02-13 18:25:33.26-05', 'prueba', 0, '2016-07-13 18:28:29-05', 0, 3, '0ch9br2yo3', '2016-02-13 13:28:16-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (59, 37, '2016-02-13 18:29:20.835-05', 'prueba', 0, '2016-07-13 18:29:56-05', 0, 3, '3yv3og3rh8', '2016-02-13 13:29:45-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (60, 37, '2016-02-13 18:30:26.728-05', 'prueba', 0, '2016-07-13 18:30:52-05', 0, 3, '6vo8go7bg6', '2016-02-13 13:30:42-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (61, 37, '2016-02-13 23:16:27.461-05', 'pruebas jhon', 0, '2016-08-13 23:21:01-05', 0, 3, '9pu3lm6if0', '2016-02-13 18:20:55-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (62, 37, '2016-02-13 23:22:50.486-05', 'pruebas', 0, '2016-08-13 23:24:47-05', 0, 3, '0lv8is1ka8', '2016-02-13 18:24:36-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (63, 37, '2016-02-13 23:25:35.677-05', 'pruebas', 0, '2016-08-13 23:27:03-05', 0, 3, '3qy9pq8vg9', '2016-02-13 18:26:54-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (64, 37, '2016-02-13 23:27:42.866-05', 'pruebas', 0, '2016-08-13 23:28:49-05', 0, 3, '7ia3um4sj3', '2016-02-13 18:28:36-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (65, 37, '2016-02-13 23:29:42.937-05', 'pruebas', 0, '2016-08-13 23:32:29-05', 0, 3, '3nh6yr0dt3', '2016-02-13 18:32:22-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (66, 37, '2016-02-13 23:34:37.14-05', 'pppp', 0, '2016-08-13 23:35:31-05', 0, 3, '6eq5fu1jo1', '2016-02-13 18:35:24-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (67, 37, '2016-02-13 23:52:15.292-05', 'prueba', 0, '2016-08-13 23:54:42-05', 0, 3, '0hb0hs4ih1', '2016-02-13 18:54:35-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (68, NULL, '2016-02-14 00:31:20.347-05', 'pruebas', 0, NULL, NULL, 1, '4cb8cf0nb2', NULL);
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (69, 36, '2016-02-14 10:00:38.822-05', 'despertar al ingeneiro a cargo, estamos con error ', 0, '2016-08-14 10:03:58-05', 0, 3, '8hn0ry5ds9', '2016-02-14 05:03:34-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (70, 36, '2016-02-14 10:08:23.816-05', 'pruebas', 0, '2016-08-14 10:10:19-05', 0, 3, '9xv7ah3gs1', '2016-02-14 05:10:04-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (71, 36, '2016-02-14 10:40:25.727-05', 'pruebas', 0, '2016-08-14 10:45:11-05', 0, 3, '9cc4bq8dk7', '2016-02-14 05:45:01-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (72, 36, '2016-02-14 10:45:40.253-05', 'prueba', 0, '2016-08-14 10:46:15-05', 0, 3, '3ne6ls3vs7', '2016-02-14 05:46:06-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (73, 36, '2016-02-14 10:48:43.824-05', 'pruebas
', 0, '2016-08-14 10:50:09-05', 0, 3, '2tm1xe5pr1', '2016-02-14 05:50:01-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (74, 36, '2016-02-14 10:50:39.818-05', 'pruebas', 0, '2016-08-14 11:57:42-05', 0, 3, '5pl8pt7uq2', '2016-02-14 06:57:18-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (75, 36, '2016-02-14 11:58:25.673-05', 'pruebas', 0, '2016-08-14 11:59:22-05', 0, 3, '9jo2qq3ia3', '2016-02-14 06:59:09-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (76, 36, '2016-02-14 12:00:14.445-05', 'prueba', 0, '2016-08-14 12:00:53-05', 0, 3, '3my9ta2sb9', '2016-02-14 07:00:42-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (77, 36, '2016-02-14 12:01:27.047-05', 'jhon', 0, '2016-08-14 12:02:03-05', 0, 3, '6od3qy9mb6', '2016-02-14 07:01:51-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (78, 36, '2016-02-14 12:04:29.428-05', 'pruebas', 0, '2016-08-14 12:05:09-05', 0, 3, '6qn8qt5iv5', '2016-02-14 07:04:55-05');
INSERT INTO sc_stop_machine (id_stop_machine, id_maintenance, creation_date, reason, duration_estimate, end_date, duration_real, state, password, response_date) VALUES (79, NULL, '2016-03-18 16:28:19.576-05', 'Falta insumos para la orden 258', 0, '2017-02-28 01:06:05-05', 0, 3, '8tu5gf9td2', '2016-03-28 01:05:42-05');


--
-- TOC entry 2934 (class 0 OID 305650)
-- Dependencies: 239
-- Data for Name: sc_store; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_store (id_store, name) VALUES (1, 'Almacen 1');
INSERT INTO sc_store (id_store, name) VALUES (2, 'Almacen 2');
INSERT INTO sc_store (id_store, name) VALUES (3, 'Almacen 3');
INSERT INTO sc_store (id_store, name) VALUES (4, 'Almacen 4');
INSERT INTO sc_store (id_store, name) VALUES (5, 'Almacen 5');


--
-- TOC entry 2935 (class 0 OID 305656)
-- Dependencies: 240
-- Data for Name: sc_store_order; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (42, 'Ingreso', 'Herramientas', 8, '2015-07-30 17:00:00-05', NULL, 'Almacén', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (48, 'Ingreso', 'Herramientas', 8, '2015-08-07 17:00:00-05', NULL, 'Almacén', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (50, 'Ingreso', 'Insumos', 8, '2015-08-07 17:00:00-05', NULL, 'Almacén', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (51, 'Ingreso', 'Repuestos y Consumibles', 8, '2015-08-07 17:00:00-05', NULL, 'Almacén', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (53, 'Ingreso', 'Herramientas', 8, '2015-08-07 17:00:00-05', NULL, 'Almacén', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (54, 'Ingreso', 'Herramientas', 8, '2015-08-07 17:00:00-05', NULL, 'Almacén', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (59, 'Ingreso', 'Insumos', 8, '2015-08-07 17:00:00-05', NULL, 'Almacén', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (60, 'Ingreso', 'Insumos', 8, '2015-08-07 17:00:00-05', NULL, 'Almacén', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (61, 'Ingreso', 'Herramientas', 8, '2015-08-07 17:00:00-05', NULL, 'Almacén', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (62, 'Ingreso', 'Herramientas', 8, '2015-08-07 17:00:00-05', NULL, 'Almacén', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (65, 'Ingreso', 'Herramientas', 8, '2015-08-07 17:00:00-05', NULL, 'Almacén', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (66, 'Ingreso', 'Herramientas', 8, '2015-08-07 17:00:00-05', NULL, 'Almacén', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (67, 'Ingreso', 'Repuestos y Consumibles', 8, '2015-08-07 17:00:00-05', NULL, 'Almacén', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (68, 'Ingreso', 'Herramientas', 8, '2015-08-07 17:00:00-05', NULL, 'Almacén', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (69, 'Ingreso', 'Repuestos y Consumibles', 8, '2015-08-07 17:00:00-05', NULL, 'Almacén', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (70, 'Ingreso', 'Herramientas', 8, '2015-08-07 17:00:00-05', NULL, 'Almacén', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (71, 'Ingreso', 'Herramientas', 8, '2015-08-07 17:00:00-05', NULL, 'Almacén', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (46, 'Entrega', 'Herramientas', 3, '2015-08-07 17:00:00-05', NULL, 'Mantenimiento', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (47, 'Entrega', 'Insumos', 3, '2015-08-07 17:00:00-05', NULL, 'Mantenimiento', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (72, 'Ingreso', 'Herramientas', 8, '2015-08-08 17:00:00-05', NULL, 'Almacén', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (74, 'Entrega', 'Productos', 4, '2015-08-10 17:00:00-05', NULL, 'Mantenimiento', 1, 12, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (75, 'Ingreso', 'Herramientas', 8, '2015-08-10 17:00:00-05', NULL, 'Almacén', 3, 12, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (76, 'Ingreso', 'Insumos', 8, '2015-10-12 17:00:00-05', NULL, 'Almacén', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (77, 'Ingreso', 'Herramientas', 8, '2015-10-13 17:00:00-05', NULL, 'Almacén', 1, 8, NULL, NULL);
INSERT INTO sc_store_order (id_store_order, order_type, order_class, id_state, creation_date, reason_cancellation, required_by, amount_items, id_employee_create, id_employee_store, id_order_request) VALUES (78, 'Entrega', 'Herramientas', 3, '2015-10-13 17:00:00-05', NULL, 'Mantenimiento', 1, 8, NULL, 'OM201212121');


--
-- TOC entry 2936 (class 0 OID 305662)
-- Dependencies: 241
-- Data for Name: sc_store_order_item; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (27, 'Herramientas', 0, 10, 0, 0, 'Destonillador 2', 42, 7, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (29, 'Insumos', 5, 0, 0, 5, 'Prueba de Insumo', 47, 13, 5);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (30, 'Herramientas', 0, 11, 0, 0, 'Destonillador 2', 48, 7, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (31, 'Insumos', 0, 7, 0, 0, 'cercha', 50, 16, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (32, 'Repuestos y Consumibles', 0, 6, 0, 0, 'Palanca 3X2', 51, 200, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (33, 'Herramientas', 0, 8, 0, 0, 'llave', 53, 9, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (34, 'Herramientas', 0, 2, 0, 0, 'cargador', 54, 4, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (35, 'Insumos', 0, 1, 0, 0, 'Prueba de Insumo', 59, 8, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (36, 'Insumos', 0, 2, 0, 0, 'cercha', 60, 16, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (37, 'Herramientas', 0, 2, 0, 0, 'cinta', 61, 6, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (38, 'Herramientas', 0, 1, 0, 0, 'Destonillador 2', 62, 7, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (39, 'Herramientas', 0, 1, 0, 0, 'cinta', 65, 6, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (40, 'Herramientas', 0, 1, 0, 0, 'cargador', 66, 4, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (41, 'Repuestos y Consumibles', 0, 1, 0, 0, 'papel lija', 67, 7, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (42, 'Herramientas', 0, 2, 0, 0, 'Destonillador 2', 68, 7, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (43, 'Repuestos y Consumibles', 0, 2, 0, 0, 'Palanca 3X2', 69, 200, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (44, 'Herramientas', 0, 2, 0, 0, 'equipo terminal', 70, 8, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (45, 'Herramientas', 0, 2, 0, 0, 'equipo terminal', 71, 8, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (46, 'Herramientas', 0, 4, 0, 0, 'Destonillador 2', 72, 7, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (47, 'Productos', 2, 2, 30, 0, 'Carros Chevrolet', 74, 12, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (48, 'Herramientas', 0, 3, 0, 0, 'cargador', 75, 4, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (49, 'Herramientas', 0, 3, 0, 0, 'cinta', 75, 6, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (50, 'Herramientas', 0, 3, 0, 0, 'equipo terminal', 75, 8, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (51, 'Insumos', 0, 5, 0, 0, 'Prueba Jhon', 76, 14, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (52, 'Herramientas', 0, 10, 0, 0, 'Destonillador 2', 77, 7, 0);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (28, 'Herramientas', 6, 4, 110, 2, 'cargador', 46, 4, 6);
INSERT INTO sc_store_order_item (id_item, class_item, amount_required, amount_delivery, amount_store, amount_pending, item_description, id_store_order, id_item_class, amount_pending_hidden) VALUES (53, 'Herramientas', 8, 0, 0, 8, 'llave', 78, 9, 8);


--
-- TOC entry 2937 (class 0 OID 305665)
-- Dependencies: 242
-- Data for Name: sc_store_order_state; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_store_order_state (id_state, description) VALUES (1, 'Programada');
INSERT INTO sc_store_order_state (id_state, description) VALUES (2, 'En Proceso');
INSERT INTO sc_store_order_state (id_state, description) VALUES (3, 'Atrasada');
INSERT INTO sc_store_order_state (id_state, description) VALUES (4, 'Entregada');
INSERT INTO sc_store_order_state (id_state, description) VALUES (5, 'Cancelada por el Almacén');
INSERT INTO sc_store_order_state (id_state, description) VALUES (6, 'Cancelada por Producción');
INSERT INTO sc_store_order_state (id_state, description) VALUES (7, 'Cancelada por Mantenimiento');
INSERT INTO sc_store_order_state (id_state, description) VALUES (8, 'Recibido');


--
-- TOC entry 2938 (class 0 OID 305668)
-- Dependencies: 243
-- Data for Name: sc_store_requisition; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2939 (class 0 OID 305674)
-- Dependencies: 244
-- Data for Name: sc_store_requisition_item; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2940 (class 0 OID 305677)
-- Dependencies: 245
-- Data for Name: sc_store_requisition_state; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2941 (class 0 OID 305680)
-- Dependencies: 246
-- Data for Name: sc_time; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_time (id_time, acronym, minutes) VALUES (1, 'Dia(s)', 1440);
INSERT INTO sc_time (id_time, acronym, minutes) VALUES (2, 'Semana(s)', 10080);
INSERT INTO sc_time (id_time, acronym, minutes) VALUES (3, 'Mes(es)', 43200);
INSERT INTO sc_time (id_time, acronym, minutes) VALUES (4, 'Año(s)', 525600);


--
-- TOC entry 2942 (class 0 OID 305683)
-- Dependencies: 247
-- Data for Name: sc_tool; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_tool (id_tool, type_tool, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_tool_dimension, id_money, id_location, name, "time", value_minutes) VALUES (2, 'Mecánica', 120, 1, 'martillos y martillos', 12000.00, '/home/gchavarro88/inputs_filePath/img/desorientado.jpg', 12, 'mart123', '2015-05-13 17:00:00-05', 'hola', 23, 2, 35, 3, 7, 'martillo', 2, 1209600);
INSERT INTO sc_tool (id_tool, type_tool, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_tool_dimension, id_money, id_location, name, "time", value_minutes) VALUES (4, 'Limpieza', 30, 16, 'samsung', 30000.00, '/home/gchavarro88/inputs_filePath/img/banda peligrp.jpg', 2, 'cem-80', '2015-05-29 17:00:00-05', 'solo para celulares samsung duos', 25, 1, 37, 3, 10, 'cargador', 3, 1296000);
INSERT INTO sc_tool (id_tool, type_tool, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_tool_dimension, id_money, id_location, name, "time", value_minutes) VALUES (5, 'Administrativa', 33, 15, '32232', 333.00, '/home/gchavarro88/inputs_filePath/img/producto1.jpg', 1, '3w3we', '2015-05-29 17:00:00-05', 'desde', 26, 1, 38, 4, 7, 'Yoelidy', 2, 332640);
INSERT INTO sc_tool (id_tool, type_tool, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_tool_dimension, id_money, id_location, name, "time", value_minutes) VALUES (6, 'Mecánica', 65, 1, 'cito', 400000.00, '/home/gchavarro88/inputs_filePath/img/banda peligrp.jpg', 4, 'mn455-98', '2015-05-29 17:00:00-05', 'rfhsdsfzh', 27, 3, 39, 3, 6, 'cinta', 3, 2808000);
INSERT INTO sc_tool (id_tool, type_tool, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_tool_dimension, id_money, id_location, name, "time", value_minutes) VALUES (7, 'Limpieza', 500, 1, 'gato', 6000.00, '/home/gchavarro88/inputs_filePath/img/producto1.png', 1, '454544556', '2015-05-29 17:00:00-05', '', 28, 1, 40, 1, 2, 'Destonillador 2', 2, 5040000);
INSERT INTO sc_tool (id_tool, type_tool, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_tool_dimension, id_money, id_location, name, "time", value_minutes) VALUES (3, 'Eléctrica', 98, 16, 'complex', 30000000.00, '/home/gchavarro88/inputs_filePath/img/1796433_10153244835662971_6352760795065761361_n.jpg', 3, 'plccomplex', '2015-05-13 17:00:00-05', '', 24, 2, 36, 4, 6, 'plc', 2, 987840);
INSERT INTO sc_tool (id_tool, type_tool, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_tool_dimension, id_money, id_location, name, "time", value_minutes) VALUES (8, 'Eléctrica', 5, 16, 'sip ingenieria ', 7000000.00, '/home/gchavarro88/inputs_filePath/img/desorientado.jpg', 3, 'sip001', '2015-06-03 17:00:00-05', 'hiu', 29, 1, 41, 1, 6, 'equipo terminal', 4, 2628000);
INSERT INTO sc_tool (id_tool, type_tool, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_tool_dimension, id_money, id_location, name, "time", value_minutes) VALUES (9, 'Eléctrica', 43, 16, 'termos y termos', 12340.00, '/home/gchavarro88/inputs_filePath/img/termo.jpg', 12, 're43', '2015-06-03 17:00:00-05', 'dwfsd', 30, 3, 42, 1, 12, 'llave', 2, 433440);
INSERT INTO sc_tool (id_tool, type_tool, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_tool_dimension, id_money, id_location, name, "time", value_minutes) VALUES (10, 'Eléctrica', 5656, 1, 'gato', 5645645.00, '/home/gchavarro88/inputs_filePath/img/producto3.png', 3, '564546456', '2015-06-05 17:00:00-05', '', 31, 1, 43, 4, 6, 'Martillo de tor', 1, 8144640);
INSERT INTO sc_tool (id_tool, type_tool, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_tool_dimension, id_money, id_location, name, "time", value_minutes) VALUES (11, 'Eléctrica', 64665, 16, 'gato', 565656.00, '/home/gchavarro88/tools_filePath/img/producto2.jpg', 4, '5656mlñj', '2015-06-14 17:00:00-05', '', 32, 1, 44, 1, 5, 'Alicate de Tor', 2, 651823200);
INSERT INTO sc_tool (id_tool, type_tool, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_tool_dimension, id_money, id_location, name, "time", value_minutes) VALUES (12, 'Eléctrica', 54545, 16, 'gato', 455.00, '/home/gchavarro88/tools_filePath/img/producto1.png', 4, '4545', '2015-06-14 17:00:00-05', '', 33, 1, 45, 1, 6, 'Alicate montañero', 2, 549813600);
INSERT INTO sc_tool (id_tool, type_tool, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_tool_dimension, id_money, id_location, name, "time", value_minutes) VALUES (13, 'Eléctrica', 2, 16, 'bosch', 2000000.00, ' ', 1, 'mi897rd', '2015-08-11 17:00:00-05', '', 35, 1, 55, 3, 13, 'pilidora', 4, 1051200);
INSERT INTO sc_tool (id_tool, type_tool, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_tool_dimension, id_money, id_location, name, "time", value_minutes) VALUES (1, 'Mecánica', 122, 15, 'test', 222.00, '/home/ubuntu/tools_filePath/img/flecha oee.jpg', 4, 'test', '2015-05-12 17:00:00-05', 'test', 22, 1, 34, 3, 6, 'test', 1, 175680);


--
-- TOC entry 2943 (class 0 OID 305689)
-- Dependencies: 248
-- Data for Name: sc_tool_attached; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_tool_attached (id_tool_attached, type, tittle, description, id_tool) VALUES (1, 'SPECIFICATION', 'dsfdfsd', 'test', 1);
INSERT INTO sc_tool_attached (id_tool_attached, type, tittle, description, id_tool) VALUES (2, 'SPECIFICATION', 'test', 'test', 1);
INSERT INTO sc_tool_attached (id_tool_attached, type, tittle, description, id_tool) VALUES (3, 'FEACTURE', 'tets', 'tests', 1);
INSERT INTO sc_tool_attached (id_tool_attached, type, tittle, description, id_tool) VALUES (4, 'OBSERVATION', 'ddd', 'ddd', 1);
INSERT INTO sc_tool_attached (id_tool_attached, type, tittle, description, id_tool) VALUES (5, 'SPECIFICATION', 'almacenado', '1. no dejar en zona humeda.
2. voltaje mayor a 220v
3. cable intercambiable
4. uso de guantes.
5. patillas blandas', 4);
INSERT INTO sc_tool_attached (id_tool_attached, type, tittle, description, id_tool) VALUES (6, 'FEACTURE', 'color', '1. hay color negro, el cual es usado para bajo voltaje.
2. el color gris, es usado para alto voltaje', 4);
INSERT INTO sc_tool_attached (id_tool_attached, type, tittle, description, id_tool) VALUES (7, 'OBSERVATION', 'tamaño', 'pequeño para mayor versatilidad ', 4);
INSERT INTO sc_tool_attached (id_tool_attached, type, tittle, description, id_tool) VALUES (8, 'SPECIFICATION', 'voltaje ', 'conectar a una linea 220v', 13);
INSERT INTO sc_tool_attached (id_tool_attached, type, tittle, description, id_tool) VALUES (9, 'FEACTURE', 'pulido fino', 'discos de lija delgada', 13);
INSERT INTO sc_tool_attached (id_tool_attached, type, tittle, description, id_tool) VALUES (10, 'OBSERVATION', 'uso de gafas', 'para la manipulación de esta herramienta se debe usar gafas como medida de seguridad', 13);


--
-- TOC entry 2944 (class 0 OID 305695)
-- Dependencies: 249
-- Data for Name: sc_tool_documents; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (1, '/home/gchavarro88/inputs_filePath/docs', 'test', '2015-05-12 17:00:00-05', '126575463-Heinrich-Boll-Opiniones-de-un-payaso-pdf.pdf', 'guschaor', 'application/pdf', 1);
INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (2, '/home/gchavarro88/inputs_filePath/docs', '1qw', '2015-05-13 17:00:00-05', '4437-22933-1-PB.pdf', 'yaconcha', 'application/pdf', 2);
INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (3, '/home/gchavarro88/inputs_filePath/docs', 'habalr', '2015-05-13 17:00:00-05', 'Plan de Trabajo Modalides version 1-2012 (1).docx', 'yaconcha', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 2);
INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (4, '/home/gchavarro88/inputs_filePath/docs', 'conyrol', '2015-05-13 17:00:00-05', '11-08-09 revision bd.docx', 'yaconcha', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 3);
INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (5, '/home/gchavarro88/inputs_filePath/docs', 'tarea1', '2015-05-29 17:00:00-05', 'Actividad de Aprendizaje unidad 1 Introduccion a los Sistemas de Gestion de la Calidad.docx', 'yaconcha', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 4);
INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (6, '/home/gchavarro88/inputs_filePath/docs', 'tarea3', '2015-05-29 17:00:00-05', 'Actividad de Aprendizaje unidad 3 Requisitos e Interpretacion de la Norma ISO 90012008.docx', 'yaconcha', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 4);
INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (7, '/home/gchavarro88/inputs_filePath/docs', 'tarea4', '2015-05-29 17:00:00-05', 'Actividad de Aprendizaje unidad 4 Calidad Enfocada al Cliente.docx', 'yaconcha', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 4);
INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (8, '/home/gchavarro88/inputs_filePath/docs', 'prueba', '2015-05-29 17:00:00-05', 'GuiaCalidad.pdf', 'jguerrero', 'application/pdf', 1);
INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (9, '/home/gchavarro88/inputs_filePath/docs', 'titulo 2', '2015-05-29 17:00:00-05', 'MANUAL DE CALIDAD FELIX DE BEDOUT.doc', 'jguerrero', 'application/octet-stream', 1);
INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (10, '/home/gchavarro88/inputs_filePath/docs', 'mn', '2015-05-29 17:00:00-05', '4437-22933-1-PB.pdf', 'yaconcha', 'application/pdf', 6);
INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (11, '/home/gchavarro88/inputs_filePath/docs', 'mn', '2015-05-29 17:00:00-05', '4437-22933-1-PB.pdf', 'yaconcha', 'application/pdf', 6);
INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (12, '/home/gchavarro88/inputs_filePath/docs', '1', '2015-06-03 17:00:00-05', '4437-22933-1-PB.pdf', 'yaconcha', 'application/pdf', 8);
INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (13, '/home/gchavarro88/inputs_filePath/docs', '2', '2015-06-03 17:00:00-05', 'taks 1.docx', 'yaconcha', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 8);
INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (14, '/home/gchavarro88/inputs_filePath/docs', '3', '2015-06-03 17:00:00-05', 'nuevasestrategiascoemrciales.docx', 'yaconcha', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 8);
INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (15, '/home/gchavarro88/inputs_filePath/docs', '5', '2015-06-03 17:00:00-05', '4437-22933-1-PB.pdf', 'yaconcha', 'application/pdf', 9);
INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (16, '/home/gchavarro88/inputs_filePath/docs', '9', '2015-06-03 17:00:00-05', 'quality herremientas.docx', 'yaconcha', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 9);
INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (17, '/home/gchavarro88/tools_filePath/docs', 'manual', '2015-08-11 17:00:00-05', '23-08-09 revision bd1.docx', 'guschaor', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 13);


--
-- TOC entry 2945 (class 0 OID 305701)
-- Dependencies: 250
-- Data for Name: sc_turn; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_turn (id_turn, description, hour_amount) VALUES (1, 'Normal', 8);
INSERT INTO sc_turn (id_turn, description, hour_amount) VALUES (2, 'Lunes a Viernes', 9);
INSERT INTO sc_turn (id_turn, description, hour_amount) VALUES (3, 'Sabatino', 3);
INSERT INTO sc_turn (id_turn, description, hour_amount) VALUES (4, 'Ninguno', 0);


--
-- TOC entry 2946 (class 0 OID 305704)
-- Dependencies: 251
-- Data for Name: sc_type; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_type (id_type, type, id_class_type, creation_date, modify_date) VALUES (1, 'Eléctrica', 1, '2014-12-14', '2014-12-14');
INSERT INTO sc_type (id_type, type, id_class_type, creation_date, modify_date) VALUES (2, 'Hidráulica', 1, '2014-12-14', '2014-12-14');
INSERT INTO sc_type (id_type, type, id_class_type, creation_date, modify_date) VALUES (3, 'Alta', 2, '2014-12-14', '2014-12-14');
INSERT INTO sc_type (id_type, type, id_class_type, creation_date, modify_date) VALUES (4, 'Media', 2, '2014-12-14', '2014-12-14');
INSERT INTO sc_type (id_type, type, id_class_type, creation_date, modify_date) VALUES (5, 'Baja', 2, '2014-12-14', '2014-12-14');
INSERT INTO sc_type (id_type, type, id_class_type, creation_date, modify_date) VALUES (6, 'Dias', 3, '2014-12-14', '2014-12-14');
INSERT INTO sc_type (id_type, type, id_class_type, creation_date, modify_date) VALUES (7, 'Meses', 3, '2014-12-14', '2014-12-14');
INSERT INTO sc_type (id_type, type, id_class_type, creation_date, modify_date) VALUES (8, 'Años', 3, '2014-12-14', '2014-12-14');
INSERT INTO sc_type (id_type, type, id_class_type, creation_date, modify_date) VALUES (9, 'Pesos', 4, '2014-12-14', '2014-12-14');
INSERT INTO sc_type (id_type, type, id_class_type, creation_date, modify_date) VALUES (10, 'Dolares', 4, '2014-12-14', '2014-12-14');
INSERT INTO sc_type (id_type, type, id_class_type, creation_date, modify_date) VALUES (11, 'Euros', 4, '2014-12-14', '2014-12-14');


--
-- TOC entry 2947 (class 0 OID 305707)
-- Dependencies: 252
-- Data for Name: sc_users; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (17, 13, 1, 'lisgirlo', '46e435b6e98cec728f5be5d4dbd97ffb', '2015-03-14 18:00:00-05', NULL);
INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (22, 20, 8, 'jlober', 'eefa0ea006378d7d50f3310d59d93820', '2015-07-28 17:00:00-05', '2015-08-14 17:00:00-05');
INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (19, 22, 1, 'jguerrero', '7453a4cfb3d3db9f6d477e5d2d87c4be', '2015-04-17 17:00:00-05', '2015-08-14 17:00:00-05');
INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (1, 1, 8, 'guschaor', '4e991c769a2b9a881189cd86c160b604', '2014-07-25 17:00:00-05', '2015-08-14 17:00:00-05');
INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (4, 3, 8, 'yaconcha', '0525484994f3e8f42ba38c49930e356a', '2014-10-20 17:00:00-05', '2015-12-12 14:32:02.39-05');
INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (24, 37, 9, 'jhon', 'a2d8ff3c6be4264eadf9400b1e47ec93', '2015-12-14 07:33:38.81-05', NULL);


--
-- TOC entry 2948 (class 0 OID 305710)
-- Dependencies: 253
-- Data for Name: sc_work_experience; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_work_experience (id_work_experience, init_date, end_date, id_employee, company_name) VALUES (9, '2014-11-08 18:00:00-05', '2014-11-10 18:00:00-05', 8, 'Ip total');
INSERT INTO sc_work_experience (id_work_experience, init_date, end_date, id_employee, company_name) VALUES (10, '2015-04-01 17:00:00-05', '2015-04-29 17:00:00-05', 9, 'Tecnoquimicas');
INSERT INTO sc_work_experience (id_work_experience, init_date, end_date, id_employee, company_name) VALUES (11, '2012-03-03 18:00:00-05', '2015-08-12 17:00:00-05', 15, 'durmax');
INSERT INTO sc_work_experience (id_work_experience, init_date, end_date, id_employee, company_name) VALUES (12, '2013-11-02 18:00:00-05', '2015-09-10 17:00:00-05', 16, 'dimel');


--
-- TOC entry 2949 (class 0 OID 305713)
-- Dependencies: 254
-- Data for Name: sc_workforce; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_workforce (id_workforce, workforce, id_employee, type_workforce) VALUES (21, '', 9, 'Eléctrico');
INSERT INTO sc_workforce (id_workforce, workforce, id_employee, type_workforce) VALUES (20, '', 8, 'Mecánico');
INSERT INTO sc_workforce (id_workforce, workforce, id_employee, type_workforce) VALUES (22, '', 8, 'Electrónico');
INSERT INTO sc_workforce (id_workforce, workforce, id_employee, type_workforce) VALUES (23, '', 10, 'Electrónico');
INSERT INTO sc_workforce (id_workforce, workforce, id_employee, type_workforce) VALUES (24, '', 12, 'Electrónico');
INSERT INTO sc_workforce (id_workforce, workforce, id_employee, type_workforce) VALUES (25, '', 10, 'Eléctrico');
INSERT INTO sc_workforce (id_workforce, workforce, id_employee, type_workforce) VALUES (26, '', 15, 'Eléctrico');
INSERT INTO sc_workforce (id_workforce, workforce, id_employee, type_workforce) VALUES (27, '', 8, 'Mecánico');
INSERT INTO sc_workforce (id_workforce, workforce, id_employee, type_workforce) VALUES (28, '', 12, 'Eléctrico');
INSERT INTO sc_workforce (id_workforce, workforce, id_employee, type_workforce) VALUES (29, 'test', 12, 'Mecánico');
INSERT INTO sc_workforce (id_workforce, workforce, id_employee, type_workforce) VALUES (30, '', 13, 'Mecánico');
INSERT INTO sc_workforce (id_workforce, workforce, id_employee, type_workforce) VALUES (31, '', 9, 'Electrónico');
INSERT INTO sc_workforce (id_workforce, workforce, id_employee, type_workforce) VALUES (32, '', 8, 'Mecánico');
INSERT INTO sc_workforce (id_workforce, workforce, id_employee, type_workforce) VALUES (33, 'test', 13, 'Mecánico');
INSERT INTO sc_workforce (id_workforce, workforce, id_employee, type_workforce) VALUES (34, 'test', 15, 'Eléctrico');
INSERT INTO sc_workforce (id_workforce, workforce, id_employee, type_workforce) VALUES (35, 'test', 15, 'Mecánico');


--
-- TOC entry 3041 (class 0 OID 0)
-- Dependencies: 255
-- Name: sqclasstype; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqclasstype', 1, false);


--
-- TOC entry 3042 (class 0 OID 0)
-- Dependencies: 256
-- Name: sqmaintenanceschedule; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqmaintenanceschedule', 61, true);


--
-- TOC entry 3043 (class 0 OID 0)
-- Dependencies: 337
-- Name: sqotlogproduction; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqotlogproduction', 5, true);


--
-- TOC entry 3044 (class 0 OID 0)
-- Dependencies: 257
-- Name: sqotmaintenance; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqotmaintenance', 43, true);


--
-- TOC entry 3045 (class 0 OID 0)
-- Dependencies: 258
-- Name: sqotmaintenancecorrective; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqotmaintenancecorrective', 27, true);


--
-- TOC entry 3046 (class 0 OID 0)
-- Dependencies: 259
-- Name: sqotmaintenancepreventive; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqotmaintenancepreventive', 8, true);


--
-- TOC entry 3047 (class 0 OID 0)
-- Dependencies: 260
-- Name: sqotproductionorder; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqotproductionorder', 24, true);


--
-- TOC entry 3048 (class 0 OID 0)
-- Dependencies: 261
-- Name: sqotproductionproduct; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqotproductionproduct', 17, true);


--
-- TOC entry 3049 (class 0 OID 0)
-- Dependencies: 262
-- Name: sqsccompetencies; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqsccompetencies', 9, true);


--
-- TOC entry 3050 (class 0 OID 0)
-- Dependencies: 263
-- Name: sqsccostcenter; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqsccostcenter', 19, true);


--
-- TOC entry 3051 (class 0 OID 0)
-- Dependencies: 264
-- Name: sqscdistributionunit; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscdistributionunit', 4, true);


--
-- TOC entry 3052 (class 0 OID 0)
-- Dependencies: 265
-- Name: sqscdocuments; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscdocuments', 36, true);


--
-- TOC entry 3053 (class 0 OID 0)
-- Dependencies: 266
-- Name: sqscemployee; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscemployee', 18, true);


--
-- TOC entry 3054 (class 0 OID 0)
-- Dependencies: 267
-- Name: sqscfactorylocation; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscfactorylocation', 7, false);


--
-- TOC entry 3055 (class 0 OID 0)
-- Dependencies: 268
-- Name: sqscgroup; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscgroup', 1, false);


--
-- TOC entry 3056 (class 0 OID 0)
-- Dependencies: 269
-- Name: sqscinput; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinput', 18, true);


--
-- TOC entry 3057 (class 0 OID 0)
-- Dependencies: 270
-- Name: sqscinputdimension; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinputdimension', 74, true);


--
-- TOC entry 3058 (class 0 OID 0)
-- Dependencies: 271
-- Name: sqscinputdocuments; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinputdocuments', 13, true);


--
-- TOC entry 3059 (class 0 OID 0)
-- Dependencies: 272
-- Name: sqscinputequivalence; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinputequivalence', 1, false);


--
-- TOC entry 3060 (class 0 OID 0)
-- Dependencies: 273
-- Name: sqscinputfeature; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinputfeature', 17, true);


--
-- TOC entry 3061 (class 0 OID 0)
-- Dependencies: 274
-- Name: sqscinputobservation; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinputobservation', 11, true);


--
-- TOC entry 3062 (class 0 OID 0)
-- Dependencies: 275
-- Name: sqscinputspecification; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinputspecification', 20, true);


--
-- TOC entry 3063 (class 0 OID 0)
-- Dependencies: 276
-- Name: sqsclocation; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqsclocation', 18, true);


--
-- TOC entry 3064 (class 0 OID 0)
-- Dependencies: 277
-- Name: sqscmachine; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmachine', 14, true);


--
-- TOC entry 3065 (class 0 OID 0)
-- Dependencies: 278
-- Name: sqscmachineattached; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmachineattached', 22, true);


--
-- TOC entry 3066 (class 0 OID 0)
-- Dependencies: 279
-- Name: sqscmachineconditions; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmachineconditions', 2, true);


--
-- TOC entry 3067 (class 0 OID 0)
-- Dependencies: 280
-- Name: sqscmachinedocument; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmachinedocument', 7, true);


--
-- TOC entry 3068 (class 0 OID 0)
-- Dependencies: 281
-- Name: sqscmachinepart; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmachinepart', 18, true);


--
-- TOC entry 3069 (class 0 OID 0)
-- Dependencies: 282
-- Name: sqscmachinepartattached; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmachinepartattached', 34, true);


--
-- TOC entry 3070 (class 0 OID 0)
-- Dependencies: 283
-- Name: sqscmachinepartdocument; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmachinepartdocument', 16, true);


--
-- TOC entry 3071 (class 0 OID 0)
-- Dependencies: 284
-- Name: sqscmails; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmails', 39, true);


--
-- TOC entry 3072 (class 0 OID 0)
-- Dependencies: 285
-- Name: sqscmaintenanceactivity; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmaintenanceactivity', 44, true);


--
-- TOC entry 3073 (class 0 OID 0)
-- Dependencies: 286
-- Name: sqscmaintenanceplan; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmaintenanceplan', 1, false);


--
-- TOC entry 3074 (class 0 OID 0)
-- Dependencies: 287
-- Name: sqscmaintenancereplacement; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmaintenancereplacement', 1, false);


--
-- TOC entry 3075 (class 0 OID 0)
-- Dependencies: 288
-- Name: sqscmaintenancetool; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmaintenancetool', 1, false);


--
-- TOC entry 3076 (class 0 OID 0)
-- Dependencies: 289
-- Name: sqscmeasure; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmeasure', 31, true);


--
-- TOC entry 3077 (class 0 OID 0)
-- Dependencies: 290
-- Name: sqscmodulespermissionbyrole; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmodulespermissionbyrole', 2207, true);


--
-- TOC entry 3078 (class 0 OID 0)
-- Dependencies: 291
-- Name: sqscmoney; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmoney', 1, false);


--
-- TOC entry 3079 (class 0 OID 0)
-- Dependencies: 292
-- Name: sqscnotification; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscnotification', 67, true);


--
-- TOC entry 3080 (class 0 OID 0)
-- Dependencies: 293
-- Name: sqscoperatingconditions; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscoperatingconditions', 1, false);


--
-- TOC entry 3081 (class 0 OID 0)
-- Dependencies: 294
-- Name: sqscpackingunit; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpackingunit', 7, true);


--
-- TOC entry 3082 (class 0 OID 0)
-- Dependencies: 295
-- Name: sqscpartners; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpartners', 21, true);


--
-- TOC entry 3083 (class 0 OID 0)
-- Dependencies: 296
-- Name: sqscpartsandconsumables; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpartsandconsumables', 1, false);


--
-- TOC entry 3084 (class 0 OID 0)
-- Dependencies: 297
-- Name: sqscpersonbygroup; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpersonbygroup', 1, false);


--
-- TOC entry 3085 (class 0 OID 0)
-- Dependencies: 298
-- Name: sqscpersondocumentationattached; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpersondocumentationattached', 6, false);


--
-- TOC entry 3086 (class 0 OID 0)
-- Dependencies: 299
-- Name: sqscpersonobservations; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpersonobservations', 19, true);


--
-- TOC entry 3087 (class 0 OID 0)
-- Dependencies: 300
-- Name: sqscpersons; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpersons', 38, true);


--
-- TOC entry 3088 (class 0 OID 0)
-- Dependencies: 301
-- Name: sqscpersonspecifications; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpersonspecifications', 18, true);


--
-- TOC entry 3089 (class 0 OID 0)
-- Dependencies: 302
-- Name: sqscphones; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscphones', 34, true);


--
-- TOC entry 3090 (class 0 OID 0)
-- Dependencies: 303
-- Name: sqscphoto; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscphoto', 1, false);


--
-- TOC entry 3091 (class 0 OID 0)
-- Dependencies: 304
-- Name: sqscprocessattached; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocessattached', 1, false);


--
-- TOC entry 3092 (class 0 OID 0)
-- Dependencies: 305
-- Name: sqscprocessemployee; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocessemployee', 42, true);


--
-- TOC entry 3093 (class 0 OID 0)
-- Dependencies: 306
-- Name: sqscprocessemployeeorder; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocessemployeeorder', 39, true);


--
-- TOC entry 3094 (class 0 OID 0)
-- Dependencies: 307
-- Name: sqscprocessinput; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocessinput', 39, true);


--
-- TOC entry 3095 (class 0 OID 0)
-- Dependencies: 308
-- Name: sqscprocessinputorder; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocessinputorder', 36, true);


--
-- TOC entry 3096 (class 0 OID 0)
-- Dependencies: 309
-- Name: sqscprocessmachine; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocessmachine', 43, true);


--
-- TOC entry 3097 (class 0 OID 0)
-- Dependencies: 310
-- Name: sqscprocessmachineorder; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocessmachineorder', 45, true);


--
-- TOC entry 3098 (class 0 OID 0)
-- Dependencies: 311
-- Name: sqscprocessproduct; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocessproduct', 35, true);


--
-- TOC entry 3099 (class 0 OID 0)
-- Dependencies: 312
-- Name: sqscprocessproductorder; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocessproductorder', 31, true);


--
-- TOC entry 3100 (class 0 OID 0)
-- Dependencies: 313
-- Name: sqscprocesstype; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocesstype', 1, false);


--
-- TOC entry 3101 (class 0 OID 0)
-- Dependencies: 314
-- Name: sqscproductattached; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscproductattached', 48, true);


--
-- TOC entry 3102 (class 0 OID 0)
-- Dependencies: 315
-- Name: sqscproductdocuments; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscproductdocuments', 13, true);


--
-- TOC entry 3103 (class 0 OID 0)
-- Dependencies: 316
-- Name: sqscproductformulation; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscproductformulation', 15, true);


--
-- TOC entry 3104 (class 0 OID 0)
-- Dependencies: 317
-- Name: sqscproductorder; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscproductorder', 18, true);


--
-- TOC entry 3105 (class 0 OID 0)
-- Dependencies: 318
-- Name: sqscreplacement; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscreplacement', 9, true);


--
-- TOC entry 3106 (class 0 OID 0)
-- Dependencies: 319
-- Name: sqscreplacementattached; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscreplacementattached', 24, true);


--
-- TOC entry 3107 (class 0 OID 0)
-- Dependencies: 320
-- Name: sqscreplacementdocuments; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscreplacementdocuments', 15, true);


--
-- TOC entry 3108 (class 0 OID 0)
-- Dependencies: 321
-- Name: sqscroles; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscroles', 11, true);


--
-- TOC entry 3109 (class 0 OID 0)
-- Dependencies: 322
-- Name: sqscservicesorproducts; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscservicesorproducts', 23, true);


--
-- TOC entry 3110 (class 0 OID 0)
-- Dependencies: 323
-- Name: sqscstock; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscstock', 42, true);


--
-- TOC entry 3111 (class 0 OID 0)
-- Dependencies: 324
-- Name: sqscstopmachine; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscstopmachine', 79, true);


--
-- TOC entry 3112 (class 0 OID 0)
-- Dependencies: 325
-- Name: sqscstore; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscstore', 1, false);


--
-- TOC entry 3113 (class 0 OID 0)
-- Dependencies: 326
-- Name: sqscstoreorder; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscstoreorder', 78, true);


--
-- TOC entry 3114 (class 0 OID 0)
-- Dependencies: 327
-- Name: sqscstoreorderitem; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscstoreorderitem', 53, true);


--
-- TOC entry 3115 (class 0 OID 0)
-- Dependencies: 328
-- Name: sqscstoreorderstate; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscstoreorderstate', 1, false);


--
-- TOC entry 3116 (class 0 OID 0)
-- Dependencies: 329
-- Name: sqsctool; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqsctool', 13, true);


--
-- TOC entry 3117 (class 0 OID 0)
-- Dependencies: 330
-- Name: sqsctoolattached; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqsctoolattached', 10, true);


--
-- TOC entry 3118 (class 0 OID 0)
-- Dependencies: 331
-- Name: sqsctooldocuments; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqsctooldocuments', 17, true);


--
-- TOC entry 3119 (class 0 OID 0)
-- Dependencies: 332
-- Name: sqscusers; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscusers', 24, true);


--
-- TOC entry 3120 (class 0 OID 0)
-- Dependencies: 333
-- Name: sqscworkexperience; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscworkexperience', 12, true);


--
-- TOC entry 3121 (class 0 OID 0)
-- Dependencies: 334
-- Name: sqscworkforce; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscworkforce', 35, true);


--
-- TOC entry 3122 (class 0 OID 0)
-- Dependencies: 335
-- Name: sqtype; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqtype', 1, false);


--
-- TOC entry 2469 (class 2606 OID 305879)
-- Name: PK_DISTRIBUTION_UNIT; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_distribution_unit
    ADD CONSTRAINT "PK_DISTRIBUTION_UNIT" PRIMARY KEY (id_distribution_unit);


--
-- TOC entry 2475 (class 2606 OID 305881)
-- Name: PK_FACTORY_LOCATION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_factory_location
    ADD CONSTRAINT "PK_FACTORY_LOCATION" PRIMARY KEY (id_factory_location);


--
-- TOC entry 2481 (class 2606 OID 305883)
-- Name: PK_INPUT_DIMENSION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input_dimension
    ADD CONSTRAINT "PK_INPUT_DIMENSION" PRIMARY KEY (id_input_dimension);


--
-- TOC entry 2485 (class 2606 OID 305885)
-- Name: PK_INPUT_EQUIVALENCES; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input_equivalence
    ADD CONSTRAINT "PK_INPUT_EQUIVALENCES" PRIMARY KEY (id_input_equivalence);


--
-- TOC entry 2493 (class 2606 OID 305887)
-- Name: PK_INPUT_LOCATION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_location
    ADD CONSTRAINT "PK_INPUT_LOCATION" PRIMARY KEY (id_location);


--
-- TOC entry 2595 (class 2606 OID 305889)
-- Name: PK_INPUT_STOCK; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_stock
    ADD CONSTRAINT "PK_INPUT_STOCK" PRIMARY KEY (id_stock);


--
-- TOC entry 2635 (class 2606 OID 313512)
-- Name: PK_LOG_PRODUCTION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY ot_log_production
    ADD CONSTRAINT "PK_LOG_PRODUCTION" PRIMARY KEY (id_log);


--
-- TOC entry 2501 (class 2606 OID 305891)
-- Name: PK_MACHINE_DOCUMENT; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_machine_document
    ADD CONSTRAINT "PK_MACHINE_DOCUMENT" PRIMARY KEY (id_machine_document);


--
-- TOC entry 2503 (class 2606 OID 305893)
-- Name: PK_MACHINE_PART; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_machine_part
    ADD CONSTRAINT "PK_MACHINE_PART" PRIMARY KEY (id_machine_part);


--
-- TOC entry 2505 (class 2606 OID 305895)
-- Name: PK_MACHINE_PART_ATTACHED; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_machine_part_attached
    ADD CONSTRAINT "PK_MACHINE_PART_ATTACHED" PRIMARY KEY (id_machine_part_attached);


--
-- TOC entry 2507 (class 2606 OID 305897)
-- Name: PK_MACHINE_PART_DOCUMENT; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_machine_part_document
    ADD CONSTRAINT "PK_MACHINE_PART_DOCUMENT" PRIMARY KEY (id_machine_part_document);


--
-- TOC entry 2453 (class 2606 OID 305899)
-- Name: PK_MAINTENANCE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY ot_maintenance
    ADD CONSTRAINT "PK_MAINTENANCE" PRIMARY KEY (id_maintenance);


--
-- TOC entry 2511 (class 2606 OID 305901)
-- Name: PK_MAINTENANCE_ACTIVITY; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_maintenance_activity
    ADD CONSTRAINT "PK_MAINTENANCE_ACTIVITY" PRIMARY KEY (id_maintenance_activity);


--
-- TOC entry 2513 (class 2606 OID 305903)
-- Name: PK_MAINTENANCE_CLASIFICATION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_maintenance_clasification
    ADD CONSTRAINT "PK_MAINTENANCE_CLASIFICATION" PRIMARY KEY (id_maintenance_clasification);


--
-- TOC entry 2455 (class 2606 OID 305905)
-- Name: PK_MAINTENANCE_CORRECTIVE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY ot_maintenance_corrective
    ADD CONSTRAINT "PK_MAINTENANCE_CORRECTIVE" PRIMARY KEY (id_maintenance_corrective);


--
-- TOC entry 2515 (class 2606 OID 305907)
-- Name: PK_MAINTENANCE_DAMAGE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_maintenance_damage
    ADD CONSTRAINT "PK_MAINTENANCE_DAMAGE" PRIMARY KEY (id_maintenance_damage);


--
-- TOC entry 2457 (class 2606 OID 305909)
-- Name: PK_MAINTENANCE_PREVENTIVE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY ot_maintenance_preventive
    ADD CONSTRAINT "PK_MAINTENANCE_PREVENTIVE" PRIMARY KEY (id_maintenance_preventive);


--
-- TOC entry 2517 (class 2606 OID 305911)
-- Name: PK_MAINTENANCE_STATE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_maintenance_state
    ADD CONSTRAINT "PK_MAINTENANCE_STATE" PRIMARY KEY (id_maintenance_state);


--
-- TOC entry 2557 (class 2606 OID 305913)
-- Name: PK_PROCESS_ATTACHED; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_process_attached
    ADD CONSTRAINT "PK_PROCESS_ATTACHED" PRIMARY KEY (id_process_attached);


--
-- TOC entry 2559 (class 2606 OID 305915)
-- Name: PK_PROCESS_EMPLOYEE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_process_employee
    ADD CONSTRAINT "PK_PROCESS_EMPLOYEE" PRIMARY KEY (id_process_employee);


--
-- TOC entry 2561 (class 2606 OID 305917)
-- Name: PK_PROCESS_EMPLOYEE_ORDER; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_process_employee_order
    ADD CONSTRAINT "PK_PROCESS_EMPLOYEE_ORDER" PRIMARY KEY (id_process_employee_order);


--
-- TOC entry 2563 (class 2606 OID 305919)
-- Name: PK_PROCESS_INPUT; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_process_input
    ADD CONSTRAINT "PK_PROCESS_INPUT" PRIMARY KEY (id_process_input);


--
-- TOC entry 2565 (class 2606 OID 305921)
-- Name: PK_PROCESS_INPUT_ORDER; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_process_input_order
    ADD CONSTRAINT "PK_PROCESS_INPUT_ORDER" PRIMARY KEY (id_process_input_order);


--
-- TOC entry 2553 (class 2606 OID 305923)
-- Name: PK_PROCESS_PRODUCT; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_procces_product
    ADD CONSTRAINT "PK_PROCESS_PRODUCT" PRIMARY KEY (id_process_product);


--
-- TOC entry 2555 (class 2606 OID 305926)
-- Name: PK_PROCESS_PRODUCT_ORDER; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_procces_product_order
    ADD CONSTRAINT "PK_PROCESS_PRODUCT_ORDER" PRIMARY KEY (id_process_product_order);


--
-- TOC entry 2571 (class 2606 OID 305928)
-- Name: PK_PROCESS_TYPE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_process_type
    ADD CONSTRAINT "PK_PROCESS_TYPE" PRIMARY KEY (id_process_type);


--
-- TOC entry 2461 (class 2606 OID 305930)
-- Name: PK_PRODUCTION_ORDER; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY ot_production_order
    ADD CONSTRAINT "PK_PRODUCTION_ORDER" PRIMARY KEY (id_production_order);


--
-- TOC entry 2581 (class 2606 OID 305932)
-- Name: PK_PRODUCTION_STATE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_production_state
    ADD CONSTRAINT "PK_PRODUCTION_STATE" PRIMARY KEY (id_production_state);


--
-- TOC entry 2573 (class 2606 OID 305934)
-- Name: PK_PRODUCT_ATTACHED; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_product_attached
    ADD CONSTRAINT "PK_PRODUCT_ATTACHED" PRIMARY KEY (id_product_attached);


--
-- TOC entry 2585 (class 2606 OID 305936)
-- Name: PK_REPLACEMENT_ATTACHED; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_replacement_attached
    ADD CONSTRAINT "PK_REPLACEMENT_ATTACHED" PRIMARY KEY (id_replacement_attached);


--
-- TOC entry 2459 (class 2606 OID 305938)
-- Name: PK_SCHEDULE_MAINTENANCE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY ot_maintenance_schedule
    ADD CONSTRAINT "PK_SCHEDULE_MAINTENANCE" PRIMARY KEY (id_schedule_maintenance);


--
-- TOC entry 2465 (class 2606 OID 305940)
-- Name: PK_SC_CONSTANTS_LOAD_FILES; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_constants_load_files
    ADD CONSTRAINT "PK_SC_CONSTANTS_LOAD_FILES" PRIMARY KEY (id_constants_load_file);


--
-- TOC entry 2477 (class 2606 OID 305942)
-- Name: PK_SC_GROUP; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_group
    ADD CONSTRAINT "PK_SC_GROUP" PRIMARY KEY (id_group);


--
-- TOC entry 2479 (class 2606 OID 305944)
-- Name: PK_SC_INPUT; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "PK_SC_INPUT" PRIMARY KEY (id_input);


--
-- TOC entry 2483 (class 2606 OID 305946)
-- Name: PK_SC_INPUT_DOCUMENTS; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input_documents
    ADD CONSTRAINT "PK_SC_INPUT_DOCUMENTS" PRIMARY KEY (id_input_documents);


--
-- TOC entry 2487 (class 2606 OID 305948)
-- Name: PK_SC_INPUT_FEATURES; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input_feactures
    ADD CONSTRAINT "PK_SC_INPUT_FEATURES" PRIMARY KEY (id_input_feactures);


--
-- TOC entry 2489 (class 2606 OID 305950)
-- Name: PK_SC_INPUT_OBSERVATIONS; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input_observations
    ADD CONSTRAINT "PK_SC_INPUT_OBSERVATIONS" PRIMARY KEY (id_input_observation);


--
-- TOC entry 2491 (class 2606 OID 305952)
-- Name: PK_SC_INPUT_SPECIFICATIONS; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input_specifications
    ADD CONSTRAINT "PK_SC_INPUT_SPECIFICATIONS" PRIMARY KEY (id_input_specifications);


--
-- TOC entry 2529 (class 2606 OID 305954)
-- Name: PK_SC_M0NEY; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_money
    ADD CONSTRAINT "PK_SC_M0NEY" PRIMARY KEY (id_money);


--
-- TOC entry 2495 (class 2606 OID 305956)
-- Name: PK_SC_MACHINE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_machine
    ADD CONSTRAINT "PK_SC_MACHINE" PRIMARY KEY (id_machine);


--
-- TOC entry 2497 (class 2606 OID 305958)
-- Name: PK_SC_MACHINE_ATTACHED; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_machine_attached
    ADD CONSTRAINT "PK_SC_MACHINE_ATTACHED" PRIMARY KEY (id_attached);


--
-- TOC entry 2499 (class 2606 OID 305960)
-- Name: PK_SC_MACHINE_CONDITION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_machine_conditions
    ADD CONSTRAINT "PK_SC_MACHINE_CONDITION" PRIMARY KEY (id_condition);


--
-- TOC entry 2519 (class 2606 OID 305962)
-- Name: PK_SC_MEASURE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_measure_unit
    ADD CONSTRAINT "PK_SC_MEASURE" PRIMARY KEY (id_measure);


--
-- TOC entry 2531 (class 2606 OID 305964)
-- Name: PK_SC_NOTIFICATION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_notification
    ADD CONSTRAINT "PK_SC_NOTIFICATION" PRIMARY KEY (id_notification);


--
-- TOC entry 2535 (class 2606 OID 305966)
-- Name: PK_SC_PACKING; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_packing_unit
    ADD CONSTRAINT "PK_SC_PACKING" PRIMARY KEY (id_packing);


--
-- TOC entry 2543 (class 2606 OID 305968)
-- Name: PK_SC_PERSON_BY_GROUP; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_person_by_group
    ADD CONSTRAINT "PK_SC_PERSON_BY_GROUP" PRIMARY KEY (id_person_by_group);


--
-- TOC entry 2551 (class 2606 OID 305970)
-- Name: PK_SC_PRIORITY; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_priority
    ADD CONSTRAINT "PK_SC_PRIORITY" PRIMARY KEY (id_priority);


--
-- TOC entry 2567 (class 2606 OID 305972)
-- Name: PK_SC_PROCESS_MACHINE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_process_machine
    ADD CONSTRAINT "PK_SC_PROCESS_MACHINE" PRIMARY KEY (id_process_machine);


--
-- TOC entry 2569 (class 2606 OID 305974)
-- Name: PK_SC_PROCESS_MACHINE_ORDER; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_process_machine_order
    ADD CONSTRAINT "PK_SC_PROCESS_MACHINE_ORDER" PRIMARY KEY (id_process_machine_order);


--
-- TOC entry 2575 (class 2606 OID 305976)
-- Name: PK_SC_PRODUCT_DOCUMENTS; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_product_documents
    ADD CONSTRAINT "PK_SC_PRODUCT_DOCUMENTS" PRIMARY KEY (id_product_documents);


--
-- TOC entry 2577 (class 2606 OID 305978)
-- Name: PK_SC_PRODUCT_FORMULATION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "PK_SC_PRODUCT_FORMULATION" PRIMARY KEY (id_product_formulation);


--
-- TOC entry 2579 (class 2606 OID 305980)
-- Name: PK_SC_PRODUCT_ORDER; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_product_order
    ADD CONSTRAINT "PK_SC_PRODUCT_ORDER" PRIMARY KEY (id_product_order);


--
-- TOC entry 2583 (class 2606 OID 305982)
-- Name: PK_SC_REPLACEMENT; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "PK_SC_REPLACEMENT" PRIMARY KEY (id_replacement);


--
-- TOC entry 2587 (class 2606 OID 305984)
-- Name: PK_SC_REPLACEMENT_DOCUMENTS; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_replacement_documents
    ADD CONSTRAINT "PK_SC_REPLACEMENT_DOCUMENTS" PRIMARY KEY (id_replacement_documents);


--
-- TOC entry 2599 (class 2606 OID 305986)
-- Name: PK_SC_STORE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_store
    ADD CONSTRAINT "PK_SC_STORE" PRIMARY KEY (id_store);


--
-- TOC entry 2617 (class 2606 OID 305988)
-- Name: PK_SC_TOOL; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "PK_SC_TOOL" PRIMARY KEY (id_tool);


--
-- TOC entry 2621 (class 2606 OID 305990)
-- Name: PK_SC_TOOL_DOCUMENTS; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_tool_documents
    ADD CONSTRAINT "PK_SC_TOOL_DOCUMENTS" PRIMARY KEY (id_tool_documents);


--
-- TOC entry 2597 (class 2606 OID 305992)
-- Name: PK_STOP_MACHINE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_stop_machine
    ADD CONSTRAINT "PK_STOP_MACHINE" PRIMARY KEY (id_stop_machine);


--
-- TOC entry 2601 (class 2606 OID 305994)
-- Name: PK_STORE_ORDER; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_store_order
    ADD CONSTRAINT "PK_STORE_ORDER" PRIMARY KEY (id_store_order);


--
-- TOC entry 2603 (class 2606 OID 305996)
-- Name: PK_STORE_ORDER_ITEM; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_store_order_item
    ADD CONSTRAINT "PK_STORE_ORDER_ITEM" PRIMARY KEY (id_item);


--
-- TOC entry 2605 (class 2606 OID 305998)
-- Name: PK_STORE_ORDER_STATE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_store_order_state
    ADD CONSTRAINT "PK_STORE_ORDER_STATE" PRIMARY KEY (id_state);


--
-- TOC entry 2607 (class 2606 OID 306000)
-- Name: PK_STORE_REQUISITION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_store_requisition
    ADD CONSTRAINT "PK_STORE_REQUISITION" PRIMARY KEY (id_store_requisition);


--
-- TOC entry 2609 (class 2606 OID 306002)
-- Name: PK_STORE_REQUISITION_ITEM; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_store_requisition_item
    ADD CONSTRAINT "PK_STORE_REQUISITION_ITEM" PRIMARY KEY (id_item);


--
-- TOC entry 2611 (class 2606 OID 306004)
-- Name: PK_STORE_REQUISITION_STATE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_store_requisition_state
    ADD CONSTRAINT "PK_STORE_REQUISITION_STATE" PRIMARY KEY (id_state);


--
-- TOC entry 2613 (class 2606 OID 306006)
-- Name: PK_TIME; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_time
    ADD CONSTRAINT "PK_TIME" PRIMARY KEY (id_time);


--
-- TOC entry 2619 (class 2606 OID 306008)
-- Name: PK_TOOL_ATTACHED; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_tool_attached
    ADD CONSTRAINT "PK_TOOL_ATTACHED" PRIMARY KEY (id_tool_attached);


--
-- TOC entry 2623 (class 2606 OID 306010)
-- Name: PK_TURN; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_turn
    ADD CONSTRAINT "PK_TURN" PRIMARY KEY (id_turn);


--
-- TOC entry 2633 (class 2606 OID 306012)
-- Name: PK_WORKFORCE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_workforce
    ADD CONSTRAINT "PK_WORKFORCE" PRIMARY KEY (id_workforce);


--
-- TOC entry 2539 (class 2606 OID 306014)
-- Name: UK_IDENTIFICATION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_person
    ADD CONSTRAINT "UK_IDENTIFICATION" UNIQUE (identification);


--
-- TOC entry 2589 (class 2606 OID 306016)
-- Name: UK_ROLENAME; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_roles
    ADD CONSTRAINT "UK_ROLENAME" UNIQUE (name);


--
-- TOC entry 2525 (class 2606 OID 306018)
-- Name: UK_SC_ROLES_SC_MODULE_PERMISSION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_module_permission_by_role
    ADD CONSTRAINT "UK_SC_ROLES_SC_MODULE_PERMISSION" UNIQUE (id_role, id_module_permission);


--
-- TOC entry 2615 (class 2606 OID 306020)
-- Name: UK_TIME; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_time
    ADD CONSTRAINT "UK_TIME" UNIQUE (acronym);


--
-- TOC entry 2521 (class 2606 OID 306022)
-- Name: UK_UNIT_MEASURE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_measure_unit
    ADD CONSTRAINT "UK_UNIT_MEASURE" UNIQUE (acronym);


--
-- TOC entry 2627 (class 2606 OID 306024)
-- Name: UK_USERNAME; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_users
    ADD CONSTRAINT "UK_USERNAME" UNIQUE (login);


--
-- TOC entry 2463 (class 2606 OID 306026)
-- Name: pk_competencies; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_competencies
    ADD CONSTRAINT pk_competencies PRIMARY KEY (id_competencies);


--
-- TOC entry 2467 (class 2606 OID 306028)
-- Name: pk_cost_center; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_cost_center
    ADD CONSTRAINT pk_cost_center PRIMARY KEY (id_cost_center);


--
-- TOC entry 2471 (class 2606 OID 306030)
-- Name: pk_document; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_documents
    ADD CONSTRAINT pk_document PRIMARY KEY (id_document);


--
-- TOC entry 2473 (class 2606 OID 306032)
-- Name: pk_employee; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_employee
    ADD CONSTRAINT pk_employee PRIMARY KEY (id_employee);


--
-- TOC entry 2509 (class 2606 OID 306034)
-- Name: pk_mails; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_mails
    ADD CONSTRAINT pk_mails PRIMARY KEY (id_mail);


--
-- TOC entry 2527 (class 2606 OID 306036)
-- Name: pk_module_permission_by_role; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_module_permission_by_role
    ADD CONSTRAINT pk_module_permission_by_role PRIMARY KEY (id_module_permission_by_role);


--
-- TOC entry 2533 (class 2606 OID 306038)
-- Name: pk_operatin_condition; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_operating_conditions
    ADD CONSTRAINT pk_operatin_condition PRIMARY KEY (id_operating_condition);


--
-- TOC entry 2537 (class 2606 OID 306040)
-- Name: pk_partner; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_partner
    ADD CONSTRAINT pk_partner PRIMARY KEY (id_partner);


--
-- TOC entry 2541 (class 2606 OID 306042)
-- Name: pk_person; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_person
    ADD CONSTRAINT pk_person PRIMARY KEY (id_person);


--
-- TOC entry 2545 (class 2606 OID 306044)
-- Name: pk_person_observations; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_person_observations
    ADD CONSTRAINT pk_person_observations PRIMARY KEY (id_person_observations);


--
-- TOC entry 2547 (class 2606 OID 306046)
-- Name: pk_person_specifications; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_person_specifications
    ADD CONSTRAINT pk_person_specifications PRIMARY KEY (id_person_specifications);


--
-- TOC entry 2549 (class 2606 OID 306048)
-- Name: pk_phones; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_phones
    ADD CONSTRAINT pk_phones PRIMARY KEY (id_phone);


--
-- TOC entry 2523 (class 2606 OID 306050)
-- Name: pk_sc_module_permission; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_module_permission
    ADD CONSTRAINT pk_sc_module_permission PRIMARY KEY (id_module_permission);


--
-- TOC entry 2591 (class 2606 OID 306052)
-- Name: pk_sc_roles; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_roles
    ADD CONSTRAINT pk_sc_roles PRIMARY KEY (id_role);


--
-- TOC entry 2593 (class 2606 OID 306054)
-- Name: pk_service_or_product; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_services_or_products
    ADD CONSTRAINT pk_service_or_product PRIMARY KEY (id_service_or_products);


--
-- TOC entry 2625 (class 2606 OID 306056)
-- Name: pk_type; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_type
    ADD CONSTRAINT pk_type PRIMARY KEY (id_type);


--
-- TOC entry 2629 (class 2606 OID 306058)
-- Name: pk_users; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_users
    ADD CONSTRAINT pk_users PRIMARY KEY (id_user);


--
-- TOC entry 2631 (class 2606 OID 306060)
-- Name: pk_work_experience; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_work_experience
    ADD CONSTRAINT pk_work_experience PRIMARY KEY (id_work_experience);


--
-- TOC entry 2649 (class 2606 OID 306061)
-- Name: FK_CENTER_COST_INPUT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_CENTER_COST_INPUT" FOREIGN KEY (cost_center) REFERENCES sc_cost_center(id_cost_center);


--
-- TOC entry 2727 (class 2606 OID 306066)
-- Name: FK_COSTCENTER_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_COSTCENTER_REPLACEMENT" FOREIGN KEY (cost_center) REFERENCES sc_cost_center(id_cost_center);


--
-- TOC entry 2744 (class 2606 OID 306071)
-- Name: FK_COSTCENTER_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_COSTCENTER_TOOL" FOREIGN KEY (cost_center) REFERENCES sc_cost_center(id_cost_center);


--
-- TOC entry 2674 (class 2606 OID 306076)
-- Name: FK_COST_CENTER_MACHINE_PART; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_part
    ADD CONSTRAINT "FK_COST_CENTER_MACHINE_PART" FOREIGN KEY (id_cost_center) REFERENCES sc_cost_center(id_cost_center);


--
-- TOC entry 2675 (class 2606 OID 306081)
-- Name: FK_DIMENSION_MACHINE_PART; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_part
    ADD CONSTRAINT "FK_DIMENSION_MACHINE_PART" FOREIGN KEY (id_dimension) REFERENCES sc_input_dimension(id_input_dimension);


--
-- TOC entry 2728 (class 2606 OID 306086)
-- Name: FK_DIMENSION_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_DIMENSION_REPLACEMENT" FOREIGN KEY (id_replacement_dimension) REFERENCES sc_input_dimension(id_input_dimension);


--
-- TOC entry 2745 (class 2606 OID 306091)
-- Name: FK_DIMENSION_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_DIMENSION_TOOL" FOREIGN KEY (id_tool_dimension) REFERENCES sc_input_dimension(id_input_dimension);


--
-- TOC entry 2664 (class 2606 OID 306096)
-- Name: FK_FACTORY_LOCATION_MACHINE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine
    ADD CONSTRAINT "FK_FACTORY_LOCATION_MACHINE" FOREIGN KEY (id_location) REFERENCES sc_factory_location(id_factory_location);


--
-- TOC entry 2691 (class 2606 OID 306101)
-- Name: FK_GROUP_BY_GROUP; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_person_by_group
    ADD CONSTRAINT "FK_GROUP_BY_GROUP" FOREIGN KEY (id_group) REFERENCES sc_group(id_group);


--
-- TOC entry 2687 (class 2606 OID 306106)
-- Name: FK_GROUP_NOTIFICATION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_notification
    ADD CONSTRAINT "FK_GROUP_NOTIFICATION" FOREIGN KEY (id_group) REFERENCES sc_group(id_group);


--
-- TOC entry 2659 (class 2606 OID 306111)
-- Name: FK_INPUT_EQUIVALENCE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input_equivalence
    ADD CONSTRAINT "FK_INPUT_EQUIVALENCE" FOREIGN KEY (id_input) REFERENCES sc_input(id_input);


--
-- TOC entry 2660 (class 2606 OID 306116)
-- Name: FK_INPUT_FEACTURES; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input_feactures
    ADD CONSTRAINT "FK_INPUT_FEACTURES" FOREIGN KEY (id_input) REFERENCES sc_input(id_input);


--
-- TOC entry 2661 (class 2606 OID 306121)
-- Name: FK_INPUT_OBSERVATIONS; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input_observations
    ADD CONSTRAINT "FK_INPUT_OBSERVATIONS" FOREIGN KEY (id_input) REFERENCES sc_input(id_input);


--
-- TOC entry 2662 (class 2606 OID 306126)
-- Name: FK_INPUT_SPECIFICATIONS; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input_specifications
    ADD CONSTRAINT "FK_INPUT_SPECIFICATIONS" FOREIGN KEY (id_input) REFERENCES sc_input(id_input);


--
-- TOC entry 2738 (class 2606 OID 306131)
-- Name: FK_INPUT_STOCK_STORE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_stock
    ADD CONSTRAINT "FK_INPUT_STOCK_STORE" FOREIGN KEY (id_store) REFERENCES sc_store(id_store);


--
-- TOC entry 2729 (class 2606 OID 306136)
-- Name: FK_LOCATION_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_LOCATION_REPLACEMENT" FOREIGN KEY (id_location) REFERENCES sc_location(id_location);


--
-- TOC entry 2663 (class 2606 OID 306141)
-- Name: FK_LOCATION_STORE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_location
    ADD CONSTRAINT "FK_LOCATION_STORE" FOREIGN KEY (id_store) REFERENCES sc_store(id_store);


--
-- TOC entry 2746 (class 2606 OID 306146)
-- Name: FK_LOCATION_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_LOCATION_TOOL" FOREIGN KEY (id_location) REFERENCES sc_location(id_location);


--
-- TOC entry 2671 (class 2606 OID 306151)
-- Name: FK_MACHINE_ATTACHED; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_attached
    ADD CONSTRAINT "FK_MACHINE_ATTACHED" FOREIGN KEY (id_machine) REFERENCES sc_machine(id_machine);


--
-- TOC entry 2672 (class 2606 OID 306156)
-- Name: FK_MACHINE_CONDITION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_conditions
    ADD CONSTRAINT "FK_MACHINE_CONDITION" FOREIGN KEY (id_machine) REFERENCES sc_machine(id_machine);


--
-- TOC entry 2665 (class 2606 OID 306161)
-- Name: FK_MACHINE_COST_CENTER; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine
    ADD CONSTRAINT "FK_MACHINE_COST_CENTER" FOREIGN KEY (id_cost_center) REFERENCES sc_cost_center(id_cost_center);


--
-- TOC entry 2666 (class 2606 OID 306166)
-- Name: FK_MACHINE_DIMENSION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine
    ADD CONSTRAINT "FK_MACHINE_DIMENSION" FOREIGN KEY (id_dimension) REFERENCES sc_input_dimension(id_input_dimension);


--
-- TOC entry 2673 (class 2606 OID 306171)
-- Name: FK_MACHINE_DOCUMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_document
    ADD CONSTRAINT "FK_MACHINE_DOCUMENT" FOREIGN KEY (id_machine) REFERENCES sc_machine(id_machine);


--
-- TOC entry 2676 (class 2606 OID 306176)
-- Name: FK_MACHINE_MACHINE_PART; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_part
    ADD CONSTRAINT "FK_MACHINE_MACHINE_PART" FOREIGN KEY (id_machine) REFERENCES sc_machine(id_machine);


--
-- TOC entry 2667 (class 2606 OID 306181)
-- Name: FK_MACHINE_MONEY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine
    ADD CONSTRAINT "FK_MACHINE_MONEY" FOREIGN KEY (id_money) REFERENCES sc_money(id_money);


--
-- TOC entry 2688 (class 2606 OID 306186)
-- Name: FK_MACHINE_NOTIFICATION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_notification
    ADD CONSTRAINT "FK_MACHINE_NOTIFICATION" FOREIGN KEY (id_machine) REFERENCES sc_machine(id_machine);


--
-- TOC entry 2668 (class 2606 OID 306191)
-- Name: FK_MACHINE_PARTNER; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine
    ADD CONSTRAINT "FK_MACHINE_PARTNER" FOREIGN KEY (id_partner) REFERENCES sc_partner(id_partner);


--
-- TOC entry 2681 (class 2606 OID 306196)
-- Name: FK_MACHINE_PART_ATTACHED; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_part_attached
    ADD CONSTRAINT "FK_MACHINE_PART_ATTACHED" FOREIGN KEY (id_machine_part) REFERENCES sc_machine_part(id_machine_part);


--
-- TOC entry 2682 (class 2606 OID 306201)
-- Name: FK_MACHINE_PART_DOCUMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_part_document
    ADD CONSTRAINT "FK_MACHINE_PART_DOCUMENT" FOREIGN KEY (id_machine_part) REFERENCES sc_machine_part(id_machine_part);


--
-- TOC entry 2669 (class 2606 OID 306206)
-- Name: FK_MACHINE_PRIORITY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine
    ADD CONSTRAINT "FK_MACHINE_PRIORITY" FOREIGN KEY (id_priority) REFERENCES sc_priority(id_priority);


--
-- TOC entry 2670 (class 2606 OID 306211)
-- Name: FK_MACHINE_TIME; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine
    ADD CONSTRAINT "FK_MACHINE_TIME" FOREIGN KEY (id_time) REFERENCES sc_time(id_time);


--
-- TOC entry 2684 (class 2606 OID 306216)
-- Name: FK_MAINTENANCE_ACTIVITY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_maintenance_activity
    ADD CONSTRAINT "FK_MAINTENANCE_ACTIVITY" FOREIGN KEY (id_maintenance) REFERENCES ot_maintenance(id_maintenance);


--
-- TOC entry 2636 (class 2606 OID 306221)
-- Name: FK_MAINTENANCE_CLASIFICATION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY ot_maintenance
    ADD CONSTRAINT "FK_MAINTENANCE_CLASIFICATION" FOREIGN KEY (id_maintenance_clasification) REFERENCES sc_maintenance_clasification(id_maintenance_clasification);


--
-- TOC entry 2642 (class 2606 OID 306226)
-- Name: FK_MAINTENANCE_CORRECTIVE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY ot_maintenance_corrective
    ADD CONSTRAINT "FK_MAINTENANCE_CORRECTIVE" FOREIGN KEY (id_maintenance) REFERENCES ot_maintenance(id_maintenance);


--
-- TOC entry 2637 (class 2606 OID 306231)
-- Name: FK_MAINTENANCE_DAMAGE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY ot_maintenance
    ADD CONSTRAINT "FK_MAINTENANCE_DAMAGE" FOREIGN KEY (id_maintenance_damage) REFERENCES sc_maintenance_damage(id_maintenance_damage);


--
-- TOC entry 2638 (class 2606 OID 306236)
-- Name: FK_MAINTENANCE_MACHINE_PART; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY ot_maintenance
    ADD CONSTRAINT "FK_MAINTENANCE_MACHINE_PART" FOREIGN KEY (id_machine_part) REFERENCES sc_machine_part(id_machine_part);


--
-- TOC entry 2643 (class 2606 OID 306241)
-- Name: FK_MAINTENANCE_PREVENTIVE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY ot_maintenance_preventive
    ADD CONSTRAINT "FK_MAINTENANCE_PREVENTIVE" FOREIGN KEY (id_maintenance) REFERENCES ot_maintenance(id_maintenance);


--
-- TOC entry 2639 (class 2606 OID 306246)
-- Name: FK_MAINTENANCE_PRIORITY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY ot_maintenance
    ADD CONSTRAINT "FK_MAINTENANCE_PRIORITY" FOREIGN KEY (id_priority) REFERENCES sc_priority(id_priority);


--
-- TOC entry 2640 (class 2606 OID 306251)
-- Name: FK_MAINTENANCE_STATE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY ot_maintenance
    ADD CONSTRAINT "FK_MAINTENANCE_STATE" FOREIGN KEY (id_maintenance_state) REFERENCES sc_maintenance_state(id_maintenance_state);


--
-- TOC entry 2739 (class 2606 OID 306256)
-- Name: FK_MAINTENANCE_STOP_MACHINE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_stop_machine
    ADD CONSTRAINT "FK_MAINTENANCE_STOP_MACHINE" FOREIGN KEY (id_maintenance) REFERENCES ot_maintenance(id_maintenance);


--
-- TOC entry 2641 (class 2606 OID 306261)
-- Name: FK_MAINTENANCE_WORKFORCE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY ot_maintenance
    ADD CONSTRAINT "FK_MAINTENANCE_WORKFORCE" FOREIGN KEY (id_workforce) REFERENCES sc_workforce(id_workforce);


--
-- TOC entry 2677 (class 2606 OID 306266)
-- Name: FK_MONEY_MACHINE_PART; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_part
    ADD CONSTRAINT "FK_MONEY_MACHINE_PART" FOREIGN KEY (id_money) REFERENCES sc_money(id_money);


--
-- TOC entry 2730 (class 2606 OID 306271)
-- Name: FK_MONEY_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_MONEY_REPLACEMENT" FOREIGN KEY (id_money) REFERENCES sc_money(id_money);


--
-- TOC entry 2747 (class 2606 OID 306276)
-- Name: FK_MONEY_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_MONEY_TOOL" FOREIGN KEY (id_money) REFERENCES sc_money(id_money);


--
-- TOC entry 2692 (class 2606 OID 306281)
-- Name: FK_PERSON_BY_GROUP; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_person_by_group
    ADD CONSTRAINT "FK_PERSON_BY_GROUP" FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2678 (class 2606 OID 306286)
-- Name: FK_PRIORITY_MACHINE_PART; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_part
    ADD CONSTRAINT "FK_PRIORITY_MACHINE_PART" FOREIGN KEY (id_priority) REFERENCES sc_priority(id_priority);


--
-- TOC entry 2731 (class 2606 OID 306291)
-- Name: FK_PRIORITY_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_PRIORITY_REPLACEMENT" FOREIGN KEY (id_priority) REFERENCES sc_priority(id_priority);


--
-- TOC entry 2748 (class 2606 OID 306296)
-- Name: FK_PRIORITY_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_PRIORITY_TOOL" FOREIGN KEY (id_priority) REFERENCES sc_priority(id_priority);


--
-- TOC entry 2701 (class 2606 OID 306301)
-- Name: FK_PROCESS_EMPLOYEE_EMPLOYEE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_employee
    ADD CONSTRAINT "FK_PROCESS_EMPLOYEE_EMPLOYEE" FOREIGN KEY (id_employee) REFERENCES sc_employee(id_employee);


--
-- TOC entry 2703 (class 2606 OID 306306)
-- Name: FK_PROCESS_EMPLOYEE_ORDER; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_employee_order
    ADD CONSTRAINT "FK_PROCESS_EMPLOYEE_ORDER" FOREIGN KEY (id_employee) REFERENCES sc_employee(id_employee);


--
-- TOC entry 2702 (class 2606 OID 306311)
-- Name: FK_PROCESS_EMPLOYEE_PROCESS; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_employee
    ADD CONSTRAINT "FK_PROCESS_EMPLOYEE_PROCESS" FOREIGN KEY (id_process) REFERENCES sc_procces_product(id_process_product);


--
-- TOC entry 2705 (class 2606 OID 306316)
-- Name: FK_PROCESS_INPUT_INPUT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_input
    ADD CONSTRAINT "FK_PROCESS_INPUT_INPUT" FOREIGN KEY (id_input) REFERENCES sc_input(id_input);


--
-- TOC entry 2707 (class 2606 OID 306321)
-- Name: FK_PROCESS_INPUT_ORDER; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_input_order
    ADD CONSTRAINT "FK_PROCESS_INPUT_ORDER" FOREIGN KEY (id_process_order) REFERENCES sc_procces_product_order(id_process_product_order);


--
-- TOC entry 2706 (class 2606 OID 306326)
-- Name: FK_PROCESS_INPUT_PROCESS; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_input
    ADD CONSTRAINT "FK_PROCESS_INPUT_PROCESS" FOREIGN KEY (id_process) REFERENCES sc_procces_product(id_process_product);


--
-- TOC entry 2709 (class 2606 OID 306331)
-- Name: FK_PROCESS_MACHINE_MACHINE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_machine
    ADD CONSTRAINT "FK_PROCESS_MACHINE_MACHINE" FOREIGN KEY (id_machine) REFERENCES sc_machine(id_machine);


--
-- TOC entry 2711 (class 2606 OID 306336)
-- Name: FK_PROCESS_MACHINE_ORDER; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_machine_order
    ADD CONSTRAINT "FK_PROCESS_MACHINE_ORDER" FOREIGN KEY (id_machine) REFERENCES sc_machine(id_machine);


--
-- TOC entry 2704 (class 2606 OID 306341)
-- Name: FK_PROCESS_ORDER_EMPLOYEE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_employee_order
    ADD CONSTRAINT "FK_PROCESS_ORDER_EMPLOYEE" FOREIGN KEY (id_process_order) REFERENCES sc_procces_product_order(id_process_product_order);


--
-- TOC entry 2708 (class 2606 OID 306346)
-- Name: FK_PROCESS_ORDER_INPUT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_input_order
    ADD CONSTRAINT "FK_PROCESS_ORDER_INPUT" FOREIGN KEY (id_input) REFERENCES sc_input(id_input);


--
-- TOC entry 2712 (class 2606 OID 306351)
-- Name: FK_PROCESS_ORDER_MACHINE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_machine_order
    ADD CONSTRAINT "FK_PROCESS_ORDER_MACHINE" FOREIGN KEY (id_process_order) REFERENCES sc_procces_product_order(id_process_product_order);


--
-- TOC entry 2710 (class 2606 OID 306356)
-- Name: FK_PROCESS_PROCESS_MACHINE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_machine
    ADD CONSTRAINT "FK_PROCESS_PROCESS_MACHINE" FOREIGN KEY (id_process) REFERENCES sc_procces_product(id_process_product);


--
-- TOC entry 2696 (class 2606 OID 306361)
-- Name: FK_PROCESS_PROCESS_TYPE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_procces_product
    ADD CONSTRAINT "FK_PROCESS_PROCESS_TYPE" FOREIGN KEY (id_process_type) REFERENCES sc_process_type(id_process_type);


--
-- TOC entry 2697 (class 2606 OID 306366)
-- Name: FK_PROCESS_PRODUCT_FORMULATION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_procces_product
    ADD CONSTRAINT "FK_PROCESS_PRODUCT_FORMULATION" FOREIGN KEY (id_product_formulation) REFERENCES sc_product_formulation(id_product_formulation);


--
-- TOC entry 2698 (class 2606 OID 306371)
-- Name: FK_PROCESS_PRODUCT_ORDER; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_procces_product_order
    ADD CONSTRAINT "FK_PROCESS_PRODUCT_ORDER" FOREIGN KEY (id_product_order) REFERENCES sc_product_order(id_product_order);


--
-- TOC entry 2699 (class 2606 OID 306376)
-- Name: FK_PROCESS_STATE_ORDER; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_procces_product_order
    ADD CONSTRAINT "FK_PROCESS_STATE_ORDER" FOREIGN KEY (id_process_state) REFERENCES sc_production_state(id_production_state);


--
-- TOC entry 2700 (class 2606 OID 306381)
-- Name: FK_PROCESS_TYPE_ORDER; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_procces_product_order
    ADD CONSTRAINT "FK_PROCESS_TYPE_ORDER" FOREIGN KEY (id_process_type) REFERENCES sc_process_type(id_process_type);


--
-- TOC entry 2645 (class 2606 OID 306386)
-- Name: FK_PRODUCTION_STATE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY ot_production_order
    ADD CONSTRAINT "FK_PRODUCTION_STATE" FOREIGN KEY (id_production_state) REFERENCES sc_production_state(id_production_state);


--
-- TOC entry 2715 (class 2606 OID 306391)
-- Name: FK_PRODUCT_DIMENSION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "FK_PRODUCT_DIMENSION" FOREIGN KEY (id_product_dimension) REFERENCES sc_input_dimension(id_input_dimension);


--
-- TOC entry 2721 (class 2606 OID 306396)
-- Name: FK_PRODUCT_DIMENSION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_order
    ADD CONSTRAINT "FK_PRODUCT_DIMENSION" FOREIGN KEY (id_product_dimension) REFERENCES sc_input_dimension(id_input_dimension);


--
-- TOC entry 2713 (class 2606 OID 306401)
-- Name: FK_PRODUCT_FORMULATION_ATTACHED; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_attached
    ADD CONSTRAINT "FK_PRODUCT_FORMULATION_ATTACHED" FOREIGN KEY (id_product_formulation) REFERENCES sc_product_formulation(id_product_formulation);


--
-- TOC entry 2716 (class 2606 OID 306406)
-- Name: FK_PRODUCT_FORMULATION_COST_CENTER; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "FK_PRODUCT_FORMULATION_COST_CENTER" FOREIGN KEY (id_cost_center) REFERENCES sc_cost_center(id_cost_center);


--
-- TOC entry 2717 (class 2606 OID 306411)
-- Name: FK_PRODUCT_FORMULATION_MONEY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "FK_PRODUCT_FORMULATION_MONEY" FOREIGN KEY (id_money) REFERENCES sc_money(id_money);


--
-- TOC entry 2718 (class 2606 OID 306416)
-- Name: FK_PRODUCT_FORMULATION_PACKING; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "FK_PRODUCT_FORMULATION_PACKING" FOREIGN KEY (id_packing) REFERENCES sc_packing_unit(id_packing);


--
-- TOC entry 2719 (class 2606 OID 306421)
-- Name: FK_PRODUCT_FORMULATION_PRIORITY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "FK_PRODUCT_FORMULATION_PRIORITY" FOREIGN KEY (id_priority) REFERENCES sc_priority(id_priority);


--
-- TOC entry 2720 (class 2606 OID 306426)
-- Name: FK_PRODUCT_FORMULATION_STOCK; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "FK_PRODUCT_FORMULATION_STOCK" FOREIGN KEY (id_stock) REFERENCES sc_stock(id_stock);


--
-- TOC entry 2722 (class 2606 OID 306431)
-- Name: FK_PRODUCT_ORDER_COST_CENTER; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_order
    ADD CONSTRAINT "FK_PRODUCT_ORDER_COST_CENTER" FOREIGN KEY (id_cost_center) REFERENCES sc_cost_center(id_cost_center);


--
-- TOC entry 2723 (class 2606 OID 306436)
-- Name: FK_PRODUCT_ORDER_MONEY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_order
    ADD CONSTRAINT "FK_PRODUCT_ORDER_MONEY" FOREIGN KEY (id_money) REFERENCES sc_money(id_money);


--
-- TOC entry 2724 (class 2606 OID 306441)
-- Name: FK_PRODUCT_ORDER_ORDER; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_order
    ADD CONSTRAINT "FK_PRODUCT_ORDER_ORDER" FOREIGN KEY (id_order) REFERENCES ot_production_order(id_production_order);


--
-- TOC entry 2725 (class 2606 OID 306446)
-- Name: FK_PRODUCT_ORDER_PACKING; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_order
    ADD CONSTRAINT "FK_PRODUCT_ORDER_PACKING" FOREIGN KEY (id_packing) REFERENCES sc_packing_unit(id_packing);


--
-- TOC entry 2726 (class 2606 OID 306451)
-- Name: FK_PRODUCT_ORDER_PRIORITY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_order
    ADD CONSTRAINT "FK_PRODUCT_ORDER_PRIORITY" FOREIGN KEY (id_priority) REFERENCES sc_priority(id_priority);


--
-- TOC entry 2735 (class 2606 OID 306456)
-- Name: FK_REPLACEMENT_FORMULATION_ATTACHED; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement_attached
    ADD CONSTRAINT "FK_REPLACEMENT_FORMULATION_ATTACHED" FOREIGN KEY (id_replacement) REFERENCES sc_replacement(id_replacement);


--
-- TOC entry 2644 (class 2606 OID 306461)
-- Name: FK_SCHEDULE_MAINTENANCE_EMPLOYEE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY ot_maintenance_schedule
    ADD CONSTRAINT "FK_SCHEDULE_MAINTENANCE_EMPLOYEE" FOREIGN KEY (id_employee) REFERENCES sc_employee(id_employee);


--
-- TOC entry 2650 (class 2606 OID 306466)
-- Name: FK_SC_INPUT_DISTRIBUTION_UNIT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_DISTRIBUTION_UNIT" FOREIGN KEY (id_distribution_unit) REFERENCES sc_distribution_unit(id_distribution_unit);


--
-- TOC entry 2658 (class 2606 OID 306471)
-- Name: FK_SC_INPUT_DOCUMENTS_INPUT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input_documents
    ADD CONSTRAINT "FK_SC_INPUT_DOCUMENTS_INPUT" FOREIGN KEY (id_input) REFERENCES sc_input(id_input);


--
-- TOC entry 2651 (class 2606 OID 306476)
-- Name: FK_SC_INPUT_LOCATION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_LOCATION" FOREIGN KEY (id_location) REFERENCES sc_location(id_location);


--
-- TOC entry 2652 (class 2606 OID 306481)
-- Name: FK_SC_INPUT_MONEY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_MONEY" FOREIGN KEY (id_money) REFERENCES sc_money(id_money);


--
-- TOC entry 2653 (class 2606 OID 306486)
-- Name: FK_SC_INPUT_PACKING_UNIT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_PACKING_UNIT" FOREIGN KEY (id_packing) REFERENCES sc_packing_unit(id_packing);


--
-- TOC entry 2654 (class 2606 OID 306491)
-- Name: FK_SC_INPUT_SC_DIMENSION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_SC_DIMENSION" FOREIGN KEY (id_input_dimension) REFERENCES sc_input_dimension(id_input_dimension);


--
-- TOC entry 2655 (class 2606 OID 306496)
-- Name: FK_SC_INPUT_SC_PRIORITY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_SC_PRIORITY" FOREIGN KEY (id_priority) REFERENCES sc_priority(id_priority);


--
-- TOC entry 2656 (class 2606 OID 306501)
-- Name: FK_SC_INPUT_STOCK; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_STOCK" FOREIGN KEY (id_stock) REFERENCES sc_stock(id_stock);


--
-- TOC entry 2714 (class 2606 OID 306506)
-- Name: FK_SC_PRODUCT_DOCUMENTS_INPUT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_documents
    ADD CONSTRAINT "FK_SC_PRODUCT_DOCUMENTS_INPUT" FOREIGN KEY (id_product_formulation) REFERENCES sc_product_formulation(id_product_formulation);


--
-- TOC entry 2736 (class 2606 OID 306511)
-- Name: FK_SC_REPLACEMENT_DOCUMENTS_; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement_documents
    ADD CONSTRAINT "FK_SC_REPLACEMENT_DOCUMENTS_" FOREIGN KEY (id_replacement) REFERENCES sc_replacement(id_replacement);


--
-- TOC entry 2753 (class 2606 OID 306516)
-- Name: FK_SC_TOOL_DOCUMENTS_; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool_documents
    ADD CONSTRAINT "FK_SC_TOOL_DOCUMENTS_" FOREIGN KEY (id_tool) REFERENCES sc_tool(id_tool);


--
-- TOC entry 2732 (class 2606 OID 306521)
-- Name: FK_STOCK_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_STOCK_REPLACEMENT" FOREIGN KEY (id_stock) REFERENCES sc_stock(id_stock);


--
-- TOC entry 2749 (class 2606 OID 306526)
-- Name: FK_STOCK_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_STOCK_TOOL" FOREIGN KEY (id_stock) REFERENCES sc_stock(id_stock);


--
-- TOC entry 2689 (class 2606 OID 306531)
-- Name: FK_STOP_MACHINE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_notification
    ADD CONSTRAINT "FK_STOP_MACHINE" FOREIGN KEY (id_stop_machine) REFERENCES sc_stop_machine(id_stop_machine);


--
-- TOC entry 2741 (class 2606 OID 306536)
-- Name: FK_STORE_ORDER; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_store_order_item
    ADD CONSTRAINT "FK_STORE_ORDER" FOREIGN KEY (id_store_order) REFERENCES sc_store_order(id_store_order);


--
-- TOC entry 2740 (class 2606 OID 306541)
-- Name: FK_STORE_ORDER_STATE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_store_order
    ADD CONSTRAINT "FK_STORE_ORDER_STATE" FOREIGN KEY (id_state) REFERENCES sc_store_order_state(id_state);


--
-- TOC entry 2743 (class 2606 OID 306546)
-- Name: FK_STORE_REQUISITION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_store_requisition_item
    ADD CONSTRAINT "FK_STORE_REQUISITION" FOREIGN KEY (id_store_requisition) REFERENCES sc_store_requisition(id_store_requisition);


--
-- TOC entry 2742 (class 2606 OID 306551)
-- Name: FK_STORE_REQUISITION_STATE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_store_requisition
    ADD CONSTRAINT "FK_STORE_REQUISITION_STATE" FOREIGN KEY (id_state) REFERENCES sc_store_requisition_state(id_state);


--
-- TOC entry 2733 (class 2606 OID 306556)
-- Name: FK_SUPPLIER_GUARANTEE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_SUPPLIER_GUARANTEE" FOREIGN KEY (supplier_guarantee) REFERENCES sc_partner(id_partner);


--
-- TOC entry 2679 (class 2606 OID 306561)
-- Name: FK_SUPPLIER_GUARANTEE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_part
    ADD CONSTRAINT "FK_SUPPLIER_GUARANTEE" FOREIGN KEY (id_supplier_guarantee) REFERENCES sc_partner(id_partner);


--
-- TOC entry 2657 (class 2606 OID 306566)
-- Name: FK_SUPPLIER_PARTNERS; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SUPPLIER_PARTNERS" FOREIGN KEY (supplier_guarantee) REFERENCES sc_partner(id_partner);


--
-- TOC entry 2750 (class 2606 OID 306571)
-- Name: FK_SUPPLIER_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_SUPPLIER_TOOL" FOREIGN KEY (supplier_guarantee) REFERENCES sc_partner(id_partner);


--
-- TOC entry 2680 (class 2606 OID 306576)
-- Name: FK_TIME_MACHINE_PART; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_part
    ADD CONSTRAINT "FK_TIME_MACHINE_PART" FOREIGN KEY (id_time) REFERENCES sc_time(id_time);


--
-- TOC entry 2734 (class 2606 OID 306581)
-- Name: FK_TIME_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_TIME_REPLACEMENT" FOREIGN KEY ("time") REFERENCES sc_time(id_time);


--
-- TOC entry 2751 (class 2606 OID 306586)
-- Name: FK_TIME_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_TIME_TOOL" FOREIGN KEY ("time") REFERENCES sc_time(id_time);


--
-- TOC entry 2752 (class 2606 OID 306591)
-- Name: FK_TOOL_ATTACHED; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool_attached
    ADD CONSTRAINT "FK_TOOL_ATTACHED" FOREIGN KEY (id_tool) REFERENCES sc_tool(id_tool);


--
-- TOC entry 2757 (class 2606 OID 306596)
-- Name: FK_WORKFORCE_EMPLOYEE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_workforce
    ADD CONSTRAINT "FK_WORKFORCE_EMPLOYEE" FOREIGN KEY (id_employee) REFERENCES sc_employee(id_employee);


--
-- TOC entry 2648 (class 2606 OID 306601)
-- Name: fk_employee_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_employee
    ADD CONSTRAINT fk_employee_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2683 (class 2606 OID 306606)
-- Name: fk_mails_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_mails
    ADD CONSTRAINT fk_mails_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2685 (class 2606 OID 306611)
-- Name: fk_module_permission; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_module_permission_by_role
    ADD CONSTRAINT fk_module_permission FOREIGN KEY (id_module_permission) REFERENCES sc_module_permission(id_module_permission);


--
-- TOC entry 2686 (class 2606 OID 306616)
-- Name: fk_module_permission_by_role_for_role; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_module_permission_by_role
    ADD CONSTRAINT fk_module_permission_by_role_for_role FOREIGN KEY (id_role) REFERENCES sc_roles(id_role);


--
-- TOC entry 2690 (class 2606 OID 306621)
-- Name: fk_partner_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_partner
    ADD CONSTRAINT fk_partner_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2754 (class 2606 OID 306626)
-- Name: fk_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_users
    ADD CONSTRAINT fk_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2693 (class 2606 OID 306631)
-- Name: fk_person_observation_for_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_person_observations
    ADD CONSTRAINT fk_person_observation_for_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2694 (class 2606 OID 306636)
-- Name: fk_person_specifications_for_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_person_specifications
    ADD CONSTRAINT fk_person_specifications_for_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2695 (class 2606 OID 306641)
-- Name: fk_phones_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_phones
    ADD CONSTRAINT fk_phones_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2755 (class 2606 OID 306646)
-- Name: fk_role; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_users
    ADD CONSTRAINT fk_role FOREIGN KEY (id_role) REFERENCES sc_roles(id_role);


--
-- TOC entry 2647 (class 2606 OID 306651)
-- Name: fk_sc_person_to_sc_documents; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_documents
    ADD CONSTRAINT fk_sc_person_to_sc_documents FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2737 (class 2606 OID 306656)
-- Name: fk_service_or_product_partner; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_services_or_products
    ADD CONSTRAINT fk_service_or_product_partner FOREIGN KEY (id_partner) REFERENCES sc_partner(id_partner);


--
-- TOC entry 2756 (class 2606 OID 306661)
-- Name: fk_work_experience_employee; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_work_experience
    ADD CONSTRAINT fk_work_experience_employee FOREIGN KEY (id_employee) REFERENCES sc_employee(id_employee);


--
-- TOC entry 2646 (class 2606 OID 306666)
-- Name: id_competencies_employee; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_competencies
    ADD CONSTRAINT id_competencies_employee FOREIGN KEY (id_employee) REFERENCES sc_employee(id_employee);


-- Completed on 2016-04-28 11:54:46 COT

--
-- PostgreSQL database dump complete
--

