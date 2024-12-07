import React, { useState, useEffect } from "react";
import { Layout, List, Typography, Space, Avatar } from 'antd';
import { DollarOutlined } from '@ant-design/icons';
import { GiftOutlined } from '@ant-design/icons';

import './Transaction.css';

const { Header } = Layout;
const { Title } = Typography;

const TransactionPage = () => {
    const [transactions, setTransactions] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    function formatNumber(num) {
        return num.toLocaleString().replace(/,/g, ' ');
    }

    // Adatok lekérése (Read)
    useEffect(() => {
        const fetchTransactions = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/transactions");
            if (!response.ok) throw new Error("Nem sikerült az adatok betöltése.");
            const data = await response.json();
            setTransactions(data);
            setLoading(false);
        } catch (err) {
            setError(err.message);
            setLoading(false);
        }
        };

        fetchTransactions();
    }, []);

    if (loading) return <div>Data loading...</div>;
    if (error) return <div>Error: {error}</div>;

    return (
        <Layout className={"transactions"}>
            <Header className={"title"}>
                <Space>
                    {/* Logo */}
                    <Avatar size="large" icon={<DollarOutlined />} />
                    <Title style={{ color: 'white', margin: 0 }} level={3}>Sprintform</Title>
                </Space>
            </Header>

       
            <Layout >                
                <List                    
                    dataSource={transactions}
                    renderItem={(tr) => (                        
                        <div className={"transaction"}>
                            <GiftOutlined className={"icon"} />
                            <div className={"paidcategory"}>
                                <div className={"category"}>{tr.category}</div>                            
                                <div className={"paid"}>{tr.paid}</div>
                            </div>                            
                            <div className={"sum"}>{formatNumber(tr.sum)} {tr.currency}</div>
                        </div>
                    )}
                />
            </Layout>

        </Layout>
    )
}

export default TransactionPage;