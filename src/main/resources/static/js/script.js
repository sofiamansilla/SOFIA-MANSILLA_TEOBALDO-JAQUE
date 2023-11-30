const server = "http://localhost:8081";

const getdentistRow = (dentist) => {
  const tr = document.createElement("tr");
  let td;
  let button;

  td = document.createElement("td");
  td.appendChild(document.createTextNode(dentist.name));
  tr.appendChild(td);

  td = document.createElement("td");
  td.appendChild(document.createTextNode(dentist.lastName));
  tr.appendChild(td);

  td = document.createElement("td");
  td.appendChild(document.createTextNode(dentist.licenceNumber));
  tr.appendChild(td);

  td = document.createElement("td");
  button = document.createElement("button");
  button.appendChild(document.createTextNode("Edit"));
  button.addEventListener("click", (event) => {
    findById(dentist.id);
  });

  td.appendChild(button);
  tr.appendChild(td);

  td = document.createElement("td");
  button = document.createElement("button");
  button.appendChild(document.createTextNode("Delete"));
  button.addEventListener("click", (event) => {
    deldentist(dentist.id);
  });

  td.appendChild(button);
  tr.appendChild(td);

  return tr;
};

const getdentists = () => {
  const endpoint = `${server}/dentists/`;

  const processResponse = (resp) => {
    return resp.json();
  };

  const listdentists = (dentistList) => {
    const dentistListTable = document.querySelector(".dentist_list");
    let tbodydentistList = dentistListTable.querySelector("tbody");

    dentistListTable.removeChild(tbodydentistList);
    tbodydentistList = document.createElement("tbody");
    dentistListTable.appendChild(tbodydentistList);

    dentistList.forEach((dentist) => {
      const dentistRow = getdentistRow(dentist);
      tbodydentistList.appendChild(dentistRow);
    });
  };

  return fetch(endpoint).then(processResponse).then(listdentists);
};

const deldentist = (dentistId) => {
  const endpoint = `${server}/dentists/${dentistId}`;

  const processResponse = (resp) => {
    return resp.json();
  };

  return fetch(endpoint, {
    method: "DELETE",
  })
    .then(processResponse)
    .then((resp) => {
      getdentists();
    });
};

const savedentist = (dentist) => {
  let endpoint = `${server}/dentists/`;

  return fetch(endpoint, {
    method: dentist.id ? "PUT" : "POST",
    body: JSON.stringify(dentist),
    headers: {
      "Content-Type": "application/json",
    },
  }).then((resp) => {
    getdentists();
  });
};

const findById = (id) => {
  const endpoint = `${server}/dentists/${id}`;

  const processResponse = (resp) => {
    return resp.json();
  };

  const editDentist = (dentist) => {
    document.querySelector(
      "#label-edit"
    ).textContent = `Editing dentist: ${dentist.name}`;
    document
      .querySelector("#btnSave")
      .setAttribute("data-dentistId", dentist.id);
    document.querySelector("#name").value = dentist.name;
    document.querySelector("#lastName").value = dentist.lastName;
    document.querySelector("#licenceNumber").value = dentist.licenceNumber;
  };

  return fetch(endpoint).then(processResponse).then(editDentist);
};

document.querySelector("#btnSave").addEventListener("click", (event) => {
  const id = event.target.getAttribute("data-dentistId");

  const name = document.querySelector("#name").value;
  const lastName = document.querySelector("#lastName").value;
  const licenceNumber = document.querySelector("#licenceNumber").value;

  const cleanForm = () => {
    document.querySelector("#label-edit").textContent = `New dentist`;
    event.target.removeAttribute("data-dentistId");
    document.querySelector("#name").value = "";
    document.querySelector("#lastName").value = "";
    document.querySelector("#licenceNumber").value = "";
  };

  savedentist({
    name,
    lastName,
    licenceNumber,
    id,
  }).then(cleanForm);
});

getdentists();
