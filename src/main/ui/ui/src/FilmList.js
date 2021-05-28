import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavBar';
import { Link } from 'react-router-dom';

class FilmList extends Component {

    constructor(props) {
        super(props);
        this.state = {films: []};

    }

    componentDidMount() {
        fetch('/api/films/all')
            .then(response => response.json())
            .then(data => this.setState({films: data}));
    }

    render() {
        const {films, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const filmList = films.map(film => {
            return <tr key={film.id}>
                <td style={{whiteSpace: 'nowrap'}}>{film.id}</td>
                <td>{film.title}</td>
                <td>{film.description}</td>
                <td>{film.releaseYear}</td>
                <td>{film.rating}</td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/api/categories/create">Add Category</Button>
                    </div>
                    <p></p>
                    <h3>List of Films </h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th>ID:</th>
                            <th>Title:</th>
                            <th>Description:</th>
                            <th>Release Year:</th>
                            <th>Rating:</th>
                        </tr>
                        </thead>
                        <tbody>
                        {filmList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }

}
export default FilmList;