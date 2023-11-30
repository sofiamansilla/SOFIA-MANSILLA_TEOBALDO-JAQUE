const server = "http://localhost:8081";

const getpatientRow = (patient) => {
  const tr = document.createElement("tr");
  let td;
  let button;

  td = document.createElement("td");
  td.appendChild(document.createTextNode(patient.name));
  tr.appendChild(td);

  td = document.createElement("td");
  td.appendChild(document.createTextNode(patient.lastName));
  tr.appendChild(td);

  td = document.createElement("td");
  td.appendChild(document.createTextNode(patient.dni));
  tr.appendChild(td);
  td = document.createElement("td");
  td.appendChild(document.createTextNode(patient.dateOfEntry));
  tr.appendChild(td);
  td = document.createElement("td");
  td.appendChild(document.createTextNode(patient.address));
  tr.appendChild(td);

  td = document.createElement("td");
  button = document.createElement("button");
  button.appendChild(document.createTextNode("Edit"));
  button.addEventListener("click", (event) => {
    findById(patient.id);
  });

  td.appendChild(button);
  tr.appendChild(td);

  td = document.createElement("td");
  button = document.createElement("button");
  button.appendChild(document.createTextNode("Delete"));
  button.addEventListener("click", (event) => {
    delpatient(patient.id);
  });

  td.appendChild(button);
  tr.appendChild(td);

  return tr;
};

const getpatients = () => {
  const endpoint = `${server}/patients/`;

  const processResponse = (resp) => {
    return resp.json();
  };

  const listpatients = (patientList) => {
    const patientListTable = document.querySelector(".patient_list");
    let tbodypatientList = patientListTable.querySelector("tbody");

    patientListTable.removeChild(tbodypatientList);
    tbodypatientList = document.createElement("tbody");
    patientListTable.appendChild(tbodypatientList);

    patientList.forEach((patient) => {
      const patientRow = getpatientRow(patient);
      tbodypatientList.appendChild(patientRow);
    });
  };

  return fetch(endpoint).then(processResponse).then(listpatients);
};

const delpatient = (patientId) => {
  const endpoint = `${server}/patients/${patientId}`;

  const processResponse = (resp) => {
    return resp.json();
  };

  return fetch(endpoint, {
    method: "DELETE",
  })
    .then(processResponse)
    .then((resp) => {
      getpatients();
    });
};

const savepatient = (patient) => {
  let endpoint = `${server}/patients/`;

  return fetch(endpoint, {
    method: patient.id ? "PUT" : "POST",
    body: JSON.stringify(patient),
    headers: {
      "Content-Type": "application/json",
    },
  }).then((resp) => {
    getpatients();
  });
};

const findById = (id) => {
  const endpoint = `${server}/patients/${id}`;

  const processResponse = (resp) => {
    return resp.json();
  };

  const editpatient = (patient) => {
    document.querySelector(
      "#label-edit"
    ).textContent = `Editing patient: ${patient.name}`;
    document
      .querySelector("#btnSave")
      .setAttribute("data-patientId", patient.id);
    document.querySelector("#name").value = patient.name;
    document.querySelector("#lastName").value = patient.lastName;
    document.querySelector("#dni").value = patient.dni;
    document.querySelector("#dateOfEntry").value = patient.dateOfEntry;
    document.querySelector("#address").value = patient.address;
  };

  return fetch(endpoint).then(processResponse).then(editpatient);
};

document.querySelector("#btnSave").addEventListener("click", (event) => {
  const id = event.target.getAttribute("data-patientId");

  const name = document.querySelector("#name").value;
  const lastName = document.querySelector("#lastName").value;
  const dni = (document.querySelector("#dni").value = patient.dni);
  const dateOfEntry = (document.querySelector("#dateOfEntry").value =
    patient.dateOfEntry);
  const address = (document.querySelector("#address").value = patient.address);

  const cleanForm = () => {
    document.querySelector("#label-edit").textContent = `New patient`;
    event.target.removeAttribute("data-patientId");
    document.querySelector("#name").value = "";
    document.querySelector("#lastName").value = "";
    document.querySelector("#dni").value = "";
    document.querySelector("#dateOfEntry").value = "";
    document.querySelector("#address").value = "";
  };

  savepatient({
    name,
    lastName,
    dni,
    dateOfEntry,
    address
  }).then(cleanForm);
});

getpatients();
