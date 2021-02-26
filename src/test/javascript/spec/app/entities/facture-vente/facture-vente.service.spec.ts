import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { FactureVenteService } from 'app/entities/facture-vente/facture-vente.service';
import { IFactureVente, FactureVente } from 'app/shared/model/facture-vente.model';

describe('Service Tests', () => {
  describe('FactureVente Service', () => {
    let injector: TestBed;
    let service: FactureVenteService;
    let httpMock: HttpTestingController;
    let elemDefault: IFactureVente;
    let expectedResult: IFactureVente | IFactureVente[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FactureVenteService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new FactureVente(0, currentDate, 0, 0, 0, 0, 0, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateFacture: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a FactureVente', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateFacture: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateFacture: currentDate,
          },
          returnedFromService
        );

        service.create(new FactureVente()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a FactureVente', () => {
        const returnedFromService = Object.assign(
          {
            dateFacture: currentDate.format(DATE_TIME_FORMAT),
            totalHT: 1,
            totalTVA: 1,
            totalTTC: 1,
            totalRemise: 1,
            totalNet: 1,
            timbreFiscale: true,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateFacture: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of FactureVente', () => {
        const returnedFromService = Object.assign(
          {
            dateFacture: currentDate.format(DATE_TIME_FORMAT),
            totalHT: 1,
            totalTVA: 1,
            totalTTC: 1,
            totalRemise: 1,
            totalNet: 1,
            timbreFiscale: true,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateFacture: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a FactureVente', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
