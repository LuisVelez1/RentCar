/* =========================================================
   NAVEGACIÓN
   ========================================================= */

const TABS = [
    ["empresa", "Empresa"],
    ["clientes", "Clientes"],
    ["vehiculos", "Vehículos"],
    ["modalidades", "Modalidades"],
    ["servicios", "Servicios adicionales"],
    ["reservas", "Reservas"],
    ["consultas", "Consultas"]
];

const nav =
    document.getElementById("tabs");

TABS.forEach(([id, label], i) => {

    const boton =
        document.createElement("button");

    boton.textContent = label;
    boton.dataset.tab = id;

    if (i === 0) {
        boton.classList.add("active");
    }

    boton.onclick =
        () => showTab(id);

    nav.appendChild(boton);
});


function showTab(id) {

    document
        .querySelectorAll("nav button")
        .forEach(boton => {

            boton.classList.toggle(
                "active",
                boton.dataset.tab === id
            );
        });

    document
        .querySelectorAll("main section")
        .forEach(seccion => {

            seccion.classList.toggle(
                "active",
                seccion.id === "tab-" + id
            );
        });
}


showTab("empresa");


/* =========================================================
   UTILIDADES
   ========================================================= */

function flash(
    elementoId,
    texto,
    correcto
) {

    const elemento =
        document.getElementById(
            elementoId
        );

    elemento.textContent = texto;

    elemento.className =
        "msg "
        + (correcto
            ? "ok"
            : "error");

    setTimeout(() => {

        elemento.className =
            "msg";

    }, 3500);
}


function respuestaCorrecta(
    mensaje
) {

    return !String(mensaje)
        .startsWith("ERROR:");
}


function limpiarError(
    mensaje
) {

    return String(mensaje)
        .replace("ERROR:", "")
        .trim();
}


function dinero(valor) {

    return Number(valor)
        .toLocaleString(
            "es-CO"
        );
}


function parsearJson(
    contenido,
    valorPorDefecto = []
) {

    try {

        return JSON.parse(
            String(contenido)
        );

    } catch (error) {

        console.error(
            "Error leyendo información de Java:",
            error,
            contenido
        );

        return valorPorDefecto;
    }
}


/* =========================================================
   EMPRESA
   ========================================================= */

document
    .getElementById("formEmpresa")
    .addEventListener(
        "submit",
        event => {

    event.preventDefault();

    const mensaje =
        javaBridge.guardarEmpresa(
            empNombre.value,
            empNit.value,
            empDireccion.value,
            empTelefono.value,
            empCorreo.value,
            empWeb.value
        );

    if (!respuestaCorrecta(mensaje)) {

        flash(
            "msgEmpresa",
            limpiarError(mensaje),
            false
        );

        return;
    }

    document
        .getElementById(
            "empresaNombre"
        )
        .textContent =
            empNombre.value;

    document
        .getElementById(
            "empresaSub"
        )
        .textContent =
            empDireccion.value
            + " · "
            + empTelefono.value;

    flash(
        "msgEmpresa",
        mensaje,
        true
    );
});


/* =========================================================
   CLIENTES
   ========================================================= */

document
    .getElementById("formCliente")
    .addEventListener(
        "submit",
        event => {

    event.preventDefault();

    const mensaje =
        javaBridge.registrarCliente(
            clNombre.value,
            clDocumento.value,
            clTelefono.value,
            clCorreo.value,
            Number(clEdad.value),
            clFecha.value
        );

    if (!respuestaCorrecta(mensaje)) {

        flash(
            "msgCliente",
            limpiarError(mensaje),
            false
        );

        return;
    }

    event.target.reset();

    renderClientes();
    renderSelects();

    flash(
        "msgCliente",
        mensaje,
        true
    );
});


function renderClientes() {

    const clientes =
        parsearJson(
            javaBridge.obtenerClientes()
        );

    const tabla =
        document.getElementById(
            "tblClientes"
        );

    if (clientes.length === 0) {

        tabla.innerHTML =
            `<tr>
                <td colspan="7"
                    class="empty">
                    No hay clientes registrados.
                </td>
             </tr>`;

        return;
    }

    tabla.innerHTML =
        clientes.map(cliente => `
            <tr>
                <td>${cliente.nombre}</td>
                <td>${cliente.documento}</td>
                <td>${cliente.telefono}</td>
                <td>${cliente.correo}</td>
                <td>${cliente.edad}</td>
                <td>${cliente.fecha}</td>

                <td>
                    <button
                        class="btn danger"
                        onclick="eliminarCliente(
                            '${cliente.documento}'
                        )">
                        Eliminar
                    </button>
                </td>
            </tr>
        `).join("");
}


function eliminarCliente(
    documento
) {

    javaBridge.eliminarCliente(
        documento
    );

    renderClientes();
    renderSelects();
}


/* =========================================================
   VEHÍCULOS
   ========================================================= */

document
    .getElementById("formVehiculo")
    .addEventListener(
        "submit",
        event => {

    event.preventDefault();

    const mensaje =
        javaBridge.registrarVehiculo(
            vhPlaca.value,
            vhMarca.value,
            vhModelo.value,
            Number(vhAnio.value),
            vhTipo.value,
            Number(vhTarifa.value)
        );

    if (!respuestaCorrecta(mensaje)) {

        flash(
            "msgVehiculo",
            limpiarError(mensaje),
            false
        );

        return;
    }

    event.target.reset();

    renderVehiculos();
    renderSelects();

    flash(
        "msgVehiculo",
        mensaje,
        true
    );
});


function renderVehiculos() {

    const vehiculos =
        parsearJson(
            javaBridge.obtenerVehiculos()
        );

    const tabla =
        document.getElementById(
            "tblVehiculos"
        );

    if (vehiculos.length === 0) {

        tabla.innerHTML =
            `<tr>
                <td colspan="7"
                    class="empty">
                    No hay vehículos registrados.
                </td>
             </tr>`;

        return;
    }

    tabla.innerHTML =
        vehiculos.map(vehiculo => `
            <tr>
                <td>${vehiculo.placa}</td>
                <td>${vehiculo.marca}</td>
                <td>${vehiculo.modelo}</td>
                <td>${vehiculo.ano}</td>
                <td>${vehiculo.tipo}</td>

                <td>
                    $${dinero(
                        vehiculo.tarifa
                    )}
                </td>

                <td>
                    <button
                        class="btn danger"
                        onclick="eliminarVehiculo(
                            '${vehiculo.placa}'
                        )">
                        Eliminar
                    </button>
                </td>
            </tr>
        `).join("");
}


function eliminarVehiculo(
    placa
) {

    javaBridge.eliminarVehiculo(
        placa
    );

    renderVehiculos();
    renderSelects();
}


/* =========================================================
   MODALIDADES
   ========================================================= */

document
    .getElementById("moNombre")
    .addEventListener(
        "change",
        event => {

    document
        .getElementById(
            "moPremiumBox"
        )
        .style.display =
            event.target.value === "Premium"
                ? "block"
                : "none";
});


document
    .getElementById("formModalidad")
    .addEventListener(
        "submit",
        event => {

    event.preventDefault();

    const beneficios =
        [
            ...document.querySelectorAll(
                ".moBeneficio:checked"
            )
        ]
        .map(
            elemento =>
                elemento.value
        )
        .join(",");

    const premium =
        moNombre.value === "Premium";

    const mensaje =
        javaBridge.registrarModalidad(
            moCodigo.value,
            moNombre.value,
            moDescripcion.value,
            Number(moDuracion.value),
            Number(moValor.value),
            moEstado.value,
            beneficios,

            premium
                ? moCobertura.value
                : "",

            premium
                ? Number(
                    moConductores.value || 0
                  )
                : 0,

            premium
                ? moCaracteristicas.value
                : ""
        );

    if (!respuestaCorrecta(mensaje)) {

        flash(
            "msgModalidad",
            limpiarError(mensaje),
            false
        );

        return;
    }

    event.target.reset();

    document
        .getElementById(
            "moPremiumBox"
        )
        .style.display =
            "none";

    renderModalidades();
    renderSelects();

    flash(
        "msgModalidad",
        mensaje,
        true
    );
});


function renderModalidades() {

    const modalidades =
        parsearJson(
            javaBridge.obtenerModalidades()
        );

    const tabla =
        document.getElementById(
            "tblModalidades"
        );

    if (modalidades.length === 0) {

        tabla.innerHTML =
            `<tr>
                <td colspan="7"
                    class="empty">
                    No hay modalidades registradas.
                </td>
             </tr>`;

        return;
    }

    tabla.innerHTML =
        modalidades.map(modalidad => `
            <tr>
                <td>${modalidad.codigo}</td>
                <td>${modalidad.nombre}</td>

                <td>
                    ${modalidad.duracion} días
                </td>

                <td>
                    $${dinero(
                        modalidad.valor
                    )}
                </td>

                <td>${modalidad.estado}</td>

                <td>
                    ${modalidad.beneficios || "—"}
                </td>

                <td>
                    <button
                        class="btn danger"
                        onclick="eliminarModalidad(
                            '${modalidad.codigo}'
                        )">
                        Eliminar
                    </button>
                </td>
            </tr>
        `).join("");
}


function eliminarModalidad(
    codigo
) {

    javaBridge.eliminarModalidad(
        codigo
    );

    renderModalidades();
    renderSelects();
}


/* =========================================================
   SERVICIOS
   ========================================================= */

document
    .getElementById("formServicio")
    .addEventListener(
        "submit",
        event => {

    event.preventDefault();

    const mensaje =
        javaBridge.registrarServicio(
            svCodigo.value,
            svNombre.value,
            svDescripcion.value,
            Number(svPrecio.value),
            svDisponibilidad.value
        );

    if (!respuestaCorrecta(mensaje)) {

        flash(
            "msgServicio",
            limpiarError(mensaje),
            false
        );

        return;
    }

    event.target.reset();

    renderServicios();
    renderSelects();

    flash(
        "msgServicio",
        mensaje,
        true
    );
});


function renderServicios() {

    const servicios =
        parsearJson(
            javaBridge.obtenerServicios()
        );

    const tabla =
        document.getElementById(
            "tblServicios"
        );

    if (servicios.length === 0) {

        tabla.innerHTML =
            `<tr>
                <td colspan="5"
                    class="empty">
                    No hay servicios registrados.
                </td>
             </tr>`;

        return;
    }

    tabla.innerHTML =
        servicios.map(servicio => `
            <tr>
                <td>${servicio.codigo}</td>
                <td>${servicio.nombre}</td>

                <td>
                    $${dinero(
                        servicio.precio
                    )}
                </td>

                <td>
                    ${
                        servicio.disponible
                            ? "Disponible"
                            : "No disponible"
                    }
                </td>

                <td>
                    <button
                        class="btn danger"
                        onclick="eliminarServicio(
                            '${servicio.codigo}'
                        )">
                        Eliminar
                    </button>
                </td>
            </tr>
        `).join("");
}


function eliminarServicio(
    codigo
) {

    javaBridge.eliminarServicio(
        codigo
    );

    renderServicios();
    renderSelects();
}


/* =========================================================
   SELECTS PARA RESERVA
   ========================================================= */

function renderSelects() {

    const clientes =
        parsearJson(
            javaBridge.obtenerClientes()
        );

    const vehiculos =
        parsearJson(
            javaBridge.obtenerVehiculos()
        );

    const modalidades =
        parsearJson(
            javaBridge.obtenerModalidades()
        );

    const servicios =
        parsearJson(
            javaBridge.obtenerServicios()
        );


    rsCliente.innerHTML =
        clientes.length
            ? clientes.map(cliente => `
                <option
                    value="${cliente.documento}">
                    ${cliente.nombre}
                    (${cliente.documento})
                </option>
            `).join("")
            : `<option value="">
                   Sin clientes
               </option>`;


    rsVehiculo.innerHTML =
        vehiculos.length
            ? vehiculos.map(vehiculo => `
                <option
                    value="${vehiculo.placa}">
                    ${vehiculo.placa}
                    -
                    ${vehiculo.marca}
                    ${vehiculo.modelo}
                </option>
            `).join("")
            : `<option value="">
                   Sin vehículos
               </option>`;


    const disponibles =
        modalidades.filter(
            modalidad =>
                modalidad.estado
                === "Disponible"
        );

    rsModalidad.innerHTML =
        disponibles.length
            ? disponibles.map(modalidad => `
                <option
                    value="${modalidad.codigo}">
                    ${modalidad.nombre}
                    ($${dinero(
                        modalidad.valor
                    )}/día)
                </option>
            `).join("")
            : `<option value="">
                   Sin modalidades
               </option>`;


    const serviciosDisponibles =
        servicios.filter(
            servicio =>
                servicio.disponible
        );

    rsServiciosBox.innerHTML =
        serviciosDisponibles.length
            ? serviciosDisponibles
                .map(servicio => `
                    <label>
                        <input
                            type="checkbox"
                            class="rsServicio"
                            value="${servicio.codigo}">

                        ${servicio.nombre}
                        ($${dinero(
                            servicio.precio
                        )})
                    </label>
                `).join("")
            : `<span class="empty">
                   Sin servicios disponibles.
               </span>`;
}


/* =========================================================
   RESERVAS
   ========================================================= */

document
    .getElementById("formReserva")
    .addEventListener(
        "submit",
        event => {

    event.preventDefault();

    if (
        !rsCliente.value
        || !rsVehiculo.value
        || !rsModalidad.value
    ) {

        flash(
            "msgReserva",
            "Registre primero un cliente, un vehículo y una modalidad.",
            false
        );

        return;
    }

    const serviciosSeleccionados =
        [
            ...document.querySelectorAll(
                ".rsServicio:checked"
            )
        ]
        .map(
            elemento =>
                elemento.value
        )
        .join(",");


    const mensaje =
        javaBridge.registrarReserva(
            rsCliente.value,
            rsVehiculo.value,
            rsModalidad.value,
            rsFecha.value,
            Number(rsDuracion.value),
            Number(
                rsDescuento.value || 0
            ),
            serviciosSeleccionados
        );


    if (!respuestaCorrecta(mensaje)) {

        flash(
            "msgReserva",
            limpiarError(mensaje),
            false
        );

        return;
    }


    event.target.reset();

    renderReservas();
    renderSelects();

    flash(
        "msgReserva",
        mensaje,
        true
    );
});


function renderReservas() {

    const reservas =
        parsearJson(
            javaBridge.obtenerReservas()
        );

    const tabla =
        document.getElementById(
            "tblReservas"
        );

    if (reservas.length === 0) {

        tabla.innerHTML =
            `<tr>
                <td colspan="8"
                    class="empty">
                    No hay reservas registradas.
                </td>
             </tr>`;

        return;
    }

    tabla.innerHTML =
        reservas.map(reserva => `
            <tr>
                <td>${reserva.cliente}</td>
                <td>${reserva.vehiculo}</td>
                <td>${reserva.modalidad}</td>
                <td>${reserva.fecha}</td>
                <td>${reserva.dias}</td>
                <td>${reserva.servicios}</td>

                <td>
                    $${dinero(
                        reserva.total
                    )}
                </td>

                <td>
                    <button
                        class="btn danger"
                        onclick="eliminarReserva(
                            '${reserva.id}'
                        )">
                        Eliminar
                    </button>
                </td>
            </tr>
        `).join("");
}


function eliminarReserva(
    id
) {

    javaBridge.eliminarReserva(id);

    renderReservas();
}

document
    .getElementById("formBuscarTel")
    .addEventListener(
        "submit",
        event => {

    event.preventDefault();

    const telefono =
        btTelefono.value.trim();

    const respuesta =
        javaBridge
            .verificarTelefonoPerfecto(
                telefono
            );

    const resultado =
        document.getElementById(
            "resBuscarTel"
        );

    resultado.style.display =
        "block";

    resultado.textContent =
        respuesta;
});


/* =========================================================
   INGRESOS
   ========================================================= */

document
    .getElementById("formIngresos")
    .addEventListener(
        "submit",
        event => {

    event.preventDefault();

    const respuesta =
        parsearJson(
            javaBridge
                .obtenerResumenIngresos(
                    inDesde.value,
                    inHasta.value
                ),
            null
        );

    const resultado =
        document.getElementById(
            "resIngresos"
        );

    resultado.style.display =
        "block";


    if (!respuesta
        || !respuesta.ok) {

        resultado.textContent =
            respuesta?.mensaje
            || "No fue posible calcular los ingresos.";

        return;
    }


    resultado.innerHTML =
        `<strong>
            Reservas en el periodo:
         </strong>
         ${respuesta.cantidad}

         <br>

         <strong>
            Ingresos totales:
         </strong>
         $${dinero(
             respuesta.total
         )}`;
});
