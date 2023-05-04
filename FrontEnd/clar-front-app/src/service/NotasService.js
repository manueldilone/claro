import axios from 'axios';

export class NotasService {
    baseUrl = "http://localhost:8080/api/claro/";

    getAll(){
        return axios.get(this.baseUrl + "notas").then(res => res.data);
    }

    save(persona) {
        return axios.post(this.baseUrl + "guardar", persona).then(res => res.data);
    }

    delete(id) {
        return axios.delete(this.baseUrl + "delete/"+id).then(res => res.data);
    }
}