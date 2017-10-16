import React, {Component} from "react";
import _ from "lodash";

import {stores} from "../stores/InstancesStore"

class Instances extends Component {
  constructor(props) {
    super(props);

    this.state = {
      ...props,
      instances: stores.instanceStore.getAll()
    }
  }

  componentWillMount() {
    stores.instanceStore.on("formInstancesReload", () => {
      this.setState({
        instances: stores.instanceStore.getAll()
      })
    });
  }

  handleClickOnForm(e) {
    const instance = this.state.instances[e.target.id];
    this.props.selectInstance(instance);
  }

  componentWillUpdate() {

  }

  render() {
    const curSchema = this.props.currentSchema;
    const formName = curSchema ? curSchema.form_name : "Schema";
    const currentInstances = this.state.instances;
    const instances = _.map(currentInstances, (instance, i) => {
      return (
          <tr>
            <td>{instance.form_id}</td>
            <td>
              <a id={i} key={i} onClick={this.handleClickOnForm.bind(
                  this)}>{instance.name}</a>
            </td>
            <td>{instance.schema_version}</td>
          </tr>
      )
    });
    //   this.props.changeSchema(currentSchemasDefs[0]);

    return (
        <div>
          <div>
            <h3>Instances of <b>{formName}</b></h3>
          </div>
          <table className="table">
            <thead>
            <tr>
              <th>Id</th>
              <th>Form Name</th>
              <th>Schema Version</th>
            </tr>
            </thead>
            <tbody>
            {instances}
            </tbody>
          </table>
        </div>
    );
  }
}

export default Instances;