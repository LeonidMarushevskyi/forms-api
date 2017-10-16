import React, {Component} from "react";
import _ from "lodash";
import Request from 'superagent';
import {stores} from "../stores/InstancesStore"
import {config} from "../config"

class Schemas extends Component {
  constructor(props) {
    super(props);
    this.state = {}
  }

  componentWillMount() {
    const url = config.schemas_url;
    Request.get(url).then((response) => {
      const items = response.body.items;
      this.setState({
        schemas: items,
        currentSchema: items ? items[0] : undefined
      });
    })
  }

  handleClickOnSchema(e) {
    //alert(e.target.id);
    const curentSchema = this.state.schemas[e.target.id];
    this.setState({currentSchema: curentSchema});
    this.props.changeSchema(curentSchema);
    stores.instanceStore.loadInstances(curentSchema.form_name)
    //this.props.changeSchema(schema);
  }

  render() {
    const currentSchemasDefs = this.state.schemas;
    const schemas = _.map(currentSchemasDefs, (schemaDef, i) => {
      return (
          <tr>
            <td>{schemaDef.form_schema_id}</td>
            <td>
              <a id={i} key={i} onClick={this.handleClickOnSchema.bind(
                  this)}>{schemaDef.form_name}</a>
            </td>
            <td>{schemaDef.schema_version}</td>
          </tr>
      )
    });
    //   this.props.changeSchema(currentSchemasDefs[0]);
    return (
        <div>
          <h3>Available schemas</h3>
          <table className="table">
            <thead>
            <tr>
              <th>Id</th>
              <th>Form Name</th>
              <th>Schema Version</th>
            </tr>
            </thead>
            <tbody>
            {schemas}
            </tbody>
          </table>
        </div>
    );
  }
}

export default Schemas;