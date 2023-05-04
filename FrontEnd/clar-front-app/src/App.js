import React, { Component } from 'react';
import './App.css';

import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import {Panel} from 'primereact/panel';
import {Menubar} from 'primereact/menubar';
import {Dialog} from 'primereact/dialog';
import {InputText} from 'primereact/inputtext';
import {Button} from 'primereact/button';
import 'primereact/resources/themes/saga-blue/theme.css';

import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

import {NotasService} from "./service/NotasService";


export default class App extends Component{
    constructor(){
        super();
        this.state = {
            visible : false,
            persona: {
                id: null,
                nombre: null,
                campo1: null,
                campo2: null,

            },
            selectedPersona : {

            }
        };
        this.items = [
            {
                label : 'Nuevo',
                icon  : 'pi pi-fw pi-plus',
                command : () => {this.showSaveDialog()}
            },
            {
                label : 'Editar',
                icon  : 'pi pi-fw pi-pencil',
                command : () => {this.showEditDialog()}
            },
            {
                label : 'Eliminar',
                icon  : 'pi pi-fw pi-trash',
                command : () => {this.delete()}
            }
        ];
        this.personaService = new NotasService();
        this.save = this.save.bind(this);
        this.delete = this.delete.bind(this);
        this.footer = (
            <div>
                <Button label="Generar Info" icon="pi pi-check" onClick={this.save} />
            </div>
        );
    }

    componentDidMount(){
        this.personaService.getAll().then(data => this.setState({personas: data}))


    }

    save() {

        this.personaService.save(this.state.persona).then(data => {


            this.personaService.getAll().then(data => this.setState({personas: data}))
            alert('Se guardó el registro correctamente.');
            this.setState({
                visible : false,
                persona: {
                    id: null,
                    nombre: null,
                    campo1: null,
                    campo2: null,

                }
            });
        })
    }

    delete() {
        if(window.confirm("¿Realmente desea eliminar el registro?")) {
            this.personaService.delete(this.state.selectedPersona.id).then(data => {
          
                this.personaService.getAll().then(data => this.setState({personas: data}));
                alert('Se eliminó el registro correctamente.');
            });




        }

    }

    render(){
        return (
            <div style={{width:'80%', margin: '0 auto', marginTop: '20px'}}>
                <Menubar model={this.items}/>
                <br/>
                <Panel header="Listado de Definiciones de Clases y info correspondientes">
                    <DataTable value={this.state.personas} paginator={true} rows="10" selectionMode="single" selection={this.state.selectedPersona} onSelectionChange={e => this.setState({selectedPersona: e.value})}>
                        <Column field="nombre" header="Notas"></Column>

                    </DataTable>
                </Panel>
                <Dialog header="Crear Notas" visible={this.state.visible} style={{width: '900px'} } footer={this.footer} modal={true} onHide={() => this.setState({visible: false})}>
                    <form id="persona-form">
              <span className="p-float-label">
                <InputText  value={this.state.persona.nombre} style={{width : '100%'}} id="nombre" onChange={(e) => {
                    let val = e.target.value;
                    this.setState(prevState => {
                        let persona = Object.assign({}, prevState.persona);
                        persona.nombre = val;

                        return { persona };
                    })}
                } />
                <label htmlFor="nombre">NotaNombre</label>
              </span>
                        <br/>
                        <span className="p-float-label">
                <textarea   cols="60" rows="10" value={this.state.persona.campo1} style={{width : '100%'}} id="campo1" onChange={(e) => {
                    let val = e.target.value;
                    this.setState(prevState => {
                        let persona = Object.assign({}, prevState.persona);
                        persona.campo1 = val

                        return { persona };
                    })}
                } />
                <label htmlFor="campo1">Definición de Clase</label>
              </span>
                        <br/>
                        <span className="p-float-label" >
                <textarea   cols="60" rows="10" value={this.state.persona.campo2} style={{width : '100%'}}  id="campo2" onChange={(e) => {
                    let val = e.target.value;
                    this.setState(prevState => {
                        let persona = Object.assign({}, prevState.persona);
                        persona.campo2 = val

                        return { persona };
                    })}
                } />
                <label htmlFor="campo2">Info</label>
              </span>


                    </form>
                </Dialog>

            </div>
        );
    }


    showSaveDialog(){
        const current = new Date();
        const date = `${current.getFullYear()}-${current.getMonth()+1}-${current.getDate()}_${current.getHours()}:${current.getMinutes()}:${current.getSeconds()}`;



        this.setState({

            visible : true,
            persona : {

                id: null,
                nombre: date,
                campo1: null,
                campo2: null,

            }

        });



    //    document.getElementById('persona-form');
    }

    showEditDialog() {
        this.setState({
            visible : true,
            persona : {
                id: this.state.selectedPersona.id,
                nombre: this.state.selectedPersona.nombre,
                campo1: this.state.selectedPersona.campo1,
                campo2: this.state.selectedPersona.campo2,

            }
        })
    }
}
