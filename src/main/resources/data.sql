CREATE DATABASE lv_safety_verification;
use lv_safety_verification;

-----------> EMC <----------



CREATE TABLE country_table (
			COUNTRY_ID INTEGER NOT NULL, 
			NAME VARCHAR(255), 
			CODE VARCHAR(255), 
			CONSTRAINT PK_COUNTRY_ID PRIMARY KEY(COUNTRY_ID)
);

			INSERT INTO country_table VALUES (1, 'INDIA', 'IND');
			INSERT INTO country_table VALUES (2, 'UNITED STATES OF AMERICA', 'USA');
			INSERT INTO country_table VALUES (3, 'BANGLADESH', 'BGL');
			INSERT INTO country_table VALUES (4, 'SRILANKA', 'SRI');
			INSERT INTO country_table VALUES (5, 'UNITED KINGDOM', 'UK');
			INSERT INTO country_table VALUES (6, 'PAKISTAN', 'PAK');
			INSERT INTO country_table VALUES (7, 'AFGANISTAN', 'AFG');
			INSERT INTO country_table VALUES (8, 'MALDIVES', 'MDV');
			INSERT INTO country_table VALUES (9, 'UNITED ARAB EMIRATES', 'UAE');
			INSERT INTO country_table VALUES (10, 'CHINA', 'CHN');
			INSERT INTO country_table VALUES (11, 'SINGAPORE', 'SGP');
			INSERT INTO country_table VALUES (12, 'THAILAND', 'THL');
			INSERT INTO country_table VALUES (13, 'AUSTRALIA', 'AUS');
			INSERT INTO country_table VALUES (14, 'NEW ZEALAND', 'NZL');
			INSERT INTO country_table VALUES (15, 'JAPAN', 'JPN');
			INSERT INTO country_table VALUES (16, 'INDONESIA', 'INA');
			INSERT INTO country_table VALUES (17, 'MALAYSIA', 'MLY');
			INSERT INTO country_table VALUES (18, 'GERMANY', 'GNY');
			INSERT INTO country_table VALUES (19, 'FRANCE', 'FRN');
			INSERT INTO country_table VALUES (20, 'RUSSIA', 'RUS');
			INSERT INTO country_table VALUES (21, 'NEPAL', 'NPL');

--------> State Table with insert queries <------------

CREATE TABLE state_table (
			STATE_ID INT NOT NULL, 
			COUNTRY_ID INT, 
			NAME VARCHAR(255), 
			CODE VARCHAR(255),
			CONSTRAINT PK_STATE_ID PRIMARY KEY(STATE_ID),
			CONSTRAINT FK_COUNTRY_ID FOREIGN KEY (COUNTRY_ID) REFERENCES country_table(COUNTRY_ID) ON DELETE CASCADE
);

			INSERT INTO state_table VALUES (1,1,'Tamil Nadu','TN');
			INSERT INTO state_table VALUES (2,1,'Andhra Pradesh','AP');
			INSERT INTO state_table VALUES (3,1,'Telengana','TS');
			INSERT INTO state_table VALUES (4,1,'Karnataka','KA');
			INSERT INTO state_table VALUES (5,1,'Kerala','KL');
			INSERT INTO state_table VALUES (6,1,'Odisha','OR');
			INSERT INTO state_table VALUES (7,1,'Maharastra','MH');
			INSERT INTO state_table VALUES (8,1,'Madhya Pradesh','MP');
			INSERT INTO state_table VALUES (9,1,'Chattisgarh','CG');
			INSERT INTO state_table VALUES (10,1,'Jharkand','JD');
			INSERT INTO state_table VALUES (11,1,'Bihar','BH');
			INSERT INTO state_table VALUES (12,1,'Uttar Pradesh','UP');
			INSERT INTO state_table VALUES (13,1,'Gujarat','GU');
			INSERT INTO state_table VALUES (14,1,'Rajasthan','RJ');
			INSERT INTO state_table VALUES (15,1,'Punjab','PB');
			INSERT INTO state_table VALUES (16,1,'Haryana','HR');
			INSERT INTO state_table VALUES (17,1,'Uttarkhand','UT');
			INSERT INTO state_table VALUES (18,1,'Sikkim','SK');
			INSERT INTO state_table VALUES (19,1,'West Bengal','WB');
			INSERT INTO state_table VALUES (20,1,'Assam','AS');
			INSERT INTO state_table VALUES (21,1,'Arunachal Pradesh','AR');
			INSERT INTO state_table VALUES (22,1,'Nagaland','NG');
			INSERT INTO state_table VALUES (23,1,'Tripura','TP');
			INSERT INTO state_table VALUES (24,1,'Meghalaya','MG');
			INSERT INTO state_table VALUES (25,1,'Mizoram','MZ');
			INSERT INTO state_table VALUES (26,1,'Manipur','GU');
			INSERT INTO state_table VALUES (27,1,'Goa','GA');
			INSERT INTO STATE_TABLE VALUES (28,2,'Others','OT');
            INSERT INTO STATE_TABLE VALUES (29,21,'Others','OT');
            INSERT INTO STATE_TABLE VALUES (30,3,'Others','OT');
            INSERT INTO STATE_TABLE VALUES (31,4,'Others','OT');
            INSERT INTO STATE_TABLE VALUES (32,5,'Others','OT');
            INSERT INTO STATE_TABLE VALUES (33,6,'Others','OT');
            INSERT INTO STATE_TABLE VALUES (34,7,'Others','OT');
            INSERT INTO STATE_TABLE VALUES (35,8,'Others','OT');
            INSERT INTO STATE_TABLE VALUES (36,9,'Others','OT');
            INSERT INTO STATE_TABLE VALUES (37,10,'Others','OT');
            INSERT INTO STATE_TABLE VALUES (38,11,'Others','OT');
            INSERT INTO STATE_TABLE VALUES (39,12,'Others','OT');
            INSERT INTO STATE_TABLE VALUES (40,13,'Others','OT');
            INSERT INTO STATE_TABLE VALUES (41,14,'Others','OT');
            INSERT INTO STATE_TABLE VALUES (42,15,'Others','OT');
            INSERT INTO STATE_TABLE VALUES (43,16,'Others','OT');
            INSERT INTO STATE_TABLE VALUES (44,17,'Others','OT');
            INSERT INTO STATE_TABLE VALUES (45,18,'Others','OT');
            INSERT INTO STATE_TABLE VALUES (46,19,'Others','OT');
            INSERT INTO STATE_TABLE VALUES (47,20,'Others','OT');
			INSERT INTO state_table VALUES (48,1,'NewDelhi','DE');
			INSERT INTO state_table VALUES (49,1,'Puducherry','PO');
			INSERT INTO state_table VALUES (50,1,'Daman and Diu','DA');
			INSERT INTO state_table VALUES (51,1,'Dadra and Nagar Haveli','DN');
			INSERT INTO state_table VALUES (52,1,'Himachal Pradesh','HP');
			INSERT INTO state_table VALUES (53,1,'Jammu and Kashmir','JK');
			INSERT INTO state_table VALUES (54,1,'Ladakh','LH');
			INSERT INTO state_table VALUES (55,1,'Chandigarh','CH');
			INSERT INTO state_table VALUES (56,1,'Lakshadeep','LK');
			
		-----------> CLIENT DETAILS <----------
		
			CREATE TABLE CLIENT_DETAILS_TABLE(
				    EMC_ID INT AUTO_INCREMENT,
				    USER_NAME VARCHAR(255),
				    CLIENT_NAME VARCHAR(255),
				    CLIENT_ADDRESS VARCHAR(255),
				    CLIENT_LOCATION VARCHAR(255),
				    COUNTRY VARCHAR(255),
				    STATE VARCHAR(255),
				    ALL_STEPS_COMPLETED VARCHAR(255),
				    CONTACT_PERSON VARCHAR(255),
				    EMAIL VARCHAR(255),
				    CONTACT_NUMBER  VARCHAR(255),
				    LAND_MARK VARCHAR(255),
				    CREATED_BY VARCHAR(255),
				    CREATED_DATE datetime,
			        UPDATED_BY VARCHAR(255),
			        UPDATED_DATE datetime,
			        CONSTRAINT PK_EMC_ID  PRIMARY KEY(EMC_ID)
			        )
			
			

-----------> FACILITY DATA <----------

CREATE TABLE FACILITYDATA_TABLE(
				    FACILITYDATA_ID INT AUTO_INCREMENT,
				    USER_NAME VARCHAR(255),
				    EMC_ID INT,
			        CREATED_BY VARCHAR(255),
				    CREATED_DATE datetime,
			        UPDATED_BY VARCHAR(255),
			        UPDATED_DATE datetime,
					BL_TYPE VARCHAR(255),
					BL_OTHER_DESC VARCHAR(255),
					BC_TYPE VARCHAR(255),
					BC_NO_FLOORS VARCHAR(255),
					BC_ROOM_FLOOR_LOCATION VARCHAR(255),
					BC_PRIMARY_USE VARCHAR(255),
					BC_OTHER_USES VARCHAR(255),
					RL_INTERIOR_ROOM VARCHAR(255),
					RL_EXTERIOR_ROOM VARCHAR(255),
					RL_SOLID_EXTERIOR VARCHAR(255),
					RL_WINDOWED_EXTERIOR VARCHAR(255),
					RL_WINDOWS_FACE VARCHAR(255),
					RU_DEDICATED VARCHAR(255),
					RU_OTHER_DESC VARCHAR(255),
					RM_HEIGHT_TF VARCHAR(255),
					RM_WIDTH VARCHAR(255),
					RM_HEIGHT_FF VARCHAR(255),
					RM_LENGTH VARCHAR(255),
					RM_MAX_FLOOR VARCHAR(255),
					FT_RAISED_FLOOR VARCHAR(255),
					FT_AIR_SUPPLY VARCHAR(255),
					FT_HEIGHT VARCHAR(255),
					FT_AIRFLOW_OBSTRUCTION  VARCHAR(255),
					FT_DESCRIPTION VARCHAR(255),
					FT_AIRGRILL_DAMPERS  VARCHAR(255),
					FT_CABLE_HOLE  VARCHAR(255),
					FT_PEDESTALS  VARCHAR(255),
					FT_GRIDS  VARCHAR(255),
					FT_BOLTED VARCHAR(255),
					FT_WELDED  VARCHAR(255),
					FT_EARTHING_DESCRIPTION VARCHAR(255),
					FT_TRUEFLOOR_MATERIAL  VARCHAR(255),
					FT_DESCRIBE  VARCHAR(255),
					FT_CLEANLINESS  VARCHAR(255),
					FT_OTHER_DESCRIPTION  VARCHAR(255),
					
					CONSTRAINT PK_FACILITYDATA_ID  PRIMARY KEY(FACILITYDATA_ID)
					);
					
					
			CREATE TABLE FLOORCOVERING_TABLE(
				    FLOORCOVERING_ID INT AUTO_INCREMENT,
					FC_TYPE VARCHAR(255),
                    FACILITYDATA_ID INT,
					FC_MANUFACTOR VARCHAR(255),
		            FC_DESCRIPTION VARCHAR(255),
					FC_WOVEN   VARCHAR(255),
					FC_CHEMICAL VARCHAR(255),
					FC_NONE VARCHAR(255),
				    FC_OTHER_DESCRIPTION VARCHAR(255),
				    WALL_TYPE VARCHAR(255),
				    WALL_MATERIAL VARCHAR(255),
				    WALL_COVERING_TYPE VARCHAR(255),
					WALL_HUMUDITY VARCHAR(255),
					WALL_SEALING VARCHAR(255),
					WALL_DESCRIPTION VARCHAR(255),
					CC_FALSE_DESCRIPTION VARCHAR(255),
					CC_FALSE_HUMIDITY VARCHAR(255),
					CC_FALSE_HEIGHT VARCHAR(255),
					CC_UTILISATION VARCHAR(255),
					CC_TRUE_DESCRIPTION VARCHAR(255),
					CC_TRUE_HUMIDITY VARCHAR(255),
					CC_SURFACE_DESCRIPTION VARCHAR(255),
					WINDOWS_EXTERNAL VARCHAR(255),
					WINDOWS_DESCRIPTION VARCHAR(255),
					WINDOWS_COVERING VARCHAR(255),
					WINDOWS_OTHER_DESC VARCHAR(255),
					WINDOWS_INTERNAL_DESCRIPTION VARCHAR(255),
					DOORS_MATERIAL VARCHAR(255),
					DOORS_NUMBER VARCHAR(255),
					DOORS_WIDTH VARCHAR(255),
					DOORS_HEIGHT VARCHAR(255),
					DOORS_CLOSER_MECHANISH VARCHAR(255),
					DOORS_QULITY_SEALING VARCHAR(255),
					DOORS_DESCRIPTION VARCHAR(255),
					
					CONSTRAINT PK_FLOORCOVERING_ID PRIMARY KEY(FLOORCOVERING_ID),
					CONSTRAINT FK_FACILITYDATA_ID FOREIGN KEY (FACILITYDATA_ID) REFERENCES FACILITYDATA_TABLE(FACILITYDATA_ID) ON DELETE CASCADE    
					);
					
		
-----------> POWEREARTHING DATA <----------

CREATE TABLE POWEREARTHINGDATA_TABLE(
				    POWEREARTHINGDATA_ID INT AUTO_INCREMENT,
				    USER_NAME VARCHAR(255),
				    EMC_ID INT,
			        CREATED_BY VARCHAR(255),
				    CREATED_DATE datetime,
			        UPDATED_BY VARCHAR(255),
			        UPDATED_DATE datetime,
					POWER_ELECTRICAL_UTILITY VARCHAR(255),
					POWER_BACKUP_SOURCE VARCHAR(255),
					POWER_DISTANCE_HVLV VARCHAR(255),
					POWER_CABLE_HVLV VARCHAR(255),
					POWER_DISC_SUPPLY VARCHAR(255),
					POWER_TRANSFORMATION_KVA VARCHAR(255),
					POWER_INPUT_VOLTS VARCHAR(255),
					POWER_INPUT_PHASE VARCHAR(255),
					POWER_INPUT_WIRES VARCHAR(255),
					POWER_INPUT_FEED VARCHAR(255),
					POWER_INPUT_DESCRIBE VARCHAR(255),
					POWER_OUTPUT_VOLTS VARCHAR(255),
					POWER_OUTPUT_PHASE VARCHAR(255),
					POWER_OUTPUT_WIRES VARCHAR(255),
					POWER_OUTPUT_FEED VARCHAR(255),
					POWER_OUTPUT_INCOMING_AMPS VARCHAR(255),
					POWER_OUTPUT_NEUTRAL VARCHAR(255),
					PS_EARTHING VARCHAR(255),
					PE_ATTACHEMENT VARCHAR(255),
					DEDICATED_TRANSFERMATION  VARCHAR(255),
					DEDICATED_TRANSFERMATION_OTHERBUILDING VARCHAR(255),
					TYPEOF_INCOMING  VARCHAR(255),
					DESCRIPTION_OF_SERVICE  VARCHAR(255),
					DESCRIPTION_OFTESTING_SERVICE  VARCHAR(255),
					DESCRIPTION_OF_EQUIPOTENTIALBONDING  VARCHAR(255),
					
					CONSTRAINT PK_POWEREARTHINGDATA_ID  PRIMARY KEY(POWEREARTHINGDATA_ID)
					);
					

		CREATE TABLE ELECTRONICSYSTEM_TABLE(
				    ELECTRONICSYSTEM_ID INT AUTO_INCREMENT,
				    POWEREARTHINGDATA_ID INT,
				    BD_SLD VARCHAR(255),
				    BD_RECORD_DATA VARCHAR(255),
				    BD_EARTHING VARCHAR(255),
				    ELECTRONIC_DESC VARCHAR(255),
					PANEL_ID INT,
					NAMEPLATE_DATA VARCHAR(255),
		            MAIN_CIRCUTE_BRAKER VARCHAR(255),
					MAINCIRCUTE_BRAKER_RATING   VARCHAR(255),
					EMERGENCY_TRIP_REMOTE VARCHAR(255),
					EMERGENCY_TRIP_LOCAL VARCHAR(255),
				    OHTHER_TRIP VARCHAR(255),
				    DIFFERENTAL_PROTECTION VARCHAR(255),
				    BUODING_STELL VARCHAR(255),
				    PANEL_FEED VARCHAR(255),
					PHASE_WIRES VARCHAR(255),
					NEUTRAL_WIRE VARCHAR(255),
					PE_WIRE_SIZE VARCHAR(255),
					PANNEL_CONNECTORS VARCHAR(255),
					NEUTRAL_BUS VARCHAR(255),
					EARTH_BUS VARCHAR(255),
					LIST_OF_NONELECTRONICLOAD VARCHAR(255),
					DEDICATED_ELECTRONIC_SYSTEM VARCHAR(255),
					NON_COMPUTER_LOADS VARCHAR(255),
					
					CONSTRAINT PK_ELECTRONICSYSTEM_ID PRIMARY KEY(ELECTRONICSYSTEM_ID),
					CONSTRAINT FK_POWEREARTHINGDATA_ID FOREIGN KEY (POWEREARTHINGDATA_ID) REFERENCES POWEREARTHINGDATA_TABLE(POWEREARTHINGDATA_ID) ON DELETE CASCADE    
					);

		CREATE TABLE DISTRUBUTIONPANNEL_TABLE(
			    DISTRIBUTION_ID INT AUTO_INCREMENT,
				CB_WIRE_SIZE VARCHAR(255),
				POWEREARTHINGDATA_ID INT,
				CB_DESCRIPTION VARCHAR(255),
				MATCHES_RECEPTABLE VARCHAR(255),
				MATCHES_RECEPTABLE_DESC VARCHAR(255),
				INDIVDIAL_PEWIRE VARCHAR(255),
				INDIVDIAL_PEWIRE_DESC VARCHAR(255),
				INDIVDIAL_NEUTRALWIRE VARCHAR(255),
				INDIVDIAL_NEUTRALWIRE_DES VARCHAR(255),
				COMPUTER_LOAD_CIRCUTE VARCHAR(255),
				COMPUTERLOAD_CIRCUTE_DES VARCHAR(255),
				COMPUTERLOAD_RECEPTABLE VARCHAR(255),
				COMPUTERLOAD_RECEPTABLE_DESC VARCHAR(255),
				BRACH_CIRCUTE_RUN  VARCHAR(255),
				BRACHCIRCUTE_RUN_DES VARCHAR(255),
				FREQUENTLY_CYCLIDLOADS VARCHAR(255),
				FREQUENTLY_CYCLIDLOADS_DES VARCHAR(255),
				CONDUCTORS  VARCHAR(255),
				CONDUCTORS_DES VARCHAR(255),
				
				CONSTRAINT PK_DISTRIBUTION_ID   PRIMARY KEY(DISTRIBUTION_ID),
				CONSTRAINT FK_DISTRIBUTION_POWEREARTHINGDATA_ID FOREIGN KEY (POWEREARTHINGDATA_ID) REFERENCES POWEREARTHINGDATA_TABLE(POWEREARTHINGDATA_ID) ON DELETE CASCADE    
				);


-----------> ELECTROMAGNETIC COMPATIBILITY <----------
CREATE TABLE ELECTROMAGNETIC_COMPATABILITY_TABLE(
				ELECTROMAGNETIC_COMPATIBILITY_ID INT AUTO_INCREMENT,
				SE_SINGLE_POINT VARCHAR(10),
				SE_MESHED_ARRANGEMENT VARCHAR(10),
				SE_DESC VARCHAR(255),
				USER_NAME VARCHAR(255),
				EMC_ID INT,
			    CREATED_BY VARCHAR(255),
				CREATED_DATE datetime,
			    UPDATED_BY VARCHAR(255),
			    UPDATED_DATE datetime,
            	EQUIPOTENTIAL_BONDING VARCHAR(255),
				RESISTANT_CABINET VARCHAR(10),
				RESISTANT_CABINET_DESC VARCHAR(255),
				ROOM_SHIELD VARCHAR(10),
				ROOM_SHIELD_DESC VARCHAR(255),
				SHIELDING_OTHER_DESC VARCHAR(255),
				EQUIPMENT_HIGH_FREQUENCY  VARCHAR(10),
				EQUIPMENT_HIGH_FREQUENCY_DESC VARCHAR(255),
				APPROXIMATE_DISTANCE  VARCHAR(255),
				EQUIPMENT_MAINTENANCE VARCHAR(10),
				EQUIPMENT_MAINTENANCE_DESC VARCHAR(255),
				OPERATIONAL_FREQUENCY  VARCHAR(255),
				RADIATED_POWER  VARCHAR(255),
				
				CONSTRAINT PK_ELECTROMAGNETIC_COMPATIBILITY_ID PRIMARY KEY(ELECTROMAGNETIC_COMPATIBILITY_ID)				
);

CREATE TABLE  EXTERNAL_COMPATIBILITY_TABLE(
				EXTERNAL_COMPATIBILITY_ID INTEGER AUTO_INCREMENT,
				ELECTROMAGNETIC_COMPATIBILITY_ID INT,
				COMMUNICATION VARCHAR(255),
				VISIBILITY_OF_ANTENNAS VARCHAR(255),
				TYPES_OF_TRANSMISSION VARCHAR(255),
				TRANSMISSION_DESC VARCHAR(255),
				ANTENNA_DISTANCE  VARCHAR(255),
				NO_OF_WALLS  VARCHAR(255),
				LOS_DESC VARCHAR(255),
				ELECTRONIC_SYSTEM_DISTANCE   VARCHAR(255),
				TRANSMITTER_POWER  VARCHAR(255),
				FREQUENCY  VARCHAR(255),
				ORIENTATION_ANTENNA VARCHAR(255),
				SYSTEM_SITE VARCHAR(10),
				SYSTEM_SITE_DESC VARCHAR(255),
				CONTROLLED_LOADS VARCHAR(255),
				CONTROLLED_LOADS_DESC VARCHAR(255),
				ELECTRIC_RAILWAY VARCHAR(255),
				ELECTRIC_RAILWAY_DESC VARCHAR(255),
				HV_TRANSMISSION VARCHAR(255),
				HV_TRANSMISSION_DESC VARCHAR(255),
				HP_AC_MAGNETS VARCHAR(255),
				HP_AC_MAGNETS_DESC VARCHAR(255),
				APPROXIMATE_DISTANCE  VARCHAR(255),
				RFI_SURVEY VARCHAR(255),
				NEW_RFI_SURVEY VARCHAR(255),
				NEW_RFI_SURVEY_DESC VARCHAR(255),
				
				CONSTRAINT PK_EXTERNAL_COMPATIBILITY_ID PRIMARY KEY(EXTERNAL_COMPATIBILITY_ID),
				CONSTRAINT FK_ELECTROMAGNETIC_COMPATIBILITY_ID FOREIGN KEY (ELECTROMAGNETIC_COMPATIBILITY_ID) REFERENCES ELECTROMAGNETIC_COMPATABILITY_TABLE(ELECTROMAGNETIC_COMPATIBILITY_ID) ON DELETE CASCADE              
);