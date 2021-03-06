module.exports = {
  uiSchema: {
    "ui:title": "Resource Family Criminal Record Statement (RFA O1C)",
    "ui:order": [
      "application_county",
      "child_identified",
      "child_in_home",
      "identified_children"],
    "application_county": {
      "ui:field": "dictionary"
    },
    "child_identified": {
      "ui:widget": "radio",
      "classNames": "danger"
    },
    "child_in_home": {
      "ui:widget": "radio"
    },
    "identified_children": {
      "items": {
        "county_of_jurisdiction": {
          "ui:field": "dictionary"
        },
        "name_suffix": {
          "ui:field": "dictionary"
        },
        "gender": {
          "ui:field": "dictionary"
        },
        "relationship_to_applicants": {
          "items": {
            "relationship_to_applicant": {
              "ui:field": "dictionary"
            }
          }
        },
        "school_address": {
          "type": {
            "ui:field": "dictionary"
          },
          "state": {
            "ui:field": "dictionary"
          }
        },
        "school_grade": {
          "ui:field": "dictionary"
        }
      }
    },
  },
  schema: {
    "definitions": {
      "dictionary": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer"
          },
          "value": {
            "type": "string"
          }
        }
      }
    },
    "title": "RFA 1C form",
    "type": "object",
    "properties": {
      "application_county": {
        "title": "Application county",
        "type": "object",
        "properties": {
          "id": {
            "type": "integer"
          },
          "value": {
            "type": "string"
          }
        },
        "enum": [
          {
            "value": "Alameda",
            "id": 1
          },
          {
            "value": "Alpine",
            "id": 2
          },
          {
            "value": "Amador",
            "id": 3
          },
          {
            "value": "Butte",
            "id": 4
          },
          {
            "value": "Calaveras",
            "id": 5
          },
          {
            "value": "Colusa",
            "id": 6
          },
          {
            "value": "Contra Costa",
            "id": 7
          },
          {
            "value": "Del Norte",
            "id": 8
          },
          {
            "value": "El Dorado",
            "id": 9
          },
          {
            "value": "Fresno",
            "id": 10
          },
          {
            "value": "Glenn",
            "id": 11
          },
          {
            "value": "Humboldt",
            "id": 12
          },
          {
            "value": "Imperial",
            "id": 13
          },
          {
            "value": "Inyo",
            "id": 14
          },
          {
            "value": "Kern",
            "id": 15
          },
          {
            "value": "Kings",
            "id": 16
          },
          {
            "value": "Lake",
            "id": 17
          },
          {
            "value": "Lassen",
            "id": 18
          },
          {
            "value": "Los Angeles",
            "id": 19
          },
          {
            "value": "Madera",
            "id": 20
          },
          {
            "value": "Marin",
            "id": 21
          },
          {
            "value": "Mariposa",
            "id": 22
          },
          {
            "value": "Mendocino",
            "id": 23
          },
          {
            "value": "Merced",
            "id": 24
          },
          {
            "value": "Modoc",
            "id": 25
          },
          {
            "value": "Mono",
            "id": 26
          },
          {
            "value": "Monterey",
            "id": 27
          },
          {
            "value": "Napa",
            "id": 28
          },
          {
            "value": "Nevada",
            "id": 29
          },
          {
            "value": "Orange",
            "id": 30
          },
          {
            "value": "Placer",
            "id": 31
          },
          {
            "value": "Plumas",
            "id": 32
          },
          {
            "value": "Riverside",
            "id": 33
          },
          {
            "value": "Sacramento",
            "id": 34
          },
          {
            "value": "San Benito",
            "id": 35
          },
          {
            "value": "San Bernardino",
            "id": 36
          },
          {
            "value": "San Diego",
            "id": 37
          },
          {
            "value": "San Francisco",
            "id": 38
          },
          {
            "value": "San Joaquin",
            "id": 39
          },
          {
            "value": "San Luis Obispo",
            "id": 40
          },
          {
            "value": "San Mateo",
            "id": 41
          },
          {
            "value": "Santa Barbara",
            "id": 42
          },
          {
            "value": "Santa Clara",
            "id": 43
          },
          {
            "value": "Santa Cruz",
            "id": 44
          },
          {
            "value": "Shasta",
            "id": 45
          },
          {
            "value": "Sierra",
            "id": 46
          },
          {
            "value": "Siskiyou",
            "id": 47
          },
          {
            "value": "Solano",
            "id": 48
          },
          {
            "value": "Sonoma",
            "id": 49
          },
          {
            "value": "Stanislaus",
            "id": 50
          },
          {
            "value": "Sutter",
            "id": 51
          },
          {
            "value": "Tehama",
            "id": 52
          },
          {
            "value": "Trinity",
            "id": 53
          },
          {
            "value": "Tulare",
            "id": 54
          },
          {
            "value": "Tuolumne",
            "id": 55
          },
          {
            "value": "Ventura",
            "id": 56
          },
          {
            "value": "Yolo",
            "id": 57
          },
          {
            "value": "Yuba",
            "id": 58
          },
          {
            "value": "State of California",
            "id": 59
          }
        ]
      },
      "child_identified": {
        "title": "Child identified",
        "type": "boolean",
        "description": "Is child identified",
        "enum": [
          true,
          false
        ],
      },
      "child_in_home": {
        "title": "Child in home",
        "type": "boolean",
        "description": "Is child in home",
        "enum": [
          true,
          false
        ],
      },
      "identified_children": {
        "title": "Identified children",
        "type": "array",
        "items": {
          "title": "Child",
          "type": "object",
          "properties": {
            "county_of_jurisdiction": {
              "title": "County of jurisdiction",
              "type": "object",
              "properties": {
                "id": {
                  "type": "integer"
                },
                "value": {
                  "type": "string"
                }
              },
              "enum": [
                {
                  "value": "Alameda",
                  "id": 1
                },
                {
                  "value": "Alpine",
                  "id": 2
                },
                {
                  "value": "Amador",
                  "id": 3
                },
                {
                  "value": "Butte",
                  "id": 4
                },
                {
                  "value": "Calaveras",
                  "id": 5
                },
                {
                  "value": "Colusa",
                  "id": 6
                },
                {
                  "value": "Contra Costa",
                  "id": 7
                },
                {
                  "value": "Del Norte",
                  "id": 8
                },
                {
                  "value": "El Dorado",
                  "id": 9
                },
                {
                  "value": "Fresno",
                  "id": 10
                },
                {
                  "value": "Glenn",
                  "id": 11
                },
                {
                  "value": "Humboldt",
                  "id": 12
                },
                {
                  "value": "Imperial",
                  "id": 13
                },
                {
                  "value": "Inyo",
                  "id": 14
                },
                {
                  "value": "Kern",
                  "id": 15
                },
                {
                  "value": "Kings",
                  "id": 16
                },
                {
                  "value": "Lake",
                  "id": 17
                },
                {
                  "value": "Lassen",
                  "id": 18
                },
                {
                  "value": "Los Angeles",
                  "id": 19
                },
                {
                  "value": "Madera",
                  "id": 20
                },
                {
                  "value": "Marin",
                  "id": 21
                },
                {
                  "value": "Mariposa",
                  "id": 22
                },
                {
                  "value": "Mendocino",
                  "id": 23
                },
                {
                  "value": "Merced",
                  "id": 24
                },
                {
                  "value": "Modoc",
                  "id": 25
                },
                {
                  "value": "Mono",
                  "id": 26
                },
                {
                  "value": "Monterey",
                  "id": 27
                },
                {
                  "value": "Napa",
                  "id": 28
                },
                {
                  "value": "Nevada",
                  "id": 29
                },
                {
                  "value": "Orange",
                  "id": 30
                },
                {
                  "value": "Placer",
                  "id": 31
                },
                {
                  "value": "Plumas",
                  "id": 32
                },
                {
                  "value": "Riverside",
                  "id": 33
                },
                {
                  "value": "Sacramento",
                  "id": 34
                },
                {
                  "value": "San Benito",
                  "id": 35
                },
                {
                  "value": "San Bernardino",
                  "id": 36
                },
                {
                  "value": "San Diego",
                  "id": 37
                },
                {
                  "value": "San Francisco",
                  "id": 38
                },
                {
                  "value": "San Joaquin",
                  "id": 39
                },
                {
                  "value": "San Luis Obispo",
                  "id": 40
                },
                {
                  "value": "San Mateo",
                  "id": 41
                },
                {
                  "value": "Santa Barbara",
                  "id": 42
                },
                {
                  "value": "Santa Clara",
                  "id": 43
                },
                {
                  "value": "Santa Cruz",
                  "id": 44
                },
                {
                  "value": "Shasta",
                  "id": 45
                },
                {
                  "value": "Sierra",
                  "id": 46
                },
                {
                  "value": "Siskiyou",
                  "id": 47
                },
                {
                  "value": "Solano",
                  "id": 48
                },
                {
                  "value": "Sonoma",
                  "id": 49
                },
                {
                  "value": "Stanislaus",
                  "id": 50
                },
                {
                  "value": "Sutter",
                  "id": 51
                },
                {
                  "value": "Tehama",
                  "id": 52
                },
                {
                  "value": "Trinity",
                  "id": 53
                },
                {
                  "value": "Tulare",
                  "id": 54
                },
                {
                  "value": "Tuolumne",
                  "id": 55
                },
                {
                  "value": "Ventura",
                  "id": 56
                },
                {
                  "value": "Yolo",
                  "id": 57
                },
                {
                  "value": "Yuba",
                  "id": 58
                },
                {
                  "value": "State of California",
                  "id": 59
                }
              ]
            },
            "date_of_birth": {
              "title": "Date of birth",
              "description": "Child's date of birth",
              "type": "string",
              "format": "date",
              "examples": [
                "2007-07-14"
              ]
            },
            "date_of_placement": {
              "title": "Date of placement",
              "type": "string",
              "format": "date",
              "examples": [
                "2017-05-14"
              ]
            },
            "first_name": {
              "title": "First name",
              "type": "string",
            },
            "last_name": {
              "title": "Last name",
              "type": "string",
            },
            "middle_name": {
              "title": "Middle name",
              "type": "string",
            },
            "name_suffix": {
              "title": "Suffix",
              "$ref": "#/definitions/dictionary",
              "enum": [
                {
                  "value": "Esq",
                  "id": 1
                },
                {
                  "value": "II",
                  "id": 2
                },
                {
                  "value": "III",
                  "id": 3
                },
                {
                  "value": "IV",
                  "id": 4
                },
                {
                  "value": "JD",
                  "id": 9
                },
                {
                  "value": "Jr",
                  "id": 5
                },
                {
                  "value": "MD",
                  "id": 7
                },
                {
                  "value": "PhD",
                  "id": 8
                },
                {
                  "value": "Sr",
                  "id": 6
                }
              ]
            },
            "gender": {
              "$ref": "#/definitions/dictionary",
              "title": "Gender",
              "enum": [
                {
                  "value": "Male",
                  "id": 1
                },
                {
                  "value": "Female",
                  "id": 2
                }
              ]
            },
            "relationship_to_applicants": {
              "title": "Relationship to applicants",
              "type": "array",
              "items": {
                "type": "object",
                "properties": {
                  "applicant_id": {
                    "title": "Applicant id",
                    "type": "number"
                  },
                  "relationship_to_applicant": {
                    "$ref": "#/definitions/dictionary",
                    "title": "Relationship to applicant",
                    "enum": [
                      {
                        "value": "Child",
                        "id": 1
                      },
                      {
                        "value": "Sibling",
                        "id": 2
                      },
                      {
                        "value": "Cousin",
                        "id": 3
                      },
                      {
                        "value": "Niece",
                        "id": 4
                      },
                      {
                        "value": "Nephew",
                        "id": 5
                      }
                    ]
                  }
                }
              }
            },
            "school_name": {
              "title": "School name",
              "type": "string"
            },
            "school_address": {
              "title": "School address",
              "type": "object",
              "properties": {
                "type": {
                  "$ref": "#/definitions/dictionary",
                  "title": "Address Type",
                  "enum": [
                    {
                      "value": "Residential",
                      "id": 1
                    },
                    {
                      "value": "Home",
                      "id": 2
                    },
                    {
                      "value": "Mailing",
                      "id": 3
                    }
                  ]
                },
                "street_address": {
                  "title": "Street address",
                  "type": "string",
                  "examples": [
                    "1 Main St."
                  ]
                },
                "city": {
                  "title": "City",
                  "type": "string"
                },
                "state": {
                  "title": "state",
                  "type": "object",
                  "properties": {
                    "id": {
                      "type": "string"
                    },
                    "value": {
                      "type": "string"
                    }
                  },
                  "enum": [
                    {
                      "value": "Alaska",
                      "id": "AK"
                    },
                    {
                      "value": "Alabama",
                      "id": "AL"
                    },
                    {
                      "value": "American Samoa",
                      "id": "AM"
                    },
                    {
                      "value": "Arkansas",
                      "id": "AR"
                    },
                    {
                      "value": "Arizona",
                      "id": "AZ"
                    },
                    {
                      "value": "California",
                      "id": "CA"
                    },
                    {
                      "value": "Northern Marianas Islands",
                      "id": "CM"
                    },
                    {
                      "value": "Colorado",
                      "id": "CO"
                    },
                    {
                      "value": "Connecticut",
                      "id": "CT"
                    },
                    {
                      "value": "Canal Zone",
                      "id": "CZ"
                    },
                    {
                      "value": "District of Columbia",
                      "id": "DC"
                    },
                    {
                      "value": "Delaware",
                      "id": "DE"
                    },
                    {
                      "value": "Florida",
                      "id": "FL"
                    },
                    {
                      "value": "Georgia",
                      "id": "GA"
                    },
                    {
                      "value": "Guam",
                      "id": "GU"
                    },
                    {
                      "value": "Hawaii",
                      "id": "HI"
                    },
                    {
                      "value": "Iowa",
                      "id": "IA"
                    },
                    {
                      "value": "Idaho",
                      "id": "ID"
                    },
                    {
                      "value": "Illinois",
                      "id": "IL"
                    },
                    {
                      "value": "Indiana",
                      "id": "IN"
                    },
                    {
                      "value": "Kansas",
                      "id": "KS"
                    },
                    {
                      "value": "Kentucky",
                      "id": "KY"
                    },
                    {
                      "value": "Louisiana",
                      "id": "LA"
                    },
                    {
                      "value": "Massachusetts",
                      "id": "MA"
                    },
                    {
                      "value": "Maryland",
                      "id": "MD"
                    },
                    {
                      "value": "Maine",
                      "id": "ME"
                    },
                    {
                      "value": "Michigan",
                      "id": "MI"
                    },
                    {
                      "value": "Minnesota",
                      "id": "MN"
                    },
                    {
                      "value": "Missouri",
                      "id": "MO"
                    },
                    {
                      "value": "Mississippi",
                      "id": "MS"
                    },
                    {
                      "value": "Montana",
                      "id": "MT"
                    },
                    {
                      "value": "North Carolina",
                      "id": "NC"
                    },
                    {
                      "value": "North Dakota",
                      "id": "ND"
                    },
                    {
                      "value": "Nebraska",
                      "id": "NE"
                    },
                    {
                      "value": "New Hampshire",
                      "id": "NH"
                    },
                    {
                      "value": "New Jersey",
                      "id": "NJ"
                    },
                    {
                      "value": "New Mexico",
                      "id": "NM"
                    },
                    {
                      "value": "Nevada",
                      "id": "NV"
                    },
                    {
                      "value": "New York",
                      "id": "NY"
                    },
                    {
                      "value": "Ohio",
                      "id": "OH"
                    },
                    {
                      "value": "Oklahoma",
                      "id": "OK"
                    },
                    {
                      "value": "Oregon",
                      "id": "OR"
                    },
                    {
                      "value": "Pennsylvania",
                      "id": "PA"
                    },
                    {
                      "value": "Puerto Rico",
                      "id": "PR"
                    },
                    {
                      "value": "Rhode Island",
                      "id": "RI"
                    },
                    {
                      "value": "South Carolina",
                      "id": "SC"
                    },
                    {
                      "value": "South Dakota",
                      "id": "SD"
                    },
                    {
                      "value": "Tennessee",
                      "id": "TN"
                    },
                    {
                      "value": "Trust Territories",
                      "id": "TT"
                    },
                    {
                      "value": "Texas",
                      "id": "TX"
                    },
                    {
                      "value": "Utah",
                      "id": "UT"
                    },
                    {
                      "value": "Virginia",
                      "id": "VA"
                    },
                    {
                      "value": "Virgin Islands",
                      "id": "VI"
                    },
                    {
                      "value": "Vermont",
                      "id": "VT"
                    },
                    {
                      "value": "Washington",
                      "id": "WA"
                    },
                    {
                      "value": "Wisconsin",
                      "id": "WI"
                    },
                    {
                      "value": "West Virginia",
                      "id": "WV"
                    },
                    {
                      "value": "Wyoming",
                      "id": "WY"
                    }
                  ],
                },
                "zip": {
                  "title": "Zip",
                  "type": "string",
                  "pattern": "[0-9][0-9][0-9][0-9][0-9]"
                }
              }
            },
            "school_grade": {
              "$ref": "#/definitions/dictionary",
              "title": "school_grade",
              "enum": [
                {
                  "value": "TK",
                  "id": 1
                },
                {
                  "value": "K",
                  "id": 2
                },
                {
                  "value": "1",
                  "id": 3
                },
                {
                  "value": "2",
                  "id": 4
                },
                {
                  "value": "3",
                  "id": 5
                },
                {
                  "value": "4",
                  "id": 6
                },
                {
                  "value": "5",
                  "id": 7
                },
                {
                  "value": "6",
                  "id": 8
                },
                {
                  "value": "7",
                  "id": 9
                },
                {
                  "value": "8",
                  "id": 10
                },
                {
                  "value": "9",
                  "id": 11
                },
                {
                  "value": "10",
                  "id": 12
                },
                {
                  "value": "11",
                  "id": 13
                },
                {
                  "value": "12",
                  "id": 14
                },
                {
                  "value": "College +",
                  "id": 15
                }
              ]
            }
          }
        },
      }
    }
  }
};