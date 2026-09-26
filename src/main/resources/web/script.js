/* ---------- Estado en memoria ---------- */
let empresa = { nombre:"RentCar", nit:"", direccion:"", telefono:"", correo:"", web:"" };
let clientes = [];
let vehiculos = [];
let modalidades = [];
let servicios = [];
let reservas = [];
let idCliente = 1, idReserva = 1;

/* ---------- Navegación por pestañas ---------- */
const TABS = [
  ["empresa","Empresa"], ["clientes","Clientes"], ["vehiculos","Vehículos"],
  ["modalidades","Modalidades"], ["servicios","Servicios adicionales"],
  ["reservas","Reservas"], ["consultas","Consultas"]
];

const nav = document.getElementById("tabs");

TABS.forEach(([id,label],i)=>{
  const b = document.createElement("button");
  b.textContent = label;
  b.dataset.tab = id;

  if(i===0) b.classList.add("active");

  b.onclick = () => showTab(id);
  nav.appendChild(b);
});

function showTab(id){
  document.querySelectorAll("nav button").forEach(b =>
    b.classList.toggle("active", b.dataset.tab===id)
  );

  document.querySelectorAll("main section").forEach(s =>
    s.classList.toggle("active", s.id==="tab-"+id)
  );
}

showTab("empresa");

function flash(elId, text, ok){
  const el = document.getElementById(elId);

  el.textContent = text;
  el.className = "msg " + (ok ? "ok" : "error");

  setTimeout(()=>{
    el.className = "msg";
  }, 3500);
}

/* ---------- Empresa ---------- */
document.getElementById("formEmpresa").addEventListener("submit", e=>{
  e.preventDefault();

  empresa = {
    nombre: empNombre.value,
    nit: empNit.value,
    direccion: empDireccion.value,
    telefono: empTelefono.value,
    correo: empCorreo.value,
    web: empWeb.value
  };

  document.getElementById("empresaNombre").textContent = empresa.nombre;

  document.getElementById("empresaSub").textContent =
    empresa.direccion + " · " + empresa.telefono;

  flash("msgEmpresa","Datos de la empresa guardados.", true);
});

/* ---------- Clientes ---------- */
document.getElementById("formCliente").addEventListener("submit", e=>{
  e.preventDefault();

  if(clientes.some(c=>c.documento===clDocumento.value)){
    flash("msgCliente","Ya existe un cliente con ese documento.", false);
    return;
  }

  clientes.push({
    id: idCliente++,
    nombre: clNombre.value,
    documento: clDocumento.value,
    telefono: clTelefono.value,
    correo: clCorreo.value,
    edad: clEdad.value,
    fecha: clFecha.value
  });

  e.target.reset();

  renderClientes();
  renderSelects();

  flash("msgCliente","Cliente registrado.", true);
});

function renderClientes(){
  const t = document.getElementById("tblClientes");

  if(clientes.length===0){
    t.innerHTML =
      '<tr><td colspan="7" class="empty">No hay clientes registrados.</td></tr>';
    return;
  }

  t.innerHTML = clientes.map(c=>`
    <tr>
      <td>${c.nombre}</td>
      <td>${c.documento}</td>
      <td>${c.telefono}</td>
      <td>${c.correo}</td>
      <td>${c.edad}</td>
      <td>${c.fecha}</td>
      <td>
        <button
          class="btn danger"
          onclick="eliminar('clientes',${c.id})">
          Eliminar
        </button>
      </td>
    </tr>
  `).join("");
}

/* ---------- Vehículos ---------- */
document.getElementById("formVehiculo").addEventListener("submit", e=>{
  e.preventDefault();

  if(vehiculos.some(v=>v.placa===vhPlaca.value)){
    flash("msgVehiculo","Ya existe un vehículo con esa placa.", false);
    return;
  }

  vehiculos.push({
    placa: vhPlaca.value,
    marca: vhMarca.value,
    modelo: vhModelo.value,
    anio: vhAnio.value,
    tipo: vhTipo.value,
    tarifa: Number(vhTarifa.value)
  });

  e.target.reset();

  renderVehiculos();
  renderSelects();

  flash("msgVehiculo","Vehículo registrado.", true);
});

function renderVehiculos(){
  const t = document.getElementById("tblVehiculos");

  if(vehiculos.length===0){
    t.innerHTML =
      '<tr><td colspan="7" class="empty">No hay vehículos registrados.</td></tr>';
    return;
  }

  t.innerHTML = vehiculos.map(v=>`
    <tr>
      <td>${v.placa}</td>
      <td>${v.marca}</td>
      <td>${v.modelo}</td>
      <td>${v.anio}</td>
      <td>${v.tipo}</td>
      <td>$${v.tarifa.toLocaleString()}</td>
      <td>
        <button
          class="btn danger"
          onclick="eliminar('vehiculos','${v.placa}')">
          Eliminar
        </button>
      </td>
    </tr>
  `).join("");
}

/* ---------- Modalidades ---------- */
document.getElementById("moNombre").addEventListener("change", e=>{
  document.getElementById("moPremiumBox").style.display =
    e.target.value==="Premium" ? "block" : "none";
});

document.getElementById("formModalidad").addEventListener("submit", e=>{
  e.preventDefault();

  if(modalidades.some(m=>m.codigo===moCodigo.value)){
    flash("msgModalidad","Ya existe una modalidad con ese código.", false);
    return;
  }

  const beneficios = [
    ...document.querySelectorAll(".moBeneficio:checked")
  ].map(c=>c.value);

  const esPremium = moNombre.value === "Premium";

  modalidades.push({
    codigo: moCodigo.value,
    nombre: moNombre.value,
    descripcion: moDescripcion.value,
    duracion: Number(moDuracion.value),
    valor: Number(moValor.value),
    estado: moEstado.value,
    beneficios,
    cobertura: esPremium ? moCobertura.value : "",
    conductores: esPremium ? moConductores.value : "",
    caracteristicas: esPremium ? moCaracteristicas.value : ""
  });

  e.target.reset();

  document.getElementById("moPremiumBox").style.display = "none";

  renderModalidades();
  renderSelects();

  flash("msgModalidad","Modalidad registrada.", true);
});

function renderModalidades(){
  const t = document.getElementById("tblModalidades");

  if(modalidades.length===0){
    t.innerHTML =
      '<tr><td colspan="7" class="empty">No hay modalidades registradas.</td></tr>';
    return;
  }

  t.innerHTML = modalidades.map(m=>`
    <tr>
      <td>${m.codigo}</td>
      <td>${m.nombre}</td>
      <td>${m.duracion} días</td>
      <td>$${m.valor.toLocaleString()}</td>
      <td>${m.estado}</td>
      <td>${m.beneficios.join(", ") || "—"}</td>
      <td>
        <button
          class="btn danger"
          onclick="eliminar('modalidades','${m.codigo}')">
          Eliminar
        </button>
      </td>
    </tr>
  `).join("");
}

/* ---------- Servicios adicionales ---------- */
document.getElementById("formServicio").addEventListener("submit", e=>{
  e.preventDefault();

  if(servicios.some(s=>s.codigo===svCodigo.value)){
    flash("msgServicio","Ya existe un servicio con ese código.", false);
    return;
  }

  servicios.push({
    codigo: svCodigo.value,
    nombre: svNombre.value,
    descripcion: svDescripcion.value,
    precio: Number(svPrecio.value),
    disponibilidad: svDisponibilidad.value
  });

  e.target.reset();

  renderServicios();
  renderSelects();

  flash("msgServicio","Servicio registrado.", true);
});

function renderServicios(){
  const t = document.getElementById("tblServicios");

  if(servicios.length===0){
    t.innerHTML =
      '<tr><td colspan="5" class="empty">No hay servicios registrados.</td></tr>';
    return;
  }

  t.innerHTML = servicios.map(s=>`
    <tr>
      <td>${s.codigo}</td>
      <td>${s.nombre}</td>
      <td>$${s.precio.toLocaleString()}</td>
      <td>${s.disponibilidad}</td>
      <td>
        <button
          class="btn danger"
          onclick="eliminar('servicios','${s.codigo}')">
          Eliminar
        </button>
      </td>
    </tr>
  `).join("");
}

/* ---------- Selects dependientes (reservas) ---------- */
function renderSelects(){

  rsCliente.innerHTML =
    clientes.map(c=>`
      <option value="${c.id}">
        ${c.nombre} (${c.documento})
      </option>
    `).join("") ||
    '<option value="">Sin clientes</option>';

  rsVehiculo.innerHTML =
    vehiculos.map(v=>`
      <option value="${v.placa}">
        ${v.placa} - ${v.marca} ${v.modelo}
      </option>
    `).join("") ||
    '<option value="">Sin vehículos</option>';

  rsModalidad.innerHTML =
    modalidades
      .filter(m=>m.estado==="Disponible")
      .map(m=>`
        <option value="${m.codigo}">
          ${m.nombre} ($${m.valor}/día)
        </option>
      `).join("") ||
    '<option value="">Sin modalidades</option>';

  rsServiciosBox.innerHTML =
    servicios
      .filter(s=>s.disponibilidad==="Disponible")
      .map(s=>`
        <label>
          <input
            type="checkbox"
            class="rsServicio"
            value="${s.codigo}">
          ${s.nombre} ($${s.precio})
        </label>
      `).join("") ||
    '<span class="empty">Sin servicios disponibles.</span>';
}

/* ---------- Reservas ---------- */
document.getElementById("formReserva").addEventListener("submit", e=>{
  e.preventDefault();

  if(!rsCliente.value || !rsVehiculo.value || !rsModalidad.value){
    flash(
      "msgReserva",
      "Registre primero un cliente, un vehículo y una modalidad.",
      false
    );
    return;
  }

  const cliente =
    clientes.find(c=>c.id==rsCliente.value);

  const modalidad =
    modalidades.find(m=>m.codigo===rsModalidad.value);

  const dias =
    Number(rsDuracion.value);

  const seleccionados = [
    ...document.querySelectorAll(".rsServicio:checked")
  ].map(c=>c.value);

  const serviciosReserva =
    servicios.filter(s=>seleccionados.includes(s.codigo));

  const subtotalServicios =
    serviciosReserva.reduce((a,s)=>a+s.precio,0);

  const descuento =
    Number(rsDescuento.value) || 0;

  const subtotal =
    modalidad.valor * dias + subtotalServicios;

  const total =
    subtotal - (subtotal * descuento / 100);

  reservas.push({
    id: idReserva++,
    clienteNombre: cliente.nombre,
    vehiculoPlaca: rsVehiculo.value,
    modalidadNombre: modalidad.nombre,
    fecha: rsFecha.value,
    dias,
    servicios:
      serviciosReserva.map(s=>s.nombre).join(", ") || "—",
    total
  });

  e.target.reset();

  renderReservas();

  flash(
    "msgReserva",
    `Reserva registrada. Total: $${total.toLocaleString()}`,
    true
  );
});

function renderReservas(){
  const t = document.getElementById("tblReservas");

  if(reservas.length===0){
    t.innerHTML =
      '<tr><td colspan="8" class="empty">No hay reservas registradas.</td></tr>';
    return;
  }

  t.innerHTML = reservas.map(r=>`
    <tr>
      <td>${r.clienteNombre}</td>
      <td>${r.vehiculoPlaca}</td>
      <td>${r.modalidadNombre}</td>
      <td>${r.fecha}</td>
      <td>${r.dias}</td>
      <td>${r.servicios}</td>
      <td>$${r.total.toLocaleString()}</td>
      <td>
        <button
          class="btn danger"
          onclick="eliminar('reservas',${r.id})">
          Eliminar
        </button>
      </td>
    </tr>
  `).join("");
}

/* ---------- Eliminar genérico ---------- */
function eliminar(coleccion, clave){

  const map = {
    clientes: ["clientes","id"],
    vehiculos: ["vehiculos","placa"],
    modalidades: ["modalidades","codigo"],
    servicios: ["servicios","codigo"],
    reservas: ["reservas","id"]
  };

  const [arrName, campo] = map[coleccion];

  window[arrName] =
    window[arrName].filter(
      o => String(o[campo]) !== String(clave)
    );

  ({
    clientes: renderClientes,
    vehiculos: renderVehiculos,
    modalidades: renderModalidades,
    servicios: renderServicios,
    reservas: renderReservas
  })[coleccion]();

  if(coleccion!=="reservas"){
    renderSelects();
  }
}

/* ---------- Consultas ---------- */
function esNumeroPerfecto(n){

  if(n<2) return false;

  let suma = 0;

  for(let i=1;i<n;i++){
    if(n%i===0){
      suma += i;
    }
  }

  return suma === n;
}

document.getElementById("formBuscarTel").addEventListener("submit", e=>{
  e.preventDefault();

  const tel =
    btTelefono.value.trim();

  const cliente =
    clientes.find(c=>c.telefono===tel);

  const numero =
    Number(tel);

  const perfecto =
    !isNaN(numero)
      ? esNumeroPerfecto(numero)
      : false;

  const box =
    document.getElementById("resBuscarTel");

  box.style.display = "block";

  box.innerHTML =
    (
      cliente
        ? `<strong>Cliente encontrado:</strong>
           ${cliente.nombre}
           (documento ${cliente.documento})`
        : `<strong>No se encontró ningún cliente</strong>
           con el teléfono ${tel}.`
    )
    +
    `<br>
     <strong>¿Es un número perfecto?</strong>
     ${perfecto ? "Sí" : "No"}.`;
});

document.getElementById("formIngresos").addEventListener("submit", e=>{
  e.preventDefault();

  const desde = inDesde.value;
  const hasta = inHasta.value;

  const enRango =
    reservas.filter(
      r => r.fecha >= desde && r.fecha <= hasta
    );

  const total =
    enRango.reduce(
      (a,r)=>a+r.total,
      0
    );

  const box =
    document.getElementById("resIngresos");

  box.style.display = "block";

  box.innerHTML =
    `<strong>Reservas en el periodo:</strong>
     ${enRango.length}
     <br>
     <strong>Ingresos totales:</strong>
     $${total.toLocaleString()}`;
});

/* ---------- Inicio ---------- */
renderClientes();
renderVehiculos();
renderModalidades();
renderServicios();
renderReservas();
renderSelects();