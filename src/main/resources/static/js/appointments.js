const server = "http://localhost:8081";

const getappointmentRow = (appointment) => {
  const tr = document.createElement("tr");
  let td;
  let button;

  td = document.createElement("td");
  td.appendChild(document.createTextNode(appointment.dateAndTime));
  tr.appendChild(td);

  td = document.createElement("td");
  td.appendChild(document.createTextNode(appointment.dentist));
  tr.appendChild(td);

  td = document.createElement("td");
  td.appendChild(document.createTextNode(appointment.patient));
  tr.appendChild(td);

  td = document.createElement("td");
  button = document.createElement("button");
  button.appendChild(document.createTextNode("Edit"));
  button.addEventListener("click", (event) => {
    findById(appointment.id);
  });

  td.appendChild(button);
  tr.appendChild(td);

  td = document.createElement("td");
  button = document.createElement("button");
  button.appendChild(document.createTextNode("Delete"));
  button.addEventListener("click", (event) => {
    delappointment(appointment.id);
  });

  td.appendChild(button);
  tr.appendChild(td);

  return tr;
};

const getappointments = () => {
  const endpoint = `${server}/appointments/`;

  const processResponse = (resp) => {
    return resp.json();
  };

  const listappointments = (appointmentList) => {
    const appointmentListTable = document.querySelector(".appointment_list");
    let tbodyappointmentList = appointmentListTable.querySelector("tbody");

    appointmentListTable.removeChild(tbodyappointmentList);
    tbodyappointmentList = document.createElement("tbody");
    appointmentListTable.appendChild(tbodyappointmentList);

    appointmentList.forEach((appointment) => {
      const appointmentRow = getappointmentRow(appointment);
      tbodyappointmentList.appendChild(appointmentRow);
    });
  };

  return fetch(endpoint).then(processResponse).then(listappointments);
};

const delappointment = (appointmentId) => {
  const endpoint = `${server}/appointments/${appointmentId}`;

  const processResponse = (resp) => {
    return resp.json();
  };

  return fetch(endpoint, {
    method: "DELETE",
  })
    .then(processResponse)
    .then((resp) => {
      getappointments();
    });
};

const saveappointment = (appointment) => {
  let endpoint = `${server}/appointments/`;

  return fetch(endpoint, {
    method: appointment.id ? "PUT" : "POST",
    body: JSON.stringify(appointment),
    headers: {
      "Content-Type": "application/json",
    },
  }).then((resp) => {
    getappointments();
  });
};

const findById = (id) => {
  const endpoint = `${server}/appointments/${id}`;

  const processResponse = (resp) => {
    return resp.json();
  };

  const editappointment = (appointment) => {
    document.querySelector(
      "#label-edit"
    ).textContent = `Editing appointment: ${appointment.patient}`;
    document
      .querySelector("#btnSave")
      .setAttribute("data-appointmentId", appointment.id);
    document.querySelector("#dateAndTime").value = appointment.dateAndTime;
    document.querySelector("#patient").value = appointment.patient;
    document.querySelector("#dentist").value = appointment.dentist;
  };

  return fetch(endpoint).then(processResponse).then(editappointment);
};

document.querySelector("#btnSave").addEventListener("click", (event) => {
  const id = event.target.getAttribute("data-appointmentId");

  const name = document.querySelector("#dateAndTime").value;
  const lastName = document.querySelector("#patient").value;
  const licenceNumber = document.querySelector("#dentist").value;

  const cleanForm = () => {
    document.querySelector("#label-edit").textContent = `New appointment`;
    event.target.removeAttribute("data-appointmentId");
    document.querySelector("#dateAndTime").value = "";
    document.querySelector("#patient").value = "";
    document.querySelector("#dentist").value = "";
  };

  saveappointment({
    dateAndTime,
    patient,
    dentist,
    id,
  }).then(cleanForm);
});

getappointments();
