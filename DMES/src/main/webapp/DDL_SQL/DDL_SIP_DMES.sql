
CREATE SCHEMA dmes;


ALTER SCHEMA dmes OWNER TO "sipPrueba";

--
-- TOC entry 198 (class 3079 OID 12648)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 3015 (class 0 OID 0)
-- Dependencies: 198
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = dmes, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 168 (class 1259 OID 18684)
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
-- TOC entry 169 (class 1259 OID 18690)
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
-- TOC entry 170 (class 1259 OID 18696)
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
-- TOC entry 171 (class 1259 OID 18699)
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
-- TOC entry 172 (class 1259 OID 18702)
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
-- TOC entry 173 (class 1259 OID 18708)
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
-- TOC entry 174 (class 1259 OID 18711)
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
-- TOC entry 175 (class 1259 OID 18714)
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
-- TOC entry 176 (class 1259 OID 18720)
-- Name: sc_person_documentation_attached; Type: TABLE; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

CREATE TABLE sc_person_documentation_attached (
    id_person_documentation_attached numeric(18,0) NOT NULL,
    tittle character varying(200) NOT NULL,
    path character varying(2000) NOT NULL,
    id_person numeric(18,0) NOT NULL,
    creation_date date NOT NULL
);


ALTER TABLE dmes.sc_person_documentation_attached OWNER TO "sipPrueba";

--
-- TOC entry 177 (class 1259 OID 18726)
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
-- TOC entry 178 (class 1259 OID 18732)
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
-- TOC entry 179 (class 1259 OID 18738)
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
-- TOC entry 180 (class 1259 OID 18741)
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
-- TOC entry 181 (class 1259 OID 18747)
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
-- TOC entry 182 (class 1259 OID 18753)
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
-- TOC entry 183 (class 1259 OID 18756)
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
-- TOC entry 184 (class 1259 OID 18759)
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
-- TOC entry 185 (class 1259 OID 18761)
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
-- TOC entry 186 (class 1259 OID 18763)
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
-- TOC entry 187 (class 1259 OID 18765)
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
-- TOC entry 196 (class 1259 OID 18890)
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
-- TOC entry 188 (class 1259 OID 18767)
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
-- TOC entry 189 (class 1259 OID 18769)
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
-- TOC entry 190 (class 1259 OID 18771)
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
-- TOC entry 191 (class 1259 OID 18773)
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
-- TOC entry 192 (class 1259 OID 18775)
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
-- TOC entry 193 (class 1259 OID 18777)
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
-- TOC entry 197 (class 1259 OID 18892)
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
-- TOC entry 194 (class 1259 OID 18779)
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
-- TOC entry 195 (class 1259 OID 18781)
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
-- TOC entry 2980 (class 0 OID 18684)
-- Dependencies: 168
-- Data for Name: sc_competencies; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_competencies (id_competencies, tittle, description, id_employee) VALUES (6, 'Liderazgo', 'Liderazgo', 8);


--
-- TOC entry 2981 (class 0 OID 18690)
-- Dependencies: 169
-- Data for Name: sc_constants_load_files; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_constants_load_files (id_constants_load_file, max_size_file, extension, path) VALUES (1, 10, 'pdf,xsl,doc,xlsx,docx,txt,pps,ppt,pptx,ppsx', NULL);


--
-- TOC entry 2982 (class 0 OID 18696)
-- Dependencies: 170
-- Data for Name: sc_employee; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_employee (id_employee, "position", formation, admission_date, retirement_date, active, salary, hour_value, porcentage, amount, creation_date, modify_date, id_person) VALUES (8, 'Administrador del Mundo', 'Ingeniero de Sistemas', '2014-11-10', NULL, 'Y', 30000000000000.00, 3233.00, 32.00, 33.00, '2014-11-19', NULL, 1);


--
-- TOC entry 2983 (class 0 OID 18699)
-- Dependencies: 171
-- Data for Name: sc_mails; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (9, 'notengo@notengo.com', 'No tiene', 11);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (12, 'nataliagiron99@gmail.com', 'Correo Personal', 13);
INSERT INTO sc_mails (id_mail, mail, description, id_person) VALUES (13, 'dede@hdodk.com', 'Erróneo', 11);


--
-- TOC entry 2984 (class 0 OID 18702)
-- Dependencies: 172
-- Data for Name: sc_module_permission; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (1, 'Gestión de Planta', NULL, 'bar.png', 'Home', -1, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (2, 'Visibilidad de Planta', NULL, 'oee.png', 'Folder', 1, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (3, 'Programación de Orden de Fabricación', NULL, 'ord.png', 'Folder', 1, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (4, 'estión de la Calidad y la Trazabilidad', NULL, 'cal.png', 'Folder', 1, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (5, 'Gestión del Mantenimiento', NULL, 'man.png', 'Folder', 1, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (6, 'Gestión de los Recursos', NULL, 'rec.png', 'Folder', 1, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (7, 'Configuraciones', NULL, 'confi.png', 'Folder', 1, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (9, 'Recursos Humanos', '', NULL, 'Folder', 7, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (10, 'Recursos Materiales', NULL, NULL, 'Folder', 7, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (14, 'Usuarios', '', NULL, 'Item', 13, 'security/Scusers.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (8, 'Cerrar Sesión', NULL, 'salir.png', 'Folder', 1, 'exit');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (13, 'Usuarios y Permisos', '', NULL, 'Folder', 7, 'Help.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (15, 'Grupos y Roles', NULL, NULL, 'Item', 13, 'security/Scroles.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (16, 'Terceros', NULL, NULL, 'Item', 13, 'security/Scperson.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (11, 'Empleados', NULL, NULL, 'Item', 9, 'resources/humans/ScEmployees.jsf');
INSERT INTO sc_module_permission (id_module_permission, name, description, icone, type, id_father, page) VALUES (12, 'Proveedores', NULL, NULL, 'Item', 9, 'resources/humans/ScPartners.jsf');


--
-- TOC entry 2985 (class 0 OID 18708)
-- Dependencies: 173
-- Data for Name: sc_module_permission_by_role; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (202, 6, 'CRUD', 1);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (126, 1, 'CRUD', 1);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (127, 1, 'CRUD', 7);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (128, 1, 'CRUD', 9);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (129, 1, 'CRUD', 11);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (203, 6, 'CRUD', 7);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (204, 6, 'CRUD', 13);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (205, 6, 'CRUD', 15);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (209, 6, 'CRUD', 14);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (133, 1, 'CRUD', 12);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (213, 6, 'CRUD', 16);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (2, 1, 'CRUD', 2);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (3, 1, 'CRUD', 3);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (136, 1, 'CRUD', 13);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (137, 1, 'CRUD', 14);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (4, 1, 'CRUD', 4);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (5, 1, 'CRUD', 5);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (6, 1, 'CRUD', 6);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (10, 1, 'CRUD', 10);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (141, 1, 'CRUD', 15);
INSERT INTO sc_module_permission_by_role (id_module_permission_by_role, id_role, id_type, id_module_permission) VALUES (145, 1, 'CRUD', 16);
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


--
-- TOC entry 2986 (class 0 OID 18711)
-- Dependencies: 174
-- Data for Name: sc_partner; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_partner (id_partner, active, "position", web_page, creation_date, modify_date, id_person, company_name) VALUES (1, 'Y', 'Asesor', 'http://www.google.com', '2014-11-19', NULL, 13, 'Carvajal');
INSERT INTO sc_partner (id_partner, active, "position", web_page, creation_date, modify_date, id_person, company_name) VALUES (15, 'Y', 'dd', '', '2014-11-21', NULL, 11, 'ddd');


--
-- TOC entry 2987 (class 0 OID 18714)
-- Dependencies: 175
-- Data for Name: sc_person; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification) VALUES (2, 'Cristian Camilo', 'Chaparro Cuadros', 23, 'Colombia ', 'Cali', NULL, 'Oeste de Cali', NULL, NULL, '/', '2014-09-23', NULL, 111111111111111111);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification) VALUES (11, 'Valentina', 'Trujillo Ocampo', 33, 'Colombia', 'Cali', 'Barrio Champañat', 'Carrera 28 # 9-52', 'Colegio 3 de primaria básica', 'Niña de Javier', '/', '2014-11-04', '2014-11-09', 11133333333);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification) VALUES (13, 'Lizeth Nathalia', 'López Girón', 18, 'Colombia', 'Cali', 'Minión', 'Calle 23 # Alfonso Bonilla Aragón', 'Ingeniería Agrícola', 'Persona interesada en conocer la empresa', '/', '2014-11-08', '2014-11-09', 1149493828);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification) VALUES (3, 'Yoleidy', 'Aconcha', 26, 'Colombia', 'Cali', NULL, 'Carrera 103 Calle 49', NULL, NULL, '/', '2014-10-21', NULL, 11111111111111);
INSERT INTO sc_person (id_person, first_name, last_name, age, country, city, personal_information, domicilie, studies, description, path_photo, creation_date, modify_date, identification) VALUES (1, 'Gustavo Adolfo', 'Chavarro Ortiz', 26, 'Colombia', 'Cali', NULL, 'Carrera 21 # 13-16', NULL, NULL, '/', '2014-09-26', NULL, 1107046850);


--
-- TOC entry 2988 (class 0 OID 18720)
-- Dependencies: 176
-- Data for Name: sc_person_documentation_attached; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2989 (class 0 OID 18726)
-- Dependencies: 177
-- Data for Name: sc_person_observations; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_person_observations (id_person_observations, tittle, observation, id_person) VALUES (10, 'Almuerza', 'almuerza', 11);


--
-- TOC entry 2990 (class 0 OID 18732)
-- Dependencies: 178
-- Data for Name: sc_person_specifications; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_person_specifications (id_person_specifications, tittle, specification, id_person) VALUES (11, 'Dedicación', 'Dedicación', 11);


--
-- TOC entry 2991 (class 0 OID 18738)
-- Dependencies: 179
-- Data for Name: sc_phones; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (10, 3176600681, 'Telefono de Mama', 11);
INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (14, 3117036163, 'Personal', 13);
INSERT INTO sc_phones (id_phone, number_phone, description, id_person) VALUES (15, 3182433265, 'Abuela', 11);


--
-- TOC entry 2992 (class 0 OID 18741)
-- Dependencies: 180
-- Data for Name: sc_roles; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (3, 'INGENIERO DE PRODUCCIÓN', 'Encargado de la revisión y gestión de ordenes para su aprobación', '2014-10-13', '2014-10-13');
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (5, 'AUXILIAR DE COMIDA', 'Auxiliar de Comida', '2014-10-13', '2014-10-13');
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (1, 'ADMINISTRATOR', 'Grupo de permisos infinitos', '2014-09-26', '2014-10-13');
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (2, 'GESTIÓN HUMANA', 'sdddsdfdsfsddsf', '2014-10-13', '2014-10-13');
INSERT INTO sc_roles (id_role, name, description, creation_date, modify_date) VALUES (6, 'ARQUITECTO DE SOFTWARE', 'Encargado del diseño de componentes de la aplicación', '2014-10-13', '2014-10-13');


--
-- TOC entry 2993 (class 0 OID 18747)
-- Dependencies: 181
-- Data for Name: sc_services_or_products; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--



--
-- TOC entry 2994 (class 0 OID 18753)
-- Dependencies: 182
-- Data for Name: sc_users; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (1, 1, 1, 'guschaor', '4e991c769a2b9a881189cd86c160b604', '2014-07-26', '2014-10-24');
INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (4, 3, 5, 'yaconcha', '0525484994f3e8f42ba38c49930e356a', '2014-10-21', '2014-11-07');
INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (15, 13, 2, 'dddd', 'c1ebb4933e06ce5617483f665e26627c', '2014-11-15', NULL);
INSERT INTO sc_users (id_user, id_person, id_role, login, password, creation_date, modify_date) VALUES (14, 2, 3, 'gggg', 'c1ebb4933e06ce5617483f665e26627c', '2014-11-15', '2014-11-16');


--
-- TOC entry 2995 (class 0 OID 18756)
-- Dependencies: 183
-- Data for Name: sc_work_experience; Type: TABLE DATA; Schema: dmes; Owner: sipPrueba
--

INSERT INTO sc_work_experience (id_work_experience, init_date, end_date, id_employee, company_name) VALUES (9, '2014-11-09', '2014-11-11', 8, 'Ip total');


--
-- TOC entry 3016 (class 0 OID 0)
-- Dependencies: 184
-- Name: sqsccompetencies; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqsccompetencies', 6, true);


--
-- TOC entry 3017 (class 0 OID 0)
-- Dependencies: 185
-- Name: sqscemployee; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscemployee', 8, true);


--
-- TOC entry 3018 (class 0 OID 0)
-- Dependencies: 186
-- Name: sqscmails; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmails', 13, true);


--
-- TOC entry 3019 (class 0 OID 0)
-- Dependencies: 187
-- Name: sqscmodulespermissionbyrole; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscmodulespermissionbyrole', 213, true);


--
-- TOC entry 3020 (class 0 OID 0)
-- Dependencies: 196
-- Name: sqscpartners; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpartners', 15, true);


--
-- TOC entry 3021 (class 0 OID 0)
-- Dependencies: 188
-- Name: sqscpersondocumentationattached; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpersondocumentationattached', 6, false);


--
-- TOC entry 3022 (class 0 OID 0)
-- Dependencies: 189
-- Name: sqscpersonobservations; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpersonobservations', 10, true);


--
-- TOC entry 3023 (class 0 OID 0)
-- Dependencies: 190
-- Name: sqscpersons; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpersons', 13, true);


--
-- TOC entry 3024 (class 0 OID 0)
-- Dependencies: 191
-- Name: sqscpersonspecifications; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscpersonspecifications', 11, true);


--
-- TOC entry 3025 (class 0 OID 0)
-- Dependencies: 192
-- Name: sqscphones; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscphones', 15, true);


--
-- TOC entry 3026 (class 0 OID 0)
-- Dependencies: 193
-- Name: sqscroles; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscroles', 6, true);


--
-- TOC entry 3027 (class 0 OID 0)
-- Dependencies: 197
-- Name: sqscservicesorproducts; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscservicesorproducts', 15, false);


--
-- TOC entry 3028 (class 0 OID 0)
-- Dependencies: 194
-- Name: sqscusers; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscusers', 15, true);


--
-- TOC entry 3029 (class 0 OID 0)
-- Dependencies: 195
-- Name: sqscworkexperience; Type: SEQUENCE SET; Schema: dmes; Owner: sipPrueba
--

SELECT pg_catalog.setval('sqscworkexperience', 9, true);


--
-- TOC entry 2827 (class 2606 OID 18784)
-- Name: PK_SC_CONSTANTS_LOAD_FILES; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_constants_load_files
    ADD CONSTRAINT "PK_SC_CONSTANTS_LOAD_FILES" PRIMARY KEY (id_constants_load_file);


--
-- TOC entry 2837 (class 2606 OID 18786)
-- Name: UK_IDENTIFICATION; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_person
    ADD CONSTRAINT "UK_IDENTIFICATION" UNIQUE (identification);


--
-- TOC entry 2849 (class 2606 OID 18788)
-- Name: UK_ROLENAME; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_roles
    ADD CONSTRAINT "UK_ROLENAME" UNIQUE (name);


--
-- TOC entry 2855 (class 2606 OID 18790)
-- Name: UK_USERNAME; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_users
    ADD CONSTRAINT "UK_USERNAME" UNIQUE (login);


--
-- TOC entry 2825 (class 2606 OID 18792)
-- Name: pk_competencies; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_competencies
    ADD CONSTRAINT pk_competencies PRIMARY KEY (id_competencies);


--
-- TOC entry 2829 (class 2606 OID 18794)
-- Name: pk_employee; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_employee
    ADD CONSTRAINT pk_employee PRIMARY KEY (id_employee);


--
-- TOC entry 2831 (class 2606 OID 18796)
-- Name: pk_mails; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_mails
    ADD CONSTRAINT pk_mails PRIMARY KEY (id_mail);


--
-- TOC entry 2835 (class 2606 OID 18798)
-- Name: pk_partner; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_partner
    ADD CONSTRAINT pk_partner PRIMARY KEY (id_partner);


--
-- TOC entry 2839 (class 2606 OID 18800)
-- Name: pk_person; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_person
    ADD CONSTRAINT pk_person PRIMARY KEY (id_person);


--
-- TOC entry 2841 (class 2606 OID 18802)
-- Name: pk_person_documentation_attached; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_person_documentation_attached
    ADD CONSTRAINT pk_person_documentation_attached PRIMARY KEY (id_person_documentation_attached);


--
-- TOC entry 2843 (class 2606 OID 18804)
-- Name: pk_person_observations; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_person_observations
    ADD CONSTRAINT pk_person_observations PRIMARY KEY (id_person_observations);


--
-- TOC entry 2845 (class 2606 OID 18806)
-- Name: pk_person_specifications; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_person_specifications
    ADD CONSTRAINT pk_person_specifications PRIMARY KEY (id_person_specifications);


--
-- TOC entry 2847 (class 2606 OID 18808)
-- Name: pk_phones; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_phones
    ADD CONSTRAINT pk_phones PRIMARY KEY (id_phone);


--
-- TOC entry 2833 (class 2606 OID 18810)
-- Name: pk_sc_module_permission; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_module_permission
    ADD CONSTRAINT pk_sc_module_permission PRIMARY KEY (id_module_permission);


--
-- TOC entry 2851 (class 2606 OID 18812)
-- Name: pk_sc_roles; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_roles
    ADD CONSTRAINT pk_sc_roles PRIMARY KEY (id_role);


--
-- TOC entry 2853 (class 2606 OID 18814)
-- Name: pk_service_or_product; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_services_or_products
    ADD CONSTRAINT pk_service_or_product PRIMARY KEY (id_service_or_products);


--
-- TOC entry 2857 (class 2606 OID 18816)
-- Name: pk_users; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_users
    ADD CONSTRAINT pk_users PRIMARY KEY (id_user);


--
-- TOC entry 2859 (class 2606 OID 18818)
-- Name: pk_work_experience; Type: CONSTRAINT; Schema: dmes; Owner: sipPrueba; Tablespace: 
--

ALTER TABLE ONLY sc_work_experience
    ADD CONSTRAINT pk_work_experience PRIMARY KEY (id_work_experience);


--
-- TOC entry 2861 (class 2606 OID 18819)
-- Name: fk_employee_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_employee
    ADD CONSTRAINT fk_employee_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2862 (class 2606 OID 18824)
-- Name: fk_mails_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_mails
    ADD CONSTRAINT fk_mails_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2863 (class 2606 OID 18829)
-- Name: fk_module_permission; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_module_permission_by_role
    ADD CONSTRAINT fk_module_permission FOREIGN KEY (id_module_permission) REFERENCES sc_module_permission(id_module_permission);


--
-- TOC entry 2864 (class 2606 OID 18834)
-- Name: fk_module_permission_by_role_for_role; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_module_permission_by_role
    ADD CONSTRAINT fk_module_permission_by_role_for_role FOREIGN KEY (id_role) REFERENCES sc_roles(id_role);


--
-- TOC entry 2865 (class 2606 OID 18839)
-- Name: fk_partner_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_partner
    ADD CONSTRAINT fk_partner_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2871 (class 2606 OID 18844)
-- Name: fk_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_users
    ADD CONSTRAINT fk_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2866 (class 2606 OID 18849)
-- Name: fk_person_documentation_attached_for_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_person_documentation_attached
    ADD CONSTRAINT fk_person_documentation_attached_for_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2867 (class 2606 OID 18854)
-- Name: fk_person_observation_for_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_person_observations
    ADD CONSTRAINT fk_person_observation_for_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2868 (class 2606 OID 18859)
-- Name: fk_person_specifications_for_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_person_specifications
    ADD CONSTRAINT fk_person_specifications_for_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2869 (class 2606 OID 18864)
-- Name: fk_phones_person; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_phones
    ADD CONSTRAINT fk_phones_person FOREIGN KEY (id_person) REFERENCES sc_person(id_person);


--
-- TOC entry 2872 (class 2606 OID 18869)
-- Name: fk_role; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_users
    ADD CONSTRAINT fk_role FOREIGN KEY (id_role) REFERENCES sc_roles(id_role);


--
-- TOC entry 2870 (class 2606 OID 18874)
-- Name: fk_service_or_product_partner; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_services_or_products
    ADD CONSTRAINT fk_service_or_product_partner FOREIGN KEY (id_partner) REFERENCES sc_partner(id_partner);


--
-- TOC entry 2873 (class 2606 OID 18879)
-- Name: fk_work_experience_employee; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_work_experience
    ADD CONSTRAINT fk_work_experience_employee FOREIGN KEY (id_employee) REFERENCES sc_employee(id_employee);


--
-- TOC entry 2860 (class 2606 OID 18884)
-- Name: id_competencies_employee; Type: FK CONSTRAINT; Schema: dmes; Owner: sipPrueba
--

ALTER TABLE ONLY sc_competencies
    ADD CONSTRAINT id_competencies_employee FOREIGN KEY (id_employee) REFERENCES sc_employee(id_employee);


-- Completed on 2014-11-21 15:02:39 COT

--
-- PostgreSQL database dump complete
--
