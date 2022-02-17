import React, {useEffect, useRef, useState} from 'react';
import {
    Form,
    Input,
    Row,
    Col,
    Checkbox,
    Button,
    Typography,
    Upload,
    Modal,
  } from 'antd';
  import Cropper from "cropperjs";
  import { InboxOutlined } from '@ant-design/icons';
  import "cropperjs/dist/cropper.min.css";
import axiosService from '../../../services/axiosService';
import { useNavigate } from 'react-router-dom';
import { AuthorModel, BookAddModel, FileData, FileInfo, FileObj } from '../Author/types';

import { Select } from 'antd';
    const {Option} = Select;
  const { Title } = Typography;
  

  
  const formItemLayout = {
    labelCol: {
      xs: { span: 24 },
      sm: { span: 8 },
    },
    wrapperCol: {
      xs: { span: 24 },
      sm: { span: 24 },
    },
  };
  const tailFormItemLayout = {
    wrapperCol: {
      xs: {
        span: 24,
        offset: 0,
      },
      sm: {
        span: 16,
        offset: 8,
      },
    },
  };

  

const Book: React.FC = () => 
{
    
    const [authors, setAuthors] = useState<Array<AuthorModel>>([]);
    const navigation = useNavigate();
    const [cropperObj, setCropperObj] = useState<Cropper>();
    const imageRef = useRef<HTMLImageElement>(null);
    const imagePreview = useRef<HTMLImageElement>(null);
    const [filesCode, setfilesCode] = useState<Array<FileInfo>>(new Array<FileInfo>());
    const [visible, setVisible] = useState(false);
    const [form] = Form.useForm();
  

    useEffect(() => {
        axiosService.getAuthors().then(res => {
             setAuthors(res);
        });
    }, [setAuthors]);
    

    const onFinish = (values: BookAddModel) => {
        
      values.ImageBase64 = filesCode.map(el => {
          return el.code
      });
        axiosService.addBook(values);
        navigation("/");
    };


    const [files, setFiles] = useState<FileData>();
    const normFile = (e: FileData) => {
        setFiles(e);
        if (Array.isArray(e)) {
            
            return e;
        }

        return e && e.fileList;
    }
    
      const SuccessLoad = async ({file, onSuccess}:any) => 
      {
        await setVisible(true);
        var cropper = cropperObj ? cropperObj : new Cropper(imageRef.current as HTMLImageElement, {
            aspectRatio: 1/1,
            viewMode:1,
            preview: imagePreview.current as HTMLImageElement
        });
        cropper?.replace(URL.createObjectURL(file));
        setCropperObj(cropper);
        
       
        setTimeout(() => {
            onSuccess("ok");
          }, 0);   
      }
    
      const onOkHanler = () => 
      {
        let cropped: string = cropperObj?.getCroppedCanvas().toDataURL() as string;
        filesCode.push({code: cropped, uid: files?.file.uid});
      }

      const handleRemove = async(file: any) => 
      {
        let removedFile:FileObj = file;
        let files:Array<FileInfo> = filesCode.filter(el => el.uid != removedFile.uid);
        
        await setfilesCode(files);
      }

    return (<>
        
        <Title level={3} style={{
            margin: "10px",
            textAlign: 'center'
        }}>Додавання нової книги</Title>     

      
        <Row style={
            {
                margin: "30px"
            }
        }>
            <Col md={3} xs={0}></Col>
            <Col md={13} xs={24}>
                <Form
                    {...formItemLayout}
                    form={form}
                    name="addBookForm"
                    onFinish={onFinish}
                    scrollToFirstError
                    size='large'
                >
                    
                    <Form.Item
                        name="Title"
                        label="Назва книжки"
                        tooltip="Поле для назви книжки"
                        rules={[{ required: true, message: 'Будь ласка, введіть назву книжки', whitespace: true }]}
                    >
                        <Input />
                    </Form.Item>

                    <Form.Item
                        name="Description"
                        label="Опис книжки"
                        tooltip="Поле для опису книжки"
                        rules={[{ required: true, message: 'Будь ласка, введіть опис книжки', whitespace: true }]}
                    >
                        <Input.TextArea />
                    </Form.Item>

                    <Form.Item
                        name="AuthorId"
                        label="Автор"
                        tooltip="Поле для вибору автора книги"
                        rules={[{ required: true, pattern:/^[0-9]+$/, message: 'Будь ласка, оберіть автора книги',  whitespace: true }]}
                    >
                        <Select
                            placeholder="Оберіть автора книги"
                            allowClear
                        >
                            {authors.map((el, index) => 
                            {
                                return <Option key={index} value={el.id}>{el.name}</Option>
                            })}
                        </Select>
                    </Form.Item>

                    <Form.Item label="Фотографії"> 
                    <Form.Item name="Images" valuePropName="fileList" getValueFromEvent={normFile} noStyle>
                            <Upload.Dragger name="files" onRemove={handleRemove} customRequest={SuccessLoad}>
                                <p className="ant-upload-drag-icon">
                                    <InboxOutlined />
                                </p>
                                <p className="ant-upload-text">Натисніть або перенесіть файл</p>
                                <p className="ant-upload-hint">Підтримується перекидання лише однієї фотографії.</p>
                            </Upload.Dragger>
                        </Form.Item>
                    </Form.Item>

                    <Form.Item
                        name="agreement"
                        valuePropName="checked"
                        rules={[
                            {
                                validator: (_, value) =>
                                    value ? Promise.resolve() : Promise.reject(new Error('Натисніть на чекбокс!')),
                            },
                        ]}
                        {...tailFormItemLayout}
                    >
                        <Checkbox>
                            Я підтверджую додавання нової книжки
                        </Checkbox>
                    </Form.Item>
                    <Form.Item {...tailFormItemLayout}>
                        <Button type="primary" htmlType="submit">
                            Додати нову книгу
                        </Button>
                    </Form.Item>
                </Form>
            </Col>
            <Col md={4} xs={0}></Col>

        </Row>
        <Modal
            title="Редагування фотографії"
            centered
            visible={visible}
            onOk={() => {
                onOkHanler();
                setVisible(false)}}
            width={1000}
            closable={false}
            okText="Підтвердити"
            cancelButtonProps={{ disabled: true, style:{display:'none'} }}
        >
            <Row>
                <Col md={16} xs={24}>
                    <img ref={imageRef} width="100%"/>
                </Col>
                <Col offset={2} md={6} xs={24}>
                    <div style={{
                        height: "150px",
                        border: "1px solid silver",
                        overflow: "hidden"
                    }} ref={imagePreview}>
                        
                    </div>
                </Col>
            </Row>
        </Modal>
    </>);
}

export default Book;