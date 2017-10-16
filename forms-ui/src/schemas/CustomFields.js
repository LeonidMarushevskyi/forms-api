import React, {Component} from "react";

class DictionaryField extends Component {
  constructor(props) {
    super(props);
    const enumOptions = enumOptionsList(props.schema);
    this.state = {
      enumOptions: enumOptions
    }
  }

  onChange(id) {
    const resItem = this.state.enumOptions.reduce((selected, item) => {
      if (!selected && item && item.id == id) {
        return item;
      } else {
        return selected;
      }
    }, null);

    this.props.onChange(resItem ? resItem : {});
  }

  render() {
    const {enumOptions} = this.state;
    const {
      idSchema,
      required,
      schema,
      formData
    } = this.props;

    return (
        <div>
          <Label label={schema.title} required={required} id={idSchema.$id}/>
          <select
              className="form-control"
              value={formData.id || ""}
              onChange={event => this.onChange(event.target.value)}>
            <option key="-1"/>
            {enumOptions.map((item, i) => {
              return (
                  <option key={i} value={item ? item.id : null}>
                    {item ? item.value : null}
                  </option>
              );
            })}
          </select>
        </div>
    );
  }
}

function Label(props) {
  const {label, required, id} = props;
  if (!label) {
    // See #312: Ensure compatibility with old versions of React.
    return <div/>;
  }
  return (
      <label className="control-label" htmlFor={id}>
        {required ? label + "*" : label}
      </label>
  );
}

function enumOptionsList(schema) {
  if (schema.enum) {
    return schema.enum.map((obj, i) => {
      return obj;
    });
  }
}

export const customFields = {
  "dictionary": DictionaryField
};
