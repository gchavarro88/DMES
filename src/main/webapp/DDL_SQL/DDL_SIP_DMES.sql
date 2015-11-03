--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2015-11-03 14:02:59 COT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 6 (class 2615 OID 230250)
-- Name: dmes; Type: SCHEMA; Schema: -; Owner: sipPrueba
--

CREATE SCHEMA dmes;


ALTER SCHEMA dmes OWNER TO "sipPrueba";

--
-- TOC entry 316 (class 3079 OID 11829)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2906 (class 0 OID 0)
-- Dependencies: 316
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = dmes, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 170 (class 1259 OID 230251)
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
-- TOC entry 171 (class 1259 OID 230257)
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
-- TOC entry 172 (class 1259 OID 230263)
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
-- TOC entry 173 (class 1259 OID 230269)
-- Name: ot_maintenance_schedule; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE ot_maintenance_schedule (
    id_schedule_maintenance numeric(18,0) NOT NULL,
    id_employee numeric(18,0),
    creation_date date NOT NULL,
    id_maintenance numeric(18,0) NOT NULL,
    end_date timestamp with time zone NOT NULL
);


ALTER TABLE dmes.ot_maintenance_schedule OWNER TO "sipPrueba";

--
-- TOC entry 174 (class 1259 OID 230272)
-- Name: sc_class_type; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_class_type (
    id_class_type numeric(18,0) NOT NULL,
    class_type character varying(100) NOT NULL,
    creation_date date NOT NULL,
    modify_date date NOT NULL
);


ALTER TABLE dmes.sc_class_type OWNER TO "sipPrueba";

--
-- TOC entry 175 (class 1259 OID 230275)
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
-- TOC entry 176 (class 1259 OID 230281)
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
-- TOC entry 177 (class 1259 OID 230287)
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
-- TOC entry 178 (class 1259 OID 230290)
-- Name: sc_distribution_unit; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_distribution_unit (
    id_distribution_unit numeric(18,0) NOT NULL,
    acronym character varying(20) NOT NULL,
    description character varying(200)
);


ALTER TABLE dmes.sc_distribution_unit OWNER TO "sipPrueba";

--
-- TOC entry 179 (class 1259 OID 230293)
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
-- TOC entry 180 (class 1259 OID 230299)
-- Name: sc_employee; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_employee (
    id_employee numeric(18,0) NOT NULL,
    "position" character varying(100) NOT NULL,
    formation character varying(100) NOT NULL,
    admission_date date NOT NULL,
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
-- TOC entry 181 (class 1259 OID 230302)
-- Name: sc_factory_location; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_factory_location (
    id_factory_location numeric(18,0) NOT NULL,
    location character varying(200) NOT NULL,
    description character varying(2000)
);


ALTER TABLE dmes.sc_factory_location OWNER TO "sipPrueba";

--
-- TOC entry 182 (class 1259 OID 230308)
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
-- TOC entry 183 (class 1259 OID 230314)
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
-- TOC entry 184 (class 1259 OID 230320)
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
-- TOC entry 185 (class 1259 OID 230326)
-- Name: sc_input_equivalence; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_input_equivalence (
    id_input_equivalence numeric(18,0) NOT NULL,
    id_input numeric(18,0) NOT NULL,
    id_input_referenced numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_input_equivalence OWNER TO "sipPrueba";

--
-- TOC entry 186 (class 1259 OID 230329)
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
-- TOC entry 187 (class 1259 OID 230335)
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
-- TOC entry 188 (class 1259 OID 230341)
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
-- TOC entry 189 (class 1259 OID 230347)
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
-- TOC entry 190 (class 1259 OID 230353)
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
    path_picture character varying(2000)
);


ALTER TABLE dmes.sc_machine OWNER TO "sipPrueba";

--
-- TOC entry 191 (class 1259 OID 230359)
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
-- TOC entry 192 (class 1259 OID 230362)
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
-- TOC entry 193 (class 1259 OID 230368)
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
-- TOC entry 194 (class 1259 OID 230374)
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
-- TOC entry 195 (class 1259 OID 230380)
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
-- TOC entry 196 (class 1259 OID 230383)
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
-- TOC entry 197 (class 1259 OID 230389)
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
-- TOC entry 198 (class 1259 OID 230392)
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
-- TOC entry 199 (class 1259 OID 230398)
-- Name: sc_maintenance_clasification; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_maintenance_clasification (
    id_maintenance_clasification numeric(18,0) NOT NULL,
    clasification character varying(200) NOT NULL
);


ALTER TABLE dmes.sc_maintenance_clasification OWNER TO "sipPrueba";

--
-- TOC entry 200 (class 1259 OID 230401)
-- Name: sc_maintenance_damage; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_maintenance_damage (
    id_maintenance_damage numeric(18,0) NOT NULL,
    damage character varying(200) NOT NULL
);


ALTER TABLE dmes.sc_maintenance_damage OWNER TO "sipPrueba";

--
-- TOC entry 201 (class 1259 OID 230404)
-- Name: sc_maintenance_state; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_maintenance_state (
    id_maintenance_state numeric(18,0) NOT NULL,
    state character varying(200) NOT NULL
);


ALTER TABLE dmes.sc_maintenance_state OWNER TO "sipPrueba";

--
-- TOC entry 202 (class 1259 OID 230407)
-- Name: sc_measure_unit; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_measure_unit (
    id_measure numeric(18,0) NOT NULL,
    acronym character varying(20),
    type character varying(200) NOT NULL
);


ALTER TABLE dmes.sc_measure_unit OWNER TO "sipPrueba";

--
-- TOC entry 203 (class 1259 OID 230410)
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
-- TOC entry 204 (class 1259 OID 230416)
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
-- TOC entry 205 (class 1259 OID 230419)
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
-- TOC entry 206 (class 1259 OID 230422)
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
-- TOC entry 207 (class 1259 OID 230428)
-- Name: sc_packing_unit; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_packing_unit (
    id_packing numeric(18,0) NOT NULL,
    description character varying(200),
    acronym character varying(20) NOT NULL
);


ALTER TABLE dmes.sc_packing_unit OWNER TO "sipPrueba";

--
-- TOC entry 208 (class 1259 OID 230431)
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
-- TOC entry 209 (class 1259 OID 230434)
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
-- TOC entry 210 (class 1259 OID 230440)
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
-- TOC entry 211 (class 1259 OID 230446)
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
-- TOC entry 212 (class 1259 OID 230452)
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
-- TOC entry 213 (class 1259 OID 230455)
-- Name: sc_priority; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_priority (
    id_priority numeric(18,0) NOT NULL,
    name character varying(200) NOT NULL,
    description character varying(200)
);


ALTER TABLE dmes.sc_priority OWNER TO "sipPrueba";

--
-- TOC entry 214 (class 1259 OID 230458)
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
-- TOC entry 215 (class 1259 OID 230464)
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
-- TOC entry 216 (class 1259 OID 230467)
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
-- TOC entry 217 (class 1259 OID 230473)
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
-- TOC entry 218 (class 1259 OID 230476)
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
-- TOC entry 219 (class 1259 OID 230482)
-- Name: sc_process_type; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_process_type (
    id_process_type numeric(18,0) NOT NULL,
    description character varying(200),
    type character varying(200) NOT NULL
);


ALTER TABLE dmes.sc_process_type OWNER TO "sipPrueba";

--
-- TOC entry 220 (class 1259 OID 230485)
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
-- TOC entry 221 (class 1259 OID 230491)
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
-- TOC entry 222 (class 1259 OID 230497)
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
    id_partner numeric(18,0) NOT NULL,
    id_location numeric(18,0) NOT NULL,
    manufacturing_time numeric(18,0) NOT NULL,
    description character varying(200) NOT NULL,
    id_product_dimension numeric(18,0) NOT NULL,
    id_stock numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_product_formulation OWNER TO "sipPrueba";

--
-- TOC entry 2907 (class 0 OID 0)
-- Dependencies: 222
-- Name: COLUMN sc_product_formulation.id_priority; Type: COMMENT; Schema: dmes; Owner: sipPrueba
--

COMMENT ON COLUMN sc_product_formulation.id_priority IS '
';


--
-- TOC entry 2908 (class 0 OID 0)
-- Dependencies: 222
-- Name: COLUMN sc_product_formulation.id_partner; Type: COMMENT; Schema: dmes; Owner: sipPrueba
--

COMMENT ON COLUMN sc_product_formulation.id_partner IS '
 ';


--
-- TOC entry 223 (class 1259 OID 230503)
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
-- TOC entry 224 (class 1259 OID 230509)
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
-- TOC entry 225 (class 1259 OID 230515)
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
-- TOC entry 226 (class 1259 OID 230521)
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
-- TOC entry 227 (class 1259 OID 230527)
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
-- TOC entry 228 (class 1259 OID 230533)
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
-- TOC entry 229 (class 1259 OID 230536)
-- Name: sc_stop_machine; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_stop_machine (
    id_stop_machine numeric(18,0) NOT NULL,
    id_maintenance numeric(18,0) NOT NULL,
    creation_date timestamp with time zone NOT NULL,
    reason character varying(400),
    duration_estimate numeric(18,0) NOT NULL,
    end_date timestamp with time zone,
    duration_real numeric(18,0)
);


ALTER TABLE dmes.sc_stop_machine OWNER TO "sipPrueba";

--
-- TOC entry 230 (class 1259 OID 230539)
-- Name: sc_store; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_store (
    id_store numeric(18,0) NOT NULL,
    name character varying(2000) NOT NULL
);


ALTER TABLE dmes.sc_store OWNER TO "sipPrueba";

--
-- TOC entry 231 (class 1259 OID 230545)
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
-- TOC entry 232 (class 1259 OID 230551)
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
-- TOC entry 233 (class 1259 OID 230554)
-- Name: sc_store_order_state; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_store_order_state (
    id_state numeric(18,0) NOT NULL,
    description character varying(200) NOT NULL
);


ALTER TABLE dmes.sc_store_order_state OWNER TO "sipPrueba";

--
-- TOC entry 234 (class 1259 OID 230557)
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
-- TOC entry 235 (class 1259 OID 230563)
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
-- TOC entry 236 (class 1259 OID 230566)
-- Name: sc_store_requisition_state; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_store_requisition_state (
    id_state numeric(18,0) NOT NULL,
    description character varying(200) NOT NULL
);


ALTER TABLE dmes.sc_store_requisition_state OWNER TO "sipPrueba";

--
-- TOC entry 237 (class 1259 OID 230569)
-- Name: sc_time; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_time (
    id_time numeric(18,0) NOT NULL,
    acronym character varying(200) NOT NULL,
    minutes numeric(18,0)
);


ALTER TABLE dmes.sc_time OWNER TO "sipPrueba";

--
-- TOC entry 238 (class 1259 OID 230572)
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
-- TOC entry 239 (class 1259 OID 230578)
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
-- TOC entry 240 (class 1259 OID 230584)
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
-- TOC entry 241 (class 1259 OID 230590)
-- Name: sc_turn; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_turn (
    id_turn numeric(18,0) NOT NULL,
    description character varying(100) NOT NULL,
    hour_amount numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_turn OWNER TO "sipPrueba";

--
-- TOC entry 242 (class 1259 OID 230593)
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
-- TOC entry 243 (class 1259 OID 230596)
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
-- TOC entry 244 (class 1259 OID 230599)
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
-- TOC entry 245 (class 1259 OID 230602)
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
-- TOC entry 246 (class 1259 OID 230605)
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
-- TOC entry 247 (class 1259 OID 230607)
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
-- TOC entry 248 (class 1259 OID 230609)
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
-- TOC entry 249 (class 1259 OID 230611)
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
-- TOC entry 250 (class 1259 OID 230613)
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
-- TOC entry 251 (class 1259 OID 230615)
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
-- TOC entry 252 (class 1259 OID 230617)
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
-- TOC entry 253 (class 1259 OID 230619)
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
-- TOC entry 254 (class 1259 OID 230621)
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
-- TOC entry 255 (class 1259 OID 230623)
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
-- TOC entry 256 (class 1259 OID 230625)
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
-- TOC entry 257 (class 1259 OID 230627)
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
-- TOC entry 258 (class 1259 OID 230629)
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
-- TOC entry 259 (class 1259 OID 230631)
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
-- TOC entry 260 (class 1259 OID 230633)
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
-- TOC entry 261 (class 1259 OID 230635)
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
-- TOC entry 262 (class 1259 OID 230637)
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
-- TOC entry 263 (class 1259 OID 230639)
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
-- TOC entry 264 (class 1259 OID 230641)
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
-- TOC entry 265 (class 1259 OID 230643)
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
-- TOC entry 266 (class 1259 OID 230645)
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
-- TOC entry 267 (class 1259 OID 230647)
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
-- TOC entry 268 (class 1259 OID 230649)
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
-- TOC entry 269 (class 1259 OID 230651)
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
-- TOC entry 270 (class 1259 OID 230653)
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
-- TOC entry 271 (class 1259 OID 230655)
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
-- TOC entry 272 (class 1259 OID 230657)
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
-- TOC entry 273 (class 1259 OID 230659)
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
-- TOC entry 274 (class 1259 OID 230661)
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
-- TOC entry 275 (class 1259 OID 230663)
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
-- TOC entry 276 (class 1259 OID 230665)
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
-- TOC entry 277 (class 1259 OID 230667)
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
-- TOC entry 278 (class 1259 OID 230669)
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
-- TOC entry 279 (class 1259 OID 230671)
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
-- TOC entry 280 (class 1259 OID 230673)
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
-- TOC entry 281 (class 1259 OID 230675)
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
-- TOC entry 282 (class 1259 OID 230677)
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
-- TOC entry 283 (class 1259 OID 230679)
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
-- TOC entry 284 (class 1259 OID 230681)
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
-- TOC entry 285 (class 1259 OID 230683)
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
-- TOC entry 286 (class 1259 OID 230685)
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
-- TOC entry 287 (class 1259 OID 230687)
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
-- TOC entry 288 (class 1259 OID 230689)
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
-- TOC entry 289 (class 1259 OID 230691)
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
-- TOC entry 290 (class 1259 OID 230693)
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
-- TOC entry 291 (class 1259 OID 230695)
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
-- TOC entry 292 (class 1259 OID 230697)
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
-- TOC entry 293 (class 1259 OID 230699)
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
-- TOC entry 294 (class 1259 OID 230701)
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
-- TOC entry 295 (class 1259 OID 230703)
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
-- TOC entry 296 (class 1259 OID 230705)
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
-- TOC entry 297 (class 1259 OID 230707)
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
-- TOC entry 298 (class 1259 OID 230709)
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
-- TOC entry 299 (class 1259 OID 230711)
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
-- TOC entry 300 (class 1259 OID 230713)
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
-- TOC entry 301 (class 1259 OID 230715)
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
-- TOC entry 302 (class 1259 OID 230717)
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
-- TOC entry 303 (class 1259 OID 230719)
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
-- TOC entry 304 (class 1259 OID 230721)
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
-- TOC entry 305 (class 1259 OID 230723)
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
-- TOC entry 306 (class 1259 OID 230725)
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
-- TOC entry 307 (class 1259 OID 230727)
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
-- TOC entry 308 (class 1259 OID 230729)
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
-- TOC entry 309 (class 1259 OID 230731)
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
-- TOC entry 310 (class 1259 OID 230733)
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
-- TOC entry 311 (class 1259 OID 230735)
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
-- TOC entry 312 (class 1259 OID 230737)
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
-- TOC entry 313 (class 1259 OID 230739)
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
-- TOC entry 314 (class 1259 OID 230741)
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
-- TOC entry 315 (class 1259 OID 230743)
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
-- TOC entry 2755 (class 0 OID 230251)
-- Dependencies: 170
-- Data for Name: ot_maintenance; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO ot_maintenance (id_maintenance, id_machine_part, id_priority, description, id_maintenance_clasification, id_maintenance_state, id_workforce, duration, description_damage, id_maintenance_damage, creation_date, response_date, end_date, id_maintenance_schedule) VALUES (28, 10, 2, '', 1, 1, 20, 0, '', 1, '2015-11-01 00:00:00-05', NULL, '2015-11-01 04:17:00-05', '18,19,20,21,22,23,24,25');
INSERT INTO ot_maintenance (id_maintenance, id_machine_part, id_priority, description, id_maintenance_clasification, id_maintenance_state, id_workforce, duration, description_damage, id_maintenance_damage, creation_date, response_date, end_date, id_maintenance_schedule) VALUES (29, 11, 2, '2 Minutos ', 1, 1, 21, 0, '', 2, '2015-11-01 01:00:00.141-05', NULL, '2015-11-01 01:02:00.141-05', '26');


--
-- TOC entry 2756 (class 0 OID 230257)
-- Dependencies: 171
-- Data for Name: ot_maintenance_corrective; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO ot_maintenance_corrective (id_maintenance_corrective, name, description, id_maintenance, duration) VALUES (16, 'Correctivo_Máquina 2_3320151101124746', '2 Minutos ', 29, NULL);


--
-- TOC entry 2757 (class 0 OID 230263)
-- Dependencies: 172
-- Data for Name: ot_maintenance_preventive; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO ot_maintenance_preventive (id_maintenance_preventive, name, description, id_maintenance, type_frequency, amount_schedule, duration) VALUES (5, 'Preventivo_Máquina 2_motor20151031182736', '', 28, 'DAILY', 8, NULL);


--
-- TOC entry 2758 (class 0 OID 230269)
-- Dependencies: 173
-- Data for Name: ot_maintenance_schedule; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (18, 10, '2015-11-01', 28, '2015-11-01 00:00:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (19, 10, '2015-11-02', 28, '2015-11-02 00:00:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (20, 10, '2015-11-03', 28, '2015-11-03 00:00:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (21, 10, '2015-11-04', 28, '2015-11-04 00:00:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (22, 10, '2015-11-05', 28, '2015-11-05 00:00:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (23, 10, '2015-11-06', 28, '2015-11-06 00:00:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (24, 10, '2015-11-07', 28, '2015-11-07 00:00:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (25, 10, '2015-11-08', 28, '2015-11-08 00:00:00-05');
INSERT INTO ot_maintenance_schedule (id_schedule_maintenance, id_employee, creation_date, id_maintenance, end_date) VALUES (26, 9, '2015-11-01', 29, '2015-11-01 01:02:00-05');


--
-- TOC entry 2759 (class 0 OID 230272)
-- Dependencies: 174
-- Data for Name: sc_class_type; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_class_type (id_class_type, class_type, creation_date, modify_date) VALUES (1, 'clasificacion maquina', '2014-08-12', '2014-08-12');
INSERT INTO sc_class_type (id_class_type, class_type, creation_date, modify_date) VALUES (2, 'prioridad', '2014-08-12', '2014-08-12');
INSERT INTO sc_class_type (id_class_type, class_type, creation_date, modify_date) VALUES (3, 'medida (dias, meses, años)', '2014-08-12', '2014-08-12');
INSERT INTO sc_class_type (id_class_type, class_type, creation_date, modify_date) VALUES (4, 'moneda', '2014-08-12', '2014-08-12');
INSERT INTO sc_class_type (id_class_type, class_type, creation_date, modify_date) VALUES (5, 'medida (peso)', '2014-08-12', '2014-08-12');
INSERT INTO sc_class_type (id_class_type, class_type, creation_date, modify_date) VALUES (6, 'medida (alto)', '2014-08-12', '2014-08-12');
INSERT INTO sc_class_type (id_class_type, class_type, creation_date, modify_date) VALUES (8, 'medida (corriente)', '2014-08-12', '2014-08-12');
INSERT INTO sc_class_type (id_class_type, class_type, creation_date, modify_date) VALUES (7, 'medida (potencial)', '2014-08-12', '2014-08-12');
INSERT INTO sc_class_type (id_class_type, class_type, creation_date, modify_date) VALUES (9, 'medida (voltage)', '2014-08-12', '2014-08-12');


--
-- TOC entry 2760 (class 0 OID 230275)
-- Dependencies: 175
-- Data for Name: sc_competencies; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_competencies (id_competencies, tittle, description, id_employee) VALUES (6, 'Liderazgo', 'Liderazgo', 8);
INSERT INTO sc_competencies (id_competencies, tittle, description, id_employee) VALUES (7, 'Agresividad de mercadeo', 'Agresividad de mercadeo', 9);
INSERT INTO sc_competencies (id_competencies, tittle, description, id_employee) VALUES (8, 'toleracia al estres', 'trabajo bajo presion., con exelentes resultados', 15);
INSERT INTO sc_competencies (id_competencies, tittle, description, id_employee) VALUES (9, 'comunicacion', 'tiene excelente comunicacion en trabajo en grupo ', 16);


--
-- TOC entry 2761 (class 0 OID 230281)
-- Dependencies: 176
-- Data for Name: sc_constants_load_files; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_constants_load_files (id_constants_load_file, max_size_file, extension, path) VALUES (1, 10, '/(\.|\/)(pdf|xsl|doc|xlsx|docx|txt|pps|ppt|pptx|ppsx)$/', NULL);


--
-- TOC entry 2762 (class 0 OID 230287)
-- Dependencies: 177
-- Data for Name: sc_cost_center; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (4, 'Contabilidad', '2121331', '2015-02-12', NULL);
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (2, 'Mercadeo', '11212545', '2014-12-14', NULL);
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (1, 'Compras', '14454545', '2014-12-14', '2014-12-13 18:00:00-05');
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


--
-- TOC entry 2763 (class 0 OID 230290)
-- Dependencies: 178
-- Data for Name: sc_distribution_unit; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_distribution_unit (id_distribution_unit, acronym, description) VALUES (1, 'botella', 'una botella de cajas');
INSERT INTO sc_distribution_unit (id_distribution_unit, acronym, description) VALUES (2, 'Libras', '25 libras por cajas');
INSERT INTO sc_distribution_unit (id_distribution_unit, acronym, description) VALUES (3, 'Litros Cilindricos', 'Cantidad de litros en un cilindro');
INSERT INTO sc_distribution_unit (id_distribution_unit, acronym, description) VALUES (4, 'unid', 'unidad');


--
-- TOC entry 2764 (class 0 OID 230293)
-- Dependencies: 179
-- Data for Name: sc_documents; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2765 (class 0 OID 230299)
-- Dependencies: 180
-- Data for Name: sc_employee; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, creation_date, modify_date, id_person, id_turn) VALUES (15, 'navegador', 'navegador', '2015-03-09', NULL, 'Y', 1230000.00, 45909.00, '2015-08-11', NULL, 17, NULL);
INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, creation_date, modify_date, id_person, id_turn) VALUES (10, 'Personal de Pruebas', 'NA', '2015-04-01', NULL, 'Y', 1.00, 1.00, '2015-04-19', '2015-08-14 17:00:00-05', 11, NULL);
INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, creation_date, modify_date, id_person, id_turn) VALUES (9, 'Administradora General', 'Ingeniería Electrónica', '2015-04-01', NULL, 'Y', 500000.00, 1200.00, '2015-04-19', '2015-08-14 17:00:00-05', 14, NULL);
INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, creation_date, modify_date, id_person, id_turn) VALUES (13, 'Super administrador', '', '2015-08-11', NULL, 'Y', 3000000.00, 10000.00, '2015-08-11', '2015-08-14 17:00:00-05', 22, NULL);
INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, creation_date, modify_date, id_person, id_turn) VALUES (14, 'operarios', '', '2015-08-11', NULL, 'Y', 8.00, 1000.00, '2015-08-11', '2015-08-14 17:00:00-05', 19, NULL);
INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, creation_date, modify_date, id_person, id_turn) VALUES (16, 'empleado', 'g', '2015-08-14', NULL, 'Y', 123450.00, 34000.00, '2015-08-15', NULL, 27, NULL);
INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, creation_date, modify_date, id_person, id_turn) VALUES (12, 'ventas', 'ingeiera electronica', '2015-08-10', NULL, 'Y', 2.00, 20.00, '2015-08-10', '2015-08-28 17:00:00-05', 3, 2);
INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, creation_date, modify_date, id_person, id_turn) VALUES (8, 'Administrador del Mundo', 'Ingeniero de Sistemas', '2014-11-10', NULL, 'Y', 30.00, 3233.00, '2014-11-19', '2015-08-28 17:00:00-05', 1, 1);


--
-- TOC entry 2766 (class 0 OID 230302)
-- Dependencies: 181
-- Data for Name: sc_factory_location; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_factory_location (id_factory_location, location, description) VALUES (1, 'Cortadoras', 'Sección de maquinas cortadoras');
INSERT INTO sc_factory_location (id_factory_location, location, description) VALUES (2, 'Empaquetadoras', 'Sección de máquinas empaquetadoras');


--
-- TOC entry 2767 (class 0 OID 230308)
-- Dependencies: 182
-- Data for Name: sc_input; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_input (id_input, type_material, expiry_date, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_input_dimension, id_packing, id_money, total_amount_distribution, id_distribution_unit, distribution_amount, distribution_value, id_location) VALUES (15, 'ninguno', '2015-04-29 17:00:00-05', 1, 'ninguno', 1000, '/home/gchavarro88/inputs_filePath/img/producto1.png', 4, '655555', '2015-04-24 17:00:00-05', 'producto pepe', 14, 2, 25, 4, 1, 51100, 2, 100, 10.00, 2);
INSERT INTO sc_input (id_input, type_material, expiry_date, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_input_dimension, id_packing, id_money, total_amount_distribution, id_distribution_unit, distribution_amount, distribution_value, id_location) VALUES (14, '23', NULL, 1, '33', 33, '/home/gchavarro88/inputs_filePath/img/corre papeles.jpg', 3, '33', '2015-04-24 17:00:00-05', 'Prueba Jhon', 13, 2, 21, 4, 1, 23, 1, 23, 1.00, 6);
INSERT INTO sc_input (id_input, type_material, expiry_date, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_input_dimension, id_packing, id_money, total_amount_distribution, id_distribution_unit, distribution_amount, distribution_value, id_location) VALUES (16, 'plomo', NULL, 15, 'complex', 2000, '/home/gchavarro88/inputs_filePath/img/desorientado.jpg', 1, 'plom098', '2015-04-24 17:00:00-05', 'cercha', 15, 1, 26, 3, 2, 500, 1, 10, 200.00, 12);
INSERT INTO sc_input (id_input, type_material, expiry_date, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_input_dimension, id_packing, id_money, total_amount_distribution, id_distribution_unit, distribution_amount, distribution_value, id_location) VALUES (8, 'Tipo de Prueba de Material', '2015-08-25 17:00:00-05', 1, 'Nike', 1200, '/home/gchavarro88/inputs_filePath/img/producto1.jpg', 4, '1212', '2015-03-18 18:00:00-05', 'Prueba de Insumo', 7, 1, 7, 4, 3, 123, 1, 3, 400.00, 5);
INSERT INTO sc_input (id_input, type_material, expiry_date, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_input_dimension, id_packing, id_money, total_amount_distribution, id_distribution_unit, distribution_amount, distribution_value, id_location) VALUES (17, 'verdura', '2016-02-25 18:00:00-05', 19, 'planton', 800000, ' ', 13, 'pl09oi', '2015-08-16 17:00:00-05', 'platano', 36, 1, 57, 4, 3, 1000, 1, 10, 80000.00, 3);
INSERT INTO sc_input (id_input, type_material, expiry_date, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_input_dimension, id_packing, id_money, total_amount_distribution, id_distribution_unit, distribution_amount, distribution_value, id_location) VALUES (18, 'liquido ', '2016-01-06 18:00:00-05', 16, 'brisa', 400000, ' ', 4, '5ty65', '2015-08-16 17:00:00-05', 'water', 37, 2, 58, 5, 1, 1440, 1, 10, 40000.00, 6);


--
-- TOC entry 2768 (class 0 OID 230314)
-- Dependencies: 183
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


--
-- TOC entry 2769 (class 0 OID 230320)
-- Dependencies: 184
-- Data for Name: sc_input_documents; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_input_documents (id_input_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_input) VALUES (10, '/home/gchavarro88/inputs_filePath/docs', '222', '2015-04-24 17:00:00-05', 'GSI-R-29 Formulario PQR V2.pdf', 'jguerrero', 'application/pdf', 14);
INSERT INTO sc_input_documents (id_input_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_input) VALUES (11, '/home/gchavarro88/inputs_filePath/docs', 'tiii', '2015-04-24 17:00:00-05', 'MANUAL DE CALIDAD FELIX DE BEDOUT.doc', 'jguerrero', 'application/octet-stream', 15);
INSERT INTO sc_input_documents (id_input_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_input) VALUES (12, '/home/gchavarro88/inputs_filePath/docs', 'plande trabajo', '2015-04-24 17:00:00-05', 'Plan de Trabajo Modalides version 1-2012 (1).docx', 'yaconcha', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 16);


--
-- TOC entry 2770 (class 0 OID 230326)
-- Dependencies: 185
-- Data for Name: sc_input_equivalence; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2771 (class 0 OID 230329)
-- Dependencies: 186
-- Data for Name: sc_input_feactures; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_input_feactures (id_input_feactures, tittle, description, id_input) VALUES (14, 'rojo', 'prueba', 16);
INSERT INTO sc_input_feactures (id_input_feactures, tittle, description, id_input) VALUES (15, 'rojo', 'prueba', 16);
INSERT INTO sc_input_feactures (id_input_feactures, tittle, description, id_input) VALUES (16, 'hoka5', 'nadasergg', 17);
INSERT INTO sc_input_feactures (id_input_feactures, tittle, description, id_input) VALUES (17, 'hola5', 'prueba3', 18);


--
-- TOC entry 2772 (class 0 OID 230335)
-- Dependencies: 187
-- Data for Name: sc_input_observations; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_input_observations (id_input_observation, tittle, description, id_input) VALUES (9, 'hola6', 'nadasd', 17);
INSERT INTO sc_input_observations (id_input_observation, tittle, description, id_input) VALUES (10, 'hola7', 'buenas', 17);
INSERT INTO sc_input_observations (id_input_observation, tittle, description, id_input) VALUES (11, 'hola', 'prueba', 18);


--
-- TOC entry 2773 (class 0 OID 230341)
-- Dependencies: 188
-- Data for Name: sc_input_specifications; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_input_specifications (id_input_specifications, description, tittle, creation_date, id_input) VALUES (16, 'v', 'm', '2015-04-24 17:00:00-05', 16);
INSERT INTO sc_input_specifications (id_input_specifications, description, tittle, creation_date, id_input) VALUES (17, 'prueba', 'hola', '2015-08-16 17:00:00-05', 17);
INSERT INTO sc_input_specifications (id_input_specifications, description, tittle, creation_date, id_input) VALUES (18, 'preuba2', 'hola2', '2015-08-16 17:00:00-05', 17);
INSERT INTO sc_input_specifications (id_input_specifications, description, tittle, creation_date, id_input) VALUES (19, 'prueba', 'hola', '2015-08-16 17:00:00-05', 18);
INSERT INTO sc_input_specifications (id_input_specifications, description, tittle, creation_date, id_input) VALUES (20, 'prueb1', 'hola2', '2015-08-16 17:00:00-05', 18);


--
-- TOC entry 2774 (class 0 OID 230347)
-- Dependencies: 189
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


--
-- TOC entry 2775 (class 0 OID 230353)
-- Dependencies: 190
-- Data for Name: sc_machine; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_machine (id_machine, name, hour_value, description, id_priority, id_time, useful_life, id_partner, id_cost_center, id_money, id_location, mark, serie, type, clasification, id_dimension, path_picture) VALUES (2, 'Máquina 2', 2000.00, 'Maquina para cortar tela', 2, 1, 23, 1, 2, 2, 2, 'Chromium', 'CE233', 'Cortadora', 'Electricas', 7, NULL);
INSERT INTO sc_machine (id_machine, name, hour_value, description, id_priority, id_time, useful_life, id_partner, id_cost_center, id_money, id_location, mark, serie, type, clasification, id_dimension, path_picture) VALUES (10, 'tapadora RD3', 60000.00, 'fsf', 1, 4, 5, 18, 13, 2, 1, 'cuerperty', 'dfw4', 'Troqueleadoras', 'Eléctrica', 65, '/home/ubuntu/machine_filePath/img/10428615_764352153627787_7203881861240549227_n.jpg');
INSERT INTO sc_machine (id_machine, name, hour_value, description, id_priority, id_time, useful_life, id_partner, id_cost_center, id_money, id_location, mark, serie, type, clasification, id_dimension, path_picture) VALUES (11, 'rotador', 70000.00, 'adf', 1, 3, 56, 17, 13, 3, 2, 'sentes', 't7y7', 'Soldadoras', 'Eléctrica', 66, '/home/ubuntu/machine_filePath/img/1559846_725206147504724_1463329936_n.jpg');
INSERT INTO sc_machine (id_machine, name, hour_value, description, id_priority, id_time, useful_life, id_partner, id_cost_center, id_money, id_location, mark, serie, type, clasification, id_dimension, path_picture) VALUES (7, 'Diseñadora de Software', 12000.00, 'Máquina especializada en el diseño de software', 2, 3, 2, 19, 13, 1, 2, 'Samsung', 'S4AC23', 'Troqueleadoras', 'Eléctrica', 62, '/home/ubuntu/machine_filePath/img/Selección_652.png');
INSERT INTO sc_machine (id_machine, name, hour_value, description, id_priority, id_time, useful_life, id_partner, id_cost_center, id_money, id_location, mark, serie, type, clasification, id_dimension, path_picture) VALUES (3, 'Máquina 4', 4000.00, 'Maquina para empacar', 3, 2, 23, 1, 3, 3, 1, 'Titanium', 'AT3334', 'Cortadoras', 'Mecánica', 7, '/home/ubuntu/machine_filePath/img/producto1.png');
INSERT INTO sc_machine (id_machine, name, hour_value, description, id_priority, id_time, useful_life, id_partner, id_cost_center, id_money, id_location, mark, serie, type, clasification, id_dimension, path_picture) VALUES (14, 'Enrolladora', 120000.00, 'Prueba de Sip', 2, 4, 3, 20, 1, 2, 2, 'Shimano', 'AC23445', 'Troqueleadoras', 'Eléctrica', 69, '/home/ubuntu/machine_filePath/img/yoe.jpg');
INSERT INTO sc_machine (id_machine, name, hour_value, description, id_priority, id_time, useful_life, id_partner, id_cost_center, id_money, id_location, mark, serie, type, clasification, id_dimension, path_picture) VALUES (1, 'Máquina 1', 1000.00, 'Maquina para Comidas', 1, 1, 12, 1, 1, 1, 1, 'Shines', 'SA233', 'Cortadoras', 'Eléctrica', 7, '/home/ubuntu/machine_filePath/img/producto2.jpg');


--
-- TOC entry 2776 (class 0 OID 230359)
-- Dependencies: 191
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
-- TOC entry 2777 (class 0 OID 230362)
-- Dependencies: 192
-- Data for Name: sc_machine_conditions; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_machine_conditions (id_condition, type, description, id_machine) VALUES (1, 'Interna', 'jhgjgjh', 1);
INSERT INTO sc_machine_conditions (id_condition, type, description, id_machine) VALUES (2, 'Interna', 'jkhjkl', 3);


--
-- TOC entry 2778 (class 0 OID 230368)
-- Dependencies: 193
-- Data for Name: sc_machine_document; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_machine_document (id_machine_document, document_path, document_tittle, creation_date, document_name, upload_by, id_machine, document_type) VALUES (1, '/home/ubuntu/machine_filePath/docs', 'Documento de prueba', '2015-09-11 17:00:00-05', 'Certificado HTML 5.pdf', 'guschaor', 7, 'application/stream');
INSERT INTO sc_machine_document (id_machine_document, document_path, document_tittle, creation_date, document_name, upload_by, id_machine, document_type) VALUES (4, '/home/ubuntu/machine_filePath/docs', 'tuyo', '2015-09-13 17:00:00-05', '11-08-09 revision bd.docx', 'yaconcha', 10, 'application/vnd.openxmlformats-officedocument.wordprocessingml.document');
INSERT INTO sc_machine_document (id_machine_document, document_path, document_tittle, creation_date, document_name, upload_by, id_machine, document_type) VALUES (5, '/home/ubuntu/machine_filePath/docs', 'fjhf', '2015-09-16 17:00:00-05', 'MANUAL DE CALIDAD FELIX DE BEDOUT.doc', 'jguerrero', 1, 'application/msword');


--
-- TOC entry 2779 (class 0 OID 230374)
-- Dependencies: 194
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
-- TOC entry 2780 (class 0 OID 230380)
-- Dependencies: 195
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
-- TOC entry 2781 (class 0 OID 230383)
-- Dependencies: 196
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
-- TOC entry 2782 (class 0 OID 230389)
-- Dependencies: 197
-- Data for Name: sc_mails; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (9, 'notengo@notengo.com', 'No tiene', 11);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (12, 'nataliagiron99@gmail.com', 'Correo Personal', 13);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (13, 'dede@hdodk.com', 'Erróneo', 11);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (14, 'inica@hotmail.com', 'personal', 16);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (15, 'Camilo2007@hotmal.com', '', 17);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (16, 'leonardo@sip.com', 'empresarial', 18);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (17, 'jesuslopez@hotmail.com', '', 19);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (18, 'joseloberdaza@gmail.com', 'Correo personal', 20);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (20, 'jhon.guerrero.o@gmail.com', 'Personal', 22);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (24, 'correo@gmail.com', 'personal', 27);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (26, 'correo@gmail.com', 'personal', 29);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (27, 'correo@gmail.com', 'personal', 30);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (30, 'adrasaramis@gmail.com', '', 34);


--
-- TOC entry 2783 (class 0 OID 230392)
-- Dependencies: 198
-- Data for Name: sc_maintenance_activity; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_maintenance_activity (id_maintenance_activity, name, description, id_maintenance) VALUES (28, 'Limpiar Piñon', '', 28);
INSERT INTO sc_maintenance_activity (id_maintenance_activity, name, description, id_maintenance) VALUES (29, 'yrrtr', '', 29);


--
-- TOC entry 2784 (class 0 OID 230398)
-- Dependencies: 199
-- Data for Name: sc_maintenance_clasification; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_maintenance_clasification (id_maintenance_clasification, clasification) VALUES (1, 'Eléctrica');
INSERT INTO sc_maintenance_clasification (id_maintenance_clasification, clasification) VALUES (2, 'Mecánica');
INSERT INTO sc_maintenance_clasification (id_maintenance_clasification, clasification) VALUES (3, 'Hidráulica');
INSERT INTO sc_maintenance_clasification (id_maintenance_clasification, clasification) VALUES (4, 'Electrónica');
INSERT INTO sc_maintenance_clasification (id_maintenance_clasification, clasification) VALUES (5, 'Automática');
INSERT INTO sc_maintenance_clasification (id_maintenance_clasification, clasification) VALUES (6, 'Semiautomática');


--
-- TOC entry 2785 (class 0 OID 230401)
-- Dependencies: 200
-- Data for Name: sc_maintenance_damage; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_maintenance_damage (id_maintenance_damage, damage) VALUES (1, 'Mecánico');
INSERT INTO sc_maintenance_damage (id_maintenance_damage, damage) VALUES (2, 'Eléctrico');
INSERT INTO sc_maintenance_damage (id_maintenance_damage, damage) VALUES (3, 'Fallo de Equipo');
INSERT INTO sc_maintenance_damage (id_maintenance_damage, damage) VALUES (4, 'Fallo de Servidor');
INSERT INTO sc_maintenance_damage (id_maintenance_damage, damage) VALUES (5, 'Fallo de Digitación');


--
-- TOC entry 2786 (class 0 OID 230404)
-- Dependencies: 201
-- Data for Name: sc_maintenance_state; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_maintenance_state (id_maintenance_state, state) VALUES (1, 'PENDIENTE');
INSERT INTO sc_maintenance_state (id_maintenance_state, state) VALUES (2, 'FINALIZADO');


--
-- TOC entry 2787 (class 0 OID 230407)
-- Dependencies: 202
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


--
-- TOC entry 2788 (class 0 OID 230410)
-- Dependencies: 203
-- Data for Name: sc_module_permission; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (1, 'Gestión de Planta', NULL, 'bar.png', 'Home', -1, 'Help.jsf');
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
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (32, 'Mantenimiento', NULL, 'maquinas.png', 'Folder', 7, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (34, 'Preventivo', NULL, 'cargue_documentos.png', 'Item', 32, 'orders/maintenance/OtMaintenancePreventive.jsf');


--
-- TOC entry 2789 (class 0 OID 230416)
-- Dependencies: 204
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
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1296, 1, 'CRUD', 1);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1297, 1, 'CRUD', 7);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1298, 1, 'CRUD', 13);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1299, 1, 'CRUD', 15);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1303, 1, 'CRUD', 14);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1307, 1, 'CRUD', 16);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1310, 1, 'CRUD', 9);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1311, 1, 'CRUD', 11);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1315, 1, 'CRUD', 12);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1318, 1, 'CRUD', 10);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1319, 1, 'CRUD', 17);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1322, 1, 'CRUD', 18);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1323, 1, 'CRUD', 20);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1326, 1, 'CRUD', 21);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1327, 1, 'CRUD', 22);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1331, 1, 'CRUD', 19);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1335, 1, 'CRUD', 24);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1339, 1, 'CRUD', 25);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1343, 1, 'CRUD', 26);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1347, 1, 'CRUD', 27);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1350, 1, 'CRUD', 23);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1351, 1, 'CRUD', 28);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1355, 1, 'CRUD', 29);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1359, 1, 'CRUD', 30);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1363, 1, 'CRUD', 31);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1366, 1, 'CRUD', 32);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1367, 1, 'CRUD', 34);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1371, 1, 'CRUD', 33);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1448, 8, 'CRUD', 1);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1449, 8, 'CRUD', 7);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1450, 8, 'CRUD', 13);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1451, 8, 'CRUD', 15);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1455, 8, 'CRUD', 16);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1459, 8, 'CRUD', 14);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1462, 8, 'CRUD', 9);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1463, 8, 'CRUD', 12);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1467, 8, 'CRUD', 11);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1470, 8, 'CRUD', 10);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1471, 8, 'CRUD', 17);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1474, 8, 'CRUD', 18);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1475, 8, 'CRUD', 19);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1479, 8, 'CRUD', 20);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1482, 8, 'CRUD', 21);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1483, 8, 'CRUD', 22);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1487, 8, 'CRUD', 25);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1491, 8, 'CRUD', 24);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1495, 8, 'CRUD', 26);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1499, 8, 'CRUD', 27);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1502, 8, 'CRUD', 23);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1503, 8, 'CRUD', 28);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1507, 8, 'CRUD', 29);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1511, 8, 'CRUD', 30);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1515, 8, 'CRUD', 31);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1518, 8, 'CRUD', 32);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1519, 8, 'CRUD', 33);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (1523, 8, 'CRUD', 34);


--
-- TOC entry 2790 (class 0 OID 230419)
-- Dependencies: 205
-- Data for Name: sc_money; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_money (id_money, description, acronym, trm) VALUES (1, 'Dolar', '$', 2300.00);
INSERT INTO sc_money (id_money, description, acronym, trm) VALUES (2, 'Pesos', '$', 1.00);
INSERT INTO sc_money (id_money, description, acronym, trm) VALUES (3, 'Libra', '£', 1500.00);
INSERT INTO sc_money (id_money, description, acronym, trm) VALUES (4, 'Centavo', '¢', 0.01);
INSERT INTO sc_money (id_money, description, acronym, trm) VALUES (5, 'Yen', '¥', 400.00);


--
-- TOC entry 2791 (class 0 OID 230422)
-- Dependencies: 206
-- Data for Name: sc_operating_conditions; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2792 (class 0 OID 230428)
-- Dependencies: 207
-- Data for Name: sc_packing_unit; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_packing_unit (id_packing, description, acronym) VALUES (3, 'unidad de empaque', 'Caja');
INSERT INTO sc_packing_unit (id_packing, description, acronym) VALUES (5, 'Cilindro para gases', 'Cilindro');
INSERT INTO sc_packing_unit (id_packing, description, acronym) VALUES (4, 'arrobas', 'arroba');
INSERT INTO sc_packing_unit (id_packing, description, acronym) VALUES (6, 'Estibas', 'Estiba');
INSERT INTO sc_packing_unit (id_packing, description, acronym) VALUES (7, 'Cantidad medido en vasoso milimetricos', 'Vaso');


--
-- TOC entry 2793 (class 0 OID 230431)
-- Dependencies: 208
-- Data for Name: sc_partner; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_partner (id_partner, active, "position", web_page, creation_date, modify_date, id_person, company_name) VALUES (1, 'Y', 'Asesor', 'http://www.google.com', '2014-11-19', NULL, 13, 'Carvajal');
INSERT INTO sc_partner (id_partner, active, "position", web_page, creation_date, modify_date, id_person, company_name) VALUES (15, 'Y', 'Ingeniero Analista', '', '2014-11-21', NULL, 2, 'Tecnoquímicas');
INSERT INTO sc_partner (id_partner, active, "position", web_page, creation_date, modify_date, id_person, company_name) VALUES (16, 'Y', 'jefe venta', '', '2015-05-14', NULL, 16, 'complex');
INSERT INTO sc_partner (id_partner, active, "position", web_page, creation_date, modify_date, id_person, company_name) VALUES (17, 'Y', 'vendedor', '', '2015-08-15', NULL, 18, 'durman');
INSERT INTO sc_partner (id_partner, active, "position", web_page, creation_date, modify_date, id_person, company_name) VALUES (18, 'Y', 'prueba', '', '2015-08-15', NULL, 29, 'prueba');
INSERT INTO sc_partner (id_partner, active, "position", web_page, creation_date, modify_date, id_person, company_name) VALUES (19, 'Y', 'De ventas', '', '2015-08-15', NULL, 20, 'La 14');
INSERT INTO sc_partner (id_partner, active, "position", web_page, creation_date, modify_date, id_person, company_name) VALUES (20, 'Y', 'Asesor Comercial', 'www.jgb.com', '2015-08-15', NULL, 30, 'JGB');


--
-- TOC entry 2794 (class 0 OID 230434)
-- Dependencies: 209
-- Data for Name: sc_person; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (2, 'Cristian Camilo', 'Chaparro Cuadros', 23, 'Colombia ', 'Cali', NULL, 'Oeste de Cali', NULL, NULL, '/', '2014-09-22 17:00:00-05', NULL, 111111111111111111, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (11, 'Valentina', 'Trujillo Ocampo', 33, 'Colombia', 'Cali', 'Barrio Champañat', 'Carrera 28 # 9-52', 'Colegio 3 de primaria básica', 'Niña de Javier', '/', '2014-11-03 18:00:00-05', '2014-11-08 18:00:00-05', 11133333333, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (14, 'carlos', 'uzman', 18, 'colombia', 'cali', 'ksksks', 'calle 100', 'ooo', 'sssss', '/', '2014-11-29 18:00:00-05', NULL, 222222222, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (16, 'jaime', 'acosta', 23, 'colombia', 'cali', 'szvsfzv', 'calla 1 34#45', 'fdbdf', 'sdvsv', '/', '2015-05-13 17:00:00-05', NULL, 1609873, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (17, 'Camilo', 'Topo', 22, 'Colombia', 'Cali', '', 'Crr 92 # 3 a 30', '', '', '/', '2015-07-28 17:00:00-05', NULL, 8675545, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (18, 'leonardo jose', 'ordoñes ', 29, 'colombia', 'cali', 'tiene habilidades en la creacion de diseño arquitectonico ', ' calle 49·43 - 76', 'ing. industrial', '', '/', '2015-07-28 17:00:00-05', NULL, 166905680, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (19, 'Jesus', 'Lopez', 30, 'Colombia', 'Cali', '', 'Cr 87#4-8', '', '', '/', '2015-07-28 17:00:00-05', NULL, 6456456, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (20, 'Jose Raúl', 'Lover Daza', 28, 'Colombia', 'Cali', '', 'Calle 1 # 25 - 32 Palmira', 'Ingeniería de Sistemas', '', '/', '2015-07-28 17:00:00-05', NULL, 11302322459, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (27, 'Camilo', 'Topo', 22, 'Colombia', 'Cali', '', 'Calle 2 #3-5', 'Tecnico', '', '/', '2015-08-10 17:00:00-05', NULL, 6456454, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (29, 'jorge luis', 'PEÑA', 24, 'Colombia', 'Cali', '', 'Calle 2 #3-4', 'Tecnico', '', '/', '2015-08-10 17:00:00-05', NULL, 6456458, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (30, 'fernando', 'SAUCEDO', 25, 'Colombia', 'Cali', '', 'Calle 2 #3-3', 'Tecnico', '', '/', '2015-08-10 17:00:00-05', NULL, 6456459, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (13, 'Lizeth Nathalia', 'Girón López ', 18, 'Colombia', 'Cali', 'test', 'Calle 23 # Alfonso Bonilla Aragón', 'Ingeniería Agrícola', 'Persona interesada en conocer la empresa', '/', '2014-11-07 18:00:00-05', '2015-08-14 17:00:00-05', 1149493828, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (22, 'Jhon', 'Guerrero', 30, 'Colombia', 'Cali', '', 'Calle 100', 'Ingeniero Electrónico', '', '/', '2015-08-07 17:00:00-05', '2015-08-16 17:00:00-05', 945409696, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (1, 'Gustavo Adolfo', 'Chavarro Ortiz', 26, 'Colombia', 'Cali', '', 'Carrera 21 # 13-16', 'Ingeniero de Sistemas', '', '/', '2014-09-25 17:00:00-05', '2015-08-16 17:00:00-05', 1107046850, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (34, 'Montenegro', 'Solis', 34, 'Colombia', 'Bogotá', '', 'Calle 34 # 23 -34 ', 'Licenciatura en Portugués', '', '/', '2015-08-23 17:00:00-05', NULL, 111110303, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (3, 'Yoleidy', 'Aconcha', 26, 'Colombia', 'Cali', '', 'Carrera 103 Calle 49', 'Ingeniera Electrónica', '', '/home/gchavarro88/inputs_filePath/img/monja.jpg', '2014-10-20 17:00:00-05', '2015-08-23 17:00:00-05', 11111111111111, NULL);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification, "pathFile") VALUES (36, 'Felipe Alexander', 'Escobar Giraldo', 23, 'Venezuela', 'Caracas', '', 'Calle 44 av Cali', 'Gerente en ventas', '', '/home/gchavarro88/inputs_filePath/img/superman.png', '2015-08-23 17:00:00-05', NULL, 112040405, NULL);


--
-- TOC entry 2795 (class 0 OID 230440)
-- Dependencies: 210
-- Data for Name: sc_person_observations; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_person_observations (id_person_observations, tittle, observation, id_person) VALUES (10, 'Almuerza', 'almuerza', 11);
INSERT INTO sc_person_observations (id_person_observations, tittle, observation, id_person) VALUES (11, 'Mi novia', 'es la persona que me alegra las mañanas', 13);
INSERT INTO sc_person_observations (id_person_observations, tittle, observation, id_person) VALUES (12, 'Mi novia', 'es la persona que me alegra las mañanas', 13);
INSERT INTO sc_person_observations (id_person_observations, tittle, observation, id_person) VALUES (14, 'compañerismo', 'personal con caracteristicas de trabajo en grupo', 18);
INSERT INTO sc_person_observations (id_person_observations, tittle, observation, id_person) VALUES (17, 'observacion 2', 'observacion 2', 27);
INSERT INTO sc_person_observations (id_person_observations, tittle, observation, id_person) VALUES (18, 'observacion 5', 'observacion 5', 30);


--
-- TOC entry 2796 (class 0 OID 230446)
-- Dependencies: 211
-- Data for Name: sc_person_specifications; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_person_specifications (id_person_specifications, tittle, specification, id_person) VALUES (11, 'Dedicación', 'Dedicación', 11);
INSERT INTO sc_person_specifications (id_person_specifications, tittle, specification, id_person) VALUES (12, 'especializacion ', 'con especializacion en procesos lineales y compuestos', 18);
INSERT INTO sc_person_specifications (id_person_specifications, tittle, specification, id_person) VALUES (13, 'ingeniero', 'ngeniero indstrail', 18);
INSERT INTO sc_person_specifications (id_person_specifications, tittle, specification, id_person) VALUES (16, 'Espesificacion 2', 'Espesificacion 2', 27);
INSERT INTO sc_person_specifications (id_person_specifications, tittle, specification, id_person) VALUES (17, 'Espesificacion 5', 'Espesificacion 5', 30);


--
-- TOC entry 2797 (class 0 OID 230452)
-- Dependencies: 212
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


--
-- TOC entry 2798 (class 0 OID 230455)
-- Dependencies: 213
-- Data for Name: sc_priority; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_priority (id_priority, name, description) VALUES (1, 'Alta', 'Alta');
INSERT INTO sc_priority (id_priority, name, description) VALUES (2, 'Media', 'Media');
INSERT INTO sc_priority (id_priority, name, description) VALUES (3, 'Baja', 'Baja');


--
-- TOC entry 2799 (class 0 OID 230458)
-- Dependencies: 214
-- Data for Name: sc_procces_product; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_procces_product (id_process_product, id_process_type, name, description, total_time_machine, total_value_machine, total_value_input, total_time_process, total_value_process, total_time_employee, total_value_employee, id_product_formulation) VALUES (25, 2, 'Fundido de Lamina', 'Moldeado de la lamina a 30° Celsius', 130, 2333.33, 4800.00, 250, 7135.33, 120, 2.00, 12);


--
-- TOC entry 2800 (class 0 OID 230464)
-- Dependencies: 215
-- Data for Name: sc_process_attached; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2801 (class 0 OID 230467)
-- Dependencies: 216
-- Data for Name: sc_process_employee; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_process_employee (id_process_employee, labor_description, time_use, other_expenses, total_value_employee, id_employee, id_process, description_other_expenses) VALUES (33, 'Fundido de la lámina', 120, 0.00, 2.00, 10, 25, '');


--
-- TOC entry 2802 (class 0 OID 230473)
-- Dependencies: 217
-- Data for Name: sc_process_input; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_process_input (id_process_input, id_process, id_input, amount_distribution, percentage_residue, total_value_input) VALUES (22, 25, 16, 12, 0.00, 2400.00);
INSERT INTO sc_process_input (id_process_input, id_process, id_input, amount_distribution, percentage_residue, total_value_input) VALUES (23, 25, 16, 12, 0.00, 2400.00);


--
-- TOC entry 2803 (class 0 OID 230476)
-- Dependencies: 218
-- Data for Name: sc_process_machine; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_process_machine (id_process_machine, id_machine, time_use, other_expenses, total_value_machine, id_process, description_other_expenses) VALUES (31, 1, 120, 0.00, 2000.00, 25, '');
INSERT INTO sc_process_machine (id_process_machine, id_machine, time_use, other_expenses, total_value_machine, id_process, description_other_expenses) VALUES (32, 2, 10, 0.00, 333.33, 25, '');


--
-- TOC entry 2804 (class 0 OID 230482)
-- Dependencies: 219
-- Data for Name: sc_process_type; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_process_type (id_process_type, description, type) VALUES (1, 'Proceso realizado por la misma empresa', 'Interno');
INSERT INTO sc_process_type (id_process_type, description, type) VALUES (2, 'Proceso realizado po r un proveedor o fabricante', 'Externo');


--
-- TOC entry 2805 (class 0 OID 230485)
-- Dependencies: 220
-- Data for Name: sc_product_attached; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_product_attached (id_product_attached, type, tittle, description, id_product_formulation) VALUES (43, 'CARACTERISTICA', '5 Puertas', 'Carro tipo sedán', 12);
INSERT INTO sc_product_attached (id_product_attached, type, tittle, description, id_product_formulation) VALUES (44, 'ESPECIFICACION', 'Luces Antiniebla', 'Luces que permiten desplegar un rayo de luz en la oscuridad, traspasando la niebla', 12);
INSERT INTO sc_product_attached (id_product_attached, type, tittle, description, id_product_formulation) VALUES (45, 'CARACTERISTICA', '5 Llantas', '4 Llantas de uso y 1 de Repuesto', 12);
INSERT INTO sc_product_attached (id_product_attached, type, tittle, description, id_product_formulation) VALUES (46, 'ESPECIFICACION', 'Frenos ABS', 'Frenos que permiten mantener el equilibrio después de un duro frenado', 12);
INSERT INTO sc_product_attached (id_product_attached, type, tittle, description, id_product_formulation) VALUES (47, 'OBSERVACION', 'Pintura de policromado', 'Pintura con brillo', 12);


--
-- TOC entry 2806 (class 0 OID 230491)
-- Dependencies: 221
-- Data for Name: sc_product_documents; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_product_documents (id_product_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_product_formulation) VALUES (13, '/home/gchavarro88/product_filePath/docs', 'Manual técnico', '2015-04-30 17:00:00-05', 'GSI-R-29 Formulario PQR V2.pdf', 'guschaor', 'application/pdf', 12);


--
-- TOC entry 2807 (class 0 OID 230497)
-- Dependencies: 222
-- Data for Name: sc_product_formulation; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_product_formulation (id_product_formulation, path_picture, type_material, mark, serie, id_packing, expiry_date, creation_date, id_priority, id_cost_center, value, id_money, id_partner, id_location, manufacturing_time, description, id_product_dimension, id_stock) VALUES (12, '/home/gchavarro88/products_filePath/img/celos.jpg', 'Metal', 'Spark GT', 'ACGD12021988', 6, '2020-02-11 18:00:00-05', '2015-04-30 17:00:00-05', 1, 10, 15000, 1, 1, 1, 250, 'Carros Chevrolet', 27, 2);


--
-- TOC entry 2808 (class 0 OID 230503)
-- Dependencies: 223
-- Data for Name: sc_replacement; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_replacement (id_replacement, type_replacement, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_replacement_dimension, id_money, id_location, name, "time", value_minutes) VALUES (200, 'Repuesto', 2, 1, 'Pioner', 12500.00, '/home/guschaor/inputs_filePath/img/yo.jpg', 1, 'AC1211212', '2015-05-01 17:00:00-05', 'Ninguna', 2, 1, 2, 1, 2, 'Palanca 3X2', 2, 20160);
INSERT INTO sc_replacement (id_replacement, type_replacement, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_replacement_dimension, id_money, id_location, name, "time", value_minutes) VALUES (7, 'Consumible', 6565, 16, 'familia', 5656.00, ' ', 4, '5656', '2015-06-14 17:00:00-05', '', 34, 2, 46, 1, 10, 'papel lija', 2, 66175200);
INSERT INTO sc_replacement (id_replacement, type_replacement, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_replacement_dimension, id_money, id_location, name, "time", value_minutes) VALUES (8, 'Consumible', 4, 17, 'silicom', 20000.00, '/home/ubuntu/replacement_filePath/img/consumible1.jpg', 13, 'rt56', '2015-09-21 17:00:00-05', 'geg', 38, 1, 70, 3, 7, 'silicona', 3, 172800);
INSERT INTO sc_replacement (id_replacement, type_replacement, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_replacement_dimension, id_money, id_location, name, "time", value_minutes) VALUES (9, 'Consumible', 6, 20, 'induplast', 40000.00, '/home/ubuntu/replacement_filePath/img/insumo.jpg', 1, 'fgfg5', '2015-09-21 17:00:00-05', 'retety4', 39, 3, 71, 3, 12, 'cinta', 3, 259200);
INSERT INTO sc_replacement (id_replacement, type_replacement, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_replacement_dimension, id_money, id_location, name, "time", value_minutes) VALUES (6, 'Consumible', 67, 15, 'hjkjhk', 676.00, '/home/ubuntu/replacement_filePath/img/1451371_761042713962949_8212055255207949329_n.jpg', 4, '678878', '2015-05-08 17:00:00-05', '', 21, 2, 33, 4, 15, 'jhgjh', 2, 675360);


--
-- TOC entry 2809 (class 0 OID 230509)
-- Dependencies: 224
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
-- TOC entry 2810 (class 0 OID 230515)
-- Dependencies: 225
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
-- TOC entry 2811 (class 0 OID 230521)
-- Dependencies: 226
-- Data for Name: sc_roles; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (3, 'INGENIERO DE PRODUCCIÓN', 'Encargado de la revisión y gestión de ordenes para su aprobación', '2014-10-12 17:00:00-05', '2014-10-12 17:00:00-05');
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (6, 'ARQUITECTO DE SOFTWARE', 'Encargado del diseño de componentes de la aplicación', '2014-10-12 17:00:00-05', '2014-10-12 17:00:00-05');
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (7, 'PRUEBAS', '', '2015-04-24 17:00:00-05', NULL);
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (9, 'CALIDAD', 'calidad', '2015-07-31 17:00:00-05', '2015-07-31 17:00:00-05');
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (10, 'OPERARIO', 'planta', '2015-08-10 17:00:00-05', NULL);
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (2, 'GESTIÓN HUMANA', 'Rol asignado al personal de selección y pruebas', '2014-10-12 17:00:00-05', '2015-08-14 17:00:00-05');
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (11, 'TESTING', 'pruebas', '2015-08-14 17:00:00-05', NULL);
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (1, 'ADMINISTRATOR', 'Grupo de permisos infinitos', '2014-09-25 17:00:00-05', '2015-09-29 17:00:00-05');
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (8, 'DESARROLLO', 'Perfil asociado a los desarrolladores de la aplicación ', '2015-07-28 17:00:00-05', '2015-10-25 18:00:00-05');


--
-- TOC entry 2812 (class 0 OID 230527)
-- Dependencies: 227
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


--
-- TOC entry 2813 (class 0 OID 230533)
-- Dependencies: 228
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
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (2, 4444, 1, 28, 12500, 275000, 2, 22);
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


--
-- TOC entry 2814 (class 0 OID 230536)
-- Dependencies: 229
-- Data for Name: sc_stop_machine; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2815 (class 0 OID 230539)
-- Dependencies: 230
-- Data for Name: sc_store; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_store (id_store, name) VALUES (1, 'Almacen 1');
INSERT INTO sc_store (id_store, name) VALUES (2, 'Almacen 2');
INSERT INTO sc_store (id_store, name) VALUES (3, 'Almacen 3');
INSERT INTO sc_store (id_store, name) VALUES (4, 'Almacen 4');
INSERT INTO sc_store (id_store, name) VALUES (5, 'Almacen 5');


--
-- TOC entry 2816 (class 0 OID 230545)
-- Dependencies: 231
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
-- TOC entry 2817 (class 0 OID 230551)
-- Dependencies: 232
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
-- TOC entry 2818 (class 0 OID 230554)
-- Dependencies: 233
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
-- TOC entry 2819 (class 0 OID 230557)
-- Dependencies: 234
-- Data for Name: sc_store_requisition; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2820 (class 0 OID 230563)
-- Dependencies: 235
-- Data for Name: sc_store_requisition_item; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2821 (class 0 OID 230566)
-- Dependencies: 236
-- Data for Name: sc_store_requisition_state; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2822 (class 0 OID 230569)
-- Dependencies: 237
-- Data for Name: sc_time; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_time (id_time, acronym, minutes) VALUES (1, 'Dia(s)', 1440);
INSERT INTO sc_time (id_time, acronym, minutes) VALUES (2, 'Semana(s)', 10080);
INSERT INTO sc_time (id_time, acronym, minutes) VALUES (3, 'Mes(es)', 43200);
INSERT INTO sc_time (id_time, acronym, minutes) VALUES (4, 'Año(s)', 525600);


--
-- TOC entry 2823 (class 0 OID 230572)
-- Dependencies: 238
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
-- TOC entry 2824 (class 0 OID 230578)
-- Dependencies: 239
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
-- TOC entry 2825 (class 0 OID 230584)
-- Dependencies: 240
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
-- TOC entry 2826 (class 0 OID 230590)
-- Dependencies: 241
-- Data for Name: sc_turn; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_turn (id_turn, description, hour_amount) VALUES (1, 'Normal', 8);
INSERT INTO sc_turn (id_turn, description, hour_amount) VALUES (2, 'Lunes a Viernes', 9);
INSERT INTO sc_turn (id_turn, description, hour_amount) VALUES (3, 'Sabatino', 3);
INSERT INTO sc_turn (id_turn, description, hour_amount) VALUES (4, 'Ninguno', 0);


--
-- TOC entry 2827 (class 0 OID 230593)
-- Dependencies: 242
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
-- TOC entry 2828 (class 0 OID 230596)
-- Dependencies: 243
-- Data for Name: sc_users; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (17, 13, 1, 'lisgirlo', '46e435b6e98cec728f5be5d4dbd97ffb', '2015-03-14 18:00:00-05', NULL);
INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (4, 3, 1, 'yaconcha', '0525484994f3e8f42ba38c49930e356a', '2014-10-20 17:00:00-05', '2015-08-09 17:00:00-05');
INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (22, 20, 8, 'jlober', 'eefa0ea006378d7d50f3310d59d93820', '2015-07-28 17:00:00-05', '2015-08-14 17:00:00-05');
INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (19, 22, 1, 'jguerrero', '7453a4cfb3d3db9f6d477e5d2d87c4be', '2015-04-17 17:00:00-05', '2015-08-14 17:00:00-05');
INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (1, 1, 8, 'guschaor', '4e991c769a2b9a881189cd86c160b604', '2014-07-25 17:00:00-05', '2015-08-14 17:00:00-05');


--
-- TOC entry 2829 (class 0 OID 230599)
-- Dependencies: 244
-- Data for Name: sc_work_experience; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_work_experience (id_work_experience, init_date, end_date, id_employee, company_name) VALUES (9, '2014-11-08 18:00:00-05', '2014-11-10 18:00:00-05', 8, 'Ip total');
INSERT INTO sc_work_experience (id_work_experience, init_date, end_date, id_employee, company_name) VALUES (10, '2015-04-01 17:00:00-05', '2015-04-29 17:00:00-05', 9, 'Tecnoquimicas');
INSERT INTO sc_work_experience (id_work_experience, init_date, end_date, id_employee, company_name) VALUES (11, '2012-03-03 18:00:00-05', '2015-08-12 17:00:00-05', 15, 'durmax');
INSERT INTO sc_work_experience (id_work_experience, init_date, end_date, id_employee, company_name) VALUES (12, '2013-11-02 18:00:00-05', '2015-09-10 17:00:00-05', 16, 'dimel');


--
-- TOC entry 2830 (class 0 OID 230602)
-- Dependencies: 245
-- Data for Name: sc_workforce; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_workforce (id_workforce, workforce, id_employee, type_workforce) VALUES (20, '', 10, 'Mecánico');
INSERT INTO sc_workforce (id_workforce, workforce, id_employee, type_workforce) VALUES (21, '', 9, 'Eléctrico');


--
-- TOC entry 2909 (class 0 OID 0)
-- Dependencies: 246
-- Name: sqclasstype; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqclasstype', 1, false);


--
-- TOC entry 2910 (class 0 OID 0)
-- Dependencies: 247
-- Name: sqmaintenanceschedule; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqmaintenanceschedule', 26, true);


--
-- TOC entry 2911 (class 0 OID 0)
-- Dependencies: 248
-- Name: sqotmaintenance; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqotmaintenance', 29, true);


--
-- TOC entry 2912 (class 0 OID 0)
-- Dependencies: 249
-- Name: sqotmaintenancecorrective; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqotmaintenancecorrective', 16, true);


--
-- TOC entry 2913 (class 0 OID 0)
-- Dependencies: 250
-- Name: sqotmaintenancepreventive; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqotmaintenancepreventive', 5, true);


--
-- TOC entry 2914 (class 0 OID 0)
-- Dependencies: 251
-- Name: sqsccompetencies; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqsccompetencies', 9, true);


--
-- TOC entry 2915 (class 0 OID 0)
-- Dependencies: 252
-- Name: sqsccostcenter; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqsccostcenter', 19, true);


--
-- TOC entry 2916 (class 0 OID 0)
-- Dependencies: 253
-- Name: sqscdistributionunit; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscdistributionunit', 4, true);


--
-- TOC entry 2917 (class 0 OID 0)
-- Dependencies: 254
-- Name: sqscdocuments; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscdocuments', 36, true);


--
-- TOC entry 2918 (class 0 OID 0)
-- Dependencies: 255
-- Name: sqscemployee; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscemployee', 16, true);


--
-- TOC entry 2919 (class 0 OID 0)
-- Dependencies: 256
-- Name: sqscinput; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinput', 18, true);


--
-- TOC entry 2920 (class 0 OID 0)
-- Dependencies: 257
-- Name: sqscinputdimension; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinputdimension', 71, true);


--
-- TOC entry 2921 (class 0 OID 0)
-- Dependencies: 258
-- Name: sqscinputdocuments; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinputdocuments', 13, true);


--
-- TOC entry 2922 (class 0 OID 0)
-- Dependencies: 259
-- Name: sqscinputequivalence; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinputequivalence', 1, false);


--
-- TOC entry 2923 (class 0 OID 0)
-- Dependencies: 260
-- Name: sqscinputfeature; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinputfeature', 17, true);


--
-- TOC entry 2924 (class 0 OID 0)
-- Dependencies: 261
-- Name: sqscinputobservation; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinputobservation', 11, true);


--
-- TOC entry 2925 (class 0 OID 0)
-- Dependencies: 262
-- Name: sqscinputspecification; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinputspecification', 20, true);


--
-- TOC entry 2926 (class 0 OID 0)
-- Dependencies: 263
-- Name: sqsclocation; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqsclocation', 17, true);


--
-- TOC entry 2927 (class 0 OID 0)
-- Dependencies: 264
-- Name: sqscmachine; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmachine', 14, true);


--
-- TOC entry 2928 (class 0 OID 0)
-- Dependencies: 265
-- Name: sqscmachineattached; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmachineattached', 22, true);


--
-- TOC entry 2929 (class 0 OID 0)
-- Dependencies: 266
-- Name: sqscmachineconditions; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmachineconditions', 2, true);


--
-- TOC entry 2930 (class 0 OID 0)
-- Dependencies: 267
-- Name: sqscmachinedocument; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmachinedocument', 7, true);


--
-- TOC entry 2931 (class 0 OID 0)
-- Dependencies: 268
-- Name: sqscmachinepart; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmachinepart', 18, true);


--
-- TOC entry 2932 (class 0 OID 0)
-- Dependencies: 269
-- Name: sqscmachinepartattached; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmachinepartattached', 34, true);


--
-- TOC entry 2933 (class 0 OID 0)
-- Dependencies: 270
-- Name: sqscmachinepartdocument; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmachinepartdocument', 16, true);


--
-- TOC entry 2934 (class 0 OID 0)
-- Dependencies: 271
-- Name: sqscmails; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmails', 30, true);


--
-- TOC entry 2935 (class 0 OID 0)
-- Dependencies: 272
-- Name: sqscmaintenanceactivity; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmaintenanceactivity', 29, true);


--
-- TOC entry 2936 (class 0 OID 0)
-- Dependencies: 273
-- Name: sqscmaintenanceplan; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmaintenanceplan', 1, false);


--
-- TOC entry 2937 (class 0 OID 0)
-- Dependencies: 274
-- Name: sqscmaintenancereplacement; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmaintenancereplacement', 1, false);


--
-- TOC entry 2938 (class 0 OID 0)
-- Dependencies: 275
-- Name: sqscmaintenancetool; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmaintenancetool', 1, false);


--
-- TOC entry 2939 (class 0 OID 0)
-- Dependencies: 276
-- Name: sqscmeasure; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmeasure', 28, true);


--
-- TOC entry 2940 (class 0 OID 0)
-- Dependencies: 277
-- Name: sqscmodulespermissionbyrole; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmodulespermissionbyrole', 1523, true);


--
-- TOC entry 2941 (class 0 OID 0)
-- Dependencies: 278
-- Name: sqscmoney; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmoney', 1, false);


--
-- TOC entry 2942 (class 0 OID 0)
-- Dependencies: 279
-- Name: sqscoperatingconditions; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscoperatingconditions', 1, false);


--
-- TOC entry 2943 (class 0 OID 0)
-- Dependencies: 280
-- Name: sqscpackingunit; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpackingunit', 7, true);


--
-- TOC entry 2944 (class 0 OID 0)
-- Dependencies: 281
-- Name: sqscpartners; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpartners', 20, true);


--
-- TOC entry 2945 (class 0 OID 0)
-- Dependencies: 282
-- Name: sqscpartsandconsumables; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpartsandconsumables', 1, false);


--
-- TOC entry 2946 (class 0 OID 0)
-- Dependencies: 283
-- Name: sqscpersondocumentationattached; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpersondocumentationattached', 6, false);


--
-- TOC entry 2947 (class 0 OID 0)
-- Dependencies: 284
-- Name: sqscpersonobservations; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpersonobservations', 19, true);


--
-- TOC entry 2948 (class 0 OID 0)
-- Dependencies: 285
-- Name: sqscpersons; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpersons', 36, true);


--
-- TOC entry 2949 (class 0 OID 0)
-- Dependencies: 286
-- Name: sqscpersonspecifications; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpersonspecifications', 18, true);


--
-- TOC entry 2950 (class 0 OID 0)
-- Dependencies: 287
-- Name: sqscphones; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscphones', 33, true);


--
-- TOC entry 2951 (class 0 OID 0)
-- Dependencies: 288
-- Name: sqscphoto; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscphoto', 1, false);


--
-- TOC entry 2952 (class 0 OID 0)
-- Dependencies: 289
-- Name: sqscprocessattached; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocessattached', 1, false);


--
-- TOC entry 2953 (class 0 OID 0)
-- Dependencies: 290
-- Name: sqscprocessemployee; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocessemployee', 33, true);


--
-- TOC entry 2954 (class 0 OID 0)
-- Dependencies: 291
-- Name: sqscprocessinput; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocessinput', 23, true);


--
-- TOC entry 2955 (class 0 OID 0)
-- Dependencies: 292
-- Name: sqscprocessmachine; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocessmachine', 32, true);


--
-- TOC entry 2956 (class 0 OID 0)
-- Dependencies: 293
-- Name: sqscprocessproduct; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocessproduct', 25, true);


--
-- TOC entry 2957 (class 0 OID 0)
-- Dependencies: 294
-- Name: sqscprocesstype; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocesstype', 1, false);


--
-- TOC entry 2958 (class 0 OID 0)
-- Dependencies: 295
-- Name: sqscproductattached; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscproductattached', 47, true);


--
-- TOC entry 2959 (class 0 OID 0)
-- Dependencies: 296
-- Name: sqscproductdocuments; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscproductdocuments', 13, true);


--
-- TOC entry 2960 (class 0 OID 0)
-- Dependencies: 297
-- Name: sqscproductformulation; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscproductformulation', 12, true);


--
-- TOC entry 2961 (class 0 OID 0)
-- Dependencies: 298
-- Name: sqscreplacement; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscreplacement', 9, true);


--
-- TOC entry 2962 (class 0 OID 0)
-- Dependencies: 299
-- Name: sqscreplacementattached; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscreplacementattached', 24, true);


--
-- TOC entry 2963 (class 0 OID 0)
-- Dependencies: 300
-- Name: sqscreplacementdocuments; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscreplacementdocuments', 15, true);


--
-- TOC entry 2964 (class 0 OID 0)
-- Dependencies: 301
-- Name: sqscroles; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscroles', 11, true);


--
-- TOC entry 2965 (class 0 OID 0)
-- Dependencies: 302
-- Name: sqscservicesorproducts; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscservicesorproducts', 22, true);


--
-- TOC entry 2966 (class 0 OID 0)
-- Dependencies: 303
-- Name: sqscstock; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscstock', 39, true);


--
-- TOC entry 2967 (class 0 OID 0)
-- Dependencies: 304
-- Name: sqscstopmachine; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscstopmachine', 1, false);


--
-- TOC entry 2968 (class 0 OID 0)
-- Dependencies: 305
-- Name: sqscstore; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscstore', 1, false);


--
-- TOC entry 2969 (class 0 OID 0)
-- Dependencies: 306
-- Name: sqscstoreorder; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscstoreorder', 78, true);


--
-- TOC entry 2970 (class 0 OID 0)
-- Dependencies: 307
-- Name: sqscstoreorderitem; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscstoreorderitem', 53, true);


--
-- TOC entry 2971 (class 0 OID 0)
-- Dependencies: 308
-- Name: sqscstoreorderstate; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscstoreorderstate', 1, false);


--
-- TOC entry 2972 (class 0 OID 0)
-- Dependencies: 309
-- Name: sqsctool; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqsctool', 13, true);


--
-- TOC entry 2973 (class 0 OID 0)
-- Dependencies: 310
-- Name: sqsctoolattached; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqsctoolattached', 10, true);


--
-- TOC entry 2974 (class 0 OID 0)
-- Dependencies: 311
-- Name: sqsctooldocuments; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqsctooldocuments', 17, true);


--
-- TOC entry 2975 (class 0 OID 0)
-- Dependencies: 312
-- Name: sqscusers; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscusers', 23, true);


--
-- TOC entry 2976 (class 0 OID 0)
-- Dependencies: 313
-- Name: sqscworkexperience; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscworkexperience', 12, true);


--
-- TOC entry 2977 (class 0 OID 0)
-- Dependencies: 314
-- Name: sqscworkforce; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscworkforce', 21, true);


--
-- TOC entry 2978 (class 0 OID 0)
-- Dependencies: 315
-- Name: sqtype; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqtype', 1, false);


--
-- TOC entry 2398 (class 2606 OID 230747)
-- Name: PK_DISTRIBUTION_UNIT; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_distribution_unit
    ADD CONSTRAINT "PK_DISTRIBUTION_UNIT" PRIMARY KEY (id_distribution_unit);


--
-- TOC entry 2404 (class 2606 OID 230749)
-- Name: PK_FACTORY_LOCATION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_factory_location
    ADD CONSTRAINT "PK_FACTORY_LOCATION" PRIMARY KEY (id_factory_location);


--
-- TOC entry 2408 (class 2606 OID 230751)
-- Name: PK_INPUT_DIMENSION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input_dimension
    ADD CONSTRAINT "PK_INPUT_DIMENSION" PRIMARY KEY (id_input_dimension);


--
-- TOC entry 2412 (class 2606 OID 230753)
-- Name: PK_INPUT_EQUIVALENCES; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input_equivalence
    ADD CONSTRAINT "PK_INPUT_EQUIVALENCES" PRIMARY KEY (id_input_equivalence);


--
-- TOC entry 2420 (class 2606 OID 230755)
-- Name: PK_INPUT_LOCATION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_location
    ADD CONSTRAINT "PK_INPUT_LOCATION" PRIMARY KEY (id_location);


--
-- TOC entry 2506 (class 2606 OID 230757)
-- Name: PK_INPUT_STOCK; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_stock
    ADD CONSTRAINT "PK_INPUT_STOCK" PRIMARY KEY (id_stock);


--
-- TOC entry 2428 (class 2606 OID 230759)
-- Name: PK_MACHINE_DOCUMENT; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_machine_document
    ADD CONSTRAINT "PK_MACHINE_DOCUMENT" PRIMARY KEY (id_machine_document);


--
-- TOC entry 2430 (class 2606 OID 230761)
-- Name: PK_MACHINE_PART; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_machine_part
    ADD CONSTRAINT "PK_MACHINE_PART" PRIMARY KEY (id_machine_part);


--
-- TOC entry 2432 (class 2606 OID 230763)
-- Name: PK_MACHINE_PART_ATTACHED; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_machine_part_attached
    ADD CONSTRAINT "PK_MACHINE_PART_ATTACHED" PRIMARY KEY (id_machine_part_attached);


--
-- TOC entry 2434 (class 2606 OID 230765)
-- Name: PK_MACHINE_PART_DOCUMENT; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_machine_part_document
    ADD CONSTRAINT "PK_MACHINE_PART_DOCUMENT" PRIMARY KEY (id_machine_part_document);


--
-- TOC entry 2382 (class 2606 OID 230767)
-- Name: PK_MAINTENANCE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY ot_maintenance
    ADD CONSTRAINT "PK_MAINTENANCE" PRIMARY KEY (id_maintenance);


--
-- TOC entry 2438 (class 2606 OID 230769)
-- Name: PK_MAINTENANCE_ACTIVITY; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_maintenance_activity
    ADD CONSTRAINT "PK_MAINTENANCE_ACTIVITY" PRIMARY KEY (id_maintenance_activity);


--
-- TOC entry 2440 (class 2606 OID 230771)
-- Name: PK_MAINTENANCE_CLASIFICATION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_maintenance_clasification
    ADD CONSTRAINT "PK_MAINTENANCE_CLASIFICATION" PRIMARY KEY (id_maintenance_clasification);


--
-- TOC entry 2384 (class 2606 OID 230773)
-- Name: PK_MAINTENANCE_CORRECTIVE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY ot_maintenance_corrective
    ADD CONSTRAINT "PK_MAINTENANCE_CORRECTIVE" PRIMARY KEY (id_maintenance_corrective);


--
-- TOC entry 2442 (class 2606 OID 230775)
-- Name: PK_MAINTENANCE_DAMAGE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_maintenance_damage
    ADD CONSTRAINT "PK_MAINTENANCE_DAMAGE" PRIMARY KEY (id_maintenance_damage);


--
-- TOC entry 2386 (class 2606 OID 230777)
-- Name: PK_MAINTENANCE_PREVENTIVE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY ot_maintenance_preventive
    ADD CONSTRAINT "PK_MAINTENANCE_PREVENTIVE" PRIMARY KEY (id_maintenance_preventive);


--
-- TOC entry 2444 (class 2606 OID 230779)
-- Name: PK_MAINTENANCE_STATE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_maintenance_state
    ADD CONSTRAINT "PK_MAINTENANCE_STATE" PRIMARY KEY (id_maintenance_state);


--
-- TOC entry 2478 (class 2606 OID 230781)
-- Name: PK_PROCESS_ATTACHED; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_process_attached
    ADD CONSTRAINT "PK_PROCESS_ATTACHED" PRIMARY KEY (id_process_attached);


--
-- TOC entry 2480 (class 2606 OID 230783)
-- Name: PK_PROCESS_EMPLOYEE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_process_employee
    ADD CONSTRAINT "PK_PROCESS_EMPLOYEE" PRIMARY KEY (id_process_employee);


--
-- TOC entry 2482 (class 2606 OID 230785)
-- Name: PK_PROCESS_INPUT; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_process_input
    ADD CONSTRAINT "PK_PROCESS_INPUT" PRIMARY KEY (id_process_input);


--
-- TOC entry 2476 (class 2606 OID 230787)
-- Name: PK_PROCESS_PRODUCT; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_procces_product
    ADD CONSTRAINT "PK_PROCESS_PRODUCT" PRIMARY KEY (id_process_product);


--
-- TOC entry 2486 (class 2606 OID 230789)
-- Name: PK_PROCESS_TYPE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_process_type
    ADD CONSTRAINT "PK_PROCESS_TYPE" PRIMARY KEY (id_process_type);


--
-- TOC entry 2488 (class 2606 OID 230791)
-- Name: PK_PRODUCT_ATTACHED; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_product_attached
    ADD CONSTRAINT "PK_PRODUCT_ATTACHED" PRIMARY KEY (id_product_attached);


--
-- TOC entry 2496 (class 2606 OID 230793)
-- Name: PK_REPLACEMENT_ATTACHED; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_replacement_attached
    ADD CONSTRAINT "PK_REPLACEMENT_ATTACHED" PRIMARY KEY (id_replacement_attached);


--
-- TOC entry 2388 (class 2606 OID 230795)
-- Name: PK_SCHEDULE_MAINTENANCE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY ot_maintenance_schedule
    ADD CONSTRAINT "PK_SCHEDULE_MAINTENANCE" PRIMARY KEY (id_schedule_maintenance);


--
-- TOC entry 2394 (class 2606 OID 230797)
-- Name: PK_SC_CONSTANTS_LOAD_FILES; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_constants_load_files
    ADD CONSTRAINT "PK_SC_CONSTANTS_LOAD_FILES" PRIMARY KEY (id_constants_load_file);


--
-- TOC entry 2406 (class 2606 OID 230799)
-- Name: PK_SC_INPUT; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "PK_SC_INPUT" PRIMARY KEY (id_input);


--
-- TOC entry 2410 (class 2606 OID 230801)
-- Name: PK_SC_INPUT_DOCUMENTS; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input_documents
    ADD CONSTRAINT "PK_SC_INPUT_DOCUMENTS" PRIMARY KEY (id_input_documents);


--
-- TOC entry 2414 (class 2606 OID 230803)
-- Name: PK_SC_INPUT_FEATURES; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input_feactures
    ADD CONSTRAINT "PK_SC_INPUT_FEATURES" PRIMARY KEY (id_input_feactures);


--
-- TOC entry 2416 (class 2606 OID 230805)
-- Name: PK_SC_INPUT_OBSERVATIONS; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input_observations
    ADD CONSTRAINT "PK_SC_INPUT_OBSERVATIONS" PRIMARY KEY (id_input_observation);


--
-- TOC entry 2418 (class 2606 OID 230807)
-- Name: PK_SC_INPUT_SPECIFICATIONS; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input_specifications
    ADD CONSTRAINT "PK_SC_INPUT_SPECIFICATIONS" PRIMARY KEY (id_input_specifications);


--
-- TOC entry 2456 (class 2606 OID 230809)
-- Name: PK_SC_M0NEY; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_money
    ADD CONSTRAINT "PK_SC_M0NEY" PRIMARY KEY (id_money);


--
-- TOC entry 2422 (class 2606 OID 230811)
-- Name: PK_SC_MACHINE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_machine
    ADD CONSTRAINT "PK_SC_MACHINE" PRIMARY KEY (id_machine);


--
-- TOC entry 2424 (class 2606 OID 230813)
-- Name: PK_SC_MACHINE_ATTACHED; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_machine_attached
    ADD CONSTRAINT "PK_SC_MACHINE_ATTACHED" PRIMARY KEY (id_attached);


--
-- TOC entry 2426 (class 2606 OID 230815)
-- Name: PK_SC_MACHINE_CONDITION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_machine_conditions
    ADD CONSTRAINT "PK_SC_MACHINE_CONDITION" PRIMARY KEY (id_condition);


--
-- TOC entry 2446 (class 2606 OID 230817)
-- Name: PK_SC_MEASURE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_measure_unit
    ADD CONSTRAINT "PK_SC_MEASURE" PRIMARY KEY (id_measure);


--
-- TOC entry 2460 (class 2606 OID 230819)
-- Name: PK_SC_PACKING; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_packing_unit
    ADD CONSTRAINT "PK_SC_PACKING" PRIMARY KEY (id_packing);


--
-- TOC entry 2474 (class 2606 OID 230821)
-- Name: PK_SC_PRIORITY; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_priority
    ADD CONSTRAINT "PK_SC_PRIORITY" PRIMARY KEY (id_priority);


--
-- TOC entry 2484 (class 2606 OID 230823)
-- Name: PK_SC_PROCESS_MACHINE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_process_machine
    ADD CONSTRAINT "PK_SC_PROCESS_MACHINE" PRIMARY KEY (id_process_machine);


--
-- TOC entry 2490 (class 2606 OID 230825)
-- Name: PK_SC_PRODUCT_DOCUMENTS; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_product_documents
    ADD CONSTRAINT "PK_SC_PRODUCT_DOCUMENTS" PRIMARY KEY (id_product_documents);


--
-- TOC entry 2492 (class 2606 OID 230827)
-- Name: PK_SC_PRODUCT_FORMULATION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "PK_SC_PRODUCT_FORMULATION" PRIMARY KEY (id_product_formulation);


--
-- TOC entry 2494 (class 2606 OID 230829)
-- Name: PK_SC_REPLACEMENT; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "PK_SC_REPLACEMENT" PRIMARY KEY (id_replacement);


--
-- TOC entry 2498 (class 2606 OID 230831)
-- Name: PK_SC_REPLACEMENT_DOCUMENTS; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_replacement_documents
    ADD CONSTRAINT "PK_SC_REPLACEMENT_DOCUMENTS" PRIMARY KEY (id_replacement_documents);


--
-- TOC entry 2510 (class 2606 OID 230833)
-- Name: PK_SC_STORE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_store
    ADD CONSTRAINT "PK_SC_STORE" PRIMARY KEY (id_store);


--
-- TOC entry 2528 (class 2606 OID 230835)
-- Name: PK_SC_TOOL; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "PK_SC_TOOL" PRIMARY KEY (id_tool);


--
-- TOC entry 2532 (class 2606 OID 230837)
-- Name: PK_SC_TOOL_DOCUMENTS; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_tool_documents
    ADD CONSTRAINT "PK_SC_TOOL_DOCUMENTS" PRIMARY KEY (id_tool_documents);


--
-- TOC entry 2508 (class 2606 OID 230839)
-- Name: PK_STOP_MACHINE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_stop_machine
    ADD CONSTRAINT "PK_STOP_MACHINE" PRIMARY KEY (id_stop_machine);


--
-- TOC entry 2512 (class 2606 OID 230841)
-- Name: PK_STORE_ORDER; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_store_order
    ADD CONSTRAINT "PK_STORE_ORDER" PRIMARY KEY (id_store_order);


--
-- TOC entry 2514 (class 2606 OID 230843)
-- Name: PK_STORE_ORDER_ITEM; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_store_order_item
    ADD CONSTRAINT "PK_STORE_ORDER_ITEM" PRIMARY KEY (id_item);


--
-- TOC entry 2516 (class 2606 OID 230845)
-- Name: PK_STORE_ORDER_STATE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_store_order_state
    ADD CONSTRAINT "PK_STORE_ORDER_STATE" PRIMARY KEY (id_state);


--
-- TOC entry 2518 (class 2606 OID 230847)
-- Name: PK_STORE_REQUISITION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_store_requisition
    ADD CONSTRAINT "PK_STORE_REQUISITION" PRIMARY KEY (id_store_requisition);


--
-- TOC entry 2520 (class 2606 OID 230849)
-- Name: PK_STORE_REQUISITION_ITEM; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_store_requisition_item
    ADD CONSTRAINT "PK_STORE_REQUISITION_ITEM" PRIMARY KEY (id_item);


--
-- TOC entry 2522 (class 2606 OID 230851)
-- Name: PK_STORE_REQUISITION_STATE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_store_requisition_state
    ADD CONSTRAINT "PK_STORE_REQUISITION_STATE" PRIMARY KEY (id_state);


--
-- TOC entry 2524 (class 2606 OID 230853)
-- Name: PK_TIME; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_time
    ADD CONSTRAINT "PK_TIME" PRIMARY KEY (id_time);


--
-- TOC entry 2530 (class 2606 OID 230855)
-- Name: PK_TOOL_ATTACHED; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_tool_attached
    ADD CONSTRAINT "PK_TOOL_ATTACHED" PRIMARY KEY (id_tool_attached);


--
-- TOC entry 2534 (class 2606 OID 230857)
-- Name: PK_TURN; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_turn
    ADD CONSTRAINT "PK_TURN" PRIMARY KEY (id_turn);


--
-- TOC entry 2544 (class 2606 OID 230859)
-- Name: PK_WORKFORCE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_workforce
    ADD CONSTRAINT "PK_WORKFORCE" PRIMARY KEY (id_workforce);


--
-- TOC entry 2464 (class 2606 OID 230861)
-- Name: UK_IDENTIFICATION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_person
    ADD CONSTRAINT "UK_IDENTIFICATION" UNIQUE (identification);


--
-- TOC entry 2500 (class 2606 OID 230863)
-- Name: UK_ROLENAME; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_roles
    ADD CONSTRAINT "UK_ROLENAME" UNIQUE (name);


--
-- TOC entry 2452 (class 2606 OID 230865)
-- Name: UK_SC_ROLES_SC_MODULE_PERMISSION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_module_permission_by_role
    ADD CONSTRAINT "UK_SC_ROLES_SC_MODULE_PERMISSION" UNIQUE (id_role, id_module_permission);


--
-- TOC entry 2526 (class 2606 OID 230867)
-- Name: UK_TIME; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_time
    ADD CONSTRAINT "UK_TIME" UNIQUE (acronym);


--
-- TOC entry 2448 (class 2606 OID 230869)
-- Name: UK_UNIT_MEASURE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_measure_unit
    ADD CONSTRAINT "UK_UNIT_MEASURE" UNIQUE (acronym);


--
-- TOC entry 2538 (class 2606 OID 230871)
-- Name: UK_USERNAME; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_users
    ADD CONSTRAINT "UK_USERNAME" UNIQUE (login);


--
-- TOC entry 2390 (class 2606 OID 230873)
-- Name: pk_class_type; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_class_type
    ADD CONSTRAINT pk_class_type PRIMARY KEY (id_class_type);


--
-- TOC entry 2392 (class 2606 OID 230875)
-- Name: pk_competencies; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_competencies
    ADD CONSTRAINT pk_competencies PRIMARY KEY (id_competencies);


--
-- TOC entry 2396 (class 2606 OID 230877)
-- Name: pk_cost_center; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_cost_center
    ADD CONSTRAINT pk_cost_center PRIMARY KEY (id_cost_center);


--
-- TOC entry 2400 (class 2606 OID 230879)
-- Name: pk_document; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_documents
    ADD CONSTRAINT pk_document PRIMARY KEY (id_document);


--
-- TOC entry 2402 (class 2606 OID 230881)
-- Name: pk_employee; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_employee
    ADD CONSTRAINT pk_employee PRIMARY KEY (id_employee);


--
-- TOC entry 2436 (class 2606 OID 230883)
-- Name: pk_mails; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_mails
    ADD CONSTRAINT pk_mails PRIMARY KEY (id_mail);


--
-- TOC entry 2454 (class 2606 OID 230885)
-- Name: pk_module_permission_by_role; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_module_permission_by_role
    ADD CONSTRAINT pk_module_permission_by_role PRIMARY KEY (id_module_permission_by_role);


--
-- TOC entry 2458 (class 2606 OID 230887)
-- Name: pk_operatin_condition; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_operating_conditions
    ADD CONSTRAINT pk_operatin_condition PRIMARY KEY (id_operating_condition);


--
-- TOC entry 2462 (class 2606 OID 230889)
-- Name: pk_partner; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_partner
    ADD CONSTRAINT pk_partner PRIMARY KEY (id_partner);


--
-- TOC entry 2466 (class 2606 OID 230891)
-- Name: pk_person; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_person
    ADD CONSTRAINT pk_person PRIMARY KEY (id_person);


--
-- TOC entry 2468 (class 2606 OID 230893)
-- Name: pk_person_observations; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_person_observations
    ADD CONSTRAINT pk_person_observations PRIMARY KEY (id_person_observations);


--
-- TOC entry 2470 (class 2606 OID 230895)
-- Name: pk_person_specifications; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_person_specifications
    ADD CONSTRAINT pk_person_specifications PRIMARY KEY (id_person_specifications);


--
-- TOC entry 2472 (class 2606 OID 230897)
-- Name: pk_phones; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_phones
    ADD CONSTRAINT pk_phones PRIMARY KEY (id_phone);


--
-- TOC entry 2450 (class 2606 OID 230899)
-- Name: pk_sc_module_permission; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_module_permission
    ADD CONSTRAINT pk_sc_module_permission PRIMARY KEY (id_module_permission);


--
-- TOC entry 2502 (class 2606 OID 230901)
-- Name: pk_sc_roles; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_roles
    ADD CONSTRAINT pk_sc_roles PRIMARY KEY (id_role);


--
-- TOC entry 2504 (class 2606 OID 230903)
-- Name: pk_service_or_product; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_services_or_products
    ADD CONSTRAINT pk_service_or_product PRIMARY KEY (id_service_or_products);


--
-- TOC entry 2536 (class 2606 OID 230905)
-- Name: pk_type; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_type
    ADD CONSTRAINT pk_type PRIMARY KEY (id_type);


--
-- TOC entry 2540 (class 2606 OID 230907)
-- Name: pk_users; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_users
    ADD CONSTRAINT pk_users PRIMARY KEY (id_user);


--
-- TOC entry 2542 (class 2606 OID 230909)
-- Name: pk_work_experience; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_work_experience
    ADD CONSTRAINT pk_work_experience PRIMARY KEY (id_work_experience);


--
-- TOC entry 2557 (class 2606 OID 230910)
-- Name: FK_CENTER_COST_INPUT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_CENTER_COST_INPUT" FOREIGN KEY (cost_center) REFERENCES sc_cost_center(id_cost_center);


--
-- TOC entry 2616 (class 2606 OID 230915)
-- Name: FK_COSTCENTER_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_COSTCENTER_REPLACEMENT" FOREIGN KEY (cost_center) REFERENCES sc_cost_center(id_cost_center);


--
-- TOC entry 2633 (class 2606 OID 230920)
-- Name: FK_COSTCENTER_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_COSTCENTER_TOOL" FOREIGN KEY (cost_center) REFERENCES sc_cost_center(id_cost_center);


--
-- TOC entry 2582 (class 2606 OID 230925)
-- Name: FK_COST_CENTER_MACHINE_PART; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_part
    ADD CONSTRAINT "FK_COST_CENTER_MACHINE_PART" FOREIGN KEY (id_cost_center) REFERENCES sc_cost_center(id_cost_center);


--
-- TOC entry 2583 (class 2606 OID 230930)
-- Name: FK_DIMENSION_MACHINE_PART; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_part
    ADD CONSTRAINT "FK_DIMENSION_MACHINE_PART" FOREIGN KEY (id_dimension) REFERENCES sc_input_dimension(id_input_dimension);


--
-- TOC entry 2617 (class 2606 OID 230935)
-- Name: FK_DIMENSION_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_DIMENSION_REPLACEMENT" FOREIGN KEY (id_replacement_dimension) REFERENCES sc_input_dimension(id_input_dimension);


--
-- TOC entry 2634 (class 2606 OID 230940)
-- Name: FK_DIMENSION_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_DIMENSION_TOOL" FOREIGN KEY (id_tool_dimension) REFERENCES sc_input_dimension(id_input_dimension);


--
-- TOC entry 2572 (class 2606 OID 230945)
-- Name: FK_FACTORY_LOCATION_MACHINE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine
    ADD CONSTRAINT "FK_FACTORY_LOCATION_MACHINE" FOREIGN KEY (id_location) REFERENCES sc_factory_location(id_factory_location);


--
-- TOC entry 2567 (class 2606 OID 230950)
-- Name: FK_INPUT_EQUIVALENCE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input_equivalence
    ADD CONSTRAINT "FK_INPUT_EQUIVALENCE" FOREIGN KEY (id_input) REFERENCES sc_input(id_input);


--
-- TOC entry 2568 (class 2606 OID 230955)
-- Name: FK_INPUT_FEACTURES; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input_feactures
    ADD CONSTRAINT "FK_INPUT_FEACTURES" FOREIGN KEY (id_input) REFERENCES sc_input(id_input);


--
-- TOC entry 2569 (class 2606 OID 230960)
-- Name: FK_INPUT_OBSERVATIONS; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input_observations
    ADD CONSTRAINT "FK_INPUT_OBSERVATIONS" FOREIGN KEY (id_input) REFERENCES sc_input(id_input);


--
-- TOC entry 2570 (class 2606 OID 230965)
-- Name: FK_INPUT_SPECIFICATIONS; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input_specifications
    ADD CONSTRAINT "FK_INPUT_SPECIFICATIONS" FOREIGN KEY (id_input) REFERENCES sc_input(id_input);


--
-- TOC entry 2627 (class 2606 OID 230970)
-- Name: FK_INPUT_STOCK_STORE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_stock
    ADD CONSTRAINT "FK_INPUT_STOCK_STORE" FOREIGN KEY (id_store) REFERENCES sc_store(id_store);


--
-- TOC entry 2618 (class 2606 OID 230975)
-- Name: FK_LOCATION_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_LOCATION_REPLACEMENT" FOREIGN KEY (id_location) REFERENCES sc_location(id_location);


--
-- TOC entry 2571 (class 2606 OID 230980)
-- Name: FK_LOCATION_STORE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_location
    ADD CONSTRAINT "FK_LOCATION_STORE" FOREIGN KEY (id_store) REFERENCES sc_store(id_store);


--
-- TOC entry 2635 (class 2606 OID 230985)
-- Name: FK_LOCATION_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_LOCATION_TOOL" FOREIGN KEY (id_location) REFERENCES sc_location(id_location);


--
-- TOC entry 2579 (class 2606 OID 230990)
-- Name: FK_MACHINE_ATTACHED; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_attached
    ADD CONSTRAINT "FK_MACHINE_ATTACHED" FOREIGN KEY (id_machine) REFERENCES sc_machine(id_machine);


--
-- TOC entry 2580 (class 2606 OID 230995)
-- Name: FK_MACHINE_CONDITION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_conditions
    ADD CONSTRAINT "FK_MACHINE_CONDITION" FOREIGN KEY (id_machine) REFERENCES sc_machine(id_machine);


--
-- TOC entry 2573 (class 2606 OID 231000)
-- Name: FK_MACHINE_COST_CENTER; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine
    ADD CONSTRAINT "FK_MACHINE_COST_CENTER" FOREIGN KEY (id_cost_center) REFERENCES sc_cost_center(id_cost_center);


--
-- TOC entry 2574 (class 2606 OID 231005)
-- Name: FK_MACHINE_DIMENSION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine
    ADD CONSTRAINT "FK_MACHINE_DIMENSION" FOREIGN KEY (id_dimension) REFERENCES sc_input_dimension(id_input_dimension);


--
-- TOC entry 2581 (class 2606 OID 231010)
-- Name: FK_MACHINE_DOCUMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_document
    ADD CONSTRAINT "FK_MACHINE_DOCUMENT" FOREIGN KEY (id_machine) REFERENCES sc_machine(id_machine);


--
-- TOC entry 2584 (class 2606 OID 231015)
-- Name: FK_MACHINE_MACHINE_PART; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_part
    ADD CONSTRAINT "FK_MACHINE_MACHINE_PART" FOREIGN KEY (id_machine) REFERENCES sc_machine(id_machine);


--
-- TOC entry 2575 (class 2606 OID 231020)
-- Name: FK_MACHINE_MONEY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine
    ADD CONSTRAINT "FK_MACHINE_MONEY" FOREIGN KEY (id_money) REFERENCES sc_money(id_money);


--
-- TOC entry 2576 (class 2606 OID 231025)
-- Name: FK_MACHINE_PARTNER; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine
    ADD CONSTRAINT "FK_MACHINE_PARTNER" FOREIGN KEY (id_partner) REFERENCES sc_partner(id_partner);


--
-- TOC entry 2589 (class 2606 OID 231030)
-- Name: FK_MACHINE_PART_ATTACHED; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_part_attached
    ADD CONSTRAINT "FK_MACHINE_PART_ATTACHED" FOREIGN KEY (id_machine_part) REFERENCES sc_machine_part(id_machine_part);


--
-- TOC entry 2590 (class 2606 OID 231035)
-- Name: FK_MACHINE_PART_DOCUMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_part_document
    ADD CONSTRAINT "FK_MACHINE_PART_DOCUMENT" FOREIGN KEY (id_machine_part) REFERENCES sc_machine_part(id_machine_part);


--
-- TOC entry 2577 (class 2606 OID 231040)
-- Name: FK_MACHINE_PRIORITY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine
    ADD CONSTRAINT "FK_MACHINE_PRIORITY" FOREIGN KEY (id_priority) REFERENCES sc_priority(id_priority);


--
-- TOC entry 2578 (class 2606 OID 231045)
-- Name: FK_MACHINE_TIME; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine
    ADD CONSTRAINT "FK_MACHINE_TIME" FOREIGN KEY (id_time) REFERENCES sc_time(id_time);


--
-- TOC entry 2592 (class 2606 OID 231050)
-- Name: FK_MAINTENANCE_ACTIVITY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_maintenance_activity
    ADD CONSTRAINT "FK_MAINTENANCE_ACTIVITY" FOREIGN KEY (id_maintenance) REFERENCES ot_maintenance(id_maintenance);


--
-- TOC entry 2545 (class 2606 OID 231055)
-- Name: FK_MAINTENANCE_CLASIFICATION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY ot_maintenance
    ADD CONSTRAINT "FK_MAINTENANCE_CLASIFICATION" FOREIGN KEY (id_maintenance_clasification) REFERENCES sc_maintenance_clasification(id_maintenance_clasification);


--
-- TOC entry 2551 (class 2606 OID 231060)
-- Name: FK_MAINTENANCE_CORRECTIVE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY ot_maintenance_corrective
    ADD CONSTRAINT "FK_MAINTENANCE_CORRECTIVE" FOREIGN KEY (id_maintenance) REFERENCES ot_maintenance(id_maintenance);


--
-- TOC entry 2546 (class 2606 OID 231065)
-- Name: FK_MAINTENANCE_DAMAGE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY ot_maintenance
    ADD CONSTRAINT "FK_MAINTENANCE_DAMAGE" FOREIGN KEY (id_maintenance_damage) REFERENCES sc_maintenance_damage(id_maintenance_damage);


--
-- TOC entry 2547 (class 2606 OID 231070)
-- Name: FK_MAINTENANCE_MACHINE_PART; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY ot_maintenance
    ADD CONSTRAINT "FK_MAINTENANCE_MACHINE_PART" FOREIGN KEY (id_machine_part) REFERENCES sc_machine_part(id_machine_part);


--
-- TOC entry 2552 (class 2606 OID 231075)
-- Name: FK_MAINTENANCE_PREVENTIVE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY ot_maintenance_preventive
    ADD CONSTRAINT "FK_MAINTENANCE_PREVENTIVE" FOREIGN KEY (id_maintenance) REFERENCES ot_maintenance(id_maintenance);


--
-- TOC entry 2548 (class 2606 OID 231080)
-- Name: FK_MAINTENANCE_PRIORITY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY ot_maintenance
    ADD CONSTRAINT "FK_MAINTENANCE_PRIORITY" FOREIGN KEY (id_priority) REFERENCES sc_priority(id_priority);


--
-- TOC entry 2549 (class 2606 OID 231085)
-- Name: FK_MAINTENANCE_STATE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY ot_maintenance
    ADD CONSTRAINT "FK_MAINTENANCE_STATE" FOREIGN KEY (id_maintenance_state) REFERENCES sc_maintenance_state(id_maintenance_state);


--
-- TOC entry 2628 (class 2606 OID 231090)
-- Name: FK_MAINTENANCE_STOP_MACHINE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_stop_machine
    ADD CONSTRAINT "FK_MAINTENANCE_STOP_MACHINE" FOREIGN KEY (id_maintenance) REFERENCES ot_maintenance(id_maintenance);


--
-- TOC entry 2550 (class 2606 OID 231095)
-- Name: FK_MAINTENANCE_WORKFORCE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY ot_maintenance
    ADD CONSTRAINT "FK_MAINTENANCE_WORKFORCE" FOREIGN KEY (id_workforce) REFERENCES sc_workforce(id_workforce);


--
-- TOC entry 2585 (class 2606 OID 231100)
-- Name: FK_MONEY_MACHINE_PART; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_part
    ADD CONSTRAINT "FK_MONEY_MACHINE_PART" FOREIGN KEY (id_money) REFERENCES sc_money(id_money);


--
-- TOC entry 2619 (class 2606 OID 231105)
-- Name: FK_MONEY_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_MONEY_REPLACEMENT" FOREIGN KEY (id_money) REFERENCES sc_money(id_money);


--
-- TOC entry 2636 (class 2606 OID 231110)
-- Name: FK_MONEY_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_MONEY_TOOL" FOREIGN KEY (id_money) REFERENCES sc_money(id_money);


--
-- TOC entry 2586 (class 2606 OID 231115)
-- Name: FK_PRIORITY_MACHINE_PART; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_part
    ADD CONSTRAINT "FK_PRIORITY_MACHINE_PART" FOREIGN KEY (id_priority) REFERENCES sc_priority(id_priority);


--
-- TOC entry 2620 (class 2606 OID 231120)
-- Name: FK_PRIORITY_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_PRIORITY_REPLACEMENT" FOREIGN KEY (id_priority) REFERENCES sc_priority(id_priority);


--
-- TOC entry 2637 (class 2606 OID 231125)
-- Name: FK_PRIORITY_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_PRIORITY_TOOL" FOREIGN KEY (id_priority) REFERENCES sc_priority(id_priority);


--
-- TOC entry 2601 (class 2606 OID 231130)
-- Name: FK_PROCESS_EMPLOYEE_EMPLOYEE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_employee
    ADD CONSTRAINT "FK_PROCESS_EMPLOYEE_EMPLOYEE" FOREIGN KEY (id_employee) REFERENCES sc_employee(id_employee);


--
-- TOC entry 2602 (class 2606 OID 231135)
-- Name: FK_PROCESS_EMPLOYEE_PROCESS; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_employee
    ADD CONSTRAINT "FK_PROCESS_EMPLOYEE_PROCESS" FOREIGN KEY (id_process) REFERENCES sc_procces_product(id_process_product);


--
-- TOC entry 2603 (class 2606 OID 231140)
-- Name: FK_PROCESS_INPUT_INPUT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_input
    ADD CONSTRAINT "FK_PROCESS_INPUT_INPUT" FOREIGN KEY (id_input) REFERENCES sc_input(id_input);


--
-- TOC entry 2604 (class 2606 OID 231145)
-- Name: FK_PROCESS_INPUT_PROCESS; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_input
    ADD CONSTRAINT "FK_PROCESS_INPUT_PROCESS" FOREIGN KEY (id_process) REFERENCES sc_procces_product(id_process_product);


--
-- TOC entry 2605 (class 2606 OID 231150)
-- Name: FK_PROCESS_MACHINE_MACHINE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_machine
    ADD CONSTRAINT "FK_PROCESS_MACHINE_MACHINE" FOREIGN KEY (id_machine) REFERENCES sc_machine(id_machine);


--
-- TOC entry 2606 (class 2606 OID 231155)
-- Name: FK_PROCESS_PROCESS_MACHINE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_machine
    ADD CONSTRAINT "FK_PROCESS_PROCESS_MACHINE" FOREIGN KEY (id_process) REFERENCES sc_procces_product(id_process_product);


--
-- TOC entry 2599 (class 2606 OID 231160)
-- Name: FK_PROCESS_PROCESS_TYPE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_procces_product
    ADD CONSTRAINT "FK_PROCESS_PROCESS_TYPE" FOREIGN KEY (id_process_type) REFERENCES sc_process_type(id_process_type);


--
-- TOC entry 2600 (class 2606 OID 231165)
-- Name: FK_PROCESS_PRODUCT_FORMULATION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_procces_product
    ADD CONSTRAINT "FK_PROCESS_PRODUCT_FORMULATION" FOREIGN KEY (id_product_formulation) REFERENCES sc_product_formulation(id_product_formulation);


--
-- TOC entry 2609 (class 2606 OID 231170)
-- Name: FK_PRODUCT_DIMENSION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "FK_PRODUCT_DIMENSION" FOREIGN KEY (id_product_dimension) REFERENCES sc_input_dimension(id_input_dimension);


--
-- TOC entry 2607 (class 2606 OID 231175)
-- Name: FK_PRODUCT_FORMULATION_ATTACHED; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_attached
    ADD CONSTRAINT "FK_PRODUCT_FORMULATION_ATTACHED" FOREIGN KEY (id_product_formulation) REFERENCES sc_product_formulation(id_product_formulation);


--
-- TOC entry 2610 (class 2606 OID 231180)
-- Name: FK_PRODUCT_FORMULATION_COST_CENTER; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "FK_PRODUCT_FORMULATION_COST_CENTER" FOREIGN KEY (id_cost_center) REFERENCES sc_cost_center(id_cost_center);


--
-- TOC entry 2611 (class 2606 OID 231185)
-- Name: FK_PRODUCT_FORMULATION_MONEY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "FK_PRODUCT_FORMULATION_MONEY" FOREIGN KEY (id_money) REFERENCES sc_money(id_money);


--
-- TOC entry 2612 (class 2606 OID 231190)
-- Name: FK_PRODUCT_FORMULATION_PACKING; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "FK_PRODUCT_FORMULATION_PACKING" FOREIGN KEY (id_packing) REFERENCES sc_packing_unit(id_packing);


--
-- TOC entry 2613 (class 2606 OID 231195)
-- Name: FK_PRODUCT_FORMULATION_PARTNER; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "FK_PRODUCT_FORMULATION_PARTNER" FOREIGN KEY (id_partner) REFERENCES sc_partner(id_partner);


--
-- TOC entry 2614 (class 2606 OID 231200)
-- Name: FK_PRODUCT_FORMULATION_PRIORITY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "FK_PRODUCT_FORMULATION_PRIORITY" FOREIGN KEY (id_priority) REFERENCES sc_priority(id_priority);


--
-- TOC entry 2615 (class 2606 OID 231205)
-- Name: FK_PRODUCT_FORMULATION_STOCK; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "FK_PRODUCT_FORMULATION_STOCK" FOREIGN KEY (id_stock) REFERENCES sc_stock(id_stock);


--
-- TOC entry 2624 (class 2606 OID 231210)
-- Name: FK_REPLACEMENT_FORMULATION_ATTACHED; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement_attached
    ADD CONSTRAINT "FK_REPLACEMENT_FORMULATION_ATTACHED" FOREIGN KEY (id_replacement) REFERENCES sc_replacement(id_replacement);


--
-- TOC entry 2553 (class 2606 OID 231215)
-- Name: FK_SCHEDULE_MAINTENANCE_EMPLOYEE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY ot_maintenance_schedule
    ADD CONSTRAINT "FK_SCHEDULE_MAINTENANCE_EMPLOYEE" FOREIGN KEY (id_employee) REFERENCES sc_employee(id_employee);


--
-- TOC entry 2558 (class 2606 OID 231220)
-- Name: FK_SC_INPUT_DISTRIBUTION_UNIT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_DISTRIBUTION_UNIT" FOREIGN KEY (id_distribution_unit) REFERENCES sc_distribution_unit(id_distribution_unit);


--
-- TOC entry 2566 (class 2606 OID 231225)
-- Name: FK_SC_INPUT_DOCUMENTS_INPUT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input_documents
    ADD CONSTRAINT "FK_SC_INPUT_DOCUMENTS_INPUT" FOREIGN KEY (id_input) REFERENCES sc_input(id_input);


--
-- TOC entry 2559 (class 2606 OID 231230)
-- Name: FK_SC_INPUT_LOCATION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_LOCATION" FOREIGN KEY (id_location) REFERENCES sc_location(id_location);


--
-- TOC entry 2560 (class 2606 OID 231235)
-- Name: FK_SC_INPUT_MONEY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_MONEY" FOREIGN KEY (id_money) REFERENCES sc_money(id_money);


--
-- TOC entry 2561 (class 2606 OID 231240)
-- Name: FK_SC_INPUT_PACKING_UNIT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_PACKING_UNIT" FOREIGN KEY (id_packing) REFERENCES sc_packing_unit(id_packing);


--
-- TOC entry 2562 (class 2606 OID 231245)
-- Name: FK_SC_INPUT_SC_DIMENSION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_SC_DIMENSION" FOREIGN KEY (id_input_dimension) REFERENCES sc_input_dimension(id_input_dimension);


--
-- TOC entry 2563 (class 2606 OID 231250)
-- Name: FK_SC_INPUT_SC_PRIORITY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_SC_PRIORITY" FOREIGN KEY (id_priority) REFERENCES sc_priority(id_priority);


--
-- TOC entry 2564 (class 2606 OID 231255)
-- Name: FK_SC_INPUT_STOCK; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_STOCK" FOREIGN KEY (id_stock) REFERENCES sc_stock(id_stock);


--
-- TOC entry 2608 (class 2606 OID 231260)
-- Name: FK_SC_PRODUCT_DOCUMENTS_INPUT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_documents
    ADD CONSTRAINT "FK_SC_PRODUCT_DOCUMENTS_INPUT" FOREIGN KEY (id_product_formulation) REFERENCES sc_product_formulation(id_product_formulation);


--
-- TOC entry 2625 (class 2606 OID 231265)
-- Name: FK_SC_REPLACEMENT_DOCUMENTS_; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement_documents
    ADD CONSTRAINT "FK_SC_REPLACEMENT_DOCUMENTS_" FOREIGN KEY (id_replacement) REFERENCES sc_replacement(id_replacement);


--
-- TOC entry 2642 (class 2606 OID 231270)
-- Name: FK_SC_TOOL_DOCUMENTS_; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool_documents
    ADD CONSTRAINT "FK_SC_TOOL_DOCUMENTS_" FOREIGN KEY (id_tool) REFERENCES sc_tool(id_tool);


--
-- TOC entry 2621 (class 2606 OID 231275)
-- Name: FK_STOCK_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_STOCK_REPLACEMENT" FOREIGN KEY (id_stock) REFERENCES sc_stock(id_stock);


--
-- TOC entry 2638 (class 2606 OID 231280)
-- Name: FK_STOCK_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_STOCK_TOOL" FOREIGN KEY (id_stock) REFERENCES sc_stock(id_stock);


--
-- TOC entry 2630 (class 2606 OID 231285)
-- Name: FK_STORE_ORDER; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_store_order_item
    ADD CONSTRAINT "FK_STORE_ORDER" FOREIGN KEY (id_store_order) REFERENCES sc_store_order(id_store_order);


--
-- TOC entry 2629 (class 2606 OID 231290)
-- Name: FK_STORE_ORDER_STATE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_store_order
    ADD CONSTRAINT "FK_STORE_ORDER_STATE" FOREIGN KEY (id_state) REFERENCES sc_store_order_state(id_state);


--
-- TOC entry 2632 (class 2606 OID 231295)
-- Name: FK_STORE_REQUISITION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_store_requisition_item
    ADD CONSTRAINT "FK_STORE_REQUISITION" FOREIGN KEY (id_store_requisition) REFERENCES sc_store_requisition(id_store_requisition);


--
-- TOC entry 2631 (class 2606 OID 231300)
-- Name: FK_STORE_REQUISITION_STATE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_store_requisition
    ADD CONSTRAINT "FK_STORE_REQUISITION_STATE" FOREIGN KEY (id_state) REFERENCES sc_store_requisition_state(id_state);


--
-- TOC entry 2622 (class 2606 OID 231305)
-- Name: FK_SUPPLIER_GUARANTEE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_SUPPLIER_GUARANTEE" FOREIGN KEY (supplier_guarantee) REFERENCES sc_partner(id_partner);


--
-- TOC entry 2587 (class 2606 OID 231310)
-- Name: FK_SUPPLIER_GUARANTEE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_part
    ADD CONSTRAINT "FK_SUPPLIER_GUARANTEE" FOREIGN KEY (id_supplier_guarantee) REFERENCES sc_partner(id_partner);


--
-- TOC entry 2565 (class 2606 OID 231315)
-- Name: FK_SUPPLIER_PARTNERS; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SUPPLIER_PARTNERS" FOREIGN KEY (supplier_guarantee) REFERENCES sc_partner(id_partner);


--
-- TOC entry 2639 (class 2606 OID 231320)
-- Name: FK_SUPPLIER_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_SUPPLIER_TOOL" FOREIGN KEY (supplier_guarantee) REFERENCES sc_partner(id_partner);


--
-- TOC entry 2588 (class 2606 OID 231325)
-- Name: FK_TIME_MACHINE_PART; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_machine_part
    ADD CONSTRAINT "FK_TIME_MACHINE_PART" FOREIGN KEY (id_time) REFERENCES sc_time(id_time);


--
-- TOC entry 2623 (class 2606 OID 231330)
-- Name: FK_TIME_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_TIME_REPLACEMENT" FOREIGN KEY ("time") REFERENCES sc_time(id_time);


--
-- TOC entry 2640 (class 2606 OID 231335)
-- Name: FK_TIME_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_TIME_TOOL" FOREIGN KEY ("time") REFERENCES sc_time(id_time);


--
-- TOC entry 2641 (class 2606 OID 231340)
-- Name: FK_TOOL_ATTACHED; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool_attached
    ADD CONSTRAINT "FK_TOOL_ATTACHED" FOREIGN KEY (id_tool) REFERENCES sc_tool(id_tool);


--
-- TOC entry 2647 (class 2606 OID 231345)
-- Name: FK_WORKFORCE_EMPLOYEE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_workforce
    ADD CONSTRAINT "FK_WORKFORCE_EMPLOYEE" FOREIGN KEY (id_employee) REFERENCES sc_employee(id_employee);


--
-- TOC entry 2556 (class 2606 OID 231350)
-- Name: fk_employee_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_employee
    ADD CONSTRAINT fk_employee_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2591 (class 2606 OID 231355)
-- Name: fk_mails_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_mails
    ADD CONSTRAINT fk_mails_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2593 (class 2606 OID 231360)
-- Name: fk_module_permission; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_module_permission_by_role
    ADD CONSTRAINT fk_module_permission FOREIGN KEY (id_module_permission) REFERENCES sc_module_permission(id_module_permission);


--
-- TOC entry 2594 (class 2606 OID 231365)
-- Name: fk_module_permission_by_role_for_role; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_module_permission_by_role
    ADD CONSTRAINT fk_module_permission_by_role_for_role FOREIGN KEY (id_role) REFERENCES sc_roles(id_role);


--
-- TOC entry 2595 (class 2606 OID 231370)
-- Name: fk_partner_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_partner
    ADD CONSTRAINT fk_partner_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2644 (class 2606 OID 231375)
-- Name: fk_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_users
    ADD CONSTRAINT fk_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2596 (class 2606 OID 231380)
-- Name: fk_person_observation_for_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_person_observations
    ADD CONSTRAINT fk_person_observation_for_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2597 (class 2606 OID 231385)
-- Name: fk_person_specifications_for_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_person_specifications
    ADD CONSTRAINT fk_person_specifications_for_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2598 (class 2606 OID 231390)
-- Name: fk_phones_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_phones
    ADD CONSTRAINT fk_phones_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2645 (class 2606 OID 231395)
-- Name: fk_role; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_users
    ADD CONSTRAINT fk_role FOREIGN KEY (id_role) REFERENCES sc_roles(id_role);


--
-- TOC entry 2555 (class 2606 OID 231400)
-- Name: fk_sc_person_to_sc_documents; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_documents
    ADD CONSTRAINT fk_sc_person_to_sc_documents FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2626 (class 2606 OID 231405)
-- Name: fk_service_or_product_partner; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_services_or_products
    ADD CONSTRAINT fk_service_or_product_partner FOREIGN KEY (id_partner) REFERENCES sc_partner(id_partner);


--
-- TOC entry 2646 (class 2606 OID 231410)
-- Name: fk_work_experience_employee; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_work_experience
    ADD CONSTRAINT fk_work_experience_employee FOREIGN KEY (id_employee) REFERENCES sc_employee(id_employee);


--
-- TOC entry 2643 (class 2606 OID 231415)
-- Name: id_class_type; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_type
    ADD CONSTRAINT id_class_type FOREIGN KEY (id_class_type) REFERENCES sc_class_type(id_class_type);


--
-- TOC entry 2554 (class 2606 OID 231420)
-- Name: id_competencies_employee; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_competencies
    ADD CONSTRAINT id_competencies_employee FOREIGN KEY (id_employee) REFERENCES sc_employee(id_employee);


-- Completed on 2015-11-03 14:03:01 COT

--
-- PostgreSQL database dump complete
--

