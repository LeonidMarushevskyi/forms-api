/**
 * @author CWDS TPT-2 Team
 */
@TypeDefs(
    value = {
        @TypeDef(
            name = "FormSchemaType",
            typeClass = JsonType.class,
            parameters = {
                @Parameter(name = SQL_TYPE, value = SQLTypes.CLOB_TYPE_NAME),
                @Parameter(
                    name = "returnedClassName",
                    value = "java.lang.String"
                )
            }
        ),
        @TypeDef(
            name = "FormInstanceType",
            typeClass = JsonType.class,
            parameters = {
                @Parameter(name = SQL_TYPE, value = SQLTypes.CLOB_TYPE_NAME),
                @Parameter(
                    name = "returnedClassName",
                    value = "java.lang.String"

                )
            }
        )
    }
)
package gov.ca.cwds.forms.persistence.model;

import static gov.ca.cwds.forms.Constants.SQL_TYPE;

import gov.ca.cwds.forms.persistence.hibernate.JsonType;
import gov.ca.cwds.forms.persistence.hibernate.SQLTypes;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
