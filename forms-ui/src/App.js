import React, {Component} from "react";
import {render} from "react-dom";
import Request from 'superagent';
import Form from "react-jsonschema-form";
import {config} from "./config";
import {customFields} from "./schemas/CustomFields"
import {stores} from "./stores/InstancesStore"

import Instances from "./components/Instances"
import Schemas from "./components/Schemas"

const log = (type) => console.log.bind(console, type);

const onSubmit = (instanceData) => {
  console.log("form is submitted");
  Request
  .post(config.instances_url)
  .send(instanceData)
  .set('Accept', 'application/json')
  .end((err, res) => {
    if (err || !res.ok) {
      alert('Oh no! error ' + err + ' ' + JSON.stringify(res.body));
    } else {
      stores.instanceStore.loadInstances(instanceData.name)
    }
  });
};

//const defaultSchema = formsSchemas.rfa1c.schema;
//const defaultUiSchema = formsSchemas.rfa1c.uiSchema;
const formData = {};

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      currentSchema: {},
      currentInstance: {},
      schema: {},
      uiSchema: {},
      formData: formData
    };
  }

  changeSchema(schema) {
    this.setState({
      currentSchema: schema,
      schema: schema.json_schema,
      uiSchema: schema.ui_schema,
      formData: {}
    });
  }

  selectInstance(instance) {
    console.log("Instance [" + instance.form_id + "] Selected ");
    this.setState({
      currentInstance: instance,
      formData: instance.content
    });
  }

  onFormSubmit({formData}) {
    const name = this.state.currentSchema.form_name;
    const version = this.state.currentSchema.schema_version;
    onSubmit(
        {
          "name": name,
          "schema_version": version,
          "content": formData
        }
    )
  }

  render() {
    return (
        <div className="container">
          <div>
            <h1>Forms UI</h1>
            <div className="row">
              <div className="col-sm-12">
                <div className="col-sm-4">
                  <div className="row">
                    <Schemas changeSchema={this.changeSchema.bind(this)}/>
                  </div>
                  <div className="row">
                    <Instances currentSchema={this.state.currentSchema}
                               selectInstance={this.selectInstance.bind(this)}/>
                  </div>
                </div>
                <div className="col-sm-8">
                  <Form
                      schema={this.state.schema}
                      uiSchema={this.state.uiSchema}
                      formData={this.state.formData}
                      onSubmit={this.onFormSubmit.bind(this)}
                      fields={customFields}
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
    );
  }
}

export default App;
