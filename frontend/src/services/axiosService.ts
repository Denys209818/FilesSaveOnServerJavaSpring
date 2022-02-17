import { AuthorData, AuthorModel, BookAddModel } from "../components/Default/Author/types";
import axiosCreate from "./axiosCreate";


class AxiosService 
{
    addAuthor = async (data: AuthorData) => 
    {
        await axiosCreate.post('/api/author/add', data);
    }

    getAuthors =  async() => 
    {
       return (await axiosCreate.get<Array<AuthorModel>>('/api/author/get')).data;
    }

    addBook =  async (data: BookAddModel) => 
    {
        await axiosCreate.post('/api/book/add', data);
    }
}

export default new AxiosService();