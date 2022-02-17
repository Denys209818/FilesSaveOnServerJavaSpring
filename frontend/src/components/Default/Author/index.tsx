import React, {useRef, useState} from 'react';
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
import { FileInfo, FileObj, FileData, AuthorData, AuthorDataForm } from './types';
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


const Author: React.FC = () => 
{
    const navigation = useNavigate();
    const [form] = Form.useForm();
  

    const onFinish = async (values: AuthorDataForm) => {
      let sendObject: AuthorData = 
      {
          Name: values.Name,
          Description: values.Description
      };

     await axiosService.addAuthor(sendObject);
     navigation("/");
    };

            

    return (<>
        
        <Title level={3} style={{
            margin: "10px",
            textAlign: 'center'
        }}>Додавання нового автора</Title>     

      
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
                    name="addAuthorForm"
                    onFinish={onFinish}
                    scrollToFirstError
                    size='large'
                >
                    <Form.Item
                        name="Name"
                        label="Ім'я автора"
                        tooltip="Поле для імені автора"
                        rules={[{ required: true, message: 'Будь ласка, введіть ім\'я автора', whitespace: true }]}
                    >
                        <Input />
                    </Form.Item>

                    <Form.Item
                        name="Description"
                        label="Про автора"
                        tooltip="Поле для опису автора"
                        rules={[{ required: true, message: 'Будь ласка, введіть дані про автора', whitespace: true }]}
                    >
                       <Input.TextArea />
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
                            Я підтверджую додавання нового автора
                        </Checkbox>
                    </Form.Item>
                    <Form.Item {...tailFormItemLayout}>
                        <Button type="primary" htmlType="submit">
                            Додати нового автора
                        </Button>
                    </Form.Item>
                </Form>
            </Col>
            <Col md={4} xs={0}></Col>

        </Row>
    </>);
}

export default Author;