import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { FactureAchatService } from 'app/entities/facture-achat/facture-achat.service';
import { IFactureAchat, FactureAchat } from 'app/shared/model/facture-achat.model';

describe('Service Tests', () => {
  describe('FactureAchat Service', () => {
    let injector: TestBed;
    let service: FactureAchatService;
    let httpMock: HttpTestingController;
    let elemDefault: IFactureAchat;
    let expectedResult: IFactureAchat | IFactureAchat[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FactureAchatService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new FactureAchat(0, currentDate, 0, 0, 0, 0, 0, false);
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

      it('should create a FactureAchat', () => {
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

        service.create(new FactureAchat()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a FactureAchat', () => {
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

      it('should return a list of FactureAchat', () => {
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

      it('should delete a FactureAchat', () => {
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
