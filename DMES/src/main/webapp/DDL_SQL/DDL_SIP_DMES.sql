--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2015-05-19 06:59:38 COT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 6 (class 2615 OID 129824)
-- Name: dmes; Type: SCHEMA; Schema: -; Owner: sipPrueba
--

CREATE SCHEMA dmes;


ALTER SCHEMA dmes OWNER TO "sipPrueba";

--
-- TOC entry 277 (class 3079 OID 11829)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2665 (class 0 OID 0)
-- Dependencies: 277
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = dmes, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 170 (class 1259 OID 129825)
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
-- TOC entry 171 (class 1259 OID 129828)
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
-- TOC entry 172 (class 1259 OID 129834)
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
-- TOC entry 173 (class 1259 OID 129840)
-- Name: sc_cost_center; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_cost_center (
    id_cost_center numeric(18,0) NOT NULL,
    description character varying(100) NOT NULL,
    cost_center character varying(100) NOT NULL,
    creation_date date NOT NULL,
    modify_date date
);


ALTER TABLE dmes.sc_cost_center OWNER TO "sipPrueba";

--
-- TOC entry 174 (class 1259 OID 129843)
-- Name: sc_distribution_unit; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_distribution_unit (
    id_distribution_unit numeric(18,0) NOT NULL,
    acronym character varying(20) NOT NULL,
    description character varying(200)
);


ALTER TABLE dmes.sc_distribution_unit OWNER TO "sipPrueba";

--
-- TOC entry 175 (class 1259 OID 129846)
-- Name: sc_documents; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_documents (
    id_document numeric(18,0) NOT NULL,
    document_path character varying(200) NOT NULL,
    document_tittle character varying(2000) NOT NULL,
    creation_date date NOT NULL,
    document_name character varying(2000) NOT NULL,
    id_person numeric(18,0) NOT NULL,
    upload_by character varying(2000) NOT NULL,
    type_document character varying(200)
);


ALTER TABLE dmes.sc_documents OWNER TO "sipPrueba";

--
-- TOC entry 176 (class 1259 OID 129852)
-- Name: sc_employee; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_employee (
    id_employee numeric(18,0) NOT NULL,
    "position" character varying(100) NOT NULL,
    formation character varying(100) NOT NULL,
    admission_date date NOT NULL,
    retirement_date date,
    active character(1) NOT NULL,
    salary numeric(18,2),
    hour_value numeric(18,2),
    porcentage numeric(18,2),
    amount numeric(18,2),
    creation_date date NOT NULL,
    modify_date date,
    id_person numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_employee OWNER TO "sipPrueba";

--
-- TOC entry 177 (class 1259 OID 129855)
-- Name: sc_input; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_input (
    id_input numeric(18,0) NOT NULL,
    type_material character varying(200) NOT NULL,
    expiry_date date,
    supplier_guarantee numeric(18,0) NOT NULL,
    mark character varying(200) NOT NULL,
    value numeric(18,0) NOT NULL,
    path_picture character varying(2000),
    cost_center numeric(18,0) NOT NULL,
    serie character varying(2000) NOT NULL,
    creation_date date NOT NULL,
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
-- TOC entry 178 (class 1259 OID 129861)
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
-- TOC entry 179 (class 1259 OID 129867)
-- Name: sc_input_documents; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_input_documents (
    id_input_documents numeric(18,0) NOT NULL,
    document_path character varying(200) NOT NULL,
    document_tittle character varying(200),
    creation_date date NOT NULL,
    document_name character varying(200),
    upload_by character varying(50) NOT NULL,
    type_document character varying(200) NOT NULL,
    id_input numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_input_documents OWNER TO "sipPrueba";

--
-- TOC entry 180 (class 1259 OID 129873)
-- Name: sc_input_equivalence; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_input_equivalence (
    id_input_equivalence numeric(18,0) NOT NULL,
    id_input numeric(18,0) NOT NULL,
    id_input_referenced numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_input_equivalence OWNER TO "sipPrueba";

--
-- TOC entry 181 (class 1259 OID 129876)
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
-- TOC entry 182 (class 1259 OID 129882)
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
-- TOC entry 183 (class 1259 OID 129888)
-- Name: sc_input_specifications; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_input_specifications (
    id_input_specifications numeric(18,0) NOT NULL,
    description character varying(2000) NOT NULL,
    tittle character varying(200) NOT NULL,
    creation_date date NOT NULL,
    id_input numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_input_specifications OWNER TO "sipPrueba";

--
-- TOC entry 184 (class 1259 OID 129894)
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
-- TOC entry 185 (class 1259 OID 129900)
-- Name: sc_machine; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_machine (
    id_machine numeric(18,0) NOT NULL,
    name character varying(200) NOT NULL,
    hour_value numeric(18,2) NOT NULL
);


ALTER TABLE dmes.sc_machine OWNER TO "sipPrueba";

--
-- TOC entry 186 (class 1259 OID 129903)
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
-- TOC entry 187 (class 1259 OID 129906)
-- Name: sc_maintenance_plan; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_maintenance_plan (
    id_maintenance_plan numeric(18,0) NOT NULL,
    maintenance_plan character varying(100) NOT NULL,
    id_type_maintenance numeric(18,0) NOT NULL,
    id_type_hand_work_classification numeric(18,0) NOT NULL,
    id_type_priority numeric(18,0) NOT NULL,
    frequency numeric(18,0) NOT NULL,
    id_type_frequency numeric(18,0) NOT NULL,
    duration date NOT NULL,
    id_machine numeric(18,0) NOT NULL,
    activity character varying(200) NOT NULL,
    programing_date date NOT NULL,
    id_type_downtime numeric(18,0),
    id_type_unit_unemployment numeric(18,0),
    id_tool numeric(18,0) NOT NULL,
    id_parts_and_consumables numeric(18,0) NOT NULL,
    creation_date date NOT NULL,
    modify_date date
);


ALTER TABLE dmes.sc_maintenance_plan OWNER TO "sipPrueba";

--
-- TOC entry 188 (class 1259 OID 129909)
-- Name: sc_measure_unit; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_measure_unit (
    id_measure numeric(18,0) NOT NULL,
    acronym character varying(20),
    type character varying(200) NOT NULL
);


ALTER TABLE dmes.sc_measure_unit OWNER TO "sipPrueba";

--
-- TOC entry 189 (class 1259 OID 129912)
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
-- TOC entry 190 (class 1259 OID 129918)
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
-- TOC entry 191 (class 1259 OID 129921)
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
-- TOC entry 192 (class 1259 OID 129924)
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
-- TOC entry 193 (class 1259 OID 129930)
-- Name: sc_packing_unit; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_packing_unit (
    id_packing numeric(18,0) NOT NULL,
    description character varying(200),
    acronym character varying(20) NOT NULL
);


ALTER TABLE dmes.sc_packing_unit OWNER TO "sipPrueba";

--
-- TOC entry 194 (class 1259 OID 129933)
-- Name: sc_partner; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_partner (
    id_partner numeric(18,0) NOT NULL,
    active character varying(1) NOT NULL,
    "position" character varying(100) NOT NULL,
    web_page character varying(100),
    creation_date date NOT NULL,
    modify_date date,
    id_person numeric(18,0) NOT NULL,
    company_name character varying(100) NOT NULL
);


ALTER TABLE dmes.sc_partner OWNER TO "sipPrueba";

--
-- TOC entry 195 (class 1259 OID 129936)
-- Name: sc_parts_and_consumables; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_parts_and_consumables (
    id_parts_and_consumables numeric(18,0) NOT NULL,
    parts_and_consumables character varying(100) NOT NULL,
    creation_date date NOT NULL,
    modify_date date,
    id_type numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_parts_and_consumables OWNER TO "sipPrueba";

--
-- TOC entry 196 (class 1259 OID 129939)
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
    creation_date date NOT NULL,
    modify_date date,
    identification numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_person OWNER TO "sipPrueba";

--
-- TOC entry 197 (class 1259 OID 129945)
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
-- TOC entry 198 (class 1259 OID 129951)
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
-- TOC entry 199 (class 1259 OID 129957)
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
-- TOC entry 200 (class 1259 OID 129960)
-- Name: sc_photo; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_photo (
    id_photo numeric(18,0) NOT NULL,
    photo_name bit varying(100) NOT NULL,
    photo_path character varying(200) NOT NULL,
    comments character varying(2000),
    creation_date date NOT NULL,
    modify_date date,
    id_machine numeric(18,0)
);


ALTER TABLE dmes.sc_photo OWNER TO "sipPrueba";

--
-- TOC entry 201 (class 1259 OID 129966)
-- Name: sc_priority; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_priority (
    id_priority numeric(18,0) NOT NULL,
    name character varying(200) NOT NULL,
    description character varying(200)
);


ALTER TABLE dmes.sc_priority OWNER TO "sipPrueba";

--
-- TOC entry 202 (class 1259 OID 129969)
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
-- TOC entry 203 (class 1259 OID 129975)
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
-- TOC entry 204 (class 1259 OID 129978)
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
-- TOC entry 205 (class 1259 OID 129984)
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
-- TOC entry 206 (class 1259 OID 129987)
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
-- TOC entry 207 (class 1259 OID 129993)
-- Name: sc_process_type; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_process_type (
    id_process_type numeric(18,0) NOT NULL,
    description character varying(200),
    type character varying(200) NOT NULL
);


ALTER TABLE dmes.sc_process_type OWNER TO "sipPrueba";

--
-- TOC entry 208 (class 1259 OID 129996)
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
-- TOC entry 209 (class 1259 OID 130002)
-- Name: sc_product_documents; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_product_documents (
    id_product_documents numeric(18,0) NOT NULL,
    document_path character varying(200) NOT NULL,
    document_tittle character varying(200),
    creation_date date NOT NULL,
    document_name character varying(200),
    upload_by character varying(50) NOT NULL,
    type_document character varying(200) NOT NULL,
    id_product_formulation numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_product_documents OWNER TO "sipPrueba";

--
-- TOC entry 210 (class 1259 OID 130008)
-- Name: sc_product_formulation; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_product_formulation (
    id_product_formulation numeric(18,0) NOT NULL,
    path_picture character varying(200),
    type_material character varying(200) NOT NULL,
    mark character varying(200) NOT NULL,
    serie character varying(200) NOT NULL,
    id_packing numeric(18,0) NOT NULL,
    expiry_date date,
    creation_date date NOT NULL,
    id_priority numeric(18,0) NOT NULL,
    id_cost_center numeric(18,0) NOT NULL,
    value numeric(18,0) NOT NULL,
    id_money numeric(18,0) NOT NULL,
    id_partner numeric(18,0) NOT NULL,
    id_location numeric(18,0) NOT NULL,
    manufacturing_time numeric(18,0) NOT NULL,
    description character varying(200) NOT NULL,
    id_product_dimension numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_product_formulation OWNER TO "sipPrueba";

--
-- TOC entry 2666 (class 0 OID 0)
-- Dependencies: 210
-- Name: COLUMN sc_product_formulation.id_priority; Type: COMMENT; Schema: dmes; Owner: sipPrueba
--

COMMENT ON COLUMN sc_product_formulation.id_priority IS '
';


--
-- TOC entry 2667 (class 0 OID 0)
-- Dependencies: 210
-- Name: COLUMN sc_product_formulation.id_partner; Type: COMMENT; Schema: dmes; Owner: sipPrueba
--

COMMENT ON COLUMN sc_product_formulation.id_partner IS '
 ';


--
-- TOC entry 211 (class 1259 OID 130014)
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
    creation_date date NOT NULL,
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
-- TOC entry 212 (class 1259 OID 130020)
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
-- TOC entry 213 (class 1259 OID 130026)
-- Name: sc_replacement_documents; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_replacement_documents (
    id_replacement_documents numeric(18,0) NOT NULL,
    document_path character varying(200) NOT NULL,
    document_tittle character varying(200),
    creation_date date NOT NULL,
    document_name character varying(200),
    upload_by character varying(50) NOT NULL,
    type_document character varying(200) NOT NULL,
    id_replacement numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_replacement_documents OWNER TO "sipPrueba";

--
-- TOC entry 214 (class 1259 OID 130032)
-- Name: sc_roles; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_roles (
    id_role numeric(18,0) NOT NULL,
    name character varying(100) NOT NULL,
    description character varying(2000),
    creation_date date NOT NULL,
    modify_date date
);


ALTER TABLE dmes.sc_roles OWNER TO "sipPrueba";

--
-- TOC entry 215 (class 1259 OID 130038)
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
-- TOC entry 216 (class 1259 OID 130044)
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
-- TOC entry 217 (class 1259 OID 130047)
-- Name: sc_store; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_store (
    id_store numeric(18,0) NOT NULL,
    name character varying(2000) NOT NULL
);


ALTER TABLE dmes.sc_store OWNER TO "sipPrueba";

--
-- TOC entry 218 (class 1259 OID 130053)
-- Name: sc_time; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_time (
    id_time numeric(18,0) NOT NULL,
    acronym character varying(200) NOT NULL,
    minutes numeric(18,0)
);


ALTER TABLE dmes.sc_time OWNER TO "sipPrueba";

--
-- TOC entry 272 (class 1259 OID 137190)
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
    creation_date date NOT NULL,
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
-- TOC entry 273 (class 1259 OID 137238)
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
-- TOC entry 274 (class 1259 OID 137251)
-- Name: sc_tool_documents; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_tool_documents (
    id_tool_documents numeric(18,0) NOT NULL,
    document_path character varying(200) NOT NULL,
    document_tittle character varying(200),
    creation_date date NOT NULL,
    document_name character varying(200),
    upload_by character varying(50) NOT NULL,
    type_document character varying(200) NOT NULL,
    id_tool numeric(18,0) NOT NULL
);


ALTER TABLE dmes.sc_tool_documents OWNER TO "sipPrueba";

--
-- TOC entry 219 (class 1259 OID 130059)
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
-- TOC entry 220 (class 1259 OID 130062)
-- Name: sc_users; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_users (
    id_user numeric(18,0) NOT NULL,
    id_person numeric(18,0) NOT NULL,
    id_role numeric(18,0) NOT NULL,
    login character varying(100) NOT NULL,
    password character varying(100) NOT NULL,
    creation_date date NOT NULL,
    modify_date date
);


ALTER TABLE dmes.sc_users OWNER TO "sipPrueba";

--
-- TOC entry 221 (class 1259 OID 130065)
-- Name: sc_work_experience; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_work_experience (
    id_work_experience numeric(18,0) NOT NULL,
    init_date date NOT NULL,
    end_date date NOT NULL,
    id_employee numeric(18,0) NOT NULL,
    company_name character varying(100) NOT NULL
);


ALTER TABLE dmes.sc_work_experience OWNER TO "sipPrueba";

--
-- TOC entry 222 (class 1259 OID 130068)
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
-- TOC entry 223 (class 1259 OID 130070)
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
-- TOC entry 224 (class 1259 OID 130072)
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
-- TOC entry 225 (class 1259 OID 130074)
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
-- TOC entry 226 (class 1259 OID 130076)
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
-- TOC entry 227 (class 1259 OID 130078)
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
-- TOC entry 228 (class 1259 OID 130080)
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
-- TOC entry 229 (class 1259 OID 130082)
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
-- TOC entry 230 (class 1259 OID 130084)
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
-- TOC entry 231 (class 1259 OID 130086)
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
-- TOC entry 232 (class 1259 OID 130088)
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
-- TOC entry 233 (class 1259 OID 130090)
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
-- TOC entry 234 (class 1259 OID 130092)
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
-- TOC entry 235 (class 1259 OID 130094)
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
-- TOC entry 236 (class 1259 OID 130096)
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
-- TOC entry 237 (class 1259 OID 130098)
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
-- TOC entry 238 (class 1259 OID 130100)
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
-- TOC entry 239 (class 1259 OID 130102)
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
-- TOC entry 240 (class 1259 OID 130104)
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
-- TOC entry 241 (class 1259 OID 130106)
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
-- TOC entry 242 (class 1259 OID 130108)
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
-- TOC entry 243 (class 1259 OID 130110)
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
-- TOC entry 244 (class 1259 OID 130112)
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
-- TOC entry 245 (class 1259 OID 130114)
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
-- TOC entry 246 (class 1259 OID 130116)
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
-- TOC entry 247 (class 1259 OID 130118)
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
-- TOC entry 248 (class 1259 OID 130120)
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
-- TOC entry 249 (class 1259 OID 130122)
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
-- TOC entry 250 (class 1259 OID 130124)
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
-- TOC entry 251 (class 1259 OID 130126)
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
-- TOC entry 252 (class 1259 OID 130128)
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
-- TOC entry 253 (class 1259 OID 130130)
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
-- TOC entry 254 (class 1259 OID 130132)
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
-- TOC entry 255 (class 1259 OID 130134)
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
-- TOC entry 256 (class 1259 OID 130136)
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
-- TOC entry 257 (class 1259 OID 130138)
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
-- TOC entry 258 (class 1259 OID 130140)
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
-- TOC entry 259 (class 1259 OID 130142)
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
-- TOC entry 260 (class 1259 OID 130144)
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
-- TOC entry 261 (class 1259 OID 130146)
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
-- TOC entry 262 (class 1259 OID 130148)
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
-- TOC entry 263 (class 1259 OID 130150)
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
-- TOC entry 264 (class 1259 OID 130152)
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
-- TOC entry 265 (class 1259 OID 130154)
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
-- TOC entry 266 (class 1259 OID 130156)
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
-- TOC entry 267 (class 1259 OID 130158)
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
-- TOC entry 268 (class 1259 OID 130160)
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
-- TOC entry 275 (class 1259 OID 137266)
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
-- TOC entry 276 (class 1259 OID 137268)
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
-- TOC entry 269 (class 1259 OID 130162)
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
-- TOC entry 270 (class 1259 OID 130164)
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
-- TOC entry 271 (class 1259 OID 130166)
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
-- TOC entry 2553 (class 0 OID 129825)
-- Dependencies: 170
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
-- TOC entry 2554 (class 0 OID 129828)
-- Dependencies: 171
-- Data for Name: sc_competencies; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_competencies (id_competencies, tittle, description, id_employee) VALUES (6, 'Liderazgo', 'Liderazgo', 8);
INSERT INTO sc_competencies (id_competencies, tittle, description, id_employee) VALUES (7, 'Agresividad de mercadeo', 'Agresividad de mercadeo', 9);


--
-- TOC entry 2555 (class 0 OID 129834)
-- Dependencies: 172
-- Data for Name: sc_constants_load_files; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_constants_load_files (id_constants_load_file, max_size_file, extension, path) VALUES (1, 10, '/(\.|\/)(pdf|xsl|doc|xlsx|docx|txt|pps|ppt|pptx|ppsx)$/', NULL);


--
-- TOC entry 2556 (class 0 OID 129840)
-- Dependencies: 173
-- Data for Name: sc_cost_center; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (4, 'Contabilidad', '2121331', '2015-02-12', NULL);
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (2, 'Mercadeo', '11212545', '2014-12-14', NULL);
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (1, 'Compras', '14454545', '2014-12-14', '2014-12-14');
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (3, 'Facturación', '52545582', '2015-01-14', NULL);
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (9, 'Sistemas', '32334323', '2015-02-25', NULL);
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (10, 'Servicios Generales', '32334323', '2015-02-27', NULL);
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (11, 'Recusos Humanos', '3234342', '2015-02-28', NULL);
INSERT INTO sc_cost_center (id_cost_center, description, cost_center, creation_date, modify_date) VALUES (12, 'sala', '0980', '2015-05-14', NULL);


--
-- TOC entry 2557 (class 0 OID 129843)
-- Dependencies: 174
-- Data for Name: sc_distribution_unit; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_distribution_unit (id_distribution_unit, acronym, description) VALUES (1, 'botella', 'una botella de cajas');
INSERT INTO sc_distribution_unit (id_distribution_unit, acronym, description) VALUES (2, 'Libras', '25 libras por cajas');
INSERT INTO sc_distribution_unit (id_distribution_unit, acronym, description) VALUES (3, 'Litros Cilindricos', 'Cantidad de litros en un cilindro');
INSERT INTO sc_distribution_unit (id_distribution_unit, acronym, description) VALUES (4, 'unid', 'unidad');


--
-- TOC entry 2558 (class 0 OID 129846)
-- Dependencies: 175
-- Data for Name: sc_documents; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_documents (id_document, document_path, document_tittle, creation_date, document_name, id_person, upload_by, type_document) VALUES (23, '/home/guschavor/Chavarro_Ortiz_Gustavo_Adolfo', 'archvo', '2015-02-02', 'AF-Anexo Funcional-VCDE130800-EB-CO Definición de parámetros.doc', 1, 'guschaor', 'application/msword');
INSERT INTO sc_documents (id_document, document_path, document_tittle, creation_date, document_name, id_person, upload_by, type_document) VALUES (24, '/home/guschavor/Chavarro_Ortiz_Gustavo_Adolfo', 'borrar', '2015-02-02', 'Acta de Instalación.docx', 1, 'guschaor', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document');
INSERT INTO sc_documents (id_document, document_path, document_tittle, creation_date, document_name, id_person, upload_by, type_document) VALUES (25, '/home/guschavor/Chavarro_Ortiz_Gustavo_Adolfo', 'test', '2015-02-02', 'Despliegue VMI CENCOSUD.doc', 1, 'guschaor', 'application/msword');
INSERT INTO sc_documents (id_document, document_path, document_tittle, creation_date, document_name, id_person, upload_by, type_document) VALUES (26, '/home/gchavarro88/Aconcha_Yoleidy', 'aconcha', '2015-02-03', 'JPQL BASICO.docx', 3, 'guschaor', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document');
INSERT INTO sc_documents (id_document, document_path, document_tittle, creation_date, document_name, id_person, upload_by, type_document) VALUES (35, '/home/gchavarro88/Aconcha_Yoleidy', 'jhjkhjkhjk', '2015-04-25', 'MANUAL DE CALIDAD FELIX DE BEDOUT.doc', 3, 'jguerrero', 'application/octet-stream');


--
-- TOC entry 2559 (class 0 OID 129852)
-- Dependencies: 176
-- Data for Name: sc_employee; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, porcentage, amount, creation_date, modify_date, id_person) VALUES (10, 'Personal de Pruebas', 'NA', '2015-04-01', NULL, 'Y', 1.00, 1.00, 1.00, 1.00, '2015-04-19', NULL, 11);
INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, porcentage, amount, creation_date, modify_date, id_person) VALUES (8, 'Administrador del Mundo', 'Ingeniero de Sistemas', '2014-11-10', NULL, 'Y', 30000000000000.00, 3233.00, 32.00, 33.00, '2014-11-19', '2015-04-19', 1);
INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, porcentage, amount, creation_date, modify_date, id_person) VALUES (11, 'chofer', 'ninguna', '2015-04-30', NULL, 'Y', 1500000.00, 15000.00, 1.00, 1.00, '2015-04-25', NULL, 15);
INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, porcentage, amount, creation_date, modify_date, id_person) VALUES (9, 'Administradora General', 'Ingeniería Electrónica', '2015-04-01', NULL, 'Y', 500000.00, 1200.00, 12.00, 1.00, '2015-04-19', '2015-04-25', 14);


--
-- TOC entry 2560 (class 0 OID 129855)
-- Dependencies: 177
-- Data for Name: sc_input; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_input (id_input, type_material, expiry_date, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_input_dimension, id_packing, id_money, total_amount_distribution, id_distribution_unit, distribution_amount, distribution_value, id_location) VALUES (13, 'Prueba', NULL, 1, 'Prueba', 1223, ' ', 4, 'AC3232323', '2015-03-29', 'Prueba de Insumo', 12, 2, 12, 5, 3, 72, 3, 12, 101.00, 7);
INSERT INTO sc_input (id_input, type_material, expiry_date, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_input_dimension, id_packing, id_money, total_amount_distribution, id_distribution_unit, distribution_amount, distribution_value, id_location) VALUES (7, 'dsdf', '2015-03-30', 1, 'dsfsd', 4333, ' ', 1, '22', '2015-03-17', 'amiguis', 6, 2, 6, 3, 3, 12, 1, 12, 361.00, 5);
INSERT INTO sc_input (id_input, type_material, expiry_date, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_input_dimension, id_packing, id_money, total_amount_distribution, id_distribution_unit, distribution_amount, distribution_value, id_location) VALUES (5, 'sdfsd', '2015-03-18', 15, 'fsfsd', 12323, '/home/gchavarro88/inputs_filePath/img/2014-08-10.jpg', 1, 'dfsdfsd', '2015-03-09', 'dddxxx', 4, 1, 4, 4, 1, 8400, 1, 12, 1026.00, 1);
INSERT INTO sc_input (id_input, type_material, expiry_date, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_input_dimension, id_packing, id_money, total_amount_distribution, id_distribution_unit, distribution_amount, distribution_value, id_location) VALUES (15, 'ninguno', '2015-04-30', 1, 'ninguno', 1000, '/home/gchavarro88/inputs_filePath/img/producto1.png', 4, '655555', '2015-04-25', 'producto pepe', 14, 2, 25, 4, 1, 51100, 2, 100, 10.00, 2);
INSERT INTO sc_input (id_input, type_material, expiry_date, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_input_dimension, id_packing, id_money, total_amount_distribution, id_distribution_unit, distribution_amount, distribution_value, id_location) VALUES (14, '23', NULL, 1, '33', 33, '/home/gchavarro88/inputs_filePath/img/corre papeles.jpg', 3, '33', '2015-04-25', 'Prueba Jhon', 13, 2, 21, 4, 1, 23, 1, 23, 1.00, 6);
INSERT INTO sc_input (id_input, type_material, expiry_date, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_input_dimension, id_packing, id_money, total_amount_distribution, id_distribution_unit, distribution_amount, distribution_value, id_location) VALUES (16, 'plomo', NULL, 15, 'complex', 2000, '/home/gchavarro88/inputs_filePath/img/desorientado.jpg', 1, 'plom098', '2015-04-25', 'cercha', 15, 1, 26, 3, 2, 500, 1, 10, 200.00, 12);
INSERT INTO sc_input (id_input, type_material, expiry_date, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_input_dimension, id_packing, id_money, total_amount_distribution, id_distribution_unit, distribution_amount, distribution_value, id_location) VALUES (8, 'Tipo de Prueba de Material', '2015-08-26', 1, 'Nike', 1200, '/home/gchavarro88/inputs_filePath/img/producto1.jpg', 4, '1212', '2015-03-19', 'Prueba de Insumo', 7, 1, 7, 4, 3, 123, 1, 3, 400.00, 5);


--
-- TOC entry 2561 (class 0 OID 129861)
-- Dependencies: 178
-- Data for Name: sc_input_dimension; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (2, '23-Mms', '23-Mms', '3-Mms', '23-Mts', '12-Mms', '23-Mms', '2-Mms', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (12, '33-Mms', '33-KMS', '3-Mms', '55-KMS', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (6, '1-Mms', '23-KMS', '23-KMS', '', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (4, '1-Mms', '1-Mms', '1-Mms', '1-Mts', '1-Mms', '1-KMS', '1-Mms', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (21, '1-Mms', '1-Mms', '1-KMS', '1-Mts', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (7, '12-Mms', '2-KMS', '2-Mts', '5-Mts', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (25, '12-Mts', '12-Mts', '12-Mms', '12-Mms', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (26, '54-Mts', '78-cmt', '8-Mms', '23-Mms', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (27, '2-Mts', '2.20-Mts', '3-Mts', '12000-Hershas', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (33, '5-Mts', '6-Mms', '5-Mts', '87-Mts', '7-Mts', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (34, '4-KMS', '4-Mms', '4-KMS', '4-Mts', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (35, '13-KMS', '34-Mms', '5-Mts', '9-KMS', '', '', '', '');
INSERT INTO sc_input_dimension (id_input_dimension, hight, width, large, weight, volume, thickness, radio, observations) VALUES (36, '12-KMS', '8-Mms', '8-Mms', '9-Mms', '', '', '', '');


--
-- TOC entry 2562 (class 0 OID 129867)
-- Dependencies: 179
-- Data for Name: sc_input_documents; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_input_documents (id_input_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_input) VALUES (4, '/home/guschaor/inputs_filePath/docs/carta laboral ip total.pdf', 'Hoja de Vida', '2015-03-12', 'carta laboral ip total.pdf', 'guschaor', 'carta laboral ip total.pdf', 5);
INSERT INTO sc_input_documents (id_input_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_input) VALUES (5, '/home/guschaor/inputs_filePath/docs/FActura une  HeiDy.pdf', 'Patron', '2015-03-12', 'FActura une  HeiDy.pdf', 'guschaor', 'FActura une  HeiDy.pdf', 5);
INSERT INTO sc_input_documents (id_input_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_input) VALUES (6, '/home/guschaor/inputs_filePath/docs/CVJ_CERT_TRABAJO_CO.pdf', 'Prueba de documento v', '2015-03-19', 'CVJ_CERT_TRABAJO_CO.pdf', 'guschaor', 'application/pdf', 5);
INSERT INTO sc_input_documents (id_input_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_input) VALUES (7, '/home/guschaor/inputs_filePath/docs', 'Segunda prueba', '2015-03-19', 'carta laboral ip total.pdf', 'guschaor', 'application/pdf', 5);
INSERT INTO sc_input_documents (id_input_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_input) VALUES (8, '/home/gchavarro88/inputs_filePath/docs', 'IVR 1', '2015-04-18', 'Esquema IVR.pdf', 'jguerrero', 'application/pdf', 5);
INSERT INTO sc_input_documents (id_input_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_input) VALUES (9, '/home/gchavarro88/inputs_filePath/docs', 'dfdgf', '2015-04-25', 'GuiaCalidad.pdf', 'jguerrero', 'application/pdf', 13);
INSERT INTO sc_input_documents (id_input_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_input) VALUES (10, '/home/gchavarro88/inputs_filePath/docs', '222', '2015-04-25', 'GSI-R-29 Formulario PQR V2.pdf', 'jguerrero', 'application/pdf', 14);
INSERT INTO sc_input_documents (id_input_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_input) VALUES (11, '/home/gchavarro88/inputs_filePath/docs', 'tiii', '2015-04-25', 'MANUAL DE CALIDAD FELIX DE BEDOUT.doc', 'jguerrero', 'application/octet-stream', 15);
INSERT INTO sc_input_documents (id_input_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_input) VALUES (12, '/home/gchavarro88/inputs_filePath/docs', 'plande trabajo', '2015-04-25', 'Plan de Trabajo Modalides version 1-2012 (1).docx', 'yaconcha', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 16);


--
-- TOC entry 2563 (class 0 OID 129873)
-- Dependencies: 180
-- Data for Name: sc_input_equivalence; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2564 (class 0 OID 129876)
-- Dependencies: 181
-- Data for Name: sc_input_feactures; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_input_feactures (id_input_feactures, tittle, description, id_input) VALUES (8, 'sdfsdf', 'sdfsddf', 5);
INSERT INTO sc_input_feactures (id_input_feactures, tittle, description, id_input) VALUES (9, 'sdfsdfs', 'fsdfsd', 5);
INSERT INTO sc_input_feactures (id_input_feactures, tittle, description, id_input) VALUES (10, 'sdfsdfsd', 'sdfsdf', 5);
INSERT INTO sc_input_feactures (id_input_feactures, tittle, description, id_input) VALUES (11, 'sdfsdfsd', 'fsdfsd', 5);
INSERT INTO sc_input_feactures (id_input_feactures, tittle, description, id_input) VALUES (13, 'prueba', 'nada', 13);
INSERT INTO sc_input_feactures (id_input_feactures, tittle, description, id_input) VALUES (14, 'rojo', 'prueba', 16);
INSERT INTO sc_input_feactures (id_input_feactures, tittle, description, id_input) VALUES (15, 'rojo', 'prueba', 16);


--
-- TOC entry 2565 (class 0 OID 129882)
-- Dependencies: 182
-- Data for Name: sc_input_observations; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_input_observations (id_input_observation, tittle, description, id_input) VALUES (5, 'fsdfsdf', 'dfsdfs', 5);
INSERT INTO sc_input_observations (id_input_observation, tittle, description, id_input) VALUES (6, 'sdfsdf', 'sdfsdf', 5);
INSERT INTO sc_input_observations (id_input_observation, tittle, description, id_input) VALUES (7, 'sdfsdfs', 'sdfsdfsd', 5);


--
-- TOC entry 2566 (class 0 OID 129888)
-- Dependencies: 183
-- Data for Name: sc_input_specifications; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_input_specifications (id_input_specifications, description, tittle, creation_date, id_input) VALUES (10, 'sdfsd', 'dfsdf', '2015-03-09', 5);
INSERT INTO sc_input_specifications (id_input_specifications, description, tittle, creation_date, id_input) VALUES (11, 'sdfsd', 'dfsfsd', '2015-03-09', 5);
INSERT INTO sc_input_specifications (id_input_specifications, description, tittle, creation_date, id_input) VALUES (12, 'sdfsd', 'sdfsdf', '2015-03-09', 5);
INSERT INTO sc_input_specifications (id_input_specifications, description, tittle, creation_date, id_input) VALUES (15, 'hjghjg', 'jhghg', '2015-04-25', 13);
INSERT INTO sc_input_specifications (id_input_specifications, description, tittle, creation_date, id_input) VALUES (16, 'v', 'm', '2015-04-25', 16);


--
-- TOC entry 2567 (class 0 OID 129894)
-- Dependencies: 184
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
-- TOC entry 2568 (class 0 OID 129900)
-- Dependencies: 185
-- Data for Name: sc_machine; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_machine (id_machine, name, hour_value) VALUES (1, 'Máquina 1', 1000.00);
INSERT INTO sc_machine (id_machine, name, hour_value) VALUES (2, 'Máquina 2', 2000.00);
INSERT INTO sc_machine (id_machine, name, hour_value) VALUES (3, 'Máquina 4', 4000.00);


--
-- TOC entry 2569 (class 0 OID 129903)
-- Dependencies: 186
-- Data for Name: sc_mails; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (9, 'notengo@notengo.com', 'No tiene', 11);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (12, 'nataliagiron99@gmail.com', 'Correo Personal', 13);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (13, 'dede@hdodk.com', 'Erróneo', 11);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (14, 'inica@hotmail.com', 'personal', 16);


--
-- TOC entry 2570 (class 0 OID 129906)
-- Dependencies: 187
-- Data for Name: sc_maintenance_plan; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2571 (class 0 OID 129909)
-- Dependencies: 188
-- Data for Name: sc_measure_unit; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (1, 'Mts', 'Metros');
INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (2, 'Mms', 'Milimetros');
INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (4, 'KMS', 'Kilometros');
INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (17, 'PruebaYoleidy', 'PruebaYoleidy');
INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (19, 'Cts', 'Centímetros');
INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (5, 'Pulgadas', 'Pulgadas');
INSERT INTO sc_measure_unit (id_measure, acronym, type) VALUES (20, 'Hershas', 'Hershas');


--
-- TOC entry 2572 (class 0 OID 129912)
-- Dependencies: 189
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
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (23, 'Almacén', NULL, 'maquinas.png', 'Item', 10, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (26, 'Repuestos y Consumibles', NULL, 'repuestos_y_consumibles.png', 'Item', 10, 'resources/materials/ScReplacement.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (27, 'Herramientas', NULL, 'repuestos_y_consumibles.png', 'Item', 10, 'resources/materials/ScTool.jsf');


--
-- TOC entry 2573 (class 0 OID 129918)
-- Dependencies: 190
-- Data for Name: sc_module_permission_by_role; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (202, 6, 'CRUD', 1);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (203, 6, 'CRUD', 7);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (204, 6, 'CRUD', 13);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (205, 6, 'CRUD', 15);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (209, 6, 'CRUD', 14);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (213, 6, 'CRUD', 16);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (166, 2, 'CRUD', 1);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (167, 2, 'CRUD', 7);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (168, 2, 'CRUD', 9);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (169, 2, 'CRUD', 11);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (173, 2, 'CRUD', 12);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (176, 2, 'CRUD', 13);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (177, 2, 'CRUD', 14);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (181, 2, 'CRUD', 16);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (102, 5, 'CRUD', 1);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (103, 5, 'CRUD', 7);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (104, 5, 'CRUD', 13);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (105, 5, 'CRUD', 15);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (109, 5, 'CRUD', 16);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (113, 5, 'CRUD', 14);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (116, 5, 'CRUD', 9);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (117, 5, 'CRUD', 12);
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
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (530, 1, 'CRUD', 1);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (531, 1, 'CRUD', 7);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (532, 1, 'CRUD', 13);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (533, 1, 'CRUD', 15);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (537, 1, 'CRUD', 14);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (541, 1, 'CRUD', 16);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (544, 1, 'CRUD', 9);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (545, 1, 'CRUD', 11);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (549, 1, 'CRUD', 12);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (552, 1, 'CRUD', 10);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (553, 1, 'CRUD', 17);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (556, 1, 'CRUD', 18);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (557, 1, 'CRUD', 20);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (560, 1, 'CRUD', 21);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (561, 1, 'CRUD', 22);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (565, 1, 'CRUD', 19);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (569, 1, 'CRUD', 23);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (573, 1, 'CRUD', 24);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (577, 1, 'CRUD', 25);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (581, 1, 'CRUD', 26);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (585, 1, 'CRUD', 27);


--
-- TOC entry 2574 (class 0 OID 129921)
-- Dependencies: 191
-- Data for Name: sc_money; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_money (id_money, description, acronym, trm) VALUES (1, 'Dolar', '$', 2300.00);
INSERT INTO sc_money (id_money, description, acronym, trm) VALUES (2, 'Pesos', '$', 1.00);
INSERT INTO sc_money (id_money, description, acronym, trm) VALUES (3, 'Libra', '£', 1500.00);
INSERT INTO sc_money (id_money, description, acronym, trm) VALUES (4, 'Centavo', '¢', 0.01);
INSERT INTO sc_money (id_money, description, acronym, trm) VALUES (5, 'Yen', '¥', 400.00);


--
-- TOC entry 2575 (class 0 OID 129924)
-- Dependencies: 192
-- Data for Name: sc_operating_conditions; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2576 (class 0 OID 129930)
-- Dependencies: 193
-- Data for Name: sc_packing_unit; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_packing_unit (id_packing, description, acronym) VALUES (3, 'unidad de empaque', 'Caja');
INSERT INTO sc_packing_unit (id_packing, description, acronym) VALUES (5, 'Cilindro para gases', 'Cilindro');
INSERT INTO sc_packing_unit (id_packing, description, acronym) VALUES (4, 'arrobas', 'arroba');
INSERT INTO sc_packing_unit (id_packing, description, acronym) VALUES (6, 'Estibas', 'Estiba');
INSERT INTO sc_packing_unit (id_packing, description, acronym) VALUES (7, 'Cantidad medido en vasoso milimetricos', 'Vaso');


--
-- TOC entry 2577 (class 0 OID 129933)
-- Dependencies: 194
-- Data for Name: sc_partner; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_partner (id_partner, active, "position", web_page, creation_date, modify_date, id_person, company_name) VALUES (1, 'Y', 'Asesor', 'http://www.google.com', '2014-11-19', NULL, 13, 'Carvajal');
INSERT INTO sc_partner (id_partner, active, "position", web_page, creation_date, modify_date, id_person, company_name) VALUES (15, 'Y', 'Ingeniero Analista', '', '2014-11-21', NULL, 2, 'Tecnoquímicas');
INSERT INTO sc_partner (id_partner, active, "position", web_page, creation_date, modify_date, id_person, company_name) VALUES (16, 'Y', 'jefe venta', '', '2015-05-14', NULL, 16, 'complex');


--
-- TOC entry 2578 (class 0 OID 129936)
-- Dependencies: 195
-- Data for Name: sc_parts_and_consumables; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2579 (class 0 OID 129939)
-- Dependencies: 196
-- Data for Name: sc_person; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification) VALUES (2, 'Cristian Camilo', 'Chaparro Cuadros', 23, 'Colombia ', 'Cali', NULL, 'Oeste de Cali', NULL, NULL, '/', '2014-09-23', NULL, 111111111111111111);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification) VALUES (11, 'Valentina', 'Trujillo Ocampo', 33, 'Colombia', 'Cali', 'Barrio Champañat', 'Carrera 28 # 9-52', 'Colegio 3 de primaria básica', 'Niña de Javier', '/', '2014-11-04', '2014-11-09', 11133333333);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification) VALUES (3, 'Yoleidy', 'Aconcha', 26, 'Colombia', 'Cali', NULL, 'Carrera 103 Calle 49', NULL, NULL, '/', '2014-10-21', NULL, 11111111111111);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification) VALUES (1, 'Gustavo Adolfo', 'Chavarro Ortiz', 26, 'Colombia', 'Cali', NULL, 'Carrera 21 # 13-16', NULL, NULL, '/', '2014-09-26', NULL, 1107046850);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification) VALUES (14, 'carlos', 'uzman', 18, 'colombia', 'cali', 'ksksks', 'calle 100', 'ooo', 'sssss', '/', '2014-11-30', NULL, 222222222);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification) VALUES (13, 'Lizeth Nathalia', 'Girón López ', 18, 'Colombia', 'Cali', 'Hershey''s - Maggie', 'Calle 23 # Alfonso Bonilla Aragón', 'Ingeniería Agrícola', 'Persona interesada en conocer la empresa', '/', '2014-11-08', '2015-03-15', 1149493828);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification) VALUES (15, 'pepe', 'pepe', 50, 'pepe', 'pepe', 'pepe', 'pepe', 'pepe', 'pepe', '/', '2015-04-25', NULL, 234234234);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification) VALUES (16, 'jaime', 'acosta', 23, 'colombia', 'cali', 'szvsfzv', 'calla 1 34#45', 'fdbdf', 'sdvsv', '/', '2015-05-14', NULL, 1609873);


--
-- TOC entry 2580 (class 0 OID 129945)
-- Dependencies: 197
-- Data for Name: sc_person_observations; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_person_observations (id_person_observations, tittle, observation, id_person) VALUES (10, 'Almuerza', 'almuerza', 11);
INSERT INTO sc_person_observations (id_person_observations, tittle, observation, id_person) VALUES (11, 'Mi novia', 'es la persona que me alegra las mañanas', 13);
INSERT INTO sc_person_observations (id_person_observations, tittle, observation, id_person) VALUES (12, 'Mi novia', 'es la persona que me alegra las mañanas', 13);
INSERT INTO sc_person_observations (id_person_observations, tittle, observation, id_person) VALUES (13, 'gdgfdgdf@dfgdgfd.com', 'dfsdfsfsd', 15);


--
-- TOC entry 2581 (class 0 OID 129951)
-- Dependencies: 198
-- Data for Name: sc_person_specifications; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_person_specifications (id_person_specifications, tittle, specification, id_person) VALUES (11, 'Dedicación', 'Dedicación', 11);


--
-- TOC entry 2582 (class 0 OID 129957)
-- Dependencies: 199
-- Data for Name: sc_phones; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (10, 3176600681, 'Telefono de Mama', 11);
INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (14, 3117036163, 'Personal', 13);
INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (15, 3182433265, 'Abuela', 11);
INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (16, 34443434, '', 15);
INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (17, 983245678, 'fijo', 16);


--
-- TOC entry 2583 (class 0 OID 129960)
-- Dependencies: 200
-- Data for Name: sc_photo; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2584 (class 0 OID 129966)
-- Dependencies: 201
-- Data for Name: sc_priority; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_priority (id_priority, name, description) VALUES (1, 'Alta', 'Alta');
INSERT INTO sc_priority (id_priority, name, description) VALUES (2, 'Media', 'Media');
INSERT INTO sc_priority (id_priority, name, description) VALUES (3, 'Baja', 'Baja');


--
-- TOC entry 2585 (class 0 OID 129969)
-- Dependencies: 202
-- Data for Name: sc_procces_product; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_procces_product (id_process_product, id_process_type, name, description, total_time_machine, total_value_machine, total_value_input, total_time_process, total_value_process, total_time_employee, total_value_employee, id_product_formulation) VALUES (25, 2, 'Fundido de Lamina', 'Moldeado de la lamina a 30° Celsius', 130, 2333.33, 4800.00, 250, 7135.33, 120, 2.00, 12);


--
-- TOC entry 2586 (class 0 OID 129975)
-- Dependencies: 203
-- Data for Name: sc_process_attached; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2587 (class 0 OID 129978)
-- Dependencies: 204
-- Data for Name: sc_process_employee; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_process_employee (id_process_employee, labor_description, time_use, other_expenses, total_value_employee, id_employee, id_process, description_other_expenses) VALUES (33, 'Fundido de la lámina', 120, 0.00, 2.00, 10, 25, '');


--
-- TOC entry 2588 (class 0 OID 129984)
-- Dependencies: 205
-- Data for Name: sc_process_input; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_process_input (id_process_input, id_process, id_input, amount_distribution, percentage_residue, total_value_input) VALUES (22, 25, 16, 12, 0.00, 2400.00);
INSERT INTO sc_process_input (id_process_input, id_process, id_input, amount_distribution, percentage_residue, total_value_input) VALUES (23, 25, 16, 12, 0.00, 2400.00);


--
-- TOC entry 2589 (class 0 OID 129987)
-- Dependencies: 206
-- Data for Name: sc_process_machine; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_process_machine (id_process_machine, id_machine, time_use, other_expenses, total_value_machine, id_process, description_other_expenses) VALUES (31, 1, 120, 0.00, 2000.00, 25, '');
INSERT INTO sc_process_machine (id_process_machine, id_machine, time_use, other_expenses, total_value_machine, id_process, description_other_expenses) VALUES (32, 2, 10, 0.00, 333.33, 25, '');


--
-- TOC entry 2590 (class 0 OID 129993)
-- Dependencies: 207
-- Data for Name: sc_process_type; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_process_type (id_process_type, description, type) VALUES (1, 'Proceso realizado por la misma empresa', 'Interno');
INSERT INTO sc_process_type (id_process_type, description, type) VALUES (2, 'Proceso realizado po r un proveedor o fabricante', 'Externo');


--
-- TOC entry 2591 (class 0 OID 129996)
-- Dependencies: 208
-- Data for Name: sc_product_attached; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_product_attached (id_product_attached, type, tittle, description, id_product_formulation) VALUES (43, 'CARACTERISTICA', '5 Puertas', 'Carro tipo sedán', 12);
INSERT INTO sc_product_attached (id_product_attached, type, tittle, description, id_product_formulation) VALUES (44, 'ESPECIFICACION', 'Luces Antiniebla', 'Luces que permiten desplegar un rayo de luz en la oscuridad, traspasando la niebla', 12);
INSERT INTO sc_product_attached (id_product_attached, type, tittle, description, id_product_formulation) VALUES (45, 'CARACTERISTICA', '5 Llantas', '4 Llantas de uso y 1 de Repuesto', 12);
INSERT INTO sc_product_attached (id_product_attached, type, tittle, description, id_product_formulation) VALUES (46, 'ESPECIFICACION', 'Frenos ABS', 'Frenos que permiten mantener el equilibrio después de un duro frenado', 12);
INSERT INTO sc_product_attached (id_product_attached, type, tittle, description, id_product_formulation) VALUES (47, 'OBSERVACION', 'Pintura de policromado', 'Pintura con brillo', 12);


--
-- TOC entry 2592 (class 0 OID 130002)
-- Dependencies: 209
-- Data for Name: sc_product_documents; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_product_documents (id_product_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_product_formulation) VALUES (13, '/home/gchavarro88/product_filePath/docs', 'Manual técnico', '2015-05-01', 'GSI-R-29 Formulario PQR V2.pdf', 'guschaor', 'application/pdf', 12);


--
-- TOC entry 2593 (class 0 OID 130008)
-- Dependencies: 210
-- Data for Name: sc_product_formulation; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_product_formulation (id_product_formulation, path_picture, type_material, mark, serie, id_packing, expiry_date, creation_date, id_priority, id_cost_center, value, id_money, id_partner, id_location, manufacturing_time, description, id_product_dimension) VALUES (12, '/home/gchavarro88/products_filePath/img/celos.jpg', 'Metal', 'Spark GT', 'ACGD12021988', 6, '2020-02-12', '2015-05-01', 1, 10, 15000, 1, 1, 1, 250, 'Carros Chevrolet', 27);


--
-- TOC entry 2594 (class 0 OID 130014)
-- Dependencies: 211
-- Data for Name: sc_replacement; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_replacement (id_replacement, type_replacement, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_replacement_dimension, id_money, id_location, name, "time", value_minutes) VALUES (200, 'Repuesto', 2, 1, 'Pioner', 12500.00, '/home/guschaor/inputs_filePath/img/yo.jpg', 1, 'AC1211212', '2015-05-02', 'Ninguna', 2, 1, 2, 1, 2, 'Palanca 3X2', 2, 20160);
INSERT INTO sc_replacement (id_replacement, type_replacement, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_replacement_dimension, id_money, id_location, name, "time", value_minutes) VALUES (6, 'Consumible', 67, 15, 'hjkjhk', 676.00, '/home/gchavarro88/inputs_filePath/img/Imagen2.png', 4, '678878', '2015-05-09', '', 21, 2, 33, 4, 15, 'jhgjh', 2, 675360);


--
-- TOC entry 2595 (class 0 OID 130020)
-- Dependencies: 212
-- Data for Name: sc_replacement_attached; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_replacement_attached (id_replacement_attached, type, tittle, description, id_replacement) VALUES (1, 'd', 'dd', 'sds', 200);
INSERT INTO sc_replacement_attached (id_replacement_attached, type, tittle, description, id_replacement) VALUES (18, 'SPECIFICATION', 'tete', 'tete', 200);
INSERT INTO sc_replacement_attached (id_replacement_attached, type, tittle, description, id_replacement) VALUES (19, 'SPECIFICATION', 'ete', 'tetett', 200);
INSERT INTO sc_replacement_attached (id_replacement_attached, type, tittle, description, id_replacement) VALUES (20, 'FEACTURE', 'frf', 'frdfdf', 200);
INSERT INTO sc_replacement_attached (id_replacement_attached, type, tittle, description, id_replacement) VALUES (21, 'FEACTURE', 'gtres', 'gresd', 200);
INSERT INTO sc_replacement_attached (id_replacement_attached, type, tittle, description, id_replacement) VALUES (22, 'OBSERVATION', 'desde', 'desde', 200);
INSERT INTO sc_replacement_attached (id_replacement_attached, type, tittle, description, id_replacement) VALUES (23, 'OBSERVATION', 'desde', 'desde', 200);


--
-- TOC entry 2596 (class 0 OID 130026)
-- Dependencies: 213
-- Data for Name: sc_replacement_documents; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_replacement_documents (id_replacement_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_replacement) VALUES (5, '/home/guschaor/inputs_filePath/docs', 'desd', '2015-05-05', '1107046850.pdf', 'guschaor', 'application/pdf', 200);
INSERT INTO sc_replacement_documents (id_replacement_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_replacement) VALUES (6, '/home/guschaor/inputs_filePath/docs', 'grresess', '2015-05-05', '1107046850.pdf', 'guschaor', 'application/pdf', 200);
INSERT INTO sc_replacement_documents (id_replacement_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_replacement) VALUES (7, '/home/gchavarro88/inputs_filePath/docs', 'test', '2015-05-09', 'jsgaviota.pdf', 'jguerrero', 'application/pdf', 6);
INSERT INTO sc_replacement_documents (id_replacement_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_replacement) VALUES (8, '/home/gchavarro88/inputs_filePath/docs', 'titulo 2', '2015-05-09', 'jsgaviota - copia.pdf', 'jguerrero', 'application/pdf', 6);


--
-- TOC entry 2597 (class 0 OID 130032)
-- Dependencies: 214
-- Data for Name: sc_roles; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (3, 'INGENIERO DE PRODUCCIÓN', 'Encargado de la revisión y gestión de ordenes para su aprobación', '2014-10-13', '2014-10-13');
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (5, 'AUXILIAR DE COMIDA', 'Auxiliar de Comida', '2014-10-13', '2014-10-13');
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (2, 'GESTIÓN HUMANA', 'sdddsdfdsfsddsf', '2014-10-13', '2014-10-13');
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (6, 'ARQUITECTO DE SOFTWARE', 'Encargado del diseño de componentes de la aplicación', '2014-10-13', '2014-10-13');
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (7, 'PRUEBAS', '', '2015-04-25', NULL);
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (1, 'ADMINISTRATOR', 'Grupo de permisos infinitos', '2014-09-26', '2015-05-08');


--
-- TOC entry 2598 (class 0 OID 130038)
-- Dependencies: 215
-- Data for Name: sc_services_or_products; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_services_or_products (id_service_or_products, name_service_or_product, cost, guarantee, description, amount, id_partner, type) VALUES (15, 'servicios de toma de dato', 20000000.00, '', 'nada', 2, 16, 'Servicio');


--
-- TOC entry 2599 (class 0 OID 130044)
-- Dependencies: 216
-- Data for Name: sc_stock; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (3, 12, 32, 23, 232, 2332, 2, 2332);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (4, 1, 7, 7, 7, 7, 1, 7);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (6, 12, 11, 12, 4, 48, 1, 11);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (7, 12, 2, 3, 1200, 3600, 1, 12);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (12, 30, 2, 6, 1223, 7338, 3, 3);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (13, 2, 1, 1, 33, 33, 2, 1);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (14, 515151, 12, 511, 1000, 511000, 2, 111);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (15, 100, 40, 50, 2000, 100000, 5, 60);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (20, 5555, 4, 44, 4, 176, 2, 44);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (2, 4444, 1, 22, 12500, 275000, 2, 22);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (21, 1000, 100, 500, 676, 338000, 3, 700);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (22, 1244444444, 4, 4, 222, 888, 2, 122);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (23, 15, 10, 22, 12000, 264000, 3, 12);
INSERT INTO sc_stock (id_stock, maxime_stock, minime_stock, current_stock, price_unit, total_value, id_store, optime_stock) VALUES (24, 20, 10, 40, 30000000, 1200000000, 2, 15);


--
-- TOC entry 2600 (class 0 OID 130047)
-- Dependencies: 217
-- Data for Name: sc_store; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_store (id_store, name) VALUES (1, 'Almacen 1');
INSERT INTO sc_store (id_store, name) VALUES (2, 'Almacen 2');
INSERT INTO sc_store (id_store, name) VALUES (3, 'Almacen 3');
INSERT INTO sc_store (id_store, name) VALUES (4, 'Almacen 4');
INSERT INTO sc_store (id_store, name) VALUES (5, 'Almacen 5');


--
-- TOC entry 2601 (class 0 OID 130053)
-- Dependencies: 218
-- Data for Name: sc_time; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_time (id_time, acronym, minutes) VALUES (1, 'Dia(s)', 1440);
INSERT INTO sc_time (id_time, acronym, minutes) VALUES (2, 'Semana(s)', 10080);
INSERT INTO sc_time (id_time, acronym, minutes) VALUES (3, 'Mes(es)', 43200);
INSERT INTO sc_time (id_time, acronym, minutes) VALUES (4, 'Año(s)', 525600);


--
-- TOC entry 2655 (class 0 OID 137190)
-- Dependencies: 272
-- Data for Name: sc_tool; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_tool (id_tool, type_tool, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_tool_dimension, id_money, id_location, name, "time", value_minutes) VALUES (1, 'Mecánica', 122, 15, 'test', 222.00, '/home/gchavarro88/inputs_filePath/img/linuxito.png', 4, 'test', '2015-05-13', 'test', 22, 1, 34, 3, 6, 'test', 1, 175680);
INSERT INTO sc_tool (id_tool, type_tool, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_tool_dimension, id_money, id_location, name, "time", value_minutes) VALUES (2, 'Mecánica', 120, 1, 'martillos y martillos', 12000.00, '/home/gchavarro88/inputs_filePath/img/desorientado.jpg', 12, 'mart123', '2015-05-14', 'hola', 23, 2, 35, 3, 7, 'martillo', 2, 1209600);
INSERT INTO sc_tool (id_tool, type_tool, useful_life, supplier_guarantee, mark, value, path_picture, cost_center, serie, creation_date, description, id_stock, id_priority, id_tool_dimension, id_money, id_location, name, "time", value_minutes) VALUES (3, 'Mecánica', 98, 16, 'complex', 30000000.00, '/home/gchavarro88/inputs_filePath/img/1796433_10153244835662971_6352760795065761361_n.jpg', 3, 'plccomplex', '2015-05-14', '', 24, 2, 36, 4, 6, 'plc', 2, 987840);


--
-- TOC entry 2656 (class 0 OID 137238)
-- Dependencies: 273
-- Data for Name: sc_tool_attached; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_tool_attached (id_tool_attached, type, tittle, description, id_tool) VALUES (1, 'SPECIFICATION', 'dsfdfsd', 'test', 1);
INSERT INTO sc_tool_attached (id_tool_attached, type, tittle, description, id_tool) VALUES (2, 'SPECIFICATION', 'test', 'test', 1);
INSERT INTO sc_tool_attached (id_tool_attached, type, tittle, description, id_tool) VALUES (3, 'FEACTURE', 'tets', 'tests', 1);
INSERT INTO sc_tool_attached (id_tool_attached, type, tittle, description, id_tool) VALUES (4, 'OBSERVATION', 'ddd', 'ddd', 1);


--
-- TOC entry 2657 (class 0 OID 137251)
-- Dependencies: 274
-- Data for Name: sc_tool_documents; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (1, '/home/gchavarro88/inputs_filePath/docs', 'test', '2015-05-13', '126575463-Heinrich-Boll-Opiniones-de-un-payaso-pdf.pdf', 'guschaor', 'application/pdf', 1);
INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (2, '/home/gchavarro88/inputs_filePath/docs', '1qw', '2015-05-14', '4437-22933-1-PB.pdf', 'yaconcha', 'application/pdf', 2);
INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (3, '/home/gchavarro88/inputs_filePath/docs', 'habalr', '2015-05-14', 'Plan de Trabajo Modalides version 1-2012 (1).docx', 'yaconcha', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 2);
INSERT INTO sc_tool_documents (id_tool_documents, document_path, document_tittle, creation_date, document_name, upload_by, type_document, id_tool) VALUES (4, '/home/gchavarro88/inputs_filePath/docs', 'conyrol', '2015-05-14', '11-08-09 revision bd.docx', 'yaconcha', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 3);


--
-- TOC entry 2602 (class 0 OID 130059)
-- Dependencies: 219
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
-- TOC entry 2603 (class 0 OID 130062)
-- Dependencies: 220
-- Data for Name: sc_users; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (1, 1, 1, 'guschaor', '4e991c769a2b9a881189cd86c160b604', '2014-07-26', '2014-10-24');
INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (4, 3, 1, 'yaconcha', '0525484994f3e8f42ba38c49930e356a', '2014-10-21', '2015-02-03');
INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (17, 13, 1, 'lisgirlo', '46e435b6e98cec728f5be5d4dbd97ffb', '2015-03-15', NULL);
INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (18, 11, 5, 'valentina', '3000e0a0b51c05df9739cd6c375c0330', '2015-03-15', '2015-03-15');
INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (19, 2, 1, 'jguerrero', '7453a4cfb3d3db9f6d477e5d2d87c4be', '2015-04-18', NULL);
INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (20, 15, 7, 'pepe', 'e10adc3949ba59abbe56e057f20f883e', '2015-04-25', '2015-04-25');


--
-- TOC entry 2604 (class 0 OID 130065)
-- Dependencies: 221
-- Data for Name: sc_work_experience; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_work_experience (id_work_experience, init_date, end_date, id_employee, company_name) VALUES (9, '2014-11-09', '2014-11-11', 8, 'Ip total');
INSERT INTO sc_work_experience (id_work_experience, init_date, end_date, id_employee, company_name) VALUES (10, '2015-04-02', '2015-04-30', 9, 'Tecnoquimicas');


--
-- TOC entry 2668 (class 0 OID 0)
-- Dependencies: 222
-- Name: sqclasstype; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqclasstype', 1, false);


--
-- TOC entry 2669 (class 0 OID 0)
-- Dependencies: 223
-- Name: sqsccompetencies; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqsccompetencies', 7, true);


--
-- TOC entry 2670 (class 0 OID 0)
-- Dependencies: 224
-- Name: sqsccostcenter; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqsccostcenter', 12, true);


--
-- TOC entry 2671 (class 0 OID 0)
-- Dependencies: 225
-- Name: sqscdistributionunit; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscdistributionunit', 4, true);


--
-- TOC entry 2672 (class 0 OID 0)
-- Dependencies: 226
-- Name: sqscdocuments; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscdocuments', 35, true);


--
-- TOC entry 2673 (class 0 OID 0)
-- Dependencies: 227
-- Name: sqscemployee; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscemployee', 11, true);


--
-- TOC entry 2674 (class 0 OID 0)
-- Dependencies: 228
-- Name: sqscinput; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinput', 16, true);


--
-- TOC entry 2675 (class 0 OID 0)
-- Dependencies: 229
-- Name: sqscinputdimension; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinputdimension', 36, true);


--
-- TOC entry 2676 (class 0 OID 0)
-- Dependencies: 230
-- Name: sqscinputdocuments; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinputdocuments', 12, true);


--
-- TOC entry 2677 (class 0 OID 0)
-- Dependencies: 231
-- Name: sqscinputequivalence; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinputequivalence', 1, false);


--
-- TOC entry 2678 (class 0 OID 0)
-- Dependencies: 232
-- Name: sqscinputfeature; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinputfeature', 15, true);


--
-- TOC entry 2679 (class 0 OID 0)
-- Dependencies: 233
-- Name: sqscinputobservation; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinputobservation', 8, true);


--
-- TOC entry 2680 (class 0 OID 0)
-- Dependencies: 234
-- Name: sqscinputspecification; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscinputspecification', 16, true);


--
-- TOC entry 2681 (class 0 OID 0)
-- Dependencies: 235
-- Name: sqsclocation; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqsclocation', 17, true);


--
-- TOC entry 2682 (class 0 OID 0)
-- Dependencies: 236
-- Name: sqscmachine; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmachine', 1, false);


--
-- TOC entry 2683 (class 0 OID 0)
-- Dependencies: 237
-- Name: sqscmails; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmails', 14, true);


--
-- TOC entry 2684 (class 0 OID 0)
-- Dependencies: 238
-- Name: sqscmaintenanceplan; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmaintenanceplan', 1, false);


--
-- TOC entry 2685 (class 0 OID 0)
-- Dependencies: 239
-- Name: sqscmeasure; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmeasure', 21, true);


--
-- TOC entry 2686 (class 0 OID 0)
-- Dependencies: 240
-- Name: sqscmodulespermissionbyrole; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmodulespermissionbyrole', 585, true);


--
-- TOC entry 2687 (class 0 OID 0)
-- Dependencies: 241
-- Name: sqscmoney; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmoney', 1, false);


--
-- TOC entry 2688 (class 0 OID 0)
-- Dependencies: 242
-- Name: sqscoperatingconditions; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscoperatingconditions', 1, false);


--
-- TOC entry 2689 (class 0 OID 0)
-- Dependencies: 243
-- Name: sqscpackingunit; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpackingunit', 7, true);


--
-- TOC entry 2690 (class 0 OID 0)
-- Dependencies: 244
-- Name: sqscpartners; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpartners', 16, true);


--
-- TOC entry 2691 (class 0 OID 0)
-- Dependencies: 245
-- Name: sqscpartsandconsumables; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpartsandconsumables', 1, false);


--
-- TOC entry 2692 (class 0 OID 0)
-- Dependencies: 246
-- Name: sqscpersondocumentationattached; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpersondocumentationattached', 6, false);


--
-- TOC entry 2693 (class 0 OID 0)
-- Dependencies: 247
-- Name: sqscpersonobservations; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpersonobservations', 13, true);


--
-- TOC entry 2694 (class 0 OID 0)
-- Dependencies: 248
-- Name: sqscpersons; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpersons', 16, true);


--
-- TOC entry 2695 (class 0 OID 0)
-- Dependencies: 249
-- Name: sqscpersonspecifications; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpersonspecifications', 11, true);


--
-- TOC entry 2696 (class 0 OID 0)
-- Dependencies: 250
-- Name: sqscphones; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscphones', 17, true);


--
-- TOC entry 2697 (class 0 OID 0)
-- Dependencies: 251
-- Name: sqscphoto; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscphoto', 1, false);


--
-- TOC entry 2698 (class 0 OID 0)
-- Dependencies: 252
-- Name: sqscprocessattached; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocessattached', 1, false);


--
-- TOC entry 2699 (class 0 OID 0)
-- Dependencies: 253
-- Name: sqscprocessemployee; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocessemployee', 33, true);


--
-- TOC entry 2700 (class 0 OID 0)
-- Dependencies: 254
-- Name: sqscprocessinput; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocessinput', 23, true);


--
-- TOC entry 2701 (class 0 OID 0)
-- Dependencies: 255
-- Name: sqscprocessmachine; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocessmachine', 32, true);


--
-- TOC entry 2702 (class 0 OID 0)
-- Dependencies: 256
-- Name: sqscprocessproduct; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocessproduct', 25, true);


--
-- TOC entry 2703 (class 0 OID 0)
-- Dependencies: 257
-- Name: sqscprocesstype; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscprocesstype', 1, false);


--
-- TOC entry 2704 (class 0 OID 0)
-- Dependencies: 258
-- Name: sqscproductattached; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscproductattached', 47, true);


--
-- TOC entry 2705 (class 0 OID 0)
-- Dependencies: 259
-- Name: sqscproductdocuments; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscproductdocuments', 13, true);


--
-- TOC entry 2706 (class 0 OID 0)
-- Dependencies: 260
-- Name: sqscproductformulation; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscproductformulation', 12, true);


--
-- TOC entry 2707 (class 0 OID 0)
-- Dependencies: 261
-- Name: sqscreplacement; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscreplacement', 6, true);


--
-- TOC entry 2708 (class 0 OID 0)
-- Dependencies: 262
-- Name: sqscreplacementattached; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscreplacementattached', 23, true);


--
-- TOC entry 2709 (class 0 OID 0)
-- Dependencies: 263
-- Name: sqscreplacementdocuments; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscreplacementdocuments', 8, true);


--
-- TOC entry 2710 (class 0 OID 0)
-- Dependencies: 264
-- Name: sqscroles; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscroles', 7, true);


--
-- TOC entry 2711 (class 0 OID 0)
-- Dependencies: 265
-- Name: sqscservicesorproducts; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscservicesorproducts', 15, true);


--
-- TOC entry 2712 (class 0 OID 0)
-- Dependencies: 266
-- Name: sqscstock; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscstock', 24, true);


--
-- TOC entry 2713 (class 0 OID 0)
-- Dependencies: 267
-- Name: sqscstore; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscstore', 1, false);


--
-- TOC entry 2714 (class 0 OID 0)
-- Dependencies: 268
-- Name: sqsctool; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqsctool', 3, true);


--
-- TOC entry 2715 (class 0 OID 0)
-- Dependencies: 275
-- Name: sqsctoolattached; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqsctoolattached', 4, true);


--
-- TOC entry 2716 (class 0 OID 0)
-- Dependencies: 276
-- Name: sqsctooldocuments; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqsctooldocuments', 4, true);


--
-- TOC entry 2717 (class 0 OID 0)
-- Dependencies: 269
-- Name: sqscusers; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscusers', 20, true);


--
-- TOC entry 2718 (class 0 OID 0)
-- Dependencies: 270
-- Name: sqscworkexperience; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscworkexperience', 10, true);


--
-- TOC entry 2719 (class 0 OID 0)
-- Dependencies: 271
-- Name: sqtype; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqtype', 1, false);


--
-- TOC entry 2259 (class 2606 OID 130169)
-- Name: PK_DISTRIBUTION_UNIT; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_distribution_unit
    ADD CONSTRAINT "PK_DISTRIBUTION_UNIT" PRIMARY KEY (id_distribution_unit);


--
-- TOC entry 2267 (class 2606 OID 130171)
-- Name: PK_INPUT_DIMENSION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input_dimension
    ADD CONSTRAINT "PK_INPUT_DIMENSION" PRIMARY KEY (id_input_dimension);


--
-- TOC entry 2271 (class 2606 OID 130173)
-- Name: PK_INPUT_EQUIVALENCES; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input_equivalence
    ADD CONSTRAINT "PK_INPUT_EQUIVALENCES" PRIMARY KEY (id_input_equivalence);


--
-- TOC entry 2279 (class 2606 OID 130175)
-- Name: PK_INPUT_LOCATION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_location
    ADD CONSTRAINT "PK_INPUT_LOCATION" PRIMARY KEY (id_location);


--
-- TOC entry 2351 (class 2606 OID 130177)
-- Name: PK_INPUT_STOCK; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_stock
    ADD CONSTRAINT "PK_INPUT_STOCK" PRIMARY KEY (id_stock);


--
-- TOC entry 2323 (class 2606 OID 130179)
-- Name: PK_PROCESS_ATTACHED; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_process_attached
    ADD CONSTRAINT "PK_PROCESS_ATTACHED" PRIMARY KEY (id_process_attached);


--
-- TOC entry 2325 (class 2606 OID 130181)
-- Name: PK_PROCESS_EMPLOYEE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_process_employee
    ADD CONSTRAINT "PK_PROCESS_EMPLOYEE" PRIMARY KEY (id_process_employee);


--
-- TOC entry 2327 (class 2606 OID 130183)
-- Name: PK_PROCESS_INPUT; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_process_input
    ADD CONSTRAINT "PK_PROCESS_INPUT" PRIMARY KEY (id_process_input);


--
-- TOC entry 2321 (class 2606 OID 130185)
-- Name: PK_PROCESS_PRODUCT; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_procces_product
    ADD CONSTRAINT "PK_PROCESS_PRODUCT" PRIMARY KEY (id_process_product);


--
-- TOC entry 2331 (class 2606 OID 130187)
-- Name: PK_PROCESS_TYPE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_process_type
    ADD CONSTRAINT "PK_PROCESS_TYPE" PRIMARY KEY (id_process_type);


--
-- TOC entry 2333 (class 2606 OID 130189)
-- Name: PK_PRODUCT_ATTACHED; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_product_attached
    ADD CONSTRAINT "PK_PRODUCT_ATTACHED" PRIMARY KEY (id_product_attached);


--
-- TOC entry 2341 (class 2606 OID 130191)
-- Name: PK_REPLACEMENT_ATTACHED; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_replacement_attached
    ADD CONSTRAINT "PK_REPLACEMENT_ATTACHED" PRIMARY KEY (id_replacement_attached);


--
-- TOC entry 2255 (class 2606 OID 130193)
-- Name: PK_SC_CONSTANTS_LOAD_FILES; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_constants_load_files
    ADD CONSTRAINT "PK_SC_CONSTANTS_LOAD_FILES" PRIMARY KEY (id_constants_load_file);


--
-- TOC entry 2265 (class 2606 OID 130195)
-- Name: PK_SC_INPUT; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "PK_SC_INPUT" PRIMARY KEY (id_input);


--
-- TOC entry 2269 (class 2606 OID 130197)
-- Name: PK_SC_INPUT_DOCUMENTS; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input_documents
    ADD CONSTRAINT "PK_SC_INPUT_DOCUMENTS" PRIMARY KEY (id_input_documents);


--
-- TOC entry 2273 (class 2606 OID 130199)
-- Name: PK_SC_INPUT_FEATURES; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input_feactures
    ADD CONSTRAINT "PK_SC_INPUT_FEATURES" PRIMARY KEY (id_input_feactures);


--
-- TOC entry 2275 (class 2606 OID 130201)
-- Name: PK_SC_INPUT_OBSERVATIONS; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input_observations
    ADD CONSTRAINT "PK_SC_INPUT_OBSERVATIONS" PRIMARY KEY (id_input_observation);


--
-- TOC entry 2277 (class 2606 OID 130203)
-- Name: PK_SC_INPUT_SPECIFICATIONS; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_input_specifications
    ADD CONSTRAINT "PK_SC_INPUT_SPECIFICATIONS" PRIMARY KEY (id_input_specifications);


--
-- TOC entry 2297 (class 2606 OID 130205)
-- Name: PK_SC_M0NEY; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_money
    ADD CONSTRAINT "PK_SC_M0NEY" PRIMARY KEY (id_money);


--
-- TOC entry 2281 (class 2606 OID 130207)
-- Name: PK_SC_MACHINE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_machine
    ADD CONSTRAINT "PK_SC_MACHINE" PRIMARY KEY (id_machine);


--
-- TOC entry 2287 (class 2606 OID 130209)
-- Name: PK_SC_MEASURE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_measure_unit
    ADD CONSTRAINT "PK_SC_MEASURE" PRIMARY KEY (id_measure);


--
-- TOC entry 2301 (class 2606 OID 130211)
-- Name: PK_SC_PACKING; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_packing_unit
    ADD CONSTRAINT "PK_SC_PACKING" PRIMARY KEY (id_packing);


--
-- TOC entry 2319 (class 2606 OID 130213)
-- Name: PK_SC_PRIORITY; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_priority
    ADD CONSTRAINT "PK_SC_PRIORITY" PRIMARY KEY (id_priority);


--
-- TOC entry 2329 (class 2606 OID 130215)
-- Name: PK_SC_PROCESS_MACHINE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_process_machine
    ADD CONSTRAINT "PK_SC_PROCESS_MACHINE" PRIMARY KEY (id_process_machine);


--
-- TOC entry 2335 (class 2606 OID 130217)
-- Name: PK_SC_PRODUCT_DOCUMENTS; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_product_documents
    ADD CONSTRAINT "PK_SC_PRODUCT_DOCUMENTS" PRIMARY KEY (id_product_documents);


--
-- TOC entry 2337 (class 2606 OID 130219)
-- Name: PK_SC_PRODUCT_FORMULATION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "PK_SC_PRODUCT_FORMULATION" PRIMARY KEY (id_product_formulation);


--
-- TOC entry 2339 (class 2606 OID 130221)
-- Name: PK_SC_REPLACEMENT; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "PK_SC_REPLACEMENT" PRIMARY KEY (id_replacement);


--
-- TOC entry 2343 (class 2606 OID 130223)
-- Name: PK_SC_REPLACEMENT_DOCUMENTS; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_replacement_documents
    ADD CONSTRAINT "PK_SC_REPLACEMENT_DOCUMENTS" PRIMARY KEY (id_replacement_documents);


--
-- TOC entry 2353 (class 2606 OID 130225)
-- Name: PK_SC_STORE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_store
    ADD CONSTRAINT "PK_SC_STORE" PRIMARY KEY (id_store);


--
-- TOC entry 2367 (class 2606 OID 137197)
-- Name: PK_SC_TOOL; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "PK_SC_TOOL" PRIMARY KEY (id_tool);


--
-- TOC entry 2371 (class 2606 OID 137258)
-- Name: PK_SC_TOOL_DOCUMENTS; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_tool_documents
    ADD CONSTRAINT "PK_SC_TOOL_DOCUMENTS" PRIMARY KEY (id_tool_documents);


--
-- TOC entry 2355 (class 2606 OID 130227)
-- Name: PK_TIME; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_time
    ADD CONSTRAINT "PK_TIME" PRIMARY KEY (id_time);


--
-- TOC entry 2369 (class 2606 OID 137245)
-- Name: PK_TOOL_ATTACHED; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_tool_attached
    ADD CONSTRAINT "PK_TOOL_ATTACHED" PRIMARY KEY (id_tool_attached);


--
-- TOC entry 2307 (class 2606 OID 130229)
-- Name: UK_IDENTIFICATION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_person
    ADD CONSTRAINT "UK_IDENTIFICATION" UNIQUE (identification);


--
-- TOC entry 2345 (class 2606 OID 130231)
-- Name: UK_ROLENAME; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_roles
    ADD CONSTRAINT "UK_ROLENAME" UNIQUE (name);


--
-- TOC entry 2293 (class 2606 OID 130233)
-- Name: UK_SC_ROLES_SC_MODULE_PERMISSION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_module_permission_by_role
    ADD CONSTRAINT "UK_SC_ROLES_SC_MODULE_PERMISSION" UNIQUE (id_role, id_module_permission);


--
-- TOC entry 2357 (class 2606 OID 130235)
-- Name: UK_TIME; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_time
    ADD CONSTRAINT "UK_TIME" UNIQUE (acronym);


--
-- TOC entry 2289 (class 2606 OID 130237)
-- Name: UK_UNIT_MEASURE; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_measure_unit
    ADD CONSTRAINT "UK_UNIT_MEASURE" UNIQUE (acronym);


--
-- TOC entry 2361 (class 2606 OID 130239)
-- Name: UK_USERNAME; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_users
    ADD CONSTRAINT "UK_USERNAME" UNIQUE (login);


--
-- TOC entry 2251 (class 2606 OID 130241)
-- Name: pk_class_type; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_class_type
    ADD CONSTRAINT pk_class_type PRIMARY KEY (id_class_type);


--
-- TOC entry 2253 (class 2606 OID 130243)
-- Name: pk_competencies; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_competencies
    ADD CONSTRAINT pk_competencies PRIMARY KEY (id_competencies);


--
-- TOC entry 2257 (class 2606 OID 130245)
-- Name: pk_cost_center; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_cost_center
    ADD CONSTRAINT pk_cost_center PRIMARY KEY (id_cost_center);


--
-- TOC entry 2261 (class 2606 OID 130247)
-- Name: pk_document; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_documents
    ADD CONSTRAINT pk_document PRIMARY KEY (id_document);


--
-- TOC entry 2263 (class 2606 OID 130249)
-- Name: pk_employee; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_employee
    ADD CONSTRAINT pk_employee PRIMARY KEY (id_employee);


--
-- TOC entry 2283 (class 2606 OID 130251)
-- Name: pk_mails; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_mails
    ADD CONSTRAINT pk_mails PRIMARY KEY (id_mail);


--
-- TOC entry 2285 (class 2606 OID 130253)
-- Name: pk_maintenance_plan; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_maintenance_plan
    ADD CONSTRAINT pk_maintenance_plan PRIMARY KEY (id_maintenance_plan);


--
-- TOC entry 2295 (class 2606 OID 130255)
-- Name: pk_module_permission_by_role; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_module_permission_by_role
    ADD CONSTRAINT pk_module_permission_by_role PRIMARY KEY (id_module_permission_by_role);


--
-- TOC entry 2299 (class 2606 OID 130257)
-- Name: pk_operatin_condition; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_operating_conditions
    ADD CONSTRAINT pk_operatin_condition PRIMARY KEY (id_operating_condition);


--
-- TOC entry 2303 (class 2606 OID 130259)
-- Name: pk_partner; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_partner
    ADD CONSTRAINT pk_partner PRIMARY KEY (id_partner);


--
-- TOC entry 2305 (class 2606 OID 130261)
-- Name: pk_parts_and_consumables; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_parts_and_consumables
    ADD CONSTRAINT pk_parts_and_consumables PRIMARY KEY (id_parts_and_consumables);


--
-- TOC entry 2309 (class 2606 OID 130263)
-- Name: pk_person; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_person
    ADD CONSTRAINT pk_person PRIMARY KEY (id_person);


--
-- TOC entry 2311 (class 2606 OID 130265)
-- Name: pk_person_observations; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_person_observations
    ADD CONSTRAINT pk_person_observations PRIMARY KEY (id_person_observations);


--
-- TOC entry 2313 (class 2606 OID 130267)
-- Name: pk_person_specifications; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_person_specifications
    ADD CONSTRAINT pk_person_specifications PRIMARY KEY (id_person_specifications);


--
-- TOC entry 2315 (class 2606 OID 130269)
-- Name: pk_phones; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_phones
    ADD CONSTRAINT pk_phones PRIMARY KEY (id_phone);


--
-- TOC entry 2317 (class 2606 OID 130271)
-- Name: pk_photo; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_photo
    ADD CONSTRAINT pk_photo PRIMARY KEY (id_photo);


--
-- TOC entry 2291 (class 2606 OID 130273)
-- Name: pk_sc_module_permission; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_module_permission
    ADD CONSTRAINT pk_sc_module_permission PRIMARY KEY (id_module_permission);


--
-- TOC entry 2347 (class 2606 OID 130275)
-- Name: pk_sc_roles; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_roles
    ADD CONSTRAINT pk_sc_roles PRIMARY KEY (id_role);


--
-- TOC entry 2349 (class 2606 OID 130277)
-- Name: pk_service_or_product; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_services_or_products
    ADD CONSTRAINT pk_service_or_product PRIMARY KEY (id_service_or_products);


--
-- TOC entry 2359 (class 2606 OID 130281)
-- Name: pk_type; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_type
    ADD CONSTRAINT pk_type PRIMARY KEY (id_type);


--
-- TOC entry 2363 (class 2606 OID 130283)
-- Name: pk_users; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_users
    ADD CONSTRAINT pk_users PRIMARY KEY (id_user);


--
-- TOC entry 2365 (class 2606 OID 130285)
-- Name: pk_work_experience; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_work_experience
    ADD CONSTRAINT pk_work_experience PRIMARY KEY (id_work_experience);


--
-- TOC entry 2375 (class 2606 OID 130286)
-- Name: FK_CENTER_COST_INPUT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_CENTER_COST_INPUT" FOREIGN KEY (cost_center) REFERENCES sc_cost_center(id_cost_center);


--
-- TOC entry 2420 (class 2606 OID 130291)
-- Name: FK_COSTCENTER_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_COSTCENTER_REPLACEMENT" FOREIGN KEY (cost_center) REFERENCES sc_cost_center(id_cost_center);


--
-- TOC entry 2436 (class 2606 OID 137198)
-- Name: FK_COSTCENTER_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_COSTCENTER_TOOL" FOREIGN KEY (cost_center) REFERENCES sc_cost_center(id_cost_center);


--
-- TOC entry 2421 (class 2606 OID 130296)
-- Name: FK_DIMENSION_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_DIMENSION_REPLACEMENT" FOREIGN KEY (id_replacement_dimension) REFERENCES sc_input_dimension(id_input_dimension);


--
-- TOC entry 2437 (class 2606 OID 137203)
-- Name: FK_DIMENSION_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_DIMENSION_TOOL" FOREIGN KEY (id_tool_dimension) REFERENCES sc_input_dimension(id_input_dimension);


--
-- TOC entry 2385 (class 2606 OID 130301)
-- Name: FK_INPUT_EQUIVALENCE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input_equivalence
    ADD CONSTRAINT "FK_INPUT_EQUIVALENCE" FOREIGN KEY (id_input) REFERENCES sc_input(id_input);


--
-- TOC entry 2386 (class 2606 OID 130306)
-- Name: FK_INPUT_FEACTURES; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input_feactures
    ADD CONSTRAINT "FK_INPUT_FEACTURES" FOREIGN KEY (id_input) REFERENCES sc_input(id_input);


--
-- TOC entry 2387 (class 2606 OID 130311)
-- Name: FK_INPUT_OBSERVATIONS; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input_observations
    ADD CONSTRAINT "FK_INPUT_OBSERVATIONS" FOREIGN KEY (id_input) REFERENCES sc_input(id_input);


--
-- TOC entry 2388 (class 2606 OID 130316)
-- Name: FK_INPUT_SPECIFICATIONS; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input_specifications
    ADD CONSTRAINT "FK_INPUT_SPECIFICATIONS" FOREIGN KEY (id_input) REFERENCES sc_input(id_input);


--
-- TOC entry 2431 (class 2606 OID 130321)
-- Name: FK_INPUT_STOCK_STORE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_stock
    ADD CONSTRAINT "FK_INPUT_STOCK_STORE" FOREIGN KEY (id_store) REFERENCES sc_store(id_store);


--
-- TOC entry 2422 (class 2606 OID 130326)
-- Name: FK_LOCATION_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_LOCATION_REPLACEMENT" FOREIGN KEY (id_location) REFERENCES sc_location(id_location);


--
-- TOC entry 2389 (class 2606 OID 130331)
-- Name: FK_LOCATION_STORE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_location
    ADD CONSTRAINT "FK_LOCATION_STORE" FOREIGN KEY (id_store) REFERENCES sc_store(id_store);


--
-- TOC entry 2438 (class 2606 OID 137208)
-- Name: FK_LOCATION_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_LOCATION_TOOL" FOREIGN KEY (id_location) REFERENCES sc_location(id_location);


--
-- TOC entry 2423 (class 2606 OID 130336)
-- Name: FK_MONEY_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_MONEY_REPLACEMENT" FOREIGN KEY (id_money) REFERENCES sc_money(id_money);


--
-- TOC entry 2439 (class 2606 OID 137213)
-- Name: FK_MONEY_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_MONEY_TOOL" FOREIGN KEY (id_money) REFERENCES sc_money(id_money);


--
-- TOC entry 2424 (class 2606 OID 130341)
-- Name: FK_PRIORITY_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_PRIORITY_REPLACEMENT" FOREIGN KEY (id_priority) REFERENCES sc_priority(id_priority);


--
-- TOC entry 2440 (class 2606 OID 137218)
-- Name: FK_PRIORITY_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_PRIORITY_TOOL" FOREIGN KEY (id_priority) REFERENCES sc_priority(id_priority);


--
-- TOC entry 2406 (class 2606 OID 130346)
-- Name: FK_PROCESS_EMPLOYEE_EMPLOYEE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_employee
    ADD CONSTRAINT "FK_PROCESS_EMPLOYEE_EMPLOYEE" FOREIGN KEY (id_employee) REFERENCES sc_employee(id_employee);


--
-- TOC entry 2407 (class 2606 OID 130351)
-- Name: FK_PROCESS_EMPLOYEE_PROCESS; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_employee
    ADD CONSTRAINT "FK_PROCESS_EMPLOYEE_PROCESS" FOREIGN KEY (id_process) REFERENCES sc_procces_product(id_process_product);


--
-- TOC entry 2408 (class 2606 OID 130356)
-- Name: FK_PROCESS_INPUT_INPUT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_input
    ADD CONSTRAINT "FK_PROCESS_INPUT_INPUT" FOREIGN KEY (id_input) REFERENCES sc_input(id_input);


--
-- TOC entry 2409 (class 2606 OID 130361)
-- Name: FK_PROCESS_INPUT_PROCESS; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_input
    ADD CONSTRAINT "FK_PROCESS_INPUT_PROCESS" FOREIGN KEY (id_process) REFERENCES sc_procces_product(id_process_product);


--
-- TOC entry 2410 (class 2606 OID 130366)
-- Name: FK_PROCESS_MACHINE_MACHINE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_machine
    ADD CONSTRAINT "FK_PROCESS_MACHINE_MACHINE" FOREIGN KEY (id_machine) REFERENCES sc_machine(id_machine);


--
-- TOC entry 2411 (class 2606 OID 130371)
-- Name: FK_PROCESS_PROCESS_MACHINE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_process_machine
    ADD CONSTRAINT "FK_PROCESS_PROCESS_MACHINE" FOREIGN KEY (id_process) REFERENCES sc_procces_product(id_process_product);


--
-- TOC entry 2404 (class 2606 OID 130376)
-- Name: FK_PROCESS_PROCESS_TYPE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_procces_product
    ADD CONSTRAINT "FK_PROCESS_PROCESS_TYPE" FOREIGN KEY (id_process_type) REFERENCES sc_process_type(id_process_type);


--
-- TOC entry 2405 (class 2606 OID 130381)
-- Name: FK_PROCESS_PRODUCT_FORMULATION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_procces_product
    ADD CONSTRAINT "FK_PROCESS_PRODUCT_FORMULATION" FOREIGN KEY (id_product_formulation) REFERENCES sc_product_formulation(id_product_formulation);


--
-- TOC entry 2414 (class 2606 OID 130386)
-- Name: FK_PRODUCT_DIMENSION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "FK_PRODUCT_DIMENSION" FOREIGN KEY (id_product_dimension) REFERENCES sc_input_dimension(id_input_dimension);


--
-- TOC entry 2412 (class 2606 OID 130391)
-- Name: FK_PRODUCT_FORMULATION_ATTACHED; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_attached
    ADD CONSTRAINT "FK_PRODUCT_FORMULATION_ATTACHED" FOREIGN KEY (id_product_formulation) REFERENCES sc_product_formulation(id_product_formulation);


--
-- TOC entry 2415 (class 2606 OID 130396)
-- Name: FK_PRODUCT_FORMULATION_COST_CENTER; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "FK_PRODUCT_FORMULATION_COST_CENTER" FOREIGN KEY (id_cost_center) REFERENCES sc_cost_center(id_cost_center);


--
-- TOC entry 2416 (class 2606 OID 130401)
-- Name: FK_PRODUCT_FORMULATION_MONEY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "FK_PRODUCT_FORMULATION_MONEY" FOREIGN KEY (id_money) REFERENCES sc_money(id_money);


--
-- TOC entry 2417 (class 2606 OID 130406)
-- Name: FK_PRODUCT_FORMULATION_PACKING; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "FK_PRODUCT_FORMULATION_PACKING" FOREIGN KEY (id_packing) REFERENCES sc_packing_unit(id_packing);


--
-- TOC entry 2418 (class 2606 OID 130411)
-- Name: FK_PRODUCT_FORMULATION_PARTNER; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "FK_PRODUCT_FORMULATION_PARTNER" FOREIGN KEY (id_partner) REFERENCES sc_partner(id_partner);


--
-- TOC entry 2419 (class 2606 OID 130416)
-- Name: FK_PRODUCT_FORMULATION_PRIORITY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_formulation
    ADD CONSTRAINT "FK_PRODUCT_FORMULATION_PRIORITY" FOREIGN KEY (id_priority) REFERENCES sc_priority(id_priority);


--
-- TOC entry 2428 (class 2606 OID 130421)
-- Name: FK_REPLACEMENT_FORMULATION_ATTACHED; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement_attached
    ADD CONSTRAINT "FK_REPLACEMENT_FORMULATION_ATTACHED" FOREIGN KEY (id_replacement) REFERENCES sc_replacement(id_replacement);


--
-- TOC entry 2376 (class 2606 OID 130426)
-- Name: FK_SC_INPUT_DISTRIBUTION_UNIT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_DISTRIBUTION_UNIT" FOREIGN KEY (id_distribution_unit) REFERENCES sc_distribution_unit(id_distribution_unit);


--
-- TOC entry 2384 (class 2606 OID 130431)
-- Name: FK_SC_INPUT_DOCUMENTS_INPUT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input_documents
    ADD CONSTRAINT "FK_SC_INPUT_DOCUMENTS_INPUT" FOREIGN KEY (id_input) REFERENCES sc_input(id_input);


--
-- TOC entry 2377 (class 2606 OID 130436)
-- Name: FK_SC_INPUT_LOCATION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_LOCATION" FOREIGN KEY (id_location) REFERENCES sc_location(id_location);


--
-- TOC entry 2378 (class 2606 OID 130441)
-- Name: FK_SC_INPUT_MONEY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_MONEY" FOREIGN KEY (id_money) REFERENCES sc_money(id_money);


--
-- TOC entry 2379 (class 2606 OID 130446)
-- Name: FK_SC_INPUT_PACKING_UNIT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_PACKING_UNIT" FOREIGN KEY (id_packing) REFERENCES sc_packing_unit(id_packing);


--
-- TOC entry 2380 (class 2606 OID 130451)
-- Name: FK_SC_INPUT_SC_DIMENSION; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_SC_DIMENSION" FOREIGN KEY (id_input_dimension) REFERENCES sc_input_dimension(id_input_dimension);


--
-- TOC entry 2381 (class 2606 OID 130456)
-- Name: FK_SC_INPUT_SC_PRIORITY; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_SC_PRIORITY" FOREIGN KEY (id_priority) REFERENCES sc_priority(id_priority);


--
-- TOC entry 2382 (class 2606 OID 130461)
-- Name: FK_SC_INPUT_STOCK; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SC_INPUT_STOCK" FOREIGN KEY (id_stock) REFERENCES sc_stock(id_stock);


--
-- TOC entry 2413 (class 2606 OID 130466)
-- Name: FK_SC_PRODUCT_DOCUMENTS_INPUT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_product_documents
    ADD CONSTRAINT "FK_SC_PRODUCT_DOCUMENTS_INPUT" FOREIGN KEY (id_product_formulation) REFERENCES sc_product_formulation(id_product_formulation);


--
-- TOC entry 2429 (class 2606 OID 130471)
-- Name: FK_SC_REPLACEMENT_DOCUMENTS_; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement_documents
    ADD CONSTRAINT "FK_SC_REPLACEMENT_DOCUMENTS_" FOREIGN KEY (id_replacement) REFERENCES sc_replacement(id_replacement);


--
-- TOC entry 2445 (class 2606 OID 137259)
-- Name: FK_SC_TOOL_DOCUMENTS_; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool_documents
    ADD CONSTRAINT "FK_SC_TOOL_DOCUMENTS_" FOREIGN KEY (id_tool) REFERENCES sc_tool(id_tool);


--
-- TOC entry 2425 (class 2606 OID 130476)
-- Name: FK_STOCK_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_STOCK_REPLACEMENT" FOREIGN KEY (id_stock) REFERENCES sc_stock(id_stock);


--
-- TOC entry 2441 (class 2606 OID 137223)
-- Name: FK_STOCK_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_STOCK_TOOL" FOREIGN KEY (id_stock) REFERENCES sc_stock(id_stock);


--
-- TOC entry 2426 (class 2606 OID 130481)
-- Name: FK_SUPPLIER_GUARANTEE; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_SUPPLIER_GUARANTEE" FOREIGN KEY (supplier_guarantee) REFERENCES sc_partner(id_partner);


--
-- TOC entry 2383 (class 2606 OID 130486)
-- Name: FK_SUPPLIER_PARTNERS; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_input
    ADD CONSTRAINT "FK_SUPPLIER_PARTNERS" FOREIGN KEY (supplier_guarantee) REFERENCES sc_partner(id_partner);


--
-- TOC entry 2442 (class 2606 OID 137228)
-- Name: FK_SUPPLIER_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_SUPPLIER_TOOL" FOREIGN KEY (supplier_guarantee) REFERENCES sc_partner(id_partner);


--
-- TOC entry 2427 (class 2606 OID 130491)
-- Name: FK_TIME_REPLACEMENT; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_replacement
    ADD CONSTRAINT "FK_TIME_REPLACEMENT" FOREIGN KEY ("time") REFERENCES sc_time(id_time);


--
-- TOC entry 2443 (class 2606 OID 137233)
-- Name: FK_TIME_TOOL; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool
    ADD CONSTRAINT "FK_TIME_TOOL" FOREIGN KEY ("time") REFERENCES sc_time(id_time);


--
-- TOC entry 2444 (class 2606 OID 137246)
-- Name: FK_TOOL_ATTACHED; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_tool_attached
    ADD CONSTRAINT "FK_TOOL_ATTACHED" FOREIGN KEY (id_tool) REFERENCES sc_tool(id_tool);


--
-- TOC entry 2374 (class 2606 OID 130496)
-- Name: fk_employee_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_employee
    ADD CONSTRAINT fk_employee_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2390 (class 2606 OID 130501)
-- Name: fk_mails_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_mails
    ADD CONSTRAINT fk_mails_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2397 (class 2606 OID 130506)
-- Name: fk_module_permission; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_module_permission_by_role
    ADD CONSTRAINT fk_module_permission FOREIGN KEY (id_module_permission) REFERENCES sc_module_permission(id_module_permission);


--
-- TOC entry 2398 (class 2606 OID 130511)
-- Name: fk_module_permission_by_role_for_role; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_module_permission_by_role
    ADD CONSTRAINT fk_module_permission_by_role_for_role FOREIGN KEY (id_role) REFERENCES sc_roles(id_role);


--
-- TOC entry 2399 (class 2606 OID 130516)
-- Name: fk_partner_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_partner
    ADD CONSTRAINT fk_partner_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2391 (class 2606 OID 130521)
-- Name: fk_parts_and_consumables; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_maintenance_plan
    ADD CONSTRAINT fk_parts_and_consumables FOREIGN KEY (id_parts_and_consumables) REFERENCES sc_parts_and_consumables(id_parts_and_consumables);


--
-- TOC entry 2433 (class 2606 OID 130526)
-- Name: fk_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_users
    ADD CONSTRAINT fk_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2401 (class 2606 OID 130531)
-- Name: fk_person_observation_for_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_person_observations
    ADD CONSTRAINT fk_person_observation_for_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2402 (class 2606 OID 130536)
-- Name: fk_person_specifications_for_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_person_specifications
    ADD CONSTRAINT fk_person_specifications_for_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2403 (class 2606 OID 130541)
-- Name: fk_phones_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_phones
    ADD CONSTRAINT fk_phones_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2434 (class 2606 OID 130546)
-- Name: fk_role; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_users
    ADD CONSTRAINT fk_role FOREIGN KEY (id_role) REFERENCES sc_roles(id_role);


--
-- TOC entry 2373 (class 2606 OID 130551)
-- Name: fk_sc_person_to_sc_documents; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_documents
    ADD CONSTRAINT fk_sc_person_to_sc_documents FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2430 (class 2606 OID 130556)
-- Name: fk_service_or_product_partner; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_services_or_products
    ADD CONSTRAINT fk_service_or_product_partner FOREIGN KEY (id_partner) REFERENCES sc_partner(id_partner);


--
-- TOC entry 2392 (class 2606 OID 130566)
-- Name: fk_type; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_maintenance_plan
    ADD CONSTRAINT fk_type FOREIGN KEY (id_type_maintenance) REFERENCES sc_type(id_type);


--
-- TOC entry 2400 (class 2606 OID 130576)
-- Name: fk_type; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_parts_and_consumables
    ADD CONSTRAINT fk_type FOREIGN KEY (id_type) REFERENCES sc_type(id_type);


--
-- TOC entry 2393 (class 2606 OID 130581)
-- Name: fk_type2; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_maintenance_plan
    ADD CONSTRAINT fk_type2 FOREIGN KEY (id_type_hand_work_classification) REFERENCES sc_type(id_type);


--
-- TOC entry 2394 (class 2606 OID 130586)
-- Name: fk_type4; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_maintenance_plan
    ADD CONSTRAINT fk_type4 FOREIGN KEY (id_type_frequency) REFERENCES sc_type(id_type);


--
-- TOC entry 2395 (class 2606 OID 130591)
-- Name: fk_type5; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_maintenance_plan
    ADD CONSTRAINT fk_type5 FOREIGN KEY (id_type_downtime) REFERENCES sc_type(id_type);


--
-- TOC entry 2396 (class 2606 OID 130596)
-- Name: fk_type6; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_maintenance_plan
    ADD CONSTRAINT fk_type6 FOREIGN KEY (id_type_unit_unemployment) REFERENCES sc_type(id_type);


--
-- TOC entry 2435 (class 2606 OID 130601)
-- Name: fk_work_experience_employee; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_work_experience
    ADD CONSTRAINT fk_work_experience_employee FOREIGN KEY (id_employee) REFERENCES sc_employee(id_employee);


--
-- TOC entry 2432 (class 2606 OID 130606)
-- Name: id_class_type; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_type
    ADD CONSTRAINT id_class_type FOREIGN KEY (id_class_type) REFERENCES sc_class_type(id_class_type);


--
-- TOC entry 2372 (class 2606 OID 130611)
-- Name: id_competencies_employee; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_competencies
    ADD CONSTRAINT id_competencies_employee FOREIGN KEY (id_employee) REFERENCES sc_employee(id_employee);


-- Completed on 2015-05-19 06:59:39 COT

--
-- PostgreSQL database dump complete
--

