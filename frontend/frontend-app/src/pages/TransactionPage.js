import React, { useState, useEffect } from "react";
import { Layout, List, Typography, Space, Avatar } from 'antd';
import { DollarOutlined, AreaChartOutlined, PieChartOutlined, BarChartOutlined, DotChartOutlined, LineChartOutlined, RadarChartOutlined, HeatMapOutlined, FallOutlined, RiseOutlined, BoxPlotOutlined } from '@ant-design/icons';

import './Transaction.css';

const { Header } = Layout;
const { Title } = Typography;

const TransactionPage = () => {
    const [transactions, setTransactions] = useState([]);
    const [filteredTransactions, setFilteredTransactions] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const iconMap = {
        'housing': <AreaChartOutlined />,
        'travel': <PieChartOutlined />,
        'food': <BarChartOutlined />,
        'utilities': <DotChartOutlined />,
        'insurance': <LineChartOutlined />,
        'healthcare': <RadarChartOutlined />,
        'financial': <HeatMapOutlined />,
        'lifestyle': <FallOutlined />,
        'entertainment': <RiseOutlined />,
        'misc': <BoxPlotOutlined />,
    };


    function formatNumber(num) {
        return num.toLocaleString().replace(/,/g, ' ');
    }

    function filterList(categoryValue) {
        const filterValue = categoryValue.target.selectedOptions[0].value

        const data = []

        transactions.forEach(tr => {
            if (filterValue === 'all' || filterValue === tr.category) {
                data.push(tr)
            } 
        })

        setFilteredTransactions(data)
      }

    useEffect(() => {
        const fetchTransactions = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/transactions");
            if (!response.ok) throw new Error("Cannot load data.");
            const data = await response.json();

            setTransactions(data);
            setFilteredTransactions(data)
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
            <Header className={"header"}>
                <Space>
                    <Avatar size="large" icon={<DollarOutlined />} />
                    <Title style={{ color: 'white', margin: 0 }} level={3}>Sprintform</Title>
                </Space>
            </Header>

            <Layout>   
                <div className={"filter"}>
                    <label className={"filterLabel"} for="filter">Choose a category:</label>
                    <select id="filterCategory" onChange={element => filterList(element)}>
                        <option value="all" selected>All</option>
                        <option value="housing">housing</option>
                        <option value="travel">travel</option>
                        <option value="food">food</option>
                        <option value="utilities">housing</option>
                        <option value="insurance">insurance</option>
                        <option value="healthcare">healthcare</option>
                        <option value="financial">financial</option>
                        <option value="lifestyle">lifestyle</option>
                        <option value="entertainment">entertainment</option>
                        <option value="misc">misc</option>
                    </select>
                </div>             
                <List                    
                    dataSource={filteredTransactions}
                    renderItem={(tr) => (                       
                        <div className={"transaction"}>
                             <div className={"icon"}>
                                {iconMap[tr.category]}
                            </div>
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