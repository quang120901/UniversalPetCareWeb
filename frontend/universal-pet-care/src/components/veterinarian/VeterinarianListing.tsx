import React, { useEffect, useState } from "react";
import { Col, Container, Row } from "react-bootstrap";
import VeterinarianCard from "./VeterinarianCard";
import { getVeterinarians } from "./VeterinarianService";

const VeterinarianListing = () => {
    const [veterinarians, setVeterinarians] = useState([]);
    const [errorMessage, setErrorMessage] = useState("");

    useEffect(() => {
        getVeterinarians()
            .then((data) => {
                setVeterinarians(data.data);
            })
            .catch((error) => {
                setErrorMessage(error.message);
            });
    }, []);

    if (veterinarians.length === 0) {
        return <p>No veterinarians found at this time</p>;
    }

    return (
    <Container>
      <Row className='justify-content-center'>
        <h2 className='text-center mb-4 mt-4'>Meet Our Veterinarians</h2>
      </Row>

      <Row className='justify-content-center'>
        <Col md={4}>
          <h5>Search goes here</h5>
        </Col>
        <Col md={7}>
          {veterinarians.map((vet, index) => (
            <VeterinarianCard key={index} vet={vet} />
          ))}
        </Col>
      </Row>
    </Container>
  );
};

export default VeterinarianListing;