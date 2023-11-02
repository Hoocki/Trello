import axios from "axios";
import _ from "lodash";

const httpClient = axios.create({});

const handleError = (error: any) => {
    const customMessage: string = _.get(error, 'response.data.message');
    const message: string = _.isEmpty(customMessage) ? error.message : customMessage;
    console.log("interceptors: " + message);
    alert(message);
}

httpClient.interceptors.response.use(config => config, handleError);

export default httpClient;