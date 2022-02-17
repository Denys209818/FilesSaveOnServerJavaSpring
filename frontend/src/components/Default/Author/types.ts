import internal from "stream";

export interface FileObj
{
    uid: string,
    originFileObj: File
}
export interface FileInfo 
{
    code: string,
    uid?:string
}

export interface FileData 
{
    file: FileObj,
    fileList: FileList,
}

export interface AuthorData 
{
    Name: string,
    Description: string,
}

export interface AuthorDataForm
{
    Name: string,
    Description: string
}

export interface AuthorModel 
{
    id: Number,
    name: string,
    description: string
}

export interface BookAddModel 
{
    Title: string,
    Description: string,
    AuthorId: Number
    ImageBase64: Array<string>
}